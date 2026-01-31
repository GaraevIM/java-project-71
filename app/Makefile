.PHONY: setup build test run run-dist install clean

setup:
	pwd
	ls -la

build:
	chmod +x gradlew
	./gradlew build

test:
	chmod +x gradlew
	./gradlew test

run:
	chmod +x gradlew
	./gradlew run

install:
	chmod +x gradlew
	./gradlew installDist

run-dist: install
	./build/install/hexlet-git/bin/hexlet-git

clean:
	chmod +x gradlew
	./gradlew clean
