package tech.hongjian.ticket.common.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 用于标准方法需要获取“读”锁（使用redisson实现分布式读写错）
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReadLock {

    /**
     * 需要获取锁的名称
     */
    String value();

    /**
     * 超时时间，不设置则会自动续期
     */
    int timeout() default -1;

    /**
     * 超时时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
