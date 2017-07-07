package com.blueberry.multipart.config;


import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


/**
 * Created by Administrator on 7/6/2017.
 */
@Configuration
public class Config implements EnvironmentAware {
    private Environment environment;

    @Override
    public void setEnvironment(org.springframework.core.env.Environment environment) {
        this.environment = environment;
    }

}
