package com.leeleelee3264.nasabot.global.exception;

public class BotException extends RuntimeException{

    public BotException(String message) {
        super(message);
    }

    public static final class FailedFetchResources extends BotException {

        public FailedFetchResources(String message) {
            super("Failed to fetch resource for the bot, see detail: " + message);
        }
    }

    public static final class FailedTweet extends BotException {

        public FailedTweet(String message) {
            super("Failed to tweet the bot, see detail: " + message);
        }
    }
}
