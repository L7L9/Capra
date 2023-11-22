package com.capra.file.factory;

import io.ipfs.api.IPFS;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * ipfs工厂类,用于实现连接池的创建和放入接口
 *
 * @author lql
 * @date 2023/11/17
 */
public class IpfsFactory extends BasePooledObjectFactory<IPFS> {
    private final String addr;

    public IpfsFactory(String addr){
        this.addr = addr;
    }

    @Override
    public IPFS create(){
        return new IPFS(addr);
    }

    @Override
    public PooledObject<IPFS> wrap(IPFS ipfs) {
        return new DefaultPooledObject<>(ipfs);
    }
}
