package org.zmz.sb3.newfeatures.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DiEnvProperty {

    public static final Logger LOG= LoggerFactory.getLogger(DiEnvProperty.class);

    @Autowired
    private Environment environment;

    @Value("${redis.host}")
    String redisHost;

    @Value("${mysql.username}")
    String mysqlUser;

    public void printEnvConfig(){
        Arrays.stream(environment.getDefaultProfiles()).forEach(s->LOG.info("{}",s));
        String javaHome=environment.getProperty("JAVA_HOME","NotFound");
        LOG.info("Java_Home : {} , redisHost: {} ,mysqlUser: {}",javaHome,redisHost,mysqlUser);
    }

}
