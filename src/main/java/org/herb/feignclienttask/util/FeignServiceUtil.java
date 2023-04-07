package org.herb.feignclienttask.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-converter", url = "https://cbar.az/currencies")
public interface FeignServiceUtil {
    @GetMapping("/{date}.xml")
    String getXML(@PathVariable("date") String date);
}
