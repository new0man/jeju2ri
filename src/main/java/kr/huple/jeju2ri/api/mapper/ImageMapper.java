package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.ImageDto;
import kr.huple.jeju2ri.api.model.response.ImageResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {

    public String getImageId();

    public List<ImageResponse> findByImageId(String imageId);

    public void add(ImageDto image);

}
