package com.capra.article.service;

import com.capra.article.domain.bo.CreateArticleBO;

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
     */
    Boolean createArticle(CreateArticleBO createArticleBO);
}
