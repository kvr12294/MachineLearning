SRCS = $(wildcard src/*.java)
CLS  = $(SRCS:.java=.class)

default:
	javac -classpath . $(SRCS)

run:
	java -cp ./src Driver input.txt

clean:
	$(RM) $(CLS)