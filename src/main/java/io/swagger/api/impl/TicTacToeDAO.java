package io.swagger.api.impl;

import io.swagger.api.Redis;
import io.swagger.model.TicTacToeGame;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by akrishniyer on 3/21/17.
 */
public class TicTacToeDAO {

    private JedisPool jedisPool = null;
    private Jedis jedis = null;

    public TicTacToeDAO(){
        //TODO
        Properties prop = new Properties();
        prop.put("redis_url", "127.0.0.1");
        prop.put("redis_port", "6379");

        Redis rs = new Redis();
        jedisPool = rs.createJedisPool(prop);
        jedis = jedisPool.getResource();
    }

    public boolean saveGame(TicTacToeGame gm){
        boolean saveSuccess = false;
        try{
            jedis.set(serialize(gm.getGameId()), serialize(gm));
            saveSuccess = true;
        } catch(Exception e){
            e.printStackTrace();
            //TODO throw new runtime exception
        }
        return saveSuccess;
    }

    public TicTacToeGame findGame(UUID gameId){
        TicTacToeGame retrievedGame = null;
        try {
            retrievedGame = (TicTacToeGame)deserialize(jedis.get(serialize(gameId)));
        }catch (Exception e){
            System.out.println("In findGame");
            e.printStackTrace();
            //TODO throw new runtime exception
        }
        return retrievedGame;
    }

    public TicTacToeGame findGame(String gameId){
        return findGame(java.util.UUID.fromString(gameId));
    }

    public boolean updateGame(TicTacToeGame gm){
        return true;
    }

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }
    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

}

