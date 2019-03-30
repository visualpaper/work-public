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

  public void zCommands() {
    //Connecting to Redis server on localhost
    Jedis jedis = new Jedis("localhost");

    // 同じ Score の場合、メンバーの辞書順になる。
    jedis.zadd("sortHashKey", 1, "aaa");
    jedis.zadd("sortHashKey", 1, "bbb");

    // 同じメンバーの場合、スコアが更新される。
    jedis.zadd("sortHashKey", 2, "aaa");

    jedis.zadd("sortHashKey", 3, "bbb");

    // メンバーの Set が、Score の昇順になって返却される。
    // (この時点でコールすると、"aaa" のスコアの方が小さいので "aaa", "bbb" の順になる)
    jedis.zrange("sortHashKey", 0, -1);

    // MIN/MAX 値が分かれば全部取得できる。
    jedis.zcount("sortHashKey", Long.MIN_VALUE, Long.MAX_VALUE);

    // 存在しない Key の場合は Null
    jedis.zcount("sortHashKey2", Long.MIN_VALUE, Long.MAX_VALUE);

    jedis.del("sortHashKey");
  }
}
