package com.agile.test.service;

import com.agile.test.dto.ImageInfoDto;
import com.agile.test.dto.PageDto;
import com.agile.test.mappers.ImageMapper;
import com.agile.test.model.Image;
import com.agile.test.model.ImageInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

public class InjectDataService {
    private final ApiService apiService;
    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final ImageMapper imageMapper;
    private final ImageService imageService;
    private final ImageInfoService imageInfoService;

    public InjectDataService(ApiService apiService, CloseableHttpClient httpClient,
                             ObjectMapper objectMapper, ImageMapper imageMapper,
                             ImageService imageService, ImageInfoService imageInfoService) {
        this.apiService = apiService;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.imageMapper = imageMapper;
        this.imageService = imageService;
        this.imageInfoService = imageInfoService;
    }

    public void injectData() {
        imageService.clear();
        String token = apiService.getAuthorizationToken().getToken();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        int count = 1;
        while (true) {
            String url = "http://interview.agileengine.com:80/images?page=" + count++;
            HttpGet request = new HttpGet(url);
            request.addHeader("Authorization", "Bearer " + token);
            PageDto pageDto;
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                pageDto = objectMapper.readValue(response.getEntity().getContent(), PageDto.class);
                List<Image> pictures = imageMapper.toImageList(pageDto);
                for (Image picture : pictures) {
                    imageService.add(picture);
                }
            } catch (IOException e) {
                throw new RuntimeException("Couldn't inject images", e);
            }
            List<Image> pictureList = imageService.getAll();
            for (Image image : pictureList) {
                String link = "http://interview.agileengine.com:80/images/" + image.getId();
                HttpGet getRequest = new HttpGet(link);
                request.addHeader("Authorization", "Bearer " + token);
                try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
                    ImageInfoDto imageInfoDto = objectMapper.readValue(response.getEntity()
                            .getContent(), ImageInfoDto.class);
                    ImageInfo imageInfo = imageMapper.toImageInfo(imageInfoDto);
                    imageInfoService.add(imageInfo);
                } catch (IOException e) {
                    throw new RuntimeException("Couldn't inject images", e);
                }
            }
            if (!pageDto.getIsMore()) {
                break;
            }
        }
    }
}
