package com.capra.article.service.impl;

import com.capra.article.domain.bo.CreateGroupBO;
import com.capra.article.domain.po.ArticleMetadataGroup;
import com.capra.article.mapper.ArticleMetadataGroupMapper;
import com.capra.article.service.GroupService;
import com.capra.core.exception.DaoException;
import com.capra.core.exception.ServiceException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 文章分组服务的接口类
 *
 * @author lql
 * @date 2023/11/10
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private ArticleMetadataGroupMapper articleMetadataGroupMapper;

    @Override
    public Boolean createGroup(CreateGroupBO createGroupBO) {
        // 查询有无重复
        ArticleMetadataGroup articleMetadataGroup = articleMetadataGroupMapper.selectByNameAndUserId(createGroupBO.getName(), createGroupBO.getUserId());
        if(!Objects.isNull(articleMetadataGroup)){
            throw new ServiceException("该分组名已经存在,请重新输入");
        }
        // 插入
        ArticleMetadataGroup group = new ArticleMetadataGroup();
        group.setName(createGroupBO.getName()).setDescription(createGroupBO.getDescription()).setUserId(createGroupBO.getUserId());
        if (articleMetadataGroupMapper.insert(group) != 1){
            throw new DaoException("数据库插入失败");
        }

        return true;
    }
}
