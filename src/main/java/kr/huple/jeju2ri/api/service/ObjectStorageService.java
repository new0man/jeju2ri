package kr.huple.jeju2ri.api.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import kr.huple.jeju2ri.api.model.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class ObjectStorageService {

    @Value("${ncloud.object.storage.end-point}")
    private String endPoint;

    @Value("${ncloud.object.storage.region-name}")
    private String regionName;

    @Value("${ncloud.object.storage.access-key}")
    private String accessKey;

    @Value("${ncloud.object.storage.secret-key}")
    private String secretKey;

    private AmazonS3 s3;

    @PostConstruct
    private void initializeObjectStorage() {
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(endPoint, regionName);
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(credentials);
        this.s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(awsStaticCredentialsProvider)
                .build();
    }

    public Boolean createBucket(String bucketName) {
        try {
            // create bucket if the bucket name does not exist
            if (this.s3.doesBucketExistV2(bucketName)) {
                System.out.format("Bucket %s already exists.\n", bucketName);
            } else {
                this.s3.createBucket(bucketName);
                System.out.format("Bucket %s has been created.\n", bucketName);
            }
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
            return false;
        } catch(SdkClientException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean createFolder(String bucketName, String folderName) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(0L);
        objectMetadata.setContentType("application/x-directory");
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, new ByteArrayInputStream(new byte[0]), objectMetadata);

        try {
            s3.putObject(putObjectRequest);
            System.out.format("Folder %s has been created.\n", folderName);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
            return false;
        } catch(SdkClientException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private PutObjectResult uploadFileToBucket(String bucketName, String objectKey, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectKey, file);
        PutObjectResult putObjectResult = this.s3.putObject(
                putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead)
        );
        return putObjectResult;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String uploadFile(String bucketName, MultipartFile multipartFile, String objectKey) {
        try {
            File file = convertMultiPartToFile(multipartFile);
            PutObjectResult putObjectResult = uploadFileToBucket(bucketName, objectKey, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getPublicUrl(bucketName, objectKey);
    }

    private PutObjectResult uploadFileToBucket(String bucketName, String objectKey, InputStream fileStream, ObjectMetadata objectMetadata) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectKey, fileStream, objectMetadata);
        PutObjectResult putObjectResult = this.s3.putObject(
                putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead)
        );
        return putObjectResult;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String uploadFile(String bucketName, InputStream fileStream, String objectKey, ObjectMetadata objectMetadata) {
        try {
            PutObjectResult putObjectResult = uploadFileToBucket(bucketName, objectKey, fileStream, objectMetadata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getPublicUrl(bucketName, objectKey);
    }

    public void deleteFileFromObjectStorage(String bucketName, String objectKey) {
        this.s3.deleteObject(new DeleteObjectRequest(bucketName, objectKey));
    }

    public String getPublicUrl(String bucketName, String objectKey) {
        return this.s3.getUrl(bucketName, objectKey).toExternalForm();
    }

    public String getObjectKey(String folderName, String originalFileName) {
        return folderName + UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    public String getObjectKeyByToday(String folderName, String originalFileName) {
        String todayPath = LocalDate.now().format(DateTimeFormatter.ofPattern("/yyyy/MM/dd/"));
        return folderName + todayPath + originalFileName;
    }

    public ObjectMetadata getObjectMetadata(long size, String contentType) {
        ObjectMetadata fileObjectMetadata = new ObjectMetadata();
        fileObjectMetadata.setContentLength(size);
        fileObjectMetadata.setContentType(contentType);
        return fileObjectMetadata;
    }

    public boolean doesObjectExist(String bucketName, String objectKey) {
        Path path = Paths.get(objectKey);
        return s3.doesObjectExist(bucketName, path.getParent().toString());
    }

    /*
        usage case
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
        System.out.println(" - " + objectSummary.getKey() + "  " +
                "(size = " + objectSummary.getSize() + ")");
        }
     */
    public void getObjectList(String bucketName, String prefix) {
        ObjectListing objectListing = s3.listObjects(new ListObjectsRequest()
                .withBucketName(bucketName)
                .withPrefix(prefix));

    }

    public S3Object getObject(String bucketName, String key) {
        S3Object object = s3.getObject(new GetObjectRequest(bucketName, key));
        return object;
    }

    /*
    for (Bucket bucket : s3.listBuckets()) {
        String bucketnName=bucket.getName();
        System.out.println(" - " + bucketnName);
        ObjectListing objects = s3.listObjects(bucketnName);
        for (S3ObjectSummary summary : objects.getObjectSummaries()) {
            System.out.println(summary.getKey()+ "   "+summary.getOwner());
        }
    }
     */
    public List<Bucket> getBucketList() {
        return s3.listBuckets();
    }

    public Object fileUpload(FileDto fileDto) {

        // client test
        // curl -F name="abc" -F photo="@8.jpg"  http://localhost:8080/file/upload/

        String bucketName = "file-upload-bucket";

        this.createBucket(bucketName);

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
        // String objectKey = folderName + fileTestDto.getPhoto().getOriginalFilename();
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
            PutObjectResult putObjectResult = this.s3.putObject(filePutObjectRequest);
            linkUrl = this.s3.getUrl(bucketName, objectKey).toExternalForm();
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
