package com.baidu.bce.web.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.servlet.Servlet;
import java.util.Properties;

/**
 * TODO: remove this class when spring-boot-starter-velocity 1.1.0 ready
 */
@Configuration
@ConditionalOnClass({Servlet.class})
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@ConditionalOnExpression("${bce_plat_web_framework.has_velocity_template:true}")
public class VelocityConfiguration implements EnvironmentAware {

    private RelaxedPropertyResolver environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = new RelaxedPropertyResolver(environment,
                "spring.velocity.");
    }

    @Bean
    public VelocityConfigurer velocityConfig() {
        VelocityConfigurer config = new VelocityConfigurer();
        Properties props = new Properties();
        props.setProperty("input.encoding", "UTF-8");
        props.setProperty("output.encoding", "UTF-8");
        config.setVelocityProperties(props);
        return config;
    }

    @Bean
    public VelocityViewResolver velocityViewResolver() {
        VelocityViewResolver resolver = new VelocityViewResolver();
        resolver.setSuffix(this.environment.getProperty("suffix", ".html"));
        resolver.setPrefix(this.environment.getProperty("prefix", "/templates/"));
        resolver.setCache(Boolean.getBoolean(this.environment.getProperty("cache", "true")));
        resolver.setRequestContextAttribute(this.environment.getProperty("requestContext", "requestContext"));
        resolver.setNumberToolAttribute(this.environment.getProperty("numberTool", "numberTool"));
        resolver.setDateToolAttribute(this.environment.getProperty("dateTool", "dateTool"));
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 20);
        return resolver;
    }
}

