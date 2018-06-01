package com.xyibq.lanxj.m.forum.common.redis;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.stereotype.Component;

@Component
public class RedisService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServer.class);

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    public void del(String key) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            connection.del(key.getBytes("UTF-8"));
            LOGGER.info("redis删除数据:key={}成功!", key);
        } catch (Exception e) {
            LOGGER.error("redis删除数据出错:key={}||error:", key, e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void set(String key, String value) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            connection.set(key.getBytes("UTF-8"), value.getBytes("UTF-8"));
            LOGGER.info("redis保存数据:key={}|value={}", key, value);
        } catch (Exception e) {
            LOGGER.error("redis保存数据出错:key={}|value={}|error:", key, value, e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public String get(String key) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            byte[] bytes = connection.get(key.getBytes("UTF-8"));
            if (null == bytes) {
                LOGGER.info("redis缓存没有key={}", key);
            } else {
                return new String(bytes, "UTF-8");
            }
        } catch (Exception e) {
            LOGGER.error("redis查询数据出错:key={}|error:", key, e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    public void expire(String key, long time) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            connection.expire(key.getBytes("UTF-8"),time);
            LOGGER.info("redis设置数据存活时间:key={}|time={}", key, time);
        } catch (Exception e) {
            LOGGER.error("redis设置数据存活时间:key={}|time={}|error:", key, time, e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public <E> void setEntity(String key, E entity) {
        String json = JSON.toJSONString(entity);
        set(key, json);
    }
}