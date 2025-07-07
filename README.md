# [shift-demo](https://koronatech.kz/shift_java)

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
- **Java**: версия 17 или выше ([скачать JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html))

### Система сборки
- **Maven**: версия 3.6.0 или выше ([официальный сайт](https://maven.apache.org/))

### Зависимости

Список используемых сторонних библиотек:

```xml
<properties>
  <maven.compiler.source>17</maven.compiler.source>
  <maven.compiler.target>17</maven.compiler.target>
  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  <junit.version>5.8.2</junit.version>
  <lombok.version>1.18.30</lombok.version>
  <maven.compiler.plugin.version>3.11.0</maven.compiler.plugin.version>
  <maven.shade.plugin.version>3.5.0</maven.shade.plugin.version>
  <maven.surefire.plugin.version>2.22.2</maven.surefire.plugin.version>
  <maven.clean.plugin.version>3.3.2</maven.clean.plugin.version>
</properties>
<dependencies>
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter-api</artifactId>
  <version>${junit.version}</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter-engine</artifactId>
  <version>${junit.version}</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter-params</artifactId>
  <version>${junit.version}</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.junit.platform</groupId>
  <artifactId>junit-platform-suite</artifactId>
  <version>1.8.2</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <version>${lombok.version}</version>
  <scope>provided</scope>
</dependency>
</dependencies>
```
### Ссылки на библиотеки:

- [JUnit 5](https://junit.org/) - фреймворк для модульного тестирования
- [JUnit Platform](https://docs.junit.org/current/api/org.junit.platform.suite/module-summary.html) - платформа для запуска тестов
- [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok) - для сокращения boilerplate-кода

## Установка

1. Клонируйте репозиторий
2. Соберите проект с помощью Maven(предварительно нужно установить Maven):
   - Включите терминал(cmd) или же powershell
```bash
mvn clean package
```
3. После сборки проекта в корневой директории проекта создается исполняемый JAR-файл(shift-demo.jar)

## Использование

```bash
java -jar shift-demo.jar [опции] [путь к файлу 1] [путь к файлу 2] ...
```
Опции:
- -s           Показать краткую статистику
- -f           Показать полную статистику
- -a           Дописывать в выходные файлы (по умолчанию: перезапись)
- -o <путь>    Путь к директории для вывода
- -p <префикс> Префикс для имен выходных файлов

## Выходные данные

Программа создает три файла (при наличии соответствующих данных во входных файлах):

- integers.txt - целые числа

- floats.txt - числа с плавающей точкой

- strings.txt - текстовые строки

При использовании опции -p к именам файлов добавляется указанный префикс.

## Примеры

### Базовое использование:
```bash
java -jar shift-demo.jar input1.txt input2.txt
```

### С краткой статистикой и настройками вывода:
```bash
java -jar shift-demo.jar -s -o output -p result_ input.txt
```

### Режим дополнения с полной статистикой:

```bash
java -jar shift-demo.jar -f -a input.txt
```


