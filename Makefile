.PHONY: setup build test run install run-dist clean

setup:
	cd code/app && sh ./gradlew clean install

build:
	cd code/app && sh ./gradlew build

test:
	cd code/app && sh ./gradlew test

run:
	cd code/app && sh ./gradlew run

install:
	cd code/app && sh ./gradlew installDist

run-dist: install
	./build/install/hexlet-git/bin/hexlet-git

clean:
	cd code/app && sh ./gradlew clean
