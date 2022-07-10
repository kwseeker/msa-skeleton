package top.kwseeker.msa.seata.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GuavaEventBus implements EventBus {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaEventBus.class);
    private final com.google.common.eventbus.EventBus eventBus;

    public GuavaEventBus(String identifier) {
        this(identifier, false);
    }

    public GuavaEventBus(String identifier, boolean async) {
        if (!async) {
            this.eventBus = new com.google.common.eventbus.EventBus(identifier);
        } else {
            //创建线程池异步处理消息
            final ExecutorService eventExecutor = new ThreadPoolExecutor(1, 1, Integer.MAX_VALUE, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<>(2048), new NamedThreadFactory(identifier, 1), (r, executor) -> {
                LOGGER.warn("eventBus executor queue is full, size:{}", executor.getQueue().size());
            });
            this.eventBus = new com.google.common.eventbus.AsyncEventBus(identifier, eventExecutor);
        }
    }

    @Override
    public void register(Object subscriber) {
        this.eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        this.eventBus.unregister(subscriber);
    }

    @Override
    public void post(Event event) {
        this.eventBus.post(event);
    }
}
