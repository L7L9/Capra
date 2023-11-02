package com.capra.redis.service;

/**
 * redis服务接口
 *
 * @author lql
 * @date 2023/11/02
 */
public interface RedisService {
    /**
     * 设置属性
     *
     * @param key 键
     * @param value 值
     */
    void set(String key,Object value);

    /**
     * 设置属性以及过期时间
     *
     * @param key 键
     * @param value 值
     * @param ttl 过期时间
     */
    void set(String key,Object value,long ttl);

    /**
     * 获取键对应的值
     * @param key 键
     * @return 值
     */
    Object get(String key);

    /**
     * 删除键
     * @param key 键
     * @return 删除成功返回true
     */
    Boolean delete(String key);

    /**
     * 设置过期时间
     * @param key 键
     * @param ttl 过期时间
     * @return 成功返回true
     */
    Boolean setExpire(String key,long ttl);

    /**
     * 获取过期时间
     * @param key 键
     * @return 过期时间
     */
    Long getExpire(String key);

    /**
     * 判断是否有这个属性
     * @param key 键
     * @return 有返回true;否则返回false
     */
    Boolean hasKey(String key);
}
