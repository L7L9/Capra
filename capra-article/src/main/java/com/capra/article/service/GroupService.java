package com.capra.article.service;

import com.capra.article.domain.bo.CreateGroupBO;
import com.capra.article.domain.po.ArticleMetadataGroup;

import java.util.List;

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

    /**
     * 获取用户id对应的所有文章分组
     * @param userId 用户id
     * @return 返回分组列表
     */
    List<ArticleMetadataGroup> getAllGroup(Long userId);
}
