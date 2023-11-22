package com.capra.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件服务
 *
 * @author lql
 * @date 2023/11/15
 */
public interface FileService {
    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @return 返回cid
     * @throws IOException 文件上传异常
     */
    String upload(MultipartFile multipartFile) throws IOException;

    /**
     * 获取文件访问路径
     * @param cid 文件cid
     * @return 访问路径
     */
    String getAccessPath(String cid);

    /**
     * 下载文件
     * @param cid 文件cid
     * @return 文件字节数组
     */
    byte[] download(String cid);

    /**
     * 删除
     * @param cid 文件cid
     * @return 成功返回true
     */
    Boolean delete(String cid);
}
