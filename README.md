# Вычислитель отличий (gendiff)

[![CI](https://github.com/GaraevIM/java-project-71/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/GaraevIM/java-project-71/actions/workflows/gradle.yml)
[![hexlet-check](https://github.com/GaraevIM/java-project-71/actions/workflows/hexlet-check.yml/badge.svg?branch=main)](https://github.com/GaraevIM/java-project-71/actions/workflows/hexlet-check.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=GaraevIM_java-project-71&metric=alert_status)](https://sonarcloud.io/project/overview?id=GaraevIM_java-project-71)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=GaraevIM_java-project-71&metric=coverage)](https://sonarcloud.io/project/overview?id=GaraevIM_java-project-71)

CLI-утилита для сравнения двух файлов конфигурации и вывода различий. Поддерживает форматы **JSON** и **YAML**, умеет сравнивать вложенные структуры и выводить результат в нескольких форматах.

## Возможности
- Сравнение JSON/YAML файлов
- Поддержка вложенных структур
- Форматы вывода: `stylish` (по умолчанию), `plain`, `json`

## Требования
- Java 21
- (Опционально) `make`

## Установка
```bash
cd app
make install
```

## Запуск

### После установки (installDist)
```bash
cd app
./build/install/hexlet-git/bin/hexlet-git -h
./build/install/hexlet-git/bin/hexlet-git src/test/resources/fixtures/file1.json src/test/resources/fixtures/file2.json
./build/install/hexlet-git/bin/hexlet-git -f plain src/test/resources/fixtures/file1.yml src/test/resources/fixtures/file2.yml
./build/install/hexlet-git/bin/hexlet-git -f json src/test/resources/fixtures/file1.json src/test/resources/fixtures/file2.json
```

### Через Gradle
```bash
cd app
./gradlew run --args="src/test/resources/fixtures/file1.yml src/test/resources/fixtures/file2.yml"
./gradlew run --args="-f plain src/test/resources/fixtures/file1.yml src/test/resources/fixtures/file2.yml"
```

## Разработка
```bash
cd app
make test
make build
```

## Демонстрация работы
[Вычислитель отличий](https://asciinema.org/a/FJSDghOpGWzQoJaI9J0puiqlQ)

## Пример сравнения YAML-файлов
[Сравнение плоских YAML-файлов](https://asciinema.org/a/vmVCseUbrsFY7Bk6Ln9T7KPwD)

## Пример сравнения вложенных структур
[Сравнение вложенных JSON и YAML файлов (stylish по умолчанию)](https://asciinema.org/a/zzLYlXR4XaRiD6ACo0ZatdawB)

## Пример вывода в формате plain
[Сравнение файлов в формате plain](https://asciinema.org/a/tyqYTwHXuxa6Gn0o7yQIP436f)

## Пример вывода в формате json
[Сравнение файлов в формате json](https://asciinema.org/a/QUXKM189gRAeBe3b4mSeebaWh)
