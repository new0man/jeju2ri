package kr.huple.jeju2ri.api.controller;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import kr.huple.jeju2ri.api.model.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@Validated
public class FileUploadController {

    @Value("${ncloud.object.storage.end-point}")
    private String endPoint;

    @Value("${ncloud.object.storage.region-name}")
    private String regionName;

    @Value("${ncloud.object.storage.access-key}")
    private String accessKey;

    @Value("${ncloud.object.storage.secret-key}")
    private String secretKey;

    @RequestMapping(path = "/api/file/upload", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @ResponseStatus(HttpStatus.OK)
    public Object fileUpload(FileDto fileDto) {

        // client test
        // curl -F name="abc" -F photo="@8.jpg"  http://localhost:8080/file/upload/

        // S3 client
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        String bucketName = "file-upload-bucket";

        try {
            // create bucket if the bucket name does not exist
            if (s3.doesBucketExistV2(bucketName)) {
                System.out.format("Bucket %s already exists.\n", bucketName);
            } else {
                s3.createBucket(bucketName);
                System.out.format("Bucket %s has been created.\n", bucketName);
            }
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch(SdkClientException e) {
            e.printStackTrace();
        }



        // create folder
        String folderName = "upload-folder/";

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(0L);
        objectMetadata.setContentType("application/x-directory");
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, new ByteArrayInputStream(new byte[0]), objectMetadata);

        try {
            s3.putObject(putObjectRequest);
            System.out.format("Folder %s has been created.\n", folderName);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch(SdkClientException e) {
            e.printStackTrace();
        }

        // upload local file
//        String objectKey = folderName + fileTestDto.getPhoto().getOriginalFilename();
        String originalFileName = fileDto.getPhoto().getOriginalFilename();
        String objectKey = folderName + UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));

        System.out.println("============================================================");
        System.out.println(fileDto.getPhoto().getName());
        System.out.println(fileDto.getPhoto().getContentType());
        System.out.println(fileDto.getPhoto().getOriginalFilename());
        System.out.println(fileDto.getPhoto().getSize());
        System.out.println("=============================================================");

        ObjectMetadata fileObjectMetadata = new ObjectMetadata();
        fileObjectMetadata.setContentLength(fileDto.getPhoto().getSize());
        fileObjectMetadata.setContentType(fileDto.getPhoto().getContentType());

        String linkUrl = "";
        try {
            PutObjectRequest filePutObjectRequest = new PutObjectRequest(bucketName,
                    objectKey, fileDto.getPhoto().getInputStream(), fileObjectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            PutObjectResult putObjectResult = s3.putObject(filePutObjectRequest);
            linkUrl = s3.getUrl(bucketName, objectKey).toExternalForm();
            System.out.println("=======++++++ linkUrl => " + linkUrl);
            System.out.format("Object %s has been created.\n", objectKey);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch(SdkClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linkUrl;
    }


}
