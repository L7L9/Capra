package com.capra.article.mapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.capra.article.domain.po.ArticleMetadata;

/**
 * ArticleMetadata对应mapper类
 * @author lql
 * @date 2023/11/24
 */
public interface ArticleMetadataMapper extends BaseMapper<ArticleMetadata> {
    /**
     * 修改文章的分组id
     *
     * @param sourceId 原分组id
     * @param transferId 转移分组id
     * @return int 修改条数
     */
    default int updateGroupId(Long sourceId,Long transferId){
        return this.update(new ArticleMetadata().setGroupId(transferId),
                new UpdateWrapper<ArticleMetadata>().lambda()
                        .eq(ArticleMetadata::getGroupId,sourceId));
    }
}
