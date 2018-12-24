package cn.showurs.blog.user.common.annotation;

import java.lang.annotation.*;

/**
 * Created by CJ on 2018/12/23 22:07.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}
