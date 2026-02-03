.PHONY: setup build test run install run-dist clean

ifeq ($(OS),Windows_NT)
GRADLEW=gradlew.bat
GRADLEW_RUN=.\gradlew.bat
PREP=
else
GRADLEW=gradlew
GRADLEW_RUN=./gradlew
PREP=chmod +x gradlew &&
endif

setup:
	$(PREP) $(GRADLEW_RUN) clean installDist

build:
	$(PREP) $(GRADLEW_RUN) build

test:
	$(PREP) $(GRADLEW_RUN) test

run:
	$(PREP) $(GRADLEW_RUN) run

install:
	$(PREP) $(GRADLEW_RUN) installDist

run-dist: install
	./build/install/app/bin/app

clean:
	$(PREP) $(GRADLEW_RUN) clean
