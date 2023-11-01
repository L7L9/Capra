package com.capra.account.controller;

import com.capra.account.domain.bo.UpdateDescriptionBO;
import com.capra.account.domain.bo.UpdateHeadImgBO;
import com.capra.account.domain.bo.UpdateNicknameBO;
import com.capra.account.domain.po.User;
import com.capra.account.domain.vo.UserMessageVO;
import com.capra.account.service.UserService;
import com.capra.api.annotation.InnerCall;
import com.capra.api.domain.request.RegisterRequest;
import com.capra.api.domain.response.LoginResponse;
import com.capra.api.result.RemoteResult;
import com.capra.core.constant.HeaderConstant;
import com.capra.core.result.CommonResult;
import com.capra.core.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * 用户controller
 *
 * @author lql
 * @date 2023/10/24
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private HttpServletRequest request;

    /**
     * 获取用户信息
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public CommonResult<UserMessageVO> getUserMessage(@PathVariable Long id){
        return CommonResult.successWithDetail("获取用户信息成功",userService.getUserMessage(id));
    }

    /**
     * 获取登录用户的全部信息
     * @return 返回登录用户全部信息
     */
    @GetMapping("/center")
    public CommonResult<User> getUserMessage(){
        Long userId = JwtUtils.getUserId(request.getHeader(HeaderConstant.TOKEN_HEADER));
        return CommonResult.successWithDetail("获取登录用户信息成功",userService.getUserInfo(userId));
    }

    /**
     * 修改登录用户昵称
     *
     * @param updateNicknameBO 修改昵称bo类
     * @return 成功返回true
     */
    @PostMapping("/center/nickname")
    public CommonResult<Boolean> updateNickname(@RequestBody UpdateNicknameBO updateNicknameBO){
        updateNicknameBO.setId(JwtUtils.getUserId(request.getHeader(HeaderConstant.TOKEN_HEADER)));
        return CommonResult.successWithDetail("修改成功",userService.updateNickname(updateNicknameBO));
    }

    /**
     * 修改登录用户头像
     * @param imgFile 头像图片文件
     * @return 成功返回true
     */
    @PostMapping("/center/head-img")
    public CommonResult<Boolean> updateHeadImg(@RequestParam("file") MultipartFile imgFile){
        UpdateHeadImgBO updateHeadImgBO = new UpdateHeadImgBO();
        updateHeadImgBO.setId(JwtUtils.getUserId(request.getHeader(HeaderConstant.TOKEN_HEADER)));
        updateHeadImgBO.setFile(imgFile);
        return CommonResult.successWithDetail("修改成功",userService.updateHeadImg(updateHeadImgBO));
    }

    /**
     * 修改登录用户自我介绍
     *
     * @param updateDescriptionBO 修改自我介绍bo类
     * @return 成功返回true
     */
    @PostMapping("/center/description")
    public CommonResult<Boolean> updateDescription(@RequestBody UpdateDescriptionBO updateDescriptionBO){
        updateDescriptionBO.setId(JwtUtils.getUserId(request.getHeader(HeaderConstant.TOKEN_HEADER)));
        return CommonResult.successWithDetail("修改成功",userService.updateDescription(updateDescriptionBO));
    }

    /**
     * 获取需要认证的用户信息
     * @param username 用户名
     * @return 返回需要认证的用户信息
     */
    @InnerCall
    @GetMapping("/info/{username}")
    public RemoteResult<LoginResponse> getAuthUserMessage(@PathVariable String username){
        User userInfo = userService.getUserInfo(username);
        if(Objects.isNull(userInfo)){
            return RemoteResult.successWithMeg("用户不存在");
        }
        return RemoteResult.successWithData(new LoginResponse()
                .setId(userInfo.getId())
                .setUsername(userInfo.getUsername())
                .setPassword(userInfo.getPassword())
                .setStatus(userInfo.getStatus()));
    }

    /**
     * 注册用户信息到数据库
     * @param registerRequest 注册请求
     * @return 成功返回true
     */
    @InnerCall
    @PostMapping("/register")
    public RemoteResult<Boolean> register(@RequestBody RegisterRequest registerRequest){
        return RemoteResult.successWithData(userService.register(registerRequest));
    }
}
