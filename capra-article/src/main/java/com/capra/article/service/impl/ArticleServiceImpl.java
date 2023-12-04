package com.capra.article.service.impl;

import com.capra.api.client.FileClient;
import com.capra.api.constant.RemoteResultConstant;
import com.capra.api.domain.request.MinioUploadRequest;
import com.capra.api.result.RemoteResult;
import com.capra.article.constant.ArticleConstant;
import com.capra.article.domain.bo.CreateArticleBO;
import com.capra.article.domain.po.ArticleMetadata;
import com.capra.article.mapper.ArticleMetadataMapper;
import com.capra.article.service.ArticleService;
import com.capra.article.utils.MarkdownFile;
import com.capra.core.constant.MinioBucketConstant;
import com.capra.core.exception.DaoException;
import com.capra.core.exception.ServiceException;
import com.capra.core.utils.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * 文章服务接口实现类
 * @author lql
 * @date 2023/11/15
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private FileClient fileClient;

    @Resource
    private ArticleMetadataMapper articleMetadataMapper;

    @Override
    public Boolean createArticle(CreateArticleBO createArticleBO){
        // 创建空markdown文件
        MultipartFile file = new MarkdownFile(
                createArticleBO.getTitle(), new ByteArrayInputStream(StringUtils.EMPTY.getBytes(StandardCharsets.UTF_8)));

        // 上传文件
        MinioUploadRequest request = new MinioUploadRequest();
        request.setFile(file);
        request.setBucketName(MinioBucketConstant.ARTICLE);
        RemoteResult<String> result = fileClient.upload(request);

        if(result.getCode() != RemoteResultConstant.SUCCESS){
            throw new ServiceException(result.getMessage());
        }

        ArticleMetadata articleMetadata = new ArticleMetadata()
                .setGroupId(createArticleBO.getGroupId())
                .setTitle(createArticleBO.getTitle())
                .setUri(result.getData())
                .setAuthorId(createArticleBO.getAuthorId())
                .setStatus(ArticleConstant.UNRELEASED);
        // 插入数据库
        if(articleMetadataMapper.insert(articleMetadata) != 1){
            throw new DaoException("数据库插入失败");
        }
        return true;
    }
}
