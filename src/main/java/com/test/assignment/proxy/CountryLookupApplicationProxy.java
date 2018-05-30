package com.test.assignment.proxy;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "country-lookup-application", url = "localhost:8081")
public interface CountryLookupApplicationProxy {

    @GetMapping("/countryName/{ipAddress}")
    String getCountryNameByIp(@PathVariable("ipAddress") String ipAddress);

    @GetMapping("/countryCode/{ipAddress}")
    String getCountryCodeByIp(@PathVariable("ipAddress") String ipAddress);
}
