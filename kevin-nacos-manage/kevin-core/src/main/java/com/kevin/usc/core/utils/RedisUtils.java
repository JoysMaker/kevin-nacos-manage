package com.kevin.usc.core.utils;

import java.io.Serializable;
import java.io.StringReader;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

/**
 * redis 工具类
 *
 * @author jianglz
 * @since 2018/3/2.
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;


    private Lock lock = new ReentrantLock();//上锁。基于底层IO阻塞考虑  如果分布式的话，就是用分式式的锁


    private String getMergeKey(String cacheGroup, String key) {

        return cacheGroup + ":" + key;
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存(默认2小时)
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            //redisTemplate.expire(key,7200L, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存(设置过期时间)
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean set(String key, Object value, Long expireTime, TimeUnit times) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, times);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param expireTime
     */
    public void expire(String key, Long expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    public void expire(String key, Long expireTime, TimeUnit times) {
        redisTemplate.expire(key, expireTime, times);
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }


    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 递增 steps
     *
     * @param key
     * @param step
     * @return
     */
    public Long incrementByLongWithStep(String key, Long step) {
        return redisTemplate.opsForValue().increment(key, step);
    }

    /**
     * 递增 1 step
     *
     * @param key
     * @return
     */
    public Long incrementByLongOneStep(String key) {
        return redisTemplate.opsForValue().increment(key, 1L);
    }


    public void del(String key) {

        redisTemplate.delete(key);

    }

    /**
     * 判断是否过期
     *
     * @param key
     * @return
     */
    public boolean isExpired(String key) {
        Long expire = getExpire(key);
        if (expire != null && expire > 2) {
            return false;
        }
        return true;
    }

    /**
     * 获取过期时间
     *
     * @param key
     * @return
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    //***************************对List类型的操作**********************************//

    public <T> void pushFromHead(final String key, T value) {

        BoundListOperations boundListOps = redisTemplate.boundListOps(key);
        boundListOps.leftPush(value);
    }

    public <T> void pushCacheValFromHead(final String cacheGroup, final String key, T value) {
        String cacheKey = getMergeKey(cacheGroup, key);
        pushFromHead(cacheKey, value);

    }

    public <T> void pushListFromHead(final String key, T... values) {

        BoundListOperations boundListOps = redisTemplate.boundListOps(key);
        boundListOps.leftPushAll(values);
    }

    public <T> void pushFromTail(final String key, T value) {

        BoundListOperations boundListOps = redisTemplate.boundListOps(key);
        boundListOps.rightPush(value);
    }

    public <T> void pushCacheValFromTail(final String cacheGroup, final String key, T value) {
        String cacheKey = getMergeKey(cacheGroup, key);
        pushFromTail(cacheKey, value);
    }

    public <T> void pushListFromTail(final String key, T... values) {

        BoundListOperations boundListOps = redisTemplate.boundListOps(key);
        boundListOps.rightPushAll(values);
    }

    public void removeFromHead(final String key) {

        BoundListOperations boundListOps = redisTemplate.boundListOps(key);
        boundListOps.leftPop();
    }


    public void removeFromTail(final String key) {

        BoundListOperations boundListOps = redisTemplate.boundListOps(key);
        boundListOps.rightPop();
    }


    public <T> T getFromHead(final String key) {

        BoundListOperations<Serializable, T> boundListOps = redisTemplate.boundListOps(key);

        try {

            lock.lockInterruptibly();

            if (queueSize(key) == 0) {

                return null;
            }
            T value = boundListOps.leftPop();
            return value;
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            lock.unlock();
        }

        return null;
    }

    public <T> T getcacheValFromHead(final String cacheGroup, final String key) {

        String cacheKey = getMergeKey(cacheGroup, key);
        return getFromHead(cacheKey);
    }

    public <T> T getFromTail(final String key) {

        BoundListOperations<Serializable, T> boundListOps = redisTemplate.boundListOps(key);

        try {

            lock.lockInterruptibly();

            if (queueSize(key) == 0) {

                return null;
            }
            T value = boundListOps.rightPop();
            return value;
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            lock.unlock();
        }

        return null;
    }

    public <T> T getcacheValFromTail(final String cacheGroup, final String key) {

        String cacheKey = getMergeKey(cacheGroup, key);
        return getFromTail(cacheKey);
    }

    public Long queueSize(final String key) {

        BoundListOperations boundListOps = redisTemplate.boundListOps(key);
        return boundListOps.size();
    }

    //***************************对hash类型的操作**********************************//

    /**
     * @param cacheGroup
     * @param key
     * @param val
     * @author 小K
     * @time 2018年8月7日
     * @desc
     */
    public void setCacheHsetKeyVal(String cacheGroup, String key, String field, String val) {
        String cacheKey = getMergeKey(cacheGroup, key);
        // 根据缓存名称把key值也缓存起来
        sadd(cacheGroup, key);
        hset(cacheKey, field, val);

    }

    public void setCacheHmsetKeyVal(String cacheGroup, String key, Map<String, String> map) {

        String cacheKey = getMergeKey(cacheGroup, key);

        hmset(cacheKey, map);
        // 根据缓存名称把key值也缓存起来
        sadd(cacheGroup, key);
    }


    public void hmset(String key, Map map) {

        BoundHashOperations boundHashOps = redisTemplate.boundHashOps(key);
        boundHashOps.putAll(map);
    }

    public Map hgetCacheKeyVal(String cacheGroup, String key) {
        String cacheKey = getMergeKey(cacheGroup, key);
        return hgetAll(cacheKey);
    }


    /**
     * @param cacheGroup
     * @param key
     * @param field
     * @author 小K
     * @time 2017年12月8日
     * @desc
     */
    public String hgetCacheKeyFieldVal(String cacheGroup, String key, String field) {
        String cacheKey = getMergeKey(cacheGroup, key);
        return hget(cacheKey, field);
    }

    /**
     * @param cacheGroup
     * @param key
     * @author 小K
     * @time 2017年12月8日
     * @desc 清除hash缓存
     */
    public void hdelCacheKeyVal(String cacheGroup, String key, String... fields) {
        String cacheKey = getMergeKey(cacheGroup, key);
        hdel(cacheKey, fields);
        // 删除缓存名称中的key
        srem(cacheGroup, key);
    }


    public String hget(String key, String field) {

        BoundHashOperations boundHashOps = redisTemplate.boundHashOps(key);

        return String.valueOf(boundHashOps.get(field));
    }

    private Map hgetAll(String key) {

        BoundHashOperations boundHashOps = redisTemplate.boundHashOps(key);

        return boundHashOps.entries();

    }

    public void hset(String key, String field, String value) {

        BoundHashOperations boundHashOps = redisTemplate.boundHashOps(key);

        boundHashOps.put(field, value);
    }


    public Long hdel(String key, String... fields) {

        BoundHashOperations boundHashOps = redisTemplate.boundHashOps(key);

        return boundHashOps.delete(fields);
    }
    //***************************对set类型的操作**********************************//


    public void removeCacheKeyVal(String cacheGroup, String key) {
        String cacheKey = getMergeKey(cacheGroup, key);
        del(cacheKey);
        // 删除缓存名称中的key
        srem(cacheGroup, key);
    }

    public long sadd(String key, String... members) {

        BoundSetOperations boundSetOps = redisTemplate.boundSetOps(key);

        return boundSetOps.add(members);
    }

    public void srem(String key, String... field) {

        BoundSetOperations boundSetOps = redisTemplate.boundSetOps(key);

        boundSetOps.remove(field);
    }

  // bitmap相关方法

  public Boolean setBit(String key, Integer index, Boolean tag) {
    return (Boolean)redisTemplate.execute((RedisCallback<Boolean>) con -> con.setBit(key.getBytes(), index, tag));
  }

  public Boolean getBit(String key, Integer index) {
    return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) con -> con.getBit(key.getBytes(), index));
  }

  /**
   * 统计bitmap中，value为1的个数，非常适用于统计网站的每日活跃用户数等类似的场景
   *
   * @param key
   * @return
   */
  public Long bitCount(String key) {
    return (Long) redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
  }

  public Long bitCount(String key, int start, int end) {
    return (Long) redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes(), start, end));
  }
 //op: AND, OR, XOR, NOT;
  public Long bitOp(RedisStringCommands.BitOperation op, String saveKey, String... desKey) {
    byte[][] bytes = new byte[desKey.length][];
    for (int i = 0; i < desKey.length; i++) {
      bytes[i] = desKey[i].getBytes();
    }
    return (Long) redisTemplate.execute((RedisCallback<Long>) con -> con.bitOp(op, saveKey.getBytes(), bytes));
  }

}
