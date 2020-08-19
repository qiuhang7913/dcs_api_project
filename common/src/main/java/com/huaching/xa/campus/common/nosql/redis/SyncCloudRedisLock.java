package com.huaching.xa.campus.common.nosql.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * <p>分布式锁</p>
 * <p>基于redis实现</p>
 *
 * @author qiuhang
 * @version v1.0 2020/07/24
 */
@Service
public class SyncCloudRedisLock {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     *<p>分布式上锁/p>
     *
     * @param key:redis key
     * @param key:redis expire 过期时间
     * @return Boolean
     */
    public Boolean lockEnable(String key, long expire) {
        //这是将当前线程的名字置为key的value值,表明该锁被谁拿到
        String keyValue = Thread.currentThread().getName();

        //1,这是StringRedisTemplate在set key的同时增加了过期时间，防止死锁。保证了原子性。
        //2,setIfAbsent该方法如果该key不存在时候，设置值进去后，返回true;若是已经存在，则返回false;
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(key, keyValue, expire, TimeUnit.SECONDS);
        Long surplusTime = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        if (null != aBoolean && !aBoolean) {
            logger.info("该线程【{}】已被加锁,该key【{}】剩余过期时间【{}】秒", keyValue, key, surplusTime);
            return false;
        }
        logger.info("该线程【{}】加锁成功,该key【{}】剩余过期时间【{}】秒", keyValue, key, surplusTime);
        return true;
    }


    /**
     *<p>分布式解锁/p>
     *
     * @param key:redis key
     * @return Boolean
     */
    public Boolean lockUnable(String key) {
        String keyValue = Thread.currentThread().getName();
        //key和value不一致时，返回：【0】
        //key和value一致时，返回：【1】
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptText("if redis.call('get', KEYS[1]) == KEYS[2] then return redis.call('del', KEYS[1]) else return 0 end");

        Long execute = stringRedisTemplate.execute(redisScript, Arrays.asList(key, keyValue));
        if (null != execute && execute == 1){
            logger.info("该key【{}】解锁成功", key);
            return true;
        }else{
            logger.info("该key【{}】解锁失败", key);
            return false;
        }
    }
}
