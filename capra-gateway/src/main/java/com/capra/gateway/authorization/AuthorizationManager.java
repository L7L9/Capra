package com.capra.gateway.authorization;

import com.capra.core.utils.JwtUtils;
import com.capra.gateway.constant.HeaderConstant;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 认证的管理器
 * @author lql
 * @date 2023/10/26
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext context) {
        // 获取请求对象
        ServerHttpRequest request = context.getExchange().getRequest();

        // 检测是否token的合法性
        String token = request.getHeaders().getFirst(HeaderConstant.TOKEN_HEADER);
        // 判断是否为空
        if(Objects.isNull(token)){
            return Mono.just(new AuthorizationDecision((false)));
        }
        // 判断是否有效
        if(!JwtUtils.checkToken(token)){
            return Mono.just(new AuthorizationDecision((false)));
        }

        return Mono.just(new AuthorizationDecision(true));
    }
}