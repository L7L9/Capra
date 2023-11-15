package com.capra.article.controller;

import com.capra.article.domain.bo.CreateGroupBO;
import com.capra.article.domain.po.ArticleMetadataGroup;
import com.capra.article.service.GroupService;
import com.capra.core.constant.HeaderConstant;
import com.capra.core.result.CommonResult;
import com.capra.core.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lql
 * @date 2023/11/10
 */
@RestController
@RequestMapping("group")
public class GroupController {
    @Resource
    private GroupService groupService;

    /**
     * 添加分组请求
     * @param createGroupBO 创建分组bo类
     * @param httpServletRequest http请求
     * @return 成功返回ture
     */
    @PostMapping
    public CommonResult<Boolean> addGroup(@RequestBody CreateGroupBO createGroupBO, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader(HeaderConstant.TOKEN_HEADER);
        createGroupBO.setUserId(JwtUtils.getUserId(token));
        return CommonResult.successWithDetail("添加文章分组成功",groupService.createGroup(createGroupBO));
    }

    @GetMapping
    public CommonResult<List<ArticleMetadataGroup>> getAllGroups(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader(HeaderConstant.TOKEN_HEADER);
        return CommonResult.successWithDetail("获取文章分组列表成功",groupService.getAllGroup(JwtUtils.getUserId(token)));
    }

    @DeleteMapping("/{id}")
    public CommonResult<Boolean> deleteGroup(@PathVariable Long id){
        return CommonResult.successWithDetail("删除分组成功",groupService.deleteGroup(id));
    }
}
