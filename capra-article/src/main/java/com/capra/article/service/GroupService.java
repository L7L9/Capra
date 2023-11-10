package com.capra.article.service;

import com.capra.article.domain.bo.CreateGroupBO;

/**
 * 组别服务的接口
 *
 * @author lql
 * @date 2023/11/10
 */
public interface GroupService {
    /**
     * 创建分组
     *
     * @param createGroupBO 创建分组bo类
     * @return 成功返回true
     */
    Boolean createGroup(CreateGroupBO createGroupBO);
}
