package top.kwseeker.msatest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "msa-crontab")
public interface MsaCrontabClient {

    @GetMapping("/jobs/list")
    String listJob();
}
