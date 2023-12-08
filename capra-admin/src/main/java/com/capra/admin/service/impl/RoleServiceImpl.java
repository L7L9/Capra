package com.capra.admin.service.impl;

import com.capra.admin.domain.po.Role;
import com.capra.admin.mapper.RoleMapper;
import com.capra.admin.service.RoleService;
import com.capra.core.exception.DaoException;
import com.capra.core.exception.ServiceException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
}
