package com.capra.file.service.impl;

import cn.hutool.core.util.IdUtil;
import com.capra.core.exception.ServiceException;
import com.capra.core.exception.SystemException;
import com.capra.file.client.IpfsClient;
import com.capra.file.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * ipfs文件服务实现类
 * @author lql
 * @date 2023/11/22
 */
@Service("ipfsService")
public class IpfsServiceImpl implements FileService {
    @Resource
    private IpfsClient ipfsClient;

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        if(Objects.isNull(originalFilename)){
            throw new ServiceException("文件名为空");
        }
        // 获取后缀
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);
        // 生成uuid
        String uuid = IdUtil.randomUUID();
        File file = File.createTempFile(uuid,suffix);
        multipartFile.transferTo(file);

        String cid = ipfsClient.upload(file);
        if(!file.delete()){
            throw new SystemException("服务异常");
        }
        return cid;
    }

    @Override
    public String getAccessPath(String cid) {
        return ipfsClient.getFileAccessPath(cid);
    }

    @Override
    public byte[] download(String cid) {
        return ipfsClient.download(cid);
    }

    @Override
    public Boolean delete(String cid) {
        ipfsClient.delete(cid);
        return true;
    }
}
