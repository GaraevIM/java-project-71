.PHONY: setup build test run install run-dist clean

setup:
	$(MAKE) -C app setup

build:
	$(MAKE) -C app build

test:
	$(MAKE) -C app test

run:
	$(MAKE) -C app run

install:
	$(MAKE) -C app install

run-dist:
	$(MAKE) -C app run-dist

clean:
	$(MAKE) -C app clean
