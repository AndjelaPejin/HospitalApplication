package com.example.hospitalApi.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional(rollbackFor = Exception.class)
public interface IPhotoUploadService {
    String uploadPhoto(String id, MultipartFile file);
}
