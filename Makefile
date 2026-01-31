.PHONY: setup build test run run-dist install clean

setup:
	cd app && chmod +x gradlew
	cd app && ./gradlew clean install

build:
	cd app && ./gradlew build

test:
	cd app && ./gradlew test

run:
	cd app && ./gradlew run

install:
	cd app && ./gradlew installDist

run-dist: install
	./app/build/install/hexlet-git/bin/hexlet-git

clean:
	cd app && ./gradlew clean
