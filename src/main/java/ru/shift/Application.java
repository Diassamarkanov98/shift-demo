package ru.shift;

import ru.shift.cli.CommandLineParser;
import ru.shift.core.command.CommandFactory;

public class Application {
    public static void main(String[] args) {
        try {
            var commandLineOptions = CommandLineParser.parseCommandLineOptions(args);
            var command = CommandFactory.createCommand(commandLineOptions);
            command.execute();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Usage: java -jar util.jar [-s|-f] [-a] [-o path] [-p prefix] file1 file2 ...");
        }  catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        } finally {
            System.out.println("Program execution ended. Exiting...");
        }
    }

}