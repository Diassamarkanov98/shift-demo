package ru.shift.core.data.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Container class for categorized data (integers, floats, strings).
 */
@Getter
@RequiredArgsConstructor
public class Data {
    /**
     * List of parsed integer values
     */
    private final List<Long> integers;
    /**
     * List of parsed floating-point values
     */
    private final List<Double> floatingPointNumbers;
    /**
     * List of non-numeric string values
     */
    private final List<String> strings;

    /**
     * Creates an empty Data container.
     */
    public Data() {
        this.integers = new ArrayList<>();
        this.floatingPointNumbers = new ArrayList<>();
        this.strings = new ArrayList<>();
    }
}
