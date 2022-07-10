package top.kwseeker.msa.seata.event;

public class Main {

    public static void main(String[] args) {
        EventBus bus = EventBusManager.get();

        MetricsSubscriber subscriber = new MetricsSubscriber();
        bus.register(subscriber);
        GlobalTransactionEvent event = new GlobalTransactionEvent(10000L, GlobalTransactionEvent.ROLE_TC,
                "creatOrder", System.currentTimeMillis(), null, GlobalStatus.Begin);
        bus.post(event);
    }
}
