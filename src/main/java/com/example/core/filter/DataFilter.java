package com.example.core.filter;

import com.example.core.model.DataType;
import com.example.core.stats.Statistics;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DataFilter {
    private final List<String> integers = new ArrayList<>();
    private final List<String> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    public void processLines(List<String> lines, Statistics stats) {
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            DataType type = TypeDetector.detectType(line);
            switch (type) {
                case INTEGER -> {
                    integers.add(line);
                    stats.collectIntegerStats(line);
                }
                case FLOAT -> {
                    floats.add(line);
                    stats.collectFloatStats(line);
                }
                case STRING -> {
                    strings.add(line);
                    stats.collectStringStats(line);
                }
            }
        }
    }
}