package com.leeleelee3264.nasabot.infra.sms;

import com.leeleelee3264.nasabot.global.util.LoggingUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.v1.StatusUpdate;
import twitter4j.v1.UploadedMedia;

import javax.annotation.PostConstruct;
import java.io.File;

@Component
public class TwitterClient {

    @Value("${twitter.consumer.key}")
    private String consumerKey;

    @Value("${twitter.consumer.secret}")
    private String consumerSecret;

    @Value("${twitter.access.token}")
    private String accessToken;

    @Value("${twitter.access.token.secret}")
    private String accessTokenSecret;

    private Twitter twitterInstance;


    @PostConstruct
    void init() {

        this.twitterInstance = Twitter.newBuilder()
                .oAuthConsumer(consumerKey, consumerSecret)
                .oAuthAccessToken(accessToken, accessTokenSecret)
                .httpConnectionTimeout(30000)
                .build();
    }

    public void tweet(String message) {
        try {
            this.twitterInstance.v1().tweets().updateStatus(message);

            LoggingUtils.info("Successfully tweet the message: {}", message);
        } catch (TwitterException e) {
            LoggingUtils.error(e);
        }
    }

    public void tweetMedia(File file) throws TwitterException {

        // Get empty StatusUpdate object to get new StatusUpdate object with media id.
        StatusUpdate statusUpdate = StatusUpdate.of("");
        UploadedMedia media = this.twitterInstance.v1().tweets().uploadMedia(file);

        if (media != null) {
            StatusUpdate update2 = statusUpdate.mediaIds(media.getMediaId());
            this.twitterInstance.v1().tweets().updateStatus(update2);
        }

        LoggingUtils.info("Successfully upload media: {}", file.getAbsolutePath());
    }

}
