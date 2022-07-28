package com.resell.person.configs;

import com.resell.person.services.OauthService;
import com.resell.person.services.RabbitSessionToken;
import com.resell.person.services.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration extends WebMvcConfigurationSupport {

    private final Interceptor inperceptor;
//    private final UserSessionService userSessionService;
//    private final OauthService oauthService;
    private final RabbitSessionToken rabbitSessionToken;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor(rabbitSessionToken))
                .addPathPatterns("/**");
    }
}
