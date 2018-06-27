package com.hycun.session.boot.demo.redis;

import com.hycun.session.boot.demo.Session;
import com.hycun.session.boot.demo.SessionRepository;
import com.hycun.session.boot.demo.adapter.RedisSession;

public class RedisSessionRepository<S extends Session> implements SessionRepository<RedisSession> {

    @Override
    public RedisSession createSession() {
        RedisSession redisSession=new RedisSession();
        return redisSession;
    }

    @Override
    public RedisSession findById(String id) {
        return null;
    }

    @Override
    public void save(RedisSession session) {

    }

    @Override
    public void deleteById(String id) {

    }
}
