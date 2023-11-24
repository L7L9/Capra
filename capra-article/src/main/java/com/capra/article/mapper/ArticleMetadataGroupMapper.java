package com.capra.article.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.capra.article.domain.po.ArticleGroup;

import java.util.List;

/**
 * article_metadata_group对应的mapper
 * @author lql
 * @date 2023/11/10
 */
public interface ArticleMetadataGroupMapper extends BaseMapper<ArticleGroup> {
    /**
     * 通过分组名和用户id查询
     *
     * @param name 组名
     * @param userId 用户id
     * @return 文章分组po类
     */
    default ArticleGroup selectByNameAndUserId(String name, Long userId){
        return this.selectOne(new QueryWrapper<ArticleGroup>().lambda()
                .eq(ArticleGroup::getName,name)
                .eq(ArticleGroup::getUserId,userId));
    }

    /**
     * 通过用户id查询所有的分组
     * @param userId 用户id
     * @return 返回分组列表
     */
    default List<ArticleGroup> selectByUserId(Long userId){
        return this.selectList(new QueryWrapper<ArticleGroup>().lambda()
                .eq(ArticleGroup::getUserId,userId));
    }
}
