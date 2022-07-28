package com.zh.oes.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    // 上传头像文件
    String uploadAvatarFile(MultipartFile file);
}
