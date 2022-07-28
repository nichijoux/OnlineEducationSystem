package com.zh.oes.vod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

public class VodInitUtil {
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
        // 点播服务接入区
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }
}
