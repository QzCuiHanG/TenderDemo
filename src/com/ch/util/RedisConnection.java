package com.ch.util;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
/**
 * Redis Ĭ���ǵ�������ʹ�õġ�
 *�������ϴ�ʱ��Ҫshard����������������ʱ��Ҫ��ShardedJedis�� 
 *ShardedJedis�ǻ���һ���Թ�ϣ�㷨ʵ�ֵķֲ�ʽRedis��Ⱥ�ͻ���
 * @author Administrator *
 */
public class RedisConnection {
	 public Jedis jedis;//����Ƭ��ͻ�������
	 public JedisPool jedisPool;//����Ƭ���ӳ�
	 public ShardedJedis shardedJedis;//��Ƭ��ͻ�������
	 public ShardedJedisPool shardedJedisPool;//��Ƭ���ӳ�
	     /**
	      * Redis Ĭ���ǵ�������ʹ�õġ� �������ϴ�ʱ��Ҫshard�������������
	      * ���ʱ��Ҫ��ShardedJedis�� ShardedJedis�ǻ���һ���Թ�ϣ�㷨ʵ
	      * �ֵķֲ�ʽRedis��Ⱥ�ͻ���
	      */
	    public RedisConnection() 
	    { 
	        initialPool(); 
	        initialShardedPool(); 
	        shardedJedis = shardedJedisPool.getResource(); 
	        jedis = jedisPool.getResource(); 
	        
	        
	    } 
	 
	    /**
	     * ��ʼ������Ƭ��
	     */
	    public void initialPool() 
	    { 
	        // �ػ������� 
	        JedisPoolConfig config = new JedisPoolConfig(); 
	        config.setMaxTotal(20); //�������������
	        config.setMaxIdle(5);  // ������������
	        config.setMaxWaitMillis(1000l); 
	        config.setTestOnBorrow(false); 
	        jedisPool = new JedisPool(config,"127.0.0.1",6379);
	    }
	    
	    /** 
	     * ��ʼ����Ƭ�� 
	     */ 
	    public void initialShardedPool() 
	    { 
	        // �ػ������� 
	        JedisPoolConfig config = new JedisPoolConfig(); 
	        config.setMaxTotal(20); 
	        config.setMaxIdle(5); 
	        config.setMaxWaitMillis(10001); 
	        config.setTestOnBorrow(false); 
	        // slave���� 
	        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(); 
	        shards.add(new JedisShardInfo("127.0.0.1", 6379, "master")); 
	        // ����� 
	        shardedJedisPool = new ShardedJedisPool(config, shards); 
	    } 

}
