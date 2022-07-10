package top.kwseeker.msa.seata.event;

import com.google.common.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MetricsSubscriber {

    /** Metrics组件容器 */
    //private final Registry registry;
    /** 不同的消息有不同的处理逻辑 */
    private final Map<GlobalStatus, Consumer<GlobalTransactionEvent>> consumers;

    //public MetricsSubscriber(Registry registry) {
    public MetricsSubscriber() {
        //this.registry = registry;
        consumers = new HashMap<>();
        consumers.put(GlobalStatus.Begin, this::processGlobalStatusBegin);
        //consumers.put(GlobalStatus.Committed, this::processGlobalStatusCommitted);
        //consumers.put(GlobalStatus.Rollbacked, this::processGlobalStatusRollbacked);
        //
        //consumers.put(GlobalStatus.CommitFailed, this::processGlobalStatusCommitFailed);
        //consumers.put(GlobalStatus.RollbackFailed, this::processGlobalStatusRollbackFailed);
        //consumers.put(GlobalStatus.TimeoutRollbacked, this::processGlobalStatusTimeoutRollbacked);
        //consumers.put(GlobalStatus.TimeoutRollbackFailed, this::processGlobalStatusTimeoutRollbackFailed);
    }

    @Subscribe
    public void recordGlobalTransactionEventForMetrics(GlobalTransactionEvent event) {
        //if (registry != null && consumers.containsKey(event.getStatus())) {
        if (consumers.containsKey(event.getStatus())) {
            consumers.get(event.getStatus()).accept(event);
        }
    }

    private void processGlobalStatusBegin(GlobalTransactionEvent event) {
        //registry.getCounter(MeterIdConstants.COUNTER_ACTIVE).increase(1);
        System.out.println("process GlobalStatus.BEGIN ...");
    }
}
