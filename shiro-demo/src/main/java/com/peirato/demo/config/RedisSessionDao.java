package com.peirato.demo.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author peirato.
 * @date 2019/5/2323:41
 * @description:
 */
public class RedisSessionDao extends AbstractSessionDAO {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final String KEY = "shiroSessionMap";

    @Resource
    private RedisTemplate<String,Session> redisTemplate;
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        LOGGER.info("create session id="+sessionId);
        assignSessionId(session, sessionId);
        redisTemplate.opsForHash().put(KEY,sessionId,session);
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        return null;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {

    }

    @Override
    public void delete(Session session) {

    }

    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }
}
