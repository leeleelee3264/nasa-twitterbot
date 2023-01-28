package com.leeleelee3264.nasabot.global.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
public class LoggingUtils {

    private LoggingUtils() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String getStackTrace(Exception exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public static void info(String format, Object arg) {
        log.info(format, arg);
    }

    public static void info(String message, Object...data) {
        log.info(message, data);
    }

    public static void error(Exception exception) {
        String message = getExceptionMessage(exception.getMessage());
        StackTraceElement[] stackTraceElement = exception.getStackTrace();

        log.error(message, stackTraceElement[0]);
    }

    public static void error(String customMessage) {
        log.error(customMessage);
    }

    public static void error(String format, Object arg) {
        log.error(format, arg);
    }

    public static void error(Exception exception, String customMessage) {
        error(customMessage);
        error(exception);
    }

    public static void error(Exception exception, String format, Object arg) {
        error(format, arg);
        error(exception);
    }

    public static String getExceptionMessage(String message) {
        if (StringUtils.hasText(message)) {
            return message + "\n \t {}";
        }
        return "\n \t {}";
    }
}
