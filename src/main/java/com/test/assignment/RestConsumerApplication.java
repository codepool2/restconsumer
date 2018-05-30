package com.test.assignment;

import com.test.assignment.proxy.CountryLookupApplicationProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@SpringBootApplication
@EnableFeignClients("com.test.assignment")
public class RestConsumerApplication implements CommandLineRunner {


    private final CountryLookupApplicationProxy countryLookupApplicationProxy;
    private final SortByCountryNameApp sortByCountryNameApp;

    @Autowired
    public RestConsumerApplication(CountryLookupApplicationProxy countryLookupApplicationProxy, SortByCountryNameApp sortByCountryNameApp) {
        this.countryLookupApplicationProxy = countryLookupApplicationProxy;
        this.sortByCountryNameApp = sortByCountryNameApp;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestConsumerApplication.class, args);
    }


    @Override
    public void run(String... args) throws IOException {

        System.out.println("SORTING THE COUNTRY AGAINST IPADDRESS IN ASCENDING ORDER");

        Map<String, String> sortedResult = sortByCountryNameApp.getSortedResult();

        for (Map.Entry<String, String> entry : sortedResult.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        List<String> listOfIps = new ArrayList<>();
        listOfIps.add("151.38.39.114");
        listOfIps.add("12.25.205.51");
        listOfIps.add("200.21.225.82");
        listOfIps.add("64.81.104.131");


        System.out.println("\nGETTING COUNTRYNAME AND COUNTRYCODE AGAINST THE IPADDRESS");
        for (String ipAddress : listOfIps) {
            System.out.println(ipAddress + "  " + countryLookupApplicationProxy.getCountryCodeByIp(ipAddress) + "   " + countryLookupApplicationProxy.getCountryNameByIp(ipAddress));
        }
    }
}
