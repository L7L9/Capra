package com.capra.article;

/**
 * 文章状态常量类
 *
 * @author lql
 * @date 2023/11/30
 */
public class ArticleConstant {
    /**
     * 未发布
     */
    public final static int UNRELEASED = 0;

    /**
     * 待审核
     */
    public final static int UNDER_REVIEW = 1;

    /**
     * 已发布
     */
    public final static int RELEASE = 2;

    /**
     * 违禁
     */
    public final static int VIOLATION = 3;

    /**
     * 待删除
     */
    public final static int TOBE_DELETE = 4;

    /**
     * 删除
     */
    public final static int DELETE = 5;
}
