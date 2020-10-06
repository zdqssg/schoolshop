package cn.tedu.schoolshop.api.user.Utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { Redis的工具类}
 * @email zdq247209@163.com
 * @date 2020/9/15 11:13
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    /**
     * 遍历集合向向Redis中的某个List
     *
     * @param key
     * @param list
     */
    public void rightPushList(String key, List<?> list) {
        delete(key);
        for (Object value : list) {
            rightPush(key, (Serializable) value);
        }
    }

    /**
     * Redis的某个 key的Value清空
     *
     * @param key
     * @return
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 向Redis中添加单个数据
     *
     * @param key
     * @param value
     */
    public void setValue(String key, Serializable value) {
        ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        ops.set(key, value);
    }

    /**
     * 根据key向Redis中获取某个数据
     *
     * @param key
     */
    public Serializable getValue(String key) {
        ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        Serializable value = ops.get(key);
        return value;
    }

    /**
     * 向Redis中添加Hash
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void setHash(String key, String hashKey, Object value) {
        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * 向Redis中得到Hash
     *
     * @param key
     * @param hashKey
     */
    public Object getHash(String key, String hashKey) {
        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * 向Redis中的某个List添加集合元素
     *
     * @param key
     * @param value
     * @return
     */
    public Long rightPush(String key, Serializable value) {
        ListOperations<String, Serializable> forList = redisTemplate.opsForList();
        return forList.rightPush(key, value);
    }


    /**
     * 获取Redis中某List的集合片段
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Serializable> listRange(String key, Long start, Long end) {
        ListOperations<String, Serializable> forList = redisTemplate.opsForList();
        return forList.range(key, start, end);
    }

    /**
     * 获取Redis中某List的集合
     *
     * @param key
     * @return
     */
    public List<Serializable> listRange(String key) {
        ListOperations<String, Serializable> forList = redisTemplate.opsForList();
        long start = 0;
        long end = forList.size(key);
        return forList.range(key, start, end);
    }

}
