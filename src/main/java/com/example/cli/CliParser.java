package com.example.cli;

import java.util.ArrayList;
import java.util.List;

public class CliParser {
    public static CliOptions parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No input files specified");
        }

        CliOptions options = new CliOptions();
        List<String> inputFiles = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.startsWith("-")) {
                switch (arg) {
                    case "-s":
                        options.setShortStats(true);
                        break;
                    case "-f":
                        options.setFullStats(true);
                        break;
                    case "-a":
                        options.setAppendMode(true);
                        break;
                    case "-o":
                        if (i + 1 >= args.length || args[i + 1].startsWith("-")) {
                            throw new IllegalArgumentException("No path specified after -o");
                        }
                        options.setOutputPath(args[++i]);
                        break;
                    case "-p":
                        if (i + 1 >= args.length || args[i + 1].startsWith("-")) {
                            throw new IllegalArgumentException("No prefix specified after -p");
                        }
                        options.setPrefix(args[++i]);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown option: " + arg);
                }
            } else {
                inputFiles.add(arg);
            }
        }

        if (inputFiles.isEmpty()) {
            throw new IllegalArgumentException("No input files specified");
        }

        options.setInputFiles(inputFiles);
        return options;
    }
}