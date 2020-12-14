### Create an IAM user

1. Sign in to [the IAM console](https://console.aws.amazon.com/iam/)
2. In the navigation pane on the left, choose **Users**. Then choose **Add user** button on the right.
3. Enter <any name> as the **User name** and tick the **Programmatic access** checkbox.
4. Choose **Create group** button, enter <any group name> as the **Group name**. In the list of policies, tick the checkbox for the **AmazonS3FullAccess** policy. Tick the **checkbox** to attach the Group <any group name> that is just created to the IAM user. Choose **Next: Tags.**
5. (Optional) Add any tags for the IAM user.
6. Choose **Next: Review**. Then choose **Create user**.
7. On the Success screen, choose **Download .csv**. Remember to keep the csv file as you will not  have the opportunity to get Access Key ID and Secret Access Key again.

### Create a S3 bucket

1. Go to [Amazon S3 page](https://s3.console.aws.amazon.com/).
2. In the navigation pane on the left, choose **Buckets**. Then choose **Create bucket** button on the right.
3. Enter <any name> as the **Bucket name**. The bucket name must be unique.
4. Then choose the **Region** that is closest to you.
5. Unchecked **Block all public access** checkbox. Acknowledge the setting.
> Note: To configure bucket policy and enable Object Url read later.
6. (Optional) Set up other configurations per your need.
7. Choose **Create bucket** at the bottom of the page.

### Configure S3 bucket

1. Click on the S3 bucket that is just created. Go to **Permissions** tab.
2. Edit **Bucket policy** as follows. Replace placeholder **YOURBUCKETNAME** with the S3 bucket name.

    ```
    {
        "Version": "2012-10-17",
        "Statement": [
            {
                "Sid": "PublicRead",
                "Effect": "Allow",
                "Principal": "*",
                "Action": [
                    "s3:GetObject",
                    "s3:GetObjectVersion"
                ],
                "Resource": "arn:aws:s3:::YOURBUCKETNAME/*"
            }
        ]
    }
    ```
3. Edit **cross-origin resource sharing (CORS)** as follows. Only GET, PUT, POST requests are allowed.

    ```
    [
        {
            "AllowedHeaders": [
                "*"
            ],
            "AllowedMethods": [
                "PUT",
                "POST"
            ],
            "AllowedOrigins": [
                "*"
            ],
            "ExposeHeaders": []
        },
        {
            "AllowedHeaders": [],
            "AllowedMethods": [
                "GET"
            ],
            "AllowedOrigins": [
                "*"
            ],
            "ExposeHeaders": []
        }
    ]
    ```

### Configure spring boot application

1. Change application properties under `mall-third-party/src/main/resources/application.yml`.

   ![thirdpartyyaml](https://user-images.githubusercontent.com/22387966/101739543-a3434400-3b02-11eb-81d8-dd890d3cfdca.PNG)

2. Edit placeholder `accessKey, secretKey, bucketName, region`.

### More Reading