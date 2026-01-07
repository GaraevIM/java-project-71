# Вычислитель отличий (gendiff)

[![CI](https://github.com/GaraevIM/java-project-71/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/GaraevIM/java-project-71/actions/workflows/gradle.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=GaraevIM_hexlet-git&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=GaraevIM_hexlet-git)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=GaraevIM_hexlet-git&metric=coverage)](https://sonarcloud.io/summary/new_code?id=GaraevIM_hexlet-git)

CLI-утилита для сравнения двух файлов конфигурации и вывода различий. Поддерживает форматы **JSON** и **YAML**, умеет сравнивать вложенные структуры и выводить результат в нескольких форматах.

## Возможности
- Сравнение JSON/YAML файлов
- Поддержка вложенных структур
- Форматы вывода: `stylish` (по умолчанию), `plain`, `json`

## Требования
- Java 21
- (Опционально) `make`

## Установка
`cd app && make install`

## Запуск

### После установки (installDist)
`cd app && ./build/install/hexlet-git/bin/hexlet-git -h`  
`cd app && ./build/install/hexlet-git/bin/hexlet-git filepath1.json filepath2.json`  
`cd app && ./build/install/hexlet-git/bin/hexlet-git -f plain filepath1.yml filepath2.yml`  
`cd app && ./build/install/hexlet-git/bin/hexlet-git -f json filepath1.json filepath2.json`

### Через Gradle
`cd app && ./gradlew run --args="filepath1.yml filepath2.yml"`  
`cd app && ./gradlew run --args="-f plain filepath1.yml filepath2.yml"`

## Разработка
`cd app && make test`  
`cd app && make build`

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
