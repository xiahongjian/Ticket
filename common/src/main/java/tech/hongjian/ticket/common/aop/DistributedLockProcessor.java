package tech.hongjian.ticket.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.hongjian.ticket.common.anno.ReadLock;
import tech.hongjian.ticket.common.anno.ReentrantLock;
import tech.hongjian.ticket.common.anno.WriteLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by xiahongjian on 2021/6/10.
 */
@Aspect
@Component
public class DistributedLockProcessor {
    @Autowired
    private RedissonClient redissonClient;

    @Around("@annotation(readLock)")
    public Object processReadLock(ProceedingJoinPoint joinPoint, ReadLock readLock) throws Throwable {
        String lockName = readLock.value();
        int timeout = readLock.timeout();
        TimeUnit timeUnit = readLock.timeUnit();
        RLock lock = redissonClient.getLock(lockName);
        if (timeout == -1) {
            lock.lock();
        } else {
            lock.lock(timeout, timeUnit);
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            lock.unlock();
        }
    }

    @Around("@annotation(writeLock)")
    public Object processWriteLock(ProceedingJoinPoint joinPoint, WriteLock writeLock) throws Throwable {
        String lockName = writeLock.value();
        int timeout = writeLock.timeout();
        TimeUnit timeUnit = writeLock.timeUnit();
        RLock lock = redissonClient.getLock(lockName);
        if (timeout == -1) {
            lock.lock();
        } else {
            lock.lock(timeout, timeUnit);
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            lock.unlock();
        }
    }

    @Around("@annotation(reentrantLock)")
    public Object processWriteLock(ProceedingJoinPoint joinPoint, ReentrantLock reentrantLock) throws Throwable {
        String lockName = reentrantLock.value();
        int timeout = reentrantLock.timeout();
        TimeUnit timeUnit = reentrantLock.timeUnit();
        RLock lock = redissonClient.getLock(lockName);
        if (timeout == -1) {
            lock.lock();
        } else {
            lock.lock(timeout, timeUnit);
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            lock.unlock();
        }
    }
}
