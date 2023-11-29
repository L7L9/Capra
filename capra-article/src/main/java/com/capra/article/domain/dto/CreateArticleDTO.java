package com.capra.article.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 创建文章dto
 *
 * @author lql
 * @date 2023/11/29
 */
@Data
public class CreateArticleDTO {
    /**
     * 文章名字
     */
    private String title;

    /**
     * 分组id
     */
    private Long groupId;

    /**
     * 文章文件
     */
    private MultipartFile file;
}
