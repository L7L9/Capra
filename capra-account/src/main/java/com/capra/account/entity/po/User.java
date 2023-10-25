package com.capra.account.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户po
 *
 * @author lql
 * @date 2023/10/24
 */
@Data
@Accessors(chain = true)
public class User {
    /**
     * id
     */
    private Long id;

    /**
     * 全局唯一标识
     */
    private String uuid;

    /**
     * 账户名称
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 积分数量
     */
    private Long points;

    /**
     * 自我介绍
     */
    private String description;

    /**
     * 文章数量
     */
    private Long articleCount;

    /**
     * 关注用户数量
     */
    private Long followCount;

    /**
     * 粉丝数量
     */
    private Long fansCount;

    /**
     * 账户状态
     */
    private Integer status;
}
