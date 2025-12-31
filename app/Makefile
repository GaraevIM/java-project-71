.PHONY: build run run-dist install clean test

build:
	./gradlew build

test:
	./gradlew test

run:
	./gradlew run

install:
	./gradlew installDist

run-dist: install
	./build/install/hexlet-git/bin/hexlet-git

clean:
	./gradlew clean
