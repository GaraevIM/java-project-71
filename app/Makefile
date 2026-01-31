.PHONY: setup build test run run-dist install clean

APP_DIR := $(if $(wildcard code/app/gradlew),code/app,app)

setup:
	chmod +x $(APP_DIR)/gradlew
	cd $(APP_DIR) && ./gradlew clean install

build:
	chmod +x $(APP_DIR)/gradlew
	cd $(APP_DIR) && ./gradlew build

test:
	chmod +x $(APP_DIR)/gradlew
	cd $(APP_DIR) && ./gradlew test

run:
	chmod +x $(APP_DIR)/gradlew
	cd $(APP_DIR) && ./gradlew run

install:
	chmod +x $(APP_DIR)/gradlew
	cd $(APP_DIR) && ./gradlew installDist

run-dist: install
	./$(APP_DIR)/build/install/hexlet-git/bin/hexlet-git

clean:
	chmod +x $(APP_DIR)/gradlew
	cd $(APP_DIR) && ./gradlew clean
