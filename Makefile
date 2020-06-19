JAVA := java
JAVAC := javac
JAVA_OPTS := $(OPTS) -classpath bin
JAVAC_OPTS := $(OPTS) -sourcepath src -d bin

TARGET = Main
SRCS := $(wildcard src/*.java)

run: compile
	$(JAVA) $(JAVA_OPTS) src/${TARGET}

compile: $(SRCS)
	$(JAVAC) $(JAVAC_OPTS) $(SRCS)

lines:
	wc -l *.java

clean:
	rm -rf bin/*.class bin/**/*.class
