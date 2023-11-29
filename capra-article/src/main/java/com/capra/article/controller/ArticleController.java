package com.capra.article.controller;

import com.capra.article.domain.bo.CreateArticleBO;
import com.capra.article.domain.dto.CreateArticleDTO;
import com.capra.article.service.ArticleService;
import com.capra.core.constant.HeaderConstant;
import com.capra.core.result.CommonResult;
import com.capra.core.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章请求
 * @author lql
 * @date 2023/11/15
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @PostMapping
    public CommonResult<Boolean> createArticle(@RequestBody CreateArticleDTO createArticleDTO, HttpServletRequest request){
        String token = request.getHeader(HeaderConstant.TOKEN_HEADER);
        CreateArticleBO createArticleBO = new CreateArticleBO()
                .setTitle(createArticleDTO.getTitle())
                .setFile(createArticleDTO.getFile())
                .setGroupId(createArticleDTO.getGroupId())
                .setAuthorId(JwtUtils.getUserId(token))
                .setAuthorName(JwtUtils.getUsername(token));
        return CommonResult.successWithDetail("创建新文章成功",articleService.createArticle(createArticleBO));
    }
}
