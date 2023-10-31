package com.ritz.health.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig implements SchedulingConfigurer, AsyncConfigurer {//期望springtask支持并行与异步

    Logger log = LoggerFactory.getLogger(ThreadPoolConfig.class);

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(taskScheduler());
    }

    @Bean
    public TaskScheduler taskScheduler() {//并行
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        //配置线程池大小
        threadPoolTaskScheduler.setPoolSize(1);
        //线程名称前缀
        threadPoolTaskScheduler.setThreadNamePrefix("setmeal-img-clean-1-");
        //线程池关闭时最大等待时间，确保最后一定关闭
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
        //线程池关闭时等待所有任务执行结束
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        //丢弃任务策略
        threadPoolTaskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return threadPoolTaskScheduler;
    }

    @Bean
    @Override
    public Executor getAsyncExecutor() {//异步
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        threadPoolTaskExecutor.setCorePoolSize(1);
        //配置最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(20);
        //线程超时时间
        threadPoolTaskExecutor.setKeepAliveSeconds(100);
        //队列容量
        threadPoolTaskExecutor.setQueueCapacity(50);
        //线程名称前缀
        threadPoolTaskExecutor.setThreadNamePrefix("setmeal-img-clean-2-");
        //线程池关闭时最大等待时间，确保最后一定关闭
        threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
        //线程池关闭时等待所有任务执行结束
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //丢弃任务策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }


    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SpringTaskExceptionHandler();
    }

    class SpringTaskExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable ex, Method method, Object... params) {
            log.error("SpringTaskExceptionHandler execute error", params);
        }
    }
}
