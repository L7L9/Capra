package com.capra.admin.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色po类
 *
 * @author lql
 * @date 2023/12/07
 */
@Data
@Accessors(chain = true)
@TableName("tb_role")
public class Role {
    /**
     * 逐渐
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色拥有的权限资源路径数组
     */
    private Permission[] permissions;
}
