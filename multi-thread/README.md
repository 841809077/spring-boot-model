参考资料：
- https://blog.csdn.net/lzwglory/article/details/49335409
- jdk线程池关闭：https://www.cnblogs.com/windpoplar/p/10545803.html

```shell
// jdk的ThreadPoolExecutor线程池参数
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          RejectedExecutionHandler handler) {
    this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
         Executors.defaultThreadFactory(), handler);
}
```
```shell
// 有参构造方法
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler) {
    if (corePoolSize < 0 ||
        maximumPoolSize <= 0 ||
        maximumPoolSize < corePoolSize ||
        keepAliveTime < 0)
        throw new IllegalArgumentException();
    if (workQueue == null || threadFactory == null || handler == null)
        throw new NullPointerException();
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.workQueue = workQueue;
    this.keepAliveTime = unit.toNanos(keepAliveTime);
    this.threadFactory = threadFactory;
    this.handler = handler;
}
```

```shell
# 设置核心线程数、队列大小、线程名称、拒绝策略等
ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(20), new NamedThreadFactory("线程名称"), new ThreadPoolExecutor.AbortPolicy());
```

队列有三种：
- SynchronousQueue：是无界的，是一种无缓冲的等待队列，但是由于该Queue本身的特性，在某次添加元素后必须等待其他线程取走后才能继续添加；可以认为SynchronousQueue是一个缓存值为1的阻塞队列（经过测试，可认为该队列值为0，类似于直接提交）
- LinkedBlockingQueue：默认无界队列
- ArrayBlockingQueue：有界队列

监控线程池状态，常用状态：
- getTaskCount()：线程池需要执行的任务个数。
- getCompletedTaskCount()：线程池在运行过程中已完成的任务数。
- getLargestPoolSize()：线程池曾经创建过的最大线程数量。
- getPoolSize()： 获取当前线程池的线程数量。
- getQueue().size()：线程池在运行过程中队列中等待执行的任务数
- getActiveCount()：获取活动的线程的数量

获取线程的返回结果
- future
  - future.get();
  - future.get(10000, TimeUnit.MILLISECONDS);
    
获取多线程异步执行结果，但get方法是一个同步方法，如果未拿到结果或者未超时，主线程则一直等待。