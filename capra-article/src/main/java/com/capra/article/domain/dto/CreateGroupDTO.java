package com.capra.article.domain.dto;

import lombok.Data;

/**
 * 创建分组dto
 *
 * @author lql
 * @date 2023/12/01
 */
@Data
public class CreateGroupDTO {
    /**
     * 分组
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}
