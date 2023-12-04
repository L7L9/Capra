package com.capra.file.service.impl;

import com.capra.api.domain.request.MinioOperateRequest;
import com.capra.api.domain.request.MinioUploadRequest;
import com.capra.core.constant.MinioBucketConstant;
import com.capra.core.exception.ServiceException;
import com.capra.core.exception.SystemException;
import com.capra.file.service.MinioService;
import io.minio.*;
import io.minio.http.Method;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * minio服务实现类
 * @author lql
 * @date 2023/11/28
 */
@Service("minioService")
public class MinioServiceImpl implements MinioService {
    @Resource
    private MinioClient minioClient;

    @PostConstruct
    public void init(){
        // 初始化桶,判断桶是否存在,不存在则创建
        Field[] fields = MinioBucketConstant.class.getDeclaredFields();
        try{
            for(Field field: fields){
                field.setAccessible(true);
                String value = (String) field.get(null);
                if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(value).build())){
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(value).build());
                }
            }
        } catch (Exception e){
            throw new SystemException("初始化文件桶失败");
        }
    }

    @Override
    public String upload(MinioUploadRequest minioUploadRequest){
        MultipartFile file = minioUploadRequest.getFile();
        String filename = file.getOriginalFilename();
        if(Objects.isNull(filename)){
            throw new ServiceException("文件名为空");
        }
        // 根据年月分
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        String uri = date + "/" + filename;
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(minioUploadRequest.getBucketName())
                    .contentType(file.getContentType())
                    .object(uri)
                    .stream(file.getInputStream(), file.getSize(),-1)
                    .build());
        } catch (Exception e){
            throw new SystemException("上传文件失败");
        }

        return uri;
    }

    @Override
    public String getAccessPath(MinioOperateRequest minioOperateRequest) {
        String url;
        try {
            url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(minioOperateRequest.getBucketName())
                    .object(minioOperateRequest.getIdentification())
                    .method(Method.GET)
                    .build());
        } catch (Exception e) {
            throw new ServiceException("该文件找不到");
        }
        return url;
    }

    @Override
    public byte[] download(MinioOperateRequest minioOperateRequest) {
        byte[] result = new byte[1024];
        try(GetObjectResponse response = minioClient.getObject(GetObjectArgs.builder()
                .bucket(minioOperateRequest.getBucketName())
                .object(minioOperateRequest.getIdentification())
                .build())){
            response.read(result);

        }catch (Exception e){
            throw new ServiceException("文件下载失败");
        }
        return new byte[0];
    }

    @Override
    public Boolean delete(MinioOperateRequest minioOperateRequest) {
        try{
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(minioOperateRequest.getBucketName())
                    .object(minioOperateRequest.getIdentification())
                    .build());
        } catch (Exception e){
            throw new ServiceException("文件删除失败");
        }
        return true;
    }
}
