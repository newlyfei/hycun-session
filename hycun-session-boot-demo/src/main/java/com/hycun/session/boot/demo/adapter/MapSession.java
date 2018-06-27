package com.hycun.session.boot.demo.adapter;

import com.hycun.session.boot.demo.Session;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class MapSession implements Session {
    public static final int DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS = 1800;

    private String id;
    private String originalId;
    private Map<String, Object> sessionAttrs = new HashMap<>();
    private Instant creationTime = Instant.now();
    private Instant lastAccessedTime = this.creationTime;

    /**
     * Defaults to 30 minutes.
     */
    private Duration maxInactiveInterval = Duration.ofSeconds(DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS);

    public MapSession() {
        this(generateId());
    }


    public MapSession(String id) {
        this.id = id;
        this.originalId = id;
    }

    public MapSession(Session session) {
        if (session == null) {
            throw new IllegalArgumentException("session cannot be null");
        }
        this.id = session.getId();
        this.originalId = this.id;
        this.sessionAttrs = new HashMap<>(
                session.getAttributeNames().size());
        for (String attrName : session.getAttributeNames()) {
            Object attrValue = session.getAttribute(attrName);
            if (attrValue != null) {
                this.sessionAttrs.put(attrName, attrValue);
            }
        }
        this.lastAccessedTime = session.getLastAccessedTime();
        this.creationTime = session.getCreationTime();
        this.maxInactiveInterval = session.getMaxInactiveInterval();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String changeSessionId() {
        this.id = generateId();
        return this.id;
    }

    @Override
    public <T> T getAttribute(String attributeName) {
        return (T)this.sessionAttrs.get(attributeName);
    }

    @Override
    public Set<String> getAttributeNames() {
        return this.sessionAttrs.keySet();
    }

    @Override
    public void setAttribute(String attributeName, Object attributeValue) {
        this.sessionAttrs.put(attributeName,attributeValue);
    }

    @Override
    public void removeAttribute(String attributeName) {
        this.sessionAttrs.remove(attributeName);
    }

    @Override
    public Instant getCreationTime() {
        return this.creationTime;
    }

    @Override
    public void setLastAccessedTime(Instant lastAccessedTime) {
        this.lastAccessedTime=lastAccessedTime;
    }

    @Override
    public Instant getLastAccessedTime() {
        return this.lastAccessedTime;
    }

    @Override
    public void setMaxInactiveInterval(Duration interval) {
        this.maxInactiveInterval=interval;
    }

    @Override
    public Duration getMaxInactiveInterval() {
        return this.maxInactiveInterval;
    }

    @Override
    public boolean isExpired() {
        if (this.maxInactiveInterval.isNegative()) {
            return false;
        }
        return Instant.now().minus(this.maxInactiveInterval).compareTo(this.lastAccessedTime) >= 0;
    }

    private static String generateId() {
        return UUID.randomUUID().toString();
    }
}
