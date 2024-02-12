package com.queomedia;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@Transactional
@ContextConfiguration(SpringTestContext.APPLICATION)
public class SpringContextSpringTest {

    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void testSpringContextLoad() {
        Assertions.assertNotNull(this.applicationContext, "application context expected");
    }
}
