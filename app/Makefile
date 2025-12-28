.PHONY: build run run-dist install clean

build:
	./gradlew build

run:
	./gradlew run

install:
	./gradlew installDist

run-dist: install
	./build/install/app/bin/app

clean:
	./gradlew clean
