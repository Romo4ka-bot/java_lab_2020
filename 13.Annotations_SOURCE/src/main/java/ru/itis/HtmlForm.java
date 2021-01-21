package ru.itis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Roman Leontev
 * 00:15 09.12.2020
 * group 11-905
 */


@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface HtmlForm {
    String method() default "get";
    String action() default "/";
}
