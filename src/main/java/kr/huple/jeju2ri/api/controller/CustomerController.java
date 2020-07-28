package kr.huple.jeju2ri.api.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import com.amazonaws.services.s3.model.ObjectMetadata;
import kr.huple.jeju2ri.api.model.AuthRequest;
import kr.huple.jeju2ri.api.model.response.CustomerResponse;
import kr.huple.jeju2ri.api.service.JwtUtilService;
import kr.huple.jeju2ri.api.service.ObjectStorageService;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.huple.jeju2ri.api.model.CustomerDto;
import kr.huple.jeju2ri.api.service.CustomerService;
import kr.huple.jeju2ri.util.Word;

@RestController
@Transactional
@RequestMapping(value = "/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final JwtUtilService jwtUtilService;
    private final AuthenticationManager authenticationManager;
    private final ObjectStorageService objectStorageService;

    @Value("${ncloud.object.storage.image-bucket-name}")
    private String bucketName;

    @Value("${ncloud.object.storage.image-bucket-folder}")
    private String bucketFolder;

    public CustomerController(CustomerService customerService, JwtUtilService jwtUtilService, AuthenticationManager authenticationManager, ObjectStorageService objectStorageService) {
        this.customerService = customerService;
        this.jwtUtilService = jwtUtilService;
        this.authenticationManager = authenticationManager;
        this.objectStorageService = objectStorageService;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            System.out.println("authRequest.getUserName(), " + authRequest.getUsername());
            System.out.println("authRequest.getPassword(), " + authRequest.getPassword());
            if (authRequest.getUsername().isEmpty() && authRequest.getPassword().isEmpty()) {

                System.out.println("===========================================");
                if (!authRequest.getSnsId().isEmpty() && !authRequest.getJoinMethod().isEmpty()) {
                    String username = "SNSID:" + authRequest.getSnsId() + "❤" + authRequest.getJoinMethod();
                    System.out.println("username =============> " + username);
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(username, authRequest.getJoinMethod())
                    );
                } else {
                    System.out.println("snsId or joinMethod is empty =============> ");
                    return "snsId or joinMethod is empty";
                }
            } else {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authRequest.getUsername(), authRequest.getPassword()
                        )
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("inavalid username/password");
        }
        return jwtUtilService.generateToken(authRequest.getUsername());
    }


    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object add(@RequestBody @Validated CustomerDto param) throws Exception {

        List<CustomerResponse> customers = customerService.findBySnsId(param);
        if(customers == null || customers.size() == 0) {
            customerService.add(param);
            return Word.REGIST_SUCCESS_MSG;
        }

        return "exist";

    }

    @GetMapping(value = "/sns-id")
    public Object findBySnsId(@RequestParam("snsId") String snsId, @RequestParam("joinMethod") String joinMethod) {
        CustomerDto param = new CustomerDto();
        param.setSnsId(snsId);
        param.setJoinMethod(joinMethod);
        if(customerService.findBySnsId(param).size() == 0) {
            return null;
        } else {
            return customerService.findBySnsId(param).get(0).getCustomerId();
        }
    }

    @GetMapping(value = "/check-email/{email}")
    public Object findByEmail(@PathVariable("email") String email) {
        Integer result = customerService.findByEmail(email);
        if(result == 0) {
            return false;
        } else {
            return true;
        }

    }

    @GetMapping(value = "/check-nickname/{nickname}")
    public Object findByNickname(@PathVariable("nickname") String nickname) {
        Integer result = customerService.findByNickname(nickname);
        if(result == 0) {
            return false;
        } else {
            return true;
        }
        //return Word.EMAIL_AVAILABLE;
    }

    @GetMapping(value = "")
    public Object findAll() {
        return customerService.findAll();
    }

    @GetMapping(value = "/{customerId}")
    public Object findByCustomerId(@PathVariable("customerId") String customerId) {
        return customerService.findByCustomerId(customerId);
    }

    @PatchMapping(value = "/{customerId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object editCustomer(@PathVariable("customerId") String customerId, CustomerDto customer) {

        customer.setCustomerId(customerId);

        // 프로필 이미지
        if(customer.getImage() != null && customer.getImage().getOriginalFilename() != null && !"".equals(customer.getImage().getOriginalFilename())) {

            String objectKey = objectStorageService.getObjectKeyByToday(bucketFolder, customer.getImage().getOriginalFilename());

            Path keyPath = Paths.get(objectKey);
            Path fileName = keyPath.getFileName();
            String contentType = customer.getImage().getContentType();
            try {
                if (contentType.startsWith("image")) {

                    BufferedImage bufferedImage = Thumbnails.of(customer.getImage().getInputStream())
                            .size(160, 160)
                            .asBufferedImage();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImage, contentType.split("/")[1], baos);                          // Passing: ​(RenderedImage im, String formatName, OutputStream output)
                    InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

                    ObjectMetadata fileObjectMetadata = objectStorageService.getObjectMetadata(
                            inputStream.available(), customer.getImage().getContentType()
                    );

                    String thumbnailObjectKey = keyPath.getParent() + "/160_160_" + fileName;
                    String thumbnailUrl = objectStorageService.uploadFile(bucketName, inputStream, thumbnailObjectKey, fileObjectMetadata);
                    customer.setThumbnailImage(thumbnailUrl);
                } else {
                    System.out.println("Wrong image file format!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String profileUrl = objectStorageService.uploadFile(bucketName, customer.getImage(), objectKey);
            customer.setProfileImage(profileUrl);
        }

        customerService.edit(customer);

        return Word.REGIST_SUCCESS_MSG;
    }

}
