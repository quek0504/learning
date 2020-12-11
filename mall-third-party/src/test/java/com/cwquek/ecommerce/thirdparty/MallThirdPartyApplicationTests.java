package com.cwquek.ecommerce.thirdparty;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class MallThirdPartyApplicationTests {


	@Value("${aws.s3.accessKey}")
	private String accessKey;

	@Value("${aws.s3.secretKey}")
	private String secretKey;

	@Value("${aws.s3.bucketName}")
	private String bucketName; // bucket name in s3

	@Value("${aws.s3.region}")
	private String region;

	@Test
	void contextLoads() {
	}

	@Test
	public void testUpload() {

		String filePath = "";  // put path to your file

		// final file name in s3
		String keyName = "test123.jpeg";

		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);

		System.out.format("Uploading to S3 bucket...");
		final AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.withRegion(region).build();
		try {
			s3Client.putObject(bucketName, keyName, new File(filePath));
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
			System.exit(1);
		}
	}

	@Test
	public void testUploadWithPreSignedUrl() {

		String filePath = ""; // put path to your file

		File uploadFile = new File(filePath);

		String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String dir = format + "/";
		String newUUID = UUID.randomUUID().toString();

		// Example objectKey - 2020-12-01/UUID
		// 2020-12-01 is logical directory, UUID is file name in s3 bucket
		String objectKey = dir + newUUID + ".jpg";

		URL url = null;

		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);

		try {
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
					.withRegion(region)
					.build();

			// Set the pre-signed URL to expire after 5 minutes.
			java.util.Date expiration = new java.util.Date();
			long expTimeMillis = expiration.getTime();
			expTimeMillis += 1000 * 5 * 60;
			expiration.setTime(expTimeMillis);

			// Generate the pre-signed URL.
			System.out.println("Generating pre-signed URL.");
			GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey)
					.withMethod(HttpMethod.PUT)
					.withExpiration(expiration);
			url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

			System.out.println("Presigned url : " + url.toString());

			// We will do upload using browser
			// Create the connection and use it to upload the new object using the pre-signed URL.
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			OutputStream outputStream = connection.getOutputStream();
			FileInputStream inputStream = new FileInputStream(uploadFile);
			byte[] buffer = new byte[4096];
			int readBytes = -1;
			while ((readBytes = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer,0, readBytes);
			}
			outputStream.close();
			inputStream.close();

			// Check the HTTP response code. To complete the upload and make the object available,
			// you must interact with the connection object in some way.
			connection.getResponseCode();
			System.out.println("HTTP response code: " + connection.getResponseCode());
			connection.disconnect();

			// Check to make sure that the object was uploaded successfully.
			S3Object object = s3Client.getObject(bucketName, objectKey);
			System.out.println("Object " + object.getKey() + " created in bucket " + object.getBucketName());


		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
