package com.example.io;

import com.example.core.filter.DataFilter;
import com.example.core.stats.Statistics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileProcessorTest {

    @TempDir
    Path tempDir;

    @Test
    public void testWriteToExistingFile() throws IOException {
        // Создаем файл заранее
        Path testFile = tempDir.resolve("existing.txt");
        Files.write(testFile, List.of("initial content"));

        FileProcessor processor = new FileProcessor();
        List<String> data = List.of("line1", "line2", "line3");

        // Запись в существующий файл
        processor.writeData(tempDir.toString(), "existing.txt", data, false);

        // Проверяем содержимое файла
        List<String> lines = Files.readAllLines(testFile);
        assertEquals(data, lines);
    }

    @Test
    public void testAppendToExistingFile() throws IOException {
        // Создаем файл с начальным содержимым
        Path testFile = tempDir.resolve("append.txt");
        Files.write(testFile, List.of("initial line"));

        FileProcessor processor = new FileProcessor();
        List<String> newData = List.of("appended line");

        // Добавляем данные в существующий файл
        processor.writeData(tempDir.toString(), "append.txt", newData, true);

        // Проверяем что содержимое добавилось
        List<String> lines = Files.readAllLines(testFile);
        assertEquals(2, lines.size());
        assertEquals("initial line", lines.get(0));
        assertEquals("appended line", lines.get(1));
    }

    @Test
    public void testWriteToNonexistentFileThrowsException() {
        FileProcessor processor = new FileProcessor();
        List<String> data = List.of("test data");

        // Пытаемся записать в несуществующий файл
        IOException exception = assertThrows(IOException.class, () -> {
            processor.writeData(tempDir.toString(), "nonexistent.txt", data, false);
        });

        // Проверяем сообщение об ошибке
        assertTrue(exception.getMessage().contains("File does not exist"));
    }

    @Test
    public void testEmptyDataDoesNotWrite() throws IOException {
        // Создаем файл заранее
        Path testFile = tempDir.resolve("empty.txt");
        Files.write(testFile, List.of("initial content"));

        FileProcessor processor = new FileProcessor();

        // Пытаемся записать пустые данные
        processor.writeData(tempDir.toString(), "empty.txt", List.of(), false);

        // Проверяем что файл не изменился
        List<String> lines = Files.readAllLines(testFile);
        assertEquals(1, lines.size());
        assertEquals("initial content", lines.get(0));
    }
}