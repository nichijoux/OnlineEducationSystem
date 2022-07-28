package com.zh.oes.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.zh.oes.common.base.exception.OESException;
import com.zh.oes.vod.service.VodService;
import com.zh.oes.vod.utils.ConstantPropertiesUtil;
import com.zh.oes.vod.utils.VodInitUtil;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    /**
     * 上传视频到阿里云云端
     *
     * @param file 要上传的视频文件
     * @return 上传成功后的url路径
     */
    @Override
    public String uploadAliVideo(MultipartFile file) {
        try {
            // 文件输入流
            InputStream inputStream = file.getInputStream();
            // 文件名称
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) throw new NullPointerException();
            // 文件展示名称
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            // title:上传后显示的名称
            // fileName:上传文件原始名称
            // inputStream:上传文件输入流
            UploadStreamRequest request = new UploadStreamRequest(
                    ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                    title, originalFilename, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            // 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                if (StringUtils.isEmpty(videoId))
                    throw new OESException(errorMessage);
            }
            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            throw new OESException("上传视频失败");
        }
    }

    /**
     * 根据视频id到阿里云删除视频
     *
     * @param videoSourceId 要删除的视频id
     */
    @Override
    public void deleteAliVideo(String videoSourceId) {
        try {
            // 初始化客户端
            DefaultAcsClient client = VodInitUtil.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            // 创建删除的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoSourceId);
            // 准备删除视频
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new OESException("删除视频失败");
        }
    }

    /**
     * 删除一系列的视频,videoSourceIdList包含的是要删除的视频的id
     *
     * @param videoSourceIdList 要删除的一系列视频id
     */
    @Override
    public void batchDeleteAliVideo(List<String> videoSourceIdList) {
        try {
            //初始化客户端
            DefaultAcsClient client = VodInitUtil.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //创建删除的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //将list转化为 11,22,33这种形式的字符串
            String videos = StringUtils.join(videoSourceIdList.toArray(), ",");

            //传递多个id ，11,22,33 这种形式
            request.setVideoIds(videos);
            // 准备删除视频
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new OESException("批量删除视频失败");
        }
    }

    /**
     * 获取播放凭证
     *
     * @param videoId 要播放的视频id
     * @return 播放凭证
     */
    @Override
    public String getPlayAuth(String videoId) {
        //创建初始化对象
        DefaultAcsClient client;
        try {
            client = VodInitUtil.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);

            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request设置视频id
            request.setVideoId(String.valueOf(videoId));

            //得到凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            return response.getPlayAuth();
        } catch (ClientException e) {
            throw new OESException("获取播放凭证失败");
        }
    }
}
