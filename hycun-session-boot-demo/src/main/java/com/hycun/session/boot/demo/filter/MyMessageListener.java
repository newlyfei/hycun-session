package com.hycun.session.boot.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.Nullable;

public class MyMessageListener {
    private static Logger logger= LoggerFactory.getLogger(MyMessageListener.class);

    public void receiveMessage(String message) {
        logger.info("监听到的消息={}",message);
    }
}
