.PHONY: setup build test run install run-dist clean

setup:
	chmod +x gradlew
	./gradlew clean installDist

build:
	chmod +x gradlew
	./gradlew build

test: install
	chmod +x gradlew
	./gradlew test

run:
	chmod +x gradlew
	./gradlew run

install:
	chmod +x gradlew
	./gradlew installDist

run-dist: install
	./build/install/app/bin/app

clean:
	chmod +x gradlew
	./gradlew clean
