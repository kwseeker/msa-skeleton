package top.kwseeker.msatest.clientApi;

import org.springframework.cloud.openfeign.FeignClient;
import top.kwseeker.msamodapi.api.MsaCrontabApi;

@FeignClient(name = "msa-crontab")
public interface MsaCrontabServiceApi extends MsaCrontabApi {
}
