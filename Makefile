.PHONY: setup build test run install run-dist clean

APP_DIR := $(shell if [ -d code/app ]; then echo code/app; else echo app; fi)

setup:
	cd $(APP_DIR) && chmod +x gradlew && sh ./gradlew clean install

build:
	cd $(APP_DIR) && chmod +x gradlew && sh ./gradlew build

test:
	cd $(APP_DIR) && chmod +x gradlew && sh ./gradlew test

run:
	cd $(APP_DIR) && chmod +x gradlew && sh ./gradlew run

install:
	cd $(APP_DIR) && chmod +x gradlew && sh ./gradlew installDist

run-dist: install
	cd $(APP_DIR) && ./build/install/hexlet-git/bin/hexlet-git

clean:
	cd $(APP_DIR) && chmod +x gradlew && sh ./gradlew clean
