package com.capra.article.service;

import com.capra.article.domain.bo.CreateArticleBO;

import java.io.IOException;

/**
 * 文章服务接口
 * @author lql
 * @date 2023/11/15
 */
public interface ArticleService {
    /**
     * 创建文章
     *
     * @param createArticleBO 创建文章bo类
     * @return 成功返回true
     * @throws IOException 文件上传异常
     */
    Boolean createArticle(CreateArticleBO createArticleBO) throws IOException;
}
