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
    public final static byte[] KEY = "gdut_topview_capra_lql".getBytes();

    /**
     * 发行者
     */
    public final static String ISSUER = "capra";

    /**
     * 存用户id
     */
    public final static String CLAIM_ID = "userId";

    /**
     * 存用户uuid
     */
    public final static String CLAIM_UUID = "uuid";

    /**
     * 存用户名
     */
    public final static String CLAIM_USERNAME = "username";

    /**
     * 持续时间(30天)
     */
    public final static int DURATION = 60 * 24 * 30;
}
