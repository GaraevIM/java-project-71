.PHONY: setup build test run install run-dist clean

setup:
	cd app && sh ./gradlew clean install

build:
	cd app && sh ./gradlew build

test:
	cd app && sh ./gradlew test

run:
	cd app && sh ./gradlew run

install:
	cd app && sh ./gradlew installDist

run-dist: install
	cd app && ./build/install/hexlet-git/bin/hexlet-git

clean:
	cd app && sh ./gradlew clean
