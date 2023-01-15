package com.leeleelee3264.earthtoday.nasa.shell;


import com.leeleelee3264.earthtoday.exception.ShellException;
import com.leeleelee3264.earthtoday.util.LoggingUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class EarthGifGenerator {

    private EarthGifGenerator() throws InstantiationException {
        throw new InstantiationException();
    }

    private static void validateOS() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.startsWith("windows")) {
            throw new ShellException.NotSupportedOS(os);
        }
    }

    public static void generate(String directory, String fileName) {
        validateOS();

        String fullFileName = directory + File.separator + fileName;

        String convertCommand = "convert -delay 60 " + directory + "/*.png " + fullFileName;
        String gifsicleCommand = "gifsicle -O3 --colors 128 --lossy=200 -o " + fullFileName + " " + fullFileName;

        try {
            run(convertCommand, true);
            run(gifsicleCommand, false);

        } catch (IOException e) {
            throw new ShellException.FailedExecution(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ShellException.FailedExecution(e.getMessage());
        }

    }

    public static void run(String command, boolean wait) throws IOException, InterruptedException {

        Process process = Runtime.getRuntime().exec(command);

        if (wait) {
            process.waitFor();
        }

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(process.getErrorStream()));

        String error = stdError.readLine();
        if (error != null) {
            LoggingUtils.error("Command Error: {}", error);
            throw new ShellException.FailedCommand(command);
        }
    }
}
