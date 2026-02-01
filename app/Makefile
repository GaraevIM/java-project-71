.PHONY: setup build test run install run-dist clean

setup:
	chmod +x gradlew
	sh ./gradlew clean installDist

build:
	chmod +x gradlew
	sh ./gradlew build

test:
	chmod +x gradlew
	sh ./gradlew installDist test

run:
	chmod +x gradlew
	sh ./gradlew run

install:
	chmod +x gradlew
	sh ./gradlew installDist

run-dist: install
	./build/install/hexlet-git/bin/hexlet-git

clean:
	chmod +x gradlew
	sh ./gradlew clean
