package com.capra.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.capra.admin.domain.po.RolePermission;

import java.util.List;

/**
 * role——permission中间表对应的mapper
 *
 * @author lql
 * @date 2023/12/08
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    /**
     * 通过角色id查询所有权限
     * @param roleId 角色id
     * @return boolean 存在返回true
     */
    default List<RolePermission> selectByRoleId(Long roleId){
        return this.selectList(new QueryWrapper<RolePermission>().lambda().eq(RolePermission::getId,roleId));
    }
}
