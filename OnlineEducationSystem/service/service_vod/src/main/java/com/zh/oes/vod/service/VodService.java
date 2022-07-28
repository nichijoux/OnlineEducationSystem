package com.zh.oes.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    // 上传视频到阿里云云端
    String uploadAliVideo(MultipartFile file);

    // 根据视频id到阿里云删除视频
    void deleteAliVideo(String videoSourceId);

    // 删除一系列的视频,videoSourceIdList包含的是要删除的视频的id
    void batchDeleteAliVideo(List<String> videoSourceIdList);

    // 获取播放凭证
    String getPlayAuth(String videoId);
}
