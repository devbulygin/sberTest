run-dist:
	./build/install/sberTest/bin/sberTest ./src/main/resources/name_java.xlsx ОЕК 052 797

clean-install:
	./gradlew clean installDist

build:
	./gradlew clean build


test:
	./gradlew test


clean-run: clean-install run-dist

install:
	./gradlew clean install

.PHONY: build