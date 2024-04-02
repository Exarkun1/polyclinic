package com.propcool.polyclinic.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для перехватчиков http запросов
 * */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface HttpInterceptor {
    @AliasFor(
            annotation = Component.class
    )
    String value() default "";
    String path();
}
