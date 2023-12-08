package com.capra.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.capra.admin.domain.po.Role;

import java.util.List;

/**
 * role表对应mapper
 *
 * @author lql
 * @date 2023/12/07
 */
public interface RoleMapper  extends BaseMapper<Role> {
    /**
     * 通过name判断role是否存在
     * @param name 名字
     * @return boolean 存在返回true
     */
    default boolean existByName(String name){
        return this.exists(new QueryWrapper<Role>().lambda()
                .eq(Role::getName,name));
    }

    /**
     * 获取所有role
     * @return 返回所有role
     */
    default List<Role> selectAll(){
        return this.selectList(new QueryWrapper<>());
    }
}
