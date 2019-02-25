package com.example.visualp.system004.accessor.redis;

import java.util.List;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

@Component
public class RedisAccessor {

  public void sample() {
    //Connecting to Redis server on localhost
    Jedis jedis = new Jedis("localhost");

    jedis.set("counter", "aa");
    jedis.set("another", "10");
    System.out.println(jedis.get("counter"));
    System.out.println(jedis.get("another"));

    jedis.watch("counter");
    Transaction t3 = jedis.multi();
    t3.set("counter", "bb");
    t3.incr("another");
    List<Object> l = t3.exec();

    System.out.println(jedis.get("counter"));
    System.out.println(jedis.get("another"));
  }

}
