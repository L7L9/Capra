package com.capra.article.domain.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文章分组po类
 *
 * @author lql
 * @date 2023/11/10
 */
@Data
@Accessors(chain = true)
public class ArticleMetadataGroup {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 组名
     */
    private String name;

    /**
     * 分组描述
     */
    private String description;

    /**
     * 用户id
     */
    private Long userId;
}
