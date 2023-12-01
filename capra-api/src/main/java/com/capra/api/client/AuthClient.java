package com.capra.api.client;

import com.capra.api.result.RemoteResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * auth模块的远程接口定义
 *
 * @author lql
 * @date 2023/12/01
 */
@FeignClient("capra-auth")
public interface AuthClient {
    /**
     * 验证并且刷新
     * @param token 用户令牌
     * @return 验证成功返回true
     */
    @PostMapping
    RemoteResult<Boolean> verify(String token);
}
