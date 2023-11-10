package com.capra.article.controller;

import com.capra.article.domain.bo.CreateGroupBO;
import com.capra.article.service.GroupService;
import com.capra.core.constant.HeaderConstant;
import com.capra.core.result.CommonResult;
import com.capra.core.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lql
 * @date 2023/11/10
 */
@RestController
@RequestMapping("group")
public class GroupController {
    @Resource
    private GroupService groupService;

    @PostMapping
    public CommonResult<Boolean> addGroup(@RequestBody CreateGroupBO createGroupBO, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader(HeaderConstant.TOKEN_HEADER);
        createGroupBO.setUserId(JwtUtils.getUserId(token));
        return CommonResult.successWithDetail("添加文章文组成功",groupService.createGroup(createGroupBO));
    }
}
