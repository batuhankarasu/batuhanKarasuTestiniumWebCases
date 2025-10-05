package com.batuhanTestiniumWebCases.automation.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public final class TxtUtils {
    static final Logger logger = LogManager.getLogger(TxtUtils.class);


    public static void write(String filePath, String text, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            writer.write(text);
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to write to file: " + e.getMessage());
        }
    }
}

