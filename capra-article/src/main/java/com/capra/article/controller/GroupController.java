package com.capra.article.controller;

import com.capra.article.domain.bo.CreateGroupBO;
import com.capra.article.domain.bo.TransferGroupBO;
import com.capra.article.domain.dto.CreateGroupDTO;
import com.capra.article.domain.po.ArticleGroup;
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
     * @param createGroupDTO 创建分组bo类
     * @param httpServletRequest http请求
     * @return 成功返回ture
     */
    @PostMapping
    public CommonResult<Boolean> addGroup(@RequestBody CreateGroupDTO createGroupDTO, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader(HeaderConstant.TOKEN_HEADER);
        CreateGroupBO createGroupBO = new CreateGroupBO()
                .setUserId(JwtUtils.getUserId(token))
                .setName(createGroupDTO.getName())
                .setDescription(createGroupDTO.getDescription());
        return CommonResult.successWithDetail("添加文章分组成功",groupService.createGroup(createGroupBO));
    }

    /**
     * 获取登录用户所有分组
     * @param httpServletRequest http请求
     * @return 分组数据
     */
    @GetMapping
    public CommonResult<List<ArticleGroup>> getAllGroups(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader(HeaderConstant.TOKEN_HEADER);
        return CommonResult.successWithDetail("获取文章分组列表成功",groupService.getAllGroup(JwtUtils.getUserId(token)));
    }

    /**
     * 删除分组
     * @param id 分组id
     * @return 成功返回true
     */
    @DeleteMapping("/{id}")
    public CommonResult<Boolean> deleteGroup(@PathVariable Long id){
        return CommonResult.successWithDetail("删除分组成功",groupService.deleteGroup(id));
    }

    /**
     * 批量转移文章,从一个分组转移到另一个分组
     * @param transferGroupBO 转移分组bo类
     * @return 操作成功返回true
     */
    @PutMapping
    public CommonResult<Boolean> transferGroup(@RequestBody TransferGroupBO transferGroupBO){
        return CommonResult.successWithDetail("转移成功",groupService.transferGroup(transferGroupBO));
    }
}
