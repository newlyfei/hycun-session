package com.hycun.session.boot.demo.adapter;

import com.hycun.session.boot.demo.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

@Component
public class RedisSession implements Session {
    private static Logger logger= LoggerFactory.getLogger(RedisSession.class);

    private MapSession mapSession;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private RedisTemplate<String, String> template;

    public void test(){
        template.opsForValue().set("tmp_test","sssssssssss");

        String value=template.opsForValue().get("tmp_test");

        logger.info("read tmp_test:{}",value);

        template.convertAndSend("tmp_message","hello world!");
    }

    public RedisSession(){
        this(new MapSession());

    }

    public RedisSession(MapSession mapSession){
        this.mapSession=mapSession;
    }

    @Override
    public String getId() {
        return this.mapSession.getId();
    }

    @Override
    public String changeSessionId() {
        return this.mapSession.changeSessionId();
    }

    @Override
    public <T> T getAttribute(String attributeName) {
        return this.mapSession.getAttribute(attributeName);
    }

    @Override
    public Set<String> getAttributeNames() {
        return this.mapSession.getAttributeNames();
    }

    @Override
    public void setAttribute(String attributeName, Object attributeValue) {
        this.mapSession.setAttribute(attributeName,attributeValue);
    }

    @Override
    public void removeAttribute(String attributeName) {
        this.mapSession.removeAttribute(attributeName);
    }

    @Override
    public Instant getCreationTime() {
        return this.mapSession.getCreationTime();
    }

    @Override
    public void setLastAccessedTime(Instant lastAccessedTime) {
        this.mapSession.setLastAccessedTime(lastAccessedTime);
    }

    @Override
    public Instant getLastAccessedTime() {
        return this.mapSession.getLastAccessedTime();
    }

    @Override
    public void setMaxInactiveInterval(Duration interval) {
        this.mapSession.setMaxInactiveInterval(interval);
    }

    @Override
    public Duration getMaxInactiveInterval() {
        return this.mapSession.getMaxInactiveInterval();
    }

    @Override
    public boolean isExpired() {
        return this.mapSession.isExpired();
    }
}
