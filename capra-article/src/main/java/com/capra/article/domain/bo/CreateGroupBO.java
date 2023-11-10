package com.capra.article.domain.bo;

import lombok.Data;

/**
 * 创建分组的bo类
 *
 * @author lql
 * @date 2023/11/10
 */
@Data
public class CreateGroupBO {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 分组
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}
