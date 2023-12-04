package com.capra.article.controller;

import com.capra.article.domain.bo.CreateArticleBO;
import com.capra.article.domain.dto.CreateArticleDTO;
import com.capra.article.service.ArticleService;
import com.capra.core.constant.HeaderConstant;
import com.capra.core.result.CommonResult;
import com.capra.core.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    /**
     * 创建文章
     *
     * @param createArticleDTO 创建文章dto
     * @param request http请求
     * @return 成功返回ture
     */
    @PostMapping
    public CommonResult<Boolean> createArticle(@RequestBody CreateArticleDTO createArticleDTO, HttpServletRequest request) throws IOException {
        String token = request.getHeader(HeaderConstant.TOKEN_HEADER);
        CreateArticleBO createArticleBO = new CreateArticleBO()
                .setTitle(createArticleDTO.getTitle())
                .setGroupId(createArticleDTO.getGroupId())
                .setAuthorId(JwtUtils.getUserId(token))
                .setAuthorName(JwtUtils.getUsername(token));
        return CommonResult.successWithDetail("创建新文章成功",articleService.createArticle(createArticleBO));
    }

    @GetMapping("/{id}")
    public CommonResult<?> getArticle(@PathVariable Long id){

        return null;
    }
}
