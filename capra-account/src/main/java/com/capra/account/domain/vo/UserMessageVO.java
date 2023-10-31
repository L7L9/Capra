package com.capra.account.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 展示用户信息vo类
 * @author lql
 * @date 2023/10/31
 */
@Data
@Accessors(chain = true)
public class UserMessageVO {
    /**
     * id
     */
    private Long id;

    /**
     * 账户名称
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

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

}
