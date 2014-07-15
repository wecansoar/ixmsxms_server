package net.flower.ixmsxms_server.service;

/**
 * Created by hyeyoungkang on 2014. 7. 15..
 */

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "classpath:ixmsxms_server-servlet.xml"
        , "classpath:applicationContext.xml"
        , "classpath:persistenceContext.xml"
}
)
public abstract class TestCore {
}
