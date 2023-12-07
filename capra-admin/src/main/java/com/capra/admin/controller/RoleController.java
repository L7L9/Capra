package com.capra.admin.controller;

import com.capra.admin.service.RoleService;
import com.capra.core.result.CommonResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色controller
 *
 * @author lql
 * @date 2023/12/07
 */
@RestController
@RequestMapping("role")
public class RoleController {
    @Resource
    private RoleService roleService;

    /**
     * 添加角色
     *
     * @param name 角色名
     * @return 添加成功返回true
     */
    @PostMapping
    public CommonResult<Boolean> addRole(@RequestParam String name){
        return CommonResult.successWithDetail("添加角色成功",roleService.addRole(name));
    }
}
