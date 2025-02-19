
all: lab1.zip lab1

lab1.zip: Makefile lab1
	zip $@ $^ *.c *.exe

lab1: helloworld.c
	gcc -o lab1 helloworld.c

clean:
	rm -rf *.o lab1 lab1.zip
