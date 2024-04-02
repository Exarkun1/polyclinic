package com.propcool.polyclinic.config;

import com.propcool.polyclinic.annotations.HttpInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    /**
     * Загрузка всех http перехватчиков
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        var interceptors = (Set<HandlerInterceptor>) context.getBean("interceptors");
        interceptors.forEach(interceptor -> {
            try {
                HttpInterceptor annotation = interceptor.getClass().getAnnotation(HttpInterceptor.class);
                registry.addInterceptor(interceptor).addPathPatterns(annotation.path());
            } catch (NullPointerException ignored) {}
        });
    }

    /**
     * Набор всех http перехватчиков
     * */
    @Bean
    public Set<HandlerInterceptor> interceptors(Set<HandlerInterceptor> interceptors) {
        return interceptors.stream().filter(interceptor -> {
            return interceptor.getClass().getAnnotation(HttpInterceptor.class) != null;
        }).collect(Collectors.toSet());
    }

    private final ApplicationContext context;
}
