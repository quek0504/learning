package com.cwquek.ecommerce.thirdparty.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.cwquek.ecommerce.common.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;


@RestController
public class OssController {

    @Value("${aws.s3.accessKey}")
    private String accessKey;

    @Value("${aws.s3.secretKey}")
    private String secretKey;

    @Value("${aws.s3.bucketName}")
    private String bucketName; // bucket name in s3

    @Value("${aws.s3.region}")
    private String region;

    @RequestMapping("/oss/policy")
    public R policy(@RequestParam String fileName) {

        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dir = format + "/";
        String newUUID = UUID.randomUUID().toString(); // to make sure file names are unique

        // Example objectKey - 2020-12-01/UUID_fileName
        // 2020-12-01 is a logical directory, UUID_fileName is the file name in s3 bucket
        String objectKey = dir + newUUID + "_" + StringUtils.deleteWhitespace(fileName);

        // Object endpoint url if upload is successful
        String endPointUrl = "https://" + bucketName + ".s3-" + region + ".amazonaws.com/" + objectKey;

        Map<String,String> respMap = null;

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
            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

            respMap = new LinkedHashMap<String, String>();
            respMap.put("url", url.toString());
            respMap.put("endPointUrl", endPointUrl);

        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
            return R.error();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
            return R.error();
        }

        return R.ok().put("data", respMap);
    }



}
