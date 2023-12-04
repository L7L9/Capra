package com.capra.article.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * 包装markdown文件的包装类
 * @author lql
 * @date 2023/12/04
 */
public class MarkdownFile implements MultipartFile {
    /**
     * 文件类型
     */
    private final static String CONTENT_TYPE = "text/markdown";

    /**
     * markdown文件名后缀
     */
    private final static String FILE_SUFFIX = "md";

    /**
     * 文件名
     */
    private final String name;

    /**
     * 文件内容
     */
    private final InputStream inputStream;

    /**
     * @param name 文件名字\前缀
     * @param inputStream 输入流
     */
    public MarkdownFile(String name, InputStream inputStream) {
        this.name = name + "." + FILE_SUFFIX;
        this.inputStream = inputStream;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return name;
    }

    @Override
    public String getContentType() {
        return CONTENT_TYPE;
    }

    @Override
    public boolean isEmpty() {
        return Objects.nonNull(inputStream);
    }

    @Override
    public long getSize() {
        try {
            return inputStream.available();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getBytes() throws IOException {
        return inputStream.readAllBytes();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {}
}
