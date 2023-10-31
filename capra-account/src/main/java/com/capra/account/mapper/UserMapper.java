package com.capra.account.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.capra.account.domain.po.User;

/**
 * @author lql
 * @date 2023/10/25
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 通过账户名查询
     *
     * @param username 账户名
     * @return 用户po
     */
    default User selectByUsername(String username){
        return this.selectOne(new QueryWrapper<User>().eq("username",username));
    }
}
