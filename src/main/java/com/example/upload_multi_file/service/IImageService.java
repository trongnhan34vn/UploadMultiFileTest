package com.example.upload_multi_file.service;

import com.example.upload_multi_file.model.Image;
import java.util.List;

public interface IImageService {
    List<Image> findAll();
    void save(Image image);
    List<Image> findImagesByProductId(int pId);
}
