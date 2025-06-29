package com.example.testutils;

import com.example.cli.CliParserTest;
import com.example.core.filter.DataFilterTest;
import com.example.core.filter.TypeDetectorTest;
import com.example.core.model.DataTypeTest;
import com.example.core.stats.StatisticsTest;
import com.example.io.FileProcessorTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Unit Tests Suite")
@SelectClasses({
        DataTypeTest.class,
        TypeDetectorTest.class,
        StatisticsTest.class,
        CliParserTest.class,
        FileProcessorTest.class,
        DataFilterTest.class
})
public class TestUtils {
}