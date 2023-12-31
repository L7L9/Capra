package com.capra.article.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文章分组po类
 *
 * @author lql
 * @date 2023/11/10
 */
@Data
@Accessors(chain = true)
@TableName("tb_article_group")
public class ArticleGroup {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 组名
     */
    private String name;

    /**
     * 分组描述
     */
    private String description;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;
}
