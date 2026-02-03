.PHONY: setup build test run install run-dist clean

APP_DIR := code/app

setup:
	cd $(APP_DIR) && chmod +x gradlew && ./gradlew clean installDist

build:
	cd $(APP_DIR) && chmod +x gradlew && ./gradlew build

test:
	cd $(APP_DIR) && chmod +x gradlew && ./gradlew test

run:
	cd $(APP_DIR) && chmod +x gradlew && ./gradlew run

install:
	cd $(APP_DIR) && chmod +x gradlew && ./gradlew installDist

run-dist: install
	cd $(APP_DIR) && ./build/install/app/bin/app

clean:
	cd $(APP_DIR) && ./gradlew clean
