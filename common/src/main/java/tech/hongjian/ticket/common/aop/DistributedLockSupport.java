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
import java.util.function.Function;

/**
 * Created by xiahongjian on 2021/6/10.
 */
@Aspect
@Component
public class DistributedLockSupport {
    @Autowired
    private RedissonClient redissonClient;

    @Around("@annotation(readLock)")
    public Object processReadLock(ProceedingJoinPoint joinPoint, ReadLock readLock) throws Throwable {
        String[] lockNames = readLock.value();
        int timeout = readLock.timeout();
        TimeUnit timeUnit = readLock.timeUnit();
        return process(joinPoint, lockNames, timeout, timeUnit, lockName -> redissonClient.getReadWriteLock(lockName).readLock());
    }


    @Around("@annotation(writeLock)")
    public Object processWriteLock(ProceedingJoinPoint joinPoint, WriteLock writeLock) throws Throwable {
        String[] lockNames = writeLock.value();
        int timeout = writeLock.timeout();
        TimeUnit timeUnit = writeLock.timeUnit();
        return process(joinPoint, lockNames, timeout, timeUnit, lockName -> redissonClient.getReadWriteLock(lockName).writeLock());
    }

    @Around("@annotation(reentrantLock)")
    public Object processWriteLock(ProceedingJoinPoint joinPoint, ReentrantLock reentrantLock) throws Throwable {
        String[] lockNames = reentrantLock.value();
        int timeout = reentrantLock.timeout();
        TimeUnit timeUnit = reentrantLock.timeUnit();
        return process(joinPoint, lockNames, timeout, timeUnit, lock -> redissonClient.getLock(lock));
    }

    private Object process(ProceedingJoinPoint joinPoint, String[] lockNames, int timeout, TimeUnit timeUnit, Function<String, RLock> lockSupplier) throws Throwable {
        RLock[] locks = new RLock[lockNames.length];
        for (int i = 0; i < lockNames.length; i++) {
            String lockName = lockNames[i];
            RLock lock = lockSupplier.apply(lockName);
            if (timeout == -1) {
                lock.lock();
            } else {
                lock.lock(timeout, timeUnit);
            }
            locks[i] = lock;
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            for (RLock lock : locks) {
                lock.unlock();
            }
        }
    }
}
