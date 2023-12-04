package com.capra.article.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    /**
     * 判断title是否重复
     *
     * @param title    文章标题
     * @param authorId 作者id
     * @return 存在返回true, 否则返回false
     */
    default Boolean existsByTitleAndAuthorId(String title,Long authorId){
        return this.exists(new QueryWrapper<ArticleMetadata>().lambda()
                .eq(ArticleMetadata::getTitle,title)
                .eq(ArticleMetadata::getAuthorId,authorId));
    }
}
