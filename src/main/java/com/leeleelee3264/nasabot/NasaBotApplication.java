package com.leeleelee3264.nasabot;

import com.leeleelee3264.nasabot.global.exception.BotException;
import com.leeleelee3264.nasabot.domain.todayearth.application.EarthService;
import com.leeleelee3264.nasabot.global.util.LoggingUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.NumberFormat;
import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class NasaBotApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(NasaBotApplication.class, args);
        LocalDate date = LocalDate.now().minusDays(2);
        EarthService service = context.getBean(EarthService.class);

        try {
            service.saveImages(date);
            service.tweetEarth(date);

            LoggingUtils.info("Successfully run EarthBot date: {}", date);
        } catch (BotException e) {
            LoggingUtils.error(e);
        }

        // for memory monitoring
        Runtime rT = Runtime.getRuntime();

        final NumberFormat format = NumberFormat.getInstance();
        final long maxMemory = rT.maxMemory();
        final long allocatedMemory = rT.totalMemory();
        final long freeMemory = rT.freeMemory();

        final int mb = 1024 * 1024;
        final String mega = " MB";

        LoggingUtils.info("========================== Memory Info ==========================");
        LoggingUtils.info("Free memory: " + format.format(freeMemory / mb) + mega);
        LoggingUtils.info("Allocated memory: " + format.format(allocatedMemory / mb) + mega);
        LoggingUtils.info("Max memory: " + format.format(maxMemory / mb) + mega);
        LoggingUtils.info("Total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / mb) + mega);
        LoggingUtils.info("=================================================================\n");

    }

}
