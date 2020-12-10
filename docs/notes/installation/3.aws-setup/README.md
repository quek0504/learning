### Create an IAM user

1. Sign in to [the IAM console](https://console.aws.amazon.com/iam/)
2. In the navigation pane on the left, choose **Users**. Then choose **Add user** button on the right.
3. Enter <any name> as the **User name** and tick the **Programmatic access** checkbox.
4. Choose **Create group** button, enter <any group name> as the **Group name**. In the list of policies, tick the checkbox for the **AmazonS3FullAccess** policy. Tick the **checkbox** to attach the Group <any group name> that is just created to the IAM user. Choose **Next: Tags.**
5. (Optional) Add any tags for the IAM user.
6. Choose **Next: Review**. Then choose **Create user**.
7. On the Success screen, choose **Download .csv**. Remember to keep the csv file as you will not  have the opportunity to get Access Key ID and Secret Access Key again.



### Configure spring boot application

1. Change application properties under `mall-third-party/src/main/resources/application.yml`.

   ![thirdpartyyaml](https://user-images.githubusercontent.com/22387966/101739543-a3434400-3b02-11eb-81d8-dd890d3cfdca.PNG)

2. Edit placeholder `accessKey, secretKey, bucketName, region`.

