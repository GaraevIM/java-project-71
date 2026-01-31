.PHONY: setup build test run install run-dist clean

ifeq ($(wildcard code/app/gradlew),)
APP_DIR = app
else
APP_DIR = code/app
endif

GRADLEW = $(APP_DIR)/gradlew

setup:
	chmod +x $(GRADLEW)
	cd $(APP_DIR) && ./gradlew clean install

build:
	chmod +x $(GRADLEW)
	cd $(APP_DIR) && ./gradlew build

test:
	chmod +x $(GRADLEW)
	cd $(APP_DIR) && ./gradlew test

run:
	chmod +x $(GRADLEW)
	cd $(APP_DIR) && ./gradlew run

install:
	chmod +x $(GRADLEW)
	cd $(APP_DIR) && ./gradlew installDist

run-dist: install
	cd $(APP_DIR) && ./build/install/hexlet-git/bin/hexlet-git

clean:
	chmod +x $(GRADLEW)
	cd $(APP_DIR) && ./gradlew clean
