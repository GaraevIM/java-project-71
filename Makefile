.PHONY: setup build test run install run-dist clean

ifeq ($(wildcard code/app/gradlew),)
APP_DIR = app
else
APP_DIR = code/app
endif

setup:
	cd $(APP_DIR) && sh ./gradlew clean install

build:
	cd $(APP_DIR) && sh ./gradlew build

test:
	cd $(APP_DIR) && sh ./gradlew test

run:
	cd $(APP_DIR) && sh ./gradlew run

install:
	cd $(APP_DIR) && sh ./gradlew installDist

run-dist: install
	cd $(APP_DIR) && ./build/install/hexlet-git/bin/hexlet-git

clean:
	cd $(APP_DIR) && sh ./gradlew clean
