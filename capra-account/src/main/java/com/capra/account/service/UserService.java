package com.capra.account.service;

import com.capra.account.domain.bo.UpdateDescriptionBO;
import com.capra.account.domain.bo.UpdateHeadImgBO;
import com.capra.account.domain.bo.UpdateNicknameBO;
import com.capra.account.domain.po.User;
import com.capra.account.domain.vo.UserMessageVO;
import com.capra.api.domain.request.RegisterRequest;

/**
 *
 * @author lql
 * @date 2023/10/31
 */
public interface UserService {
    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户po
     */
    User getUserInfo(String username);

    /**
     * 注册用户信息到数据库
     *
     * @param registerRequest 注册请求
     * @return 成功返回true
     */
    boolean register(RegisterRequest registerRequest);

    /**
     * 获取用户展示信息
     * @param id 用户id
     * @return 展示用户信息vo类
     */
    UserMessageVO getUserMessage(Long id);

    /**
     * 获取用户全部信息
     * @param id 用户id
     * @return 用户po
     */
    User getUserInfo(Long id);

    /**
     * 修改昵称
     *
     * @param updateNicknameBO 修改昵称bo类
     * @return 成功返回true
     */
    Boolean updateNickname(UpdateNicknameBO updateNicknameBO);

    /**
     * 修改头像
     * @param updateHeadImgBO 修改头像bo类
     * @return 成功返回true
     */
    Boolean updateHeadImg(UpdateHeadImgBO updateHeadImgBO);

    /**
     * 修改自我介绍
     * @param updateDescriptionBO 修改自我介绍bo类
     * @return 成功返回true
     */
    Boolean updateDescription(UpdateDescriptionBO updateDescriptionBO);
}
