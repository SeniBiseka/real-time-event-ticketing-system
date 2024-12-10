package com.backend.ticketingbackend.logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FILE = "system_logs.txt"; // Log file path

    // Method to log messages to both console and file
    public static void log(String message) {
        // Get the current timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Format the log message with the timestamp
        String logMessage = "[" + timestamp + "] " + message;

        // Print to the console
        System.out.println(logMessage);

        // Write to the log file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(logMessage);
            writer.newLine();  // Add a new line after each log
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }
}

