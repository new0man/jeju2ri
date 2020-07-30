package kr.huple.jeju2ri.api.controller;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.*;
import kr.huple.jeju2ri.api.service.ObjectStorageService;
import kr.huple.jeju2ri.configuration.response.RestResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
public class FileUploadController {

    private ObjectStorageService objectStorageService;

    public FileUploadController(ObjectStorageService objectStorageService) {
        this.objectStorageService = objectStorageService;
    }

    @Value("${ncloud.object.storage.image-bucket-name}")
    private String bucketName;

    @Value("${ncloud.object.storage.image-bucket-folder}")
    private String bucketFolder;

    @RequestMapping(path = "/api/files", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @ResponseStatus(HttpStatus.OK)
    public Object fileUpload(@RequestParam("files") List<MultipartFile> files) {

        List<String> linkUrlList = new ArrayList<>();
        for( int fileIdx = 0; fileIdx < files.size(); fileIdx++ ) {
            MultipartFile file = files.get(fileIdx);
            RestResponse<?> restResponse = (RestResponse<?>) singleFileUpload(file);
            linkUrlList.add(restResponse.getResult().toString());
        }

        return linkUrlList;
    }

    public Object singleFileUpload(MultipartFile file) {

        String linkUrl = "";

        String originalFileName = file.getOriginalFilename();
        String objectKey = objectStorageService.getObjectKeyByToday(bucketName, originalFileName);

        ObjectMetadata fileObjectMetadata = new ObjectMetadata();
        fileObjectMetadata.setContentLength(file.getSize());
        fileObjectMetadata.setContentType(file.getContentType());

        try {
            linkUrl = objectStorageService.uploadFile(bucketName, file, objectKey);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch(SdkClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return linkUrl;

    }

}
