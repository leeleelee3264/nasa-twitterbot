package com.leeleelee3264.nasabot.infra.task;

import com.leeleelee3264.nasabot.domain.model.TEHistory;
import com.leeleelee3264.nasabot.domain.todayearth.dao.TEHistoryRepository;
import com.leeleelee3264.nasabot.global.exception.BotException;
import com.leeleelee3264.nasabot.domain.todayearth.application.EarthService;
import com.leeleelee3264.nasabot.global.util.LoggingUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EarthTask {

    private final EarthService earthService;
    private final TEHistoryRepository teHistoryRepository;

    public EarthTask(
            EarthService earthService,
            TEHistoryRepository teHistoryRepository

    ) {
        this.earthService = earthService;
        this.teHistoryRepository = teHistoryRepository;
    }

    public void runBot(TEHistory todayHistory) {

        try {
            LocalDate targetDate = todayHistory.getCreateDate().minusDays(2);

            this.earthService.saveImages(targetDate);
            this.earthService.tweetEarth(targetDate);

            LoggingUtils.info("Successfully run EarthBot date");

            todayHistory.succeed();
        } catch (BotException e) {
            LoggingUtils.error(e);
        } finally {
            this.teHistoryRepository.save(todayHistory);
        }

    }

    @Scheduled(cron = "0 7 15 * * *")
    public void triggerEarthBot() {
        // 당일이나 전날 데이터를 조회하면 NASA에서 자료를 올려주지 않은 경우가 많아서 2일 정도 전의 데이터를 조회한다.
        LocalDate today = LocalDate.now();
        LoggingUtils.info("Task start, run EarthBot date: {}", today);

        TEHistory todayHistory = TEHistory.builder()
                .tweeted(false)
                .createDate(today)
                .build();

        this.runBot(todayHistory);
    }

    @Scheduled(cron = "0 0 18 * * *")
    public void triggerEarthBotBackup() {
        LocalDate today = LocalDate.now();
        TEHistory todayHistory = this.teHistoryRepository.findByCreateDate(today);

        if (todayHistory.isTweeted()) {
            return;
        }

        this.runBot(todayHistory);
    }

    @Scheduled(cron = "0 0 23 * * *")
    public void triggerEarthBotBackup2() {
        LocalDate today = LocalDate.now();
        TEHistory todayHistory = this.teHistoryRepository.findByCreateDate(today);

        if (todayHistory.isTweeted()) {
            return;
        }

        this.runBot(todayHistory);
    }


}
