package com.amazon.Hawk.Utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestLogger {
    private static TestLogger instance;
    private final Logger logger;

    private TestLogger() {
        logger = Logger.getLogger(TestLogger.class.getName());
        configureLogger();
    }

    public static synchronized TestLogger getInstance() {
        if (instance == null) {
            instance = new TestLogger();
        }
        return instance;
    }

    public Logger getLogger() {
        return logger;
    }

    private void configureLogger() {
        logger.setLevel(Level.ALL);

        // Console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter()); // Add a formatter

        logger.addHandler(consoleHandler);
    }

    private static class SimpleFormatter extends Formatter {
        @Override
        public String format(java.util.logging.LogRecord record) {
            return "[" + record.getLevel() + "] " + record.getMessage() + "\n";
        }
    }
}
