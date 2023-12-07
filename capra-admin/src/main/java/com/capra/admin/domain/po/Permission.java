package com.capra.admin.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 权限表对应po类
 *
 * @author lql
 * @date 2023/12/07
 */
@Data
@TableName("tb_permission")
public class Permission {
    /**
     * 逐渐
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 资源路径
     */
    private String uri;
}
