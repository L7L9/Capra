package com.capra.api.client;

import com.capra.api.domain.SysUser;
import com.capra.api.result.RemoteResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 定义Auth模块的远程接口
 *
 * @author lql
 * @date 2023/10/26
 */
@FeignClient("capra-auth")
public interface AuthClient {
    /**
     * 获取token
     *
     * @param sysUser 存于jwt的用户信息
     * @return jwt
     */
    @PostMapping("/auth/getToken")
    RemoteResult<String> getToken(SysUser sysUser);
}
