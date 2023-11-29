package com.capra.article.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author lql
 * @date 2023/11/10
 */
@Data
@Accessors(chain = true)
public class ArticleMetadata {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private String title;

    /**
     * 作者名
     */
    private String authorName;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 文件uri
     */
    private String uri;

    /**
     * 点赞数
     */
    private Long likeCount;

    /**
     * 收藏数量
     */
    private Long collectCount;

    /**
     * 对应分组id
     */
    private Long groupId;

    /**
     * 文章状态（0=>未发布;1=>审核;2=>发布;3=>违禁;4=>已删除）
     */
    private Integer status;
}
