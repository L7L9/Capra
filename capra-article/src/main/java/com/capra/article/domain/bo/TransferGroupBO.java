package com.capra.article.domain.bo;

import lombok.Data;

/**
 * 文章转移分组食物类
 * @author lql
 * @date 2023/11/15
 */
@Data
public class TransferGroupBO {
    /**
     * 原分组id
     */
    private Long sourceId;

    /**
     * 转移分组id
     */
    private Long transferId;
}
