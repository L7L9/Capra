package com.capra.admin.service.impl;

import com.capra.admin.domain.po.Permission;
import com.capra.admin.domain.po.Role;
import com.capra.admin.domain.po.RolePermission;
import com.capra.admin.mapper.PermissionMapper;
import com.capra.admin.mapper.RoleMapper;
import com.capra.admin.mapper.RolePermissionMapper;
import com.capra.admin.service.RoleService;
import com.capra.core.exception.DaoException;
import com.capra.core.exception.ServiceException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * role服务接口是西安类
 * @author lql
 * @date 2023/12/07
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public Boolean addRole(String name) {
        if(roleMapper.existByName(name)){
            throw new ServiceException("该角色已经存在");
        }

        if(roleMapper.insert(new Role().setName(name)) != 1){
            throw new DaoException("数据库插入异常");
        }

        return true;
    }

    @Override
    public List<Role> getAllRole() {
        return roleMapper.selectAll();
    }

    @Override
    public List<Permission> getRolePermissions(Long roleId) {
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByRoleId(roleId);

        List<Permission> permissions = new ArrayList<>();
        for(RolePermission rolePermission: rolePermissions){
            Permission permission = permissionMapper.selectById(rolePermission.getPermissionId());
            permissions.add(permission);
        }
        return permissions;
    }
}
