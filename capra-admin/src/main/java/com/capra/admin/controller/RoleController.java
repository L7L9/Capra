package com.capra.admin.controller;

import com.capra.admin.domain.po.Permission;
import com.capra.admin.domain.po.Role;
import com.capra.admin.service.RoleService;
import com.capra.core.result.CommonResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色controller
 *
 * @author lql
 * @date 2023/12/07
 */
@RestController
@RequestMapping("/role")
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

    /**
     * 获取所有角色
     * @return 返回角色列表
     */
    @GetMapping
    public CommonResult<List<Role>> getAllRole(){
        return CommonResult.successWithDetail("获取成功",roleService.getAllRole());
    }

    @GetMapping("/{roleId}")
    public CommonResult<List<Permission>> getRolePermissions(@PathVariable Long roleId){
        return CommonResult.successWithDetail("获取成功",roleService.getRolePermissions(roleId));
    }
}
