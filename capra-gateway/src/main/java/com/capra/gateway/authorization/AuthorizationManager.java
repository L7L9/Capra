package com.capra.gateway.authorization;

import com.capra.core.utils.JwtUtils;
import com.capra.gateway.config.UrlWhiteListConfig;
import com.capra.gateway.constant.HeaderConstant;
import jakarta.annotation.Resource;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

/**
 * 认证的管理器
 * @author lql
 * @date 2023/10/26
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Resource
    private UrlWhiteListConfig urlWhiteListConfig;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext context) {
        // 获取请求对象
        ServerHttpRequest request = context.getExchange().getRequest();

        // 放行白名单
        // 此处是因为nacos配置中热更新时可能会添加新的白名单路径,但是新的路径不会在security中热更新,需要在此重新检测
        // 因为是热更新,规定配置中热更新在尾部添加,因此从列表的尾部开始检测
        String url = request.getURI().getPath();
        List<String> urls = urlWhiteListConfig.getUrls();
        for(int i = urls.size() - 1;i >= urlWhiteListConfig.getInitialLength();i--){
            if (urls.get(i).equals(url)){
                return Mono.just(new AuthorizationDecision((true)));
            }
        }

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