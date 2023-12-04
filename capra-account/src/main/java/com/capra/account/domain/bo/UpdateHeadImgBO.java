package com.capra.account.domain.bo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 修改用户头像bo类
 *
 * @author lql
 * @date 2023/11/01
 */
@Data
public class UpdateHeadImgBO {
    /**
     * id
     */
    private Long id;

    /**
     * 图片文件
     */
    private MultipartFile file;

    /**
     * 用户名
     */
    private String username;
}
