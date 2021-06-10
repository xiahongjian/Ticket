package tech.hongjian.ticket.common.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 标注方法需要获取锁（使用redisson实现分布式可重入锁）
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReentrantLock {
    /**
     * 需要获取锁的名称
     */
    String value();

    /**
     * 超时时间，如果不设置则会自动续期
     */
    int timeout() default -1;

    /**
     * 超时时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
