package ru.shift;

import ru.shift.cli.CommandLineParserTest;
import ru.shift.core.command.impl.ProcessFilesCommandTest;
import ru.shift.core.data.DataProcessorTest;
import ru.shift.core.stats.StatisticsPrinterTest;
import ru.shift.file.FileProcessorTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Unit Tests Suite")
@SelectClasses({
        StatisticsPrinterTest.class,
        CommandLineParserTest.class,
        FileProcessorTest.class,
        DataProcessorTest.class,
        ProcessFilesCommandTest.class
})
public class TestUtils {
}