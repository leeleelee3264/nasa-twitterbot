package com.leeleelee3264.earthtoday.exception;

public class ShellException extends RuntimeException{

    public ShellException(String message) {
        super(message);
    }

    public static final class NotSupportedOS extends ShellException {

        public NotSupportedOS(String os) {
            super(os + " is not supported in GifGenerator");
        }
    }

    public static final class FailedCommand extends ShellException {

        public FailedCommand(String command) {
            super("Failed command: " + command);
        }
    }

    public static final class FailedExecution extends ShellException {

        public FailedExecution(String message) {
            super("Failed Shell Execution: " + message);
        }
    }

}
