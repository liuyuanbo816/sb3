package io.jcz.config;


import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Properties;

public class DruidConfig {
    private long connectCount;
    private long closeCount;
    private volatile long connectErrorCount;
    private long recycleCount;
    private long removeAbandonedCount;
    private long notEmptyWaitCount;
    private long notEmptySignalCount;
    private long notEmptyWaitNanos;
    private int keepAliveCheckCount;
    private int activePeak;
    private long activePeakTime;
    private int poolingPeak;
    private long poolingPeakTime;

    public static final int DEFAULT_INITIAL_SIZE = 0;
    public static final int DEFAULT_MAX_ACTIVE_SIZE = 8;
    public static final int DEFAULT_MAX_IDLE = 8;
    public static final int DEFAULT_MIN_IDLE = 0;
    public static final int DEFAULT_MAX_WAIT = -1;

    protected volatile int initialSize = DEFAULT_INITIAL_SIZE;
    protected volatile int maxActive = DEFAULT_MAX_ACTIVE_SIZE;
    protected volatile int minIdle = DEFAULT_MIN_IDLE;
    protected volatile int maxIdle = DEFAULT_MAX_IDLE;
    protected volatile long maxWait = DEFAULT_MAX_WAIT;
    protected int notFullTimeoutRetryCount;

    public Properties toProperties() {
        Properties properties = new Properties();
        Field[] declaredFields = this.getClass().getDeclaredFields();
        try {
            for (Field field : declaredFields) {
                Optional.ofNullable(field.get(this)).
                        ifPresent(v -> properties.setProperty(field.getName(), v.toString()));
            }
        } catch (Exception e) {
            throw new RuntimeException("DruidConfig toProperties error, e=", e);
        }
        return properties;
    }

    public long getConnectCount() {
        return connectCount;
    }

    public void setConnectCount(long connectCount) {
        this.connectCount = connectCount;
    }

    public long getCloseCount() {
        return closeCount;
    }

    public void setCloseCount(long closeCount) {
        this.closeCount = closeCount;
    }

    public long getConnectErrorCount() {
        return connectErrorCount;
    }

    public void setConnectErrorCount(long connectErrorCount) {
        this.connectErrorCount = connectErrorCount;
    }

    public long getRecycleCount() {
        return recycleCount;
    }

    public void setRecycleCount(long recycleCount) {
        this.recycleCount = recycleCount;
    }

    public long getRemoveAbandonedCount() {
        return removeAbandonedCount;
    }

    public void setRemoveAbandonedCount(long removeAbandonedCount) {
        this.removeAbandonedCount = removeAbandonedCount;
    }

    public long getNotEmptyWaitCount() {
        return notEmptyWaitCount;
    }

    public void setNotEmptyWaitCount(long notEmptyWaitCount) {
        this.notEmptyWaitCount = notEmptyWaitCount;
    }

    public long getNotEmptySignalCount() {
        return notEmptySignalCount;
    }

    public void setNotEmptySignalCount(long notEmptySignalCount) {
        this.notEmptySignalCount = notEmptySignalCount;
    }

    public long getNotEmptyWaitNanos() {
        return notEmptyWaitNanos;
    }

    public void setNotEmptyWaitNanos(long notEmptyWaitNanos) {
        this.notEmptyWaitNanos = notEmptyWaitNanos;
    }

    public int getKeepAliveCheckCount() {
        return keepAliveCheckCount;
    }

    public void setKeepAliveCheckCount(int keepAliveCheckCount) {
        this.keepAliveCheckCount = keepAliveCheckCount;
    }

    public int getActivePeak() {
        return activePeak;
    }

    public void setActivePeak(int activePeak) {
        this.activePeak = activePeak;
    }

    public long getActivePeakTime() {
        return activePeakTime;
    }

    public void setActivePeakTime(long activePeakTime) {
        this.activePeakTime = activePeakTime;
    }

    public int getPoolingPeak() {
        return poolingPeak;
    }

    public void setPoolingPeak(int poolingPeak) {
        this.poolingPeak = poolingPeak;
    }

    public long getPoolingPeakTime() {
        return poolingPeakTime;
    }

    public void setPoolingPeakTime(long poolingPeakTime) {
        this.poolingPeakTime = poolingPeakTime;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public int getNotFullTimeoutRetryCount() {
        return notFullTimeoutRetryCount;
    }

    public void setNotFullTimeoutRetryCount(int notFullTimeoutRetryCount) {
        this.notFullTimeoutRetryCount = notFullTimeoutRetryCount;
    }
}
