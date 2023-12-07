package com.capra.admin.service;

/**
 * role服务接口
 * @author lql
 * @date 2023/12/07
 */
public interface RoleService {
    /**
     * 添加角色
     * @param name 角色名
     * @return 成功返回true
     */
    Boolean addRole(String name);
}
