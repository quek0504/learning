package com.cwquek.ecommerce.thirdparty;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

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

		// final file name in s3
		String keyName = "cat1.jpeg";

		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);

		System.out.format("Uploading to S3 bucket...");
		final AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.withRegion(region).build();
		try {
			s3Client.putObject(bucketName, keyName, new File("")); // put path to your file
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
			System.exit(1);
		}
	}
}
