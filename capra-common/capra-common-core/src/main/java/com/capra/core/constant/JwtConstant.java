package com.capra.core.constant;

/**
 * jwt相关的常量类
 *
 * @author lql
 * @date 2023/10/27
 */
public class JwtConstant {
    /**
     * 密钥
     */
    public final static String KEY = "gdut_topview_capra_lql";

    /**
     * 发行者
     */
    public final static String ISSUER = "capra";

    /**
     * 存用户id
     */
    public final static String CLAIM_ID = "user_id";

    /**
     * 存用户名
     */
    public final static String CLAIM_NAME = "username";

    /**
     * 持续时间
     */
    public final static int DURATION = 30 * 60;
}
