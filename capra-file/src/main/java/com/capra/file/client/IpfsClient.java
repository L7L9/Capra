package com.capra.file.client;

import com.capra.file.factory.IpfsFactory;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import jakarta.annotation.PostConstruct;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author lql
 * @date 2023/11/17
 */
@Component
public class IpfsClient {
    @Value("${ipfs.sever-addr}")
    private String addr;

    /**
     * 最大对象数量
     */
    @Value("${ipfs.max-total}")
    private int maxTotal;

    /**
     * 最小空闲连接数
     */
    @Value("${ipfs.min-idle}")
    private int minIdle;

    /**
     * ipfs连接池
     */
    private GenericObjectPool<IPFS> ipfsPool;

    @PostConstruct
    public void init(){
        // 初始化连接池
        IpfsFactory ipfsFactory = new IpfsFactory(addr);
        // 设置连接池参数
        GenericObjectPoolConfig<IPFS> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMinIdle(minIdle);

        ipfsPool = new GenericObjectPool<>(ipfsFactory,poolConfig);
    }

    /**
     * 上传
     * @param file 文件内容
     * @return 文件cid
     */
    public String upload(File file){
        NamedStreamable.FileWrapper fileBytes = new NamedStreamable.FileWrapper(file);

        IPFS ipfs = null;
        MerkleNode result = null;
        try {
            // 从连接池中获取连接
            ipfs = ipfsPool.borrowObject();
            // 上传文件
            result = ipfs.add(fileBytes).get(0);
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败");
        } finally {
            file.delete();
            // 归还ipfs
            ipfsPool.returnObject(ipfs);
        }
        return result.hash.toString();
    }

    /**
     * 下载
     * @param cid 文件cid
     * @return 返回文件字节数组
     */
    public byte[] download(String cid){
        IPFS ipfs = null;
        byte[] result = null;
        try {
            // 从连接池中获取连接
            ipfs = ipfsPool.borrowObject();
            result = ipfs.get(Multihash.fromBase58(cid));
        } catch (Exception e) {
            throw new RuntimeException("文件下载失败");
        } finally {
            // 归还ipfs
            ipfsPool.returnObject(ipfs);
        }
        return result;
    }

    /**
     * 删除
     * @param cid 文件cid
     */
    public void delete(String cid){
        IPFS ipfs = null;
        try {
            // 从连接池中获取连接
            ipfs = ipfsPool.borrowObject();
            ipfs.pin.rm(Multihash.fromBase58(cid));
        } catch (Exception e) {
            throw new RuntimeException("文件删除失败");
        } finally {
            // 归还ipfs
            ipfsPool.returnObject(ipfs);
        }
    }
}
