package com.hycun.session.boot.demo;

public interface SessionRepository<S extends Session> {

    S createSession();

    S findById(String id);

    void save(S session);

    void deleteById(String id);

}
