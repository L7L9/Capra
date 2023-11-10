package com.capra.article.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.capra.article.domain.po.ArticleMetadataGroup;

/**
 * article_metadata_group对应的mapper
 * @author lql
 * @date 2023/11/10
 */
public interface ArticleMetadataGroupMapper extends BaseMapper<ArticleMetadataGroup> {
    /**
     * 通过分组名和用户id查询
     *
     * @param name 组名
     * @param userId 用户id
     * @return 文章分组po类
     */
    default ArticleMetadataGroup selectByNameAndUserId(String name,Long userId){
        return this.selectOne(new QueryWrapper<ArticleMetadataGroup>().eq("name",name).eq("user_id",userId));
    }
}
