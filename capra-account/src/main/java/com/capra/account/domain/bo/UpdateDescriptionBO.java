package com.capra.account.domain.bo;

import lombok.Data;

/**
 * 修改自我介绍bo类
 *
 * @author lql
 * @date 2023/11/01
 */
@Data
public class UpdateDescriptionBO {
    /**
     * id
     */
    private Long id;

    /**
     * 自我介绍
     */
    private String description;
}
