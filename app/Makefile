.PHONY: build run run-dist install clean

build:
	.\gradlew.bat build

run:
	.\gradlew.bat run

install:
	.\gradlew.bat installDist

run-dist: install
	.\build\install\app\bin\app.bat

clean:
	.\gradlew.bat clean
