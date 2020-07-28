package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.ImageMapper;
import kr.huple.jeju2ri.api.model.ImageDto;
import kr.huple.jeju2ri.api.model.response.ImageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private final ImageMapper imageMapper;

    public ImageService(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    public String getImageId() {
        return imageMapper.getImageId();
    }

    public List<ImageResponse> findByImageId(String imageId) {
        return imageMapper.findByImageId(imageId);
    }

    public void add(ImageDto image) {
        imageMapper.add(image);
    }

}
