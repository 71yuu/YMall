package com.yuu.ymall.commons.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 缓存管理
 *
 * @author by Yuu
 * @classname RegisCacheManager
 * @date 2019/6/26 8:23
 */
@Component
public class RedisCacheManager {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 指定缓存失效时间
     *
     * @param key 键
     * @param time 失效时间
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据 key 获取过期时间
     *
     * @param key 键
     * @return
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断 key 是否存在
     *
     * @param key 键
     * @return
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 键
     */
    public boolean del(String... key) {
        try {
            if (key != null && key.length > 0) {
                if (key.length == 1) {
                    redisTemplate.delete(key[0]);
                } else {
                    redisTemplate.delete(CollectionUtils.arrayToList(key));
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     * time 要大于 0，如果 time 小于等于 0 ，将设置无限期
     *
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }  else{
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存放单个 Hash
     *
     * @param key Redis key
     * @param hashKey Hash key
     * @param hashValue Hash value
     * @return
     */
    public boolean setHash(String key, String hashKey, String hashValue) {
        try {
            redisTemplate.opsForHash().put(key, hashKey, hashValue);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存放 HashMap 值
     *
     * @param key redis key
     * @param hashKey hash key
     * @param hashValue hash value
     * @return
     */
    public boolean setHashMap(String key, HashMap hashMap) {
        try {
            redisTemplate.opsForHash().putAll(key, hashMap);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断 Hash Key 是否存在
     *
     * @param key Redis 缓存 key
     * @param field Hash key
     * @return
     */
    public boolean hasHashKye(String key, String field) {
        try {
            return redisTemplate.opsForHash().hasKey(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取 Hash 值
     *
     * @param key Redis 缓存 key
     * @param field Hash key
     * @return
     */
    public Object getHash(String key, String field) {
        return key == null || field == null ? null : redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 根据 Redis key, 获取 Hash
     * @param key
     * @return
     */
    public Map<String, Object> getHashByKey(String key) {
        return key == null ? null : redisTemplate.opsForHash().entries(key);
    }

    /**
     * 删除 Hash
     *
     * @param key redis key
     * @param field hash key
     * @return
     */
    public boolean deleteHash(String key, String field) {
        try {
            redisTemplate.opsForHash().delete(key, field);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
