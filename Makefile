.PHONY: setup build test run install run-dist clean

setup:
	cd app && make setup

build:
	cd app && make build

test:
	cd app && make test

run:
	cd app && make run

install:
	cd app && make install

run-dist:
	cd app && make run-dist

clean:
	cd app && make clean
