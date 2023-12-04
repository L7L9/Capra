package com.capra.article.domain.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 创建文章bo类
 * @author lql
 * @date 2023/11/15
 */
@Data
@Accessors(chain = true)
public class CreateArticleBO {
    /**
     * 文章名字
     */
    private String title;

    /**
     * 分组id
     */
    private Long groupId;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 作者名
     */
    private String authorName;
}
