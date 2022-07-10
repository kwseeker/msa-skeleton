package top.kwseeker.msa.seata.event;

public interface EventBus {
    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Event event);
}