package com.capra.account.service.impl;

import com.capra.account.domain.bo.UpdateDescriptionBO;
import com.capra.account.domain.bo.UpdateHeadImgBO;
import com.capra.account.domain.bo.UpdateNicknameBO;
import com.capra.account.domain.po.User;
import com.capra.account.domain.vo.UserMessageVO;
import com.capra.account.mapper.UserMapper;
import com.capra.account.service.UserService;
import com.capra.account.utils.ImgUtils;
import com.capra.api.domain.request.RegisterRequest;
import com.capra.core.exception.DaoException;
import com.capra.core.exception.ServiceException;
import com.capra.core.exception.SystemException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * @author lql
 * @date 2023/10/31
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserInfo(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public boolean register(RegisterRequest registerRequest) {
        if(!Objects.isNull(userMapper.selectByUsername(registerRequest.getUsername()))){
            return false;
        }

        User user = new User()
                .setUsername(registerRequest.getUsername())
                .setNickname(registerRequest.getUsername())
                .setPassword(registerRequest.getPassword());
        if(userMapper.insert(user) != 1){
            throw new DaoException("数据库插入失败");
        }

        return true;
    }

    @Override
    public UserMessageVO getUserMessage(Long id) {
        User user = userMapper.selectById(id);
        if(Objects.isNull(user)){
            throw new SystemException("查询不到用户,用户id存在异常");
        }

        return new UserMessageVO()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setNickname(user.getNickname())
                .setHeadImg(user.getHeadImg())
                .setDescription(user.getDescription())
                .setPoints(user.getPoints())
                .setArticleCount(user.getArticleCount())
                .setFansCount(user.getFansCount())
                .setFollowCount(user.getFollowCount());
    }

    @Override
    public User getUserInfo(Long id) {
        User user = userMapper.selectById(id);
        if(Objects.isNull(user)){
            throw new SystemException("查询不到用户,用户id存在异常");
        }
        return user;
    }

    @Override
    public Boolean updateNickname(UpdateNicknameBO updateNicknameBO) {
        if(userMapper.updateById(new User().setId(updateNicknameBO.getId()).setNickname(updateNicknameBO.getNickname())) != 1){
            throw new DaoException("数据库异常");
        }
        return true;
    }

    @Override
    public Boolean updateHeadImg(UpdateHeadImgBO updateHeadImgBO) {
        MultipartFile file = updateHeadImgBO.getFile();
        // 判断是否为空
        if (Objects.isNull(file)){
            throw new ServiceException("文件信息为空,请重新上传");
        }
        // 判断类型
        if(!ImgUtils.checkType(file)){
            throw new ServiceException("文件类型错误,请重新上传");
        }
        String encodeData = ImgUtils.toBase64(file);
        if(userMapper.updateById(new User().setId(updateHeadImgBO.getId()).setHeadImg(encodeData)) != 1){
            throw new DaoException("数据库异常");
        }

        return true;
    }

    @Override
    public Boolean updateDescription(UpdateDescriptionBO updateDescriptionBO) {
        if(userMapper.updateById(new User().setId(updateDescriptionBO.getId()).setDescription(updateDescriptionBO.getDescription())) != 1){
            throw new DaoException("数据库异常");
        }
        return true;
    }
}
