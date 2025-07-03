package com.example.cli;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CliOptions {
    private List<String> inputFiles;
    private String outputPath;
    private String prefix;
    private boolean appendMode;
    private boolean shortStats;  // Lombok автоматически создаст setShortStats()
    private boolean fullStats;
}