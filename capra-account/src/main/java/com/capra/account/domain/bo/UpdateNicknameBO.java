package com.capra.account.domain.bo;

import lombok.Data;

/**
 * 修改昵称的bo类
 *
 * @author lql
 * @date 2023/11/01
 */
@Data
public class UpdateNicknameBO {
    /**
     * id
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;
}
