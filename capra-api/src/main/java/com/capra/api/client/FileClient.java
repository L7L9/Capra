package com.capra.api.client;

import com.capra.api.result.RemoteResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件模块远程接口
 * @author lql
 * @date 2023/11/15
 */
@FeignClient("capra-file")
public interface FileClient {
    /**
     * 上传文件
     * @param file 文件
     * @return 成功返回
     */
    @PostMapping("/file")
    RemoteResult<String> upload(MultipartFile file);

    /**
     * 获取文件
     * @param uri 资源路径
     * @return 返回文件byte
     */
    @GetMapping("/file")
    RemoteResult<byte[]> getFile(String uri);

    /**
     * 删除文件
     * @param uri 资源路径
     * @return 成功返回true
     */
    @DeleteMapping("/file")
    RemoteResult<Boolean> delete(String uri);
}
