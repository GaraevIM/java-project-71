.PHONY: setup build test run install clean run-dist

APP_DIR := $(if $(wildcard code/app/gradlew),code/app,app)
APP_NAME := hexlet-git

setup:
	cd $(APP_DIR) && chmod +x gradlew && ./gradlew clean install

build:
	cd $(APP_DIR) && chmod +x gradlew && ./gradlew build

test:
	cd $(APP_DIR) && chmod +x gradlew && ./gradlew test

run:
	cd $(APP_DIR) && chmod +x gradlew && ./gradlew run

install:
	cd $(APP_DIR) && chmod +x gradlew && ./gradlew installDist

clean:
	cd $(APP_DIR) && chmod +x gradlew && ./gradlew clean

run-dist: install
	cd $(APP_DIR) && ./build/install/$(APP_NAME)/bin/$(APP_NAME)
