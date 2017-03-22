package io.swagger.api;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;


public class Redis {

    public static JedisPool jedisPool = null;
    public static Jedis jedis;

    public JedisPool getJedisPool(Properties prop) {
        if (jedisPool != null) {
            return jedisPool;
        } else {
            return createJedisPool(prop);
        }
    }

    public JedisPool createJedisPool(Properties prop) {
        try {

            String redisURL = (String)prop.get("redis_url");
            int redisPort = Integer.parseInt((String) (prop.get("redis_port")));

            jedisPool = new JedisPool(new JedisPoolConfig(), redisURL,
                    redisPort, 1800);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jedisPool;
    }

    public void closeConnection() {
        try {
            jedisPool.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


