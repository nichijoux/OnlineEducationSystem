package com.zh.oes.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zh.oes.common.base.exception.OESException;
import com.zh.oes.oss.service.OssService;
import com.zh.oes.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    /**
     * 上传头像文件(会进行文件类型判断)
     *
     * @param file 要上传的头像文件
     * @return 上传成功后图片的url地址
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String uploadAvatarFile(MultipartFile file) {
        //获取阿里云存储相关变量
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        //返回的url
        String url;
        //创建oss实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 获取原始文件名称
            String fileName = file.getOriginalFilename();

            //获取上传文件输入流(提前获取,避免失败)
            InputStream inputStream = file.getInputStream();

            // 临时文件
            if (fileName == null) throw new OESException("文件错误");
            File tempFile = new File(System.getProperty("java.io.tmpdir"), fileName);

            // 先把文件序列化到临时目录
            try {
                file.transferTo(tempFile);
                // 尝试IO文件，判断文件的合法性
                BufferedImage bufferedImage = ImageIO.read(tempFile);
                bufferedImage.getWidth();
                bufferedImage.getHeight();
                // 保存后应该删除
                tempFile.delete();
            } catch (Exception e) {
                // IO异常，不是合法的图片文件，返回异常信息
                throw new OESException("文件不是图片文件");
            }

            //防止文件重复
            String uuid = UUID.randomUUID().toString().substring(25).replaceAll("-", "");
            //文件上传分类，根据日期分类
            String datePath = new DateTime().toString("yyyy/MM/dd");

            //拼接文件名称
            fileName = datePath + "/" + uuid + fileName;
            //调用oss方法实现上传
            //第一个参数 BUCKET 名称
            //第二个参数 上传到oss文件路径和文件名称
            //第三个参数 上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);
            //关闭ossClient
            ossClient.shutdown();
            //把上传之后的文件路径返回
            url = "https://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (Exception e) {
            throw new OESException("上传文件失败");
        }
        return url;
    }
}
