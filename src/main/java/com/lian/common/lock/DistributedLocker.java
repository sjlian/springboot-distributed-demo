package com.lian.common.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @Author lian
 * @Date 2019-07-29
 */

public interface DistributedLocker {
    Lock lock(String lockKey);

    Lock lock(String lockKey, int timeout);

    Lock lock(String lockKey, TimeUnit unit, int timeout);

    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    void unlock(String lockKey);

    void unlock(Lock lock);
}
