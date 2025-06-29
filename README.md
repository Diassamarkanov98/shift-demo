# shift-demo

# Утилита для обработки данных

Java-приложение для обработки входных файлов, классификации данных на целые числа, числа с плавающей точкой и строки с предоставлением статистики.

## Возможности

- Обработка нескольких входных файлов
- Классификация данных:
    - Целые числа
    - Числа с плавающей точкой
    - Строки
- Статистика:
    - Краткая сводка (флаг -s)
    - Подробная статистика (флаг -f)
- Настройки вывода:
    - Указание директории для вывода (-o)
    - Добавление префикса к выходным файлам (-p)
    - Режим дополнения файлов (-a)

## Технические характеристики

### Требования
- **Java**: версия 11 или выше ([скачать JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html))

### Система сборки
- **Maven**: версия 3.6.0 или выше ([официальный сайт](https://maven.apache.org/))

### Зависимости

Список используемых сторонних библиотек:

```xml
<dependencies>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-params</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.junit.platform</groupId>
    <artifactId>junit-platform-suite</artifactId>
    <version>1.8.2</version>
    <scope>test</scope>
  </dependency>
</dependencies>
```
### Ссылки на библиотеки:

- JUnit 5 - фреймворк для модульного тестирования
- JUnit Platform - платформа для запуска тестов

## Установка

1. Клонируйте репозиторий
2. Соберите проект:
   - Включите терминал(cmd) или же powershell
```bash
mvn clean package
```

## Использование

```bash
java -jar target/shift-demo-1.0-SNAPSHOT.jar [опции] файл1 файл2 ...
```
Опции:
- -s          Показать краткую статистику
- -f          Показать полную статистику
- -a          Дописывать в выходные файлы (по умолчанию: перезапись)
- -o <путь>   Путь к директории для вывода
- -p <префикс> Префикс для имен выходных файлов

## Примеры

### Базовое использование:
```bash
java -jar shift-demo-1.0-SNAPSHOT.jar input1.txt input2.txt
```

### Со статистикой и настройками вывода:
```bash
java -jar shift-demo-1.0-SNAPSHOT.jar -s -o output -p result_ input.txt
```

### Режим дополнения с полной статистикой:

```bash
java -jar shift-demo-1.0-SNAPSHOT.jar -f -a input.txt
```


## Об авторе

Привет! Меня зовут Диас.  
Это учебный проект для [ШИФТ](https://koronatech.kz/shift_java).  