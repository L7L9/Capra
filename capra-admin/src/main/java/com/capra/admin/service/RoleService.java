package com.capra.admin.service;

import com.capra.admin.domain.po.Role;

import java.util.List;

/**
 * role服务接口
 * @author lql
 * @date 2023/12/07
 */
public interface RoleService {
    /**
     * 添加角色
     *
     * @param name 角色名
     * @return 成功返回true
     */
    Boolean addRole(String name);

    /**
     * 获取所有的role角色
     * @return 返回role列表
     */
    List<Role> getAllRole();
}
