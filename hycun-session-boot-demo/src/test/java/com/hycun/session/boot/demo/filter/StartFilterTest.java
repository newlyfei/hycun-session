package com.hycun.session.boot.demo.filter;

import com.hycun.session.boot.demo.Session;
import com.hycun.session.boot.demo.SessionRepository;
import com.hycun.session.boot.demo.adapter.RedisSession;
import com.hycun.session.boot.demo.redis.RedisSessionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServlet;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StartFilterTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    private StartFilter filter;
    private MockFilterChain chain;

    private Session session;
    private SessionRepository<RedisSession> sessionRepository;

    @Before
    public void setup() throws Exception {
        this.session=new RedisSession();
        this.sessionRepository=new RedisSessionRepository();

        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
        this.chain = new MockFilterChain();
        this.filter = new StartFilter();
    }

    @Test
    public void doFilter() throws Exception {
        this.chain =new MockFilterChain(new HttpServlet(){},new StartFilter(){

        });

        this.filter.doFilter(this.request, this.response, this.chain);
    }

}