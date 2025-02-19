
all: lab2.zip lab2

lab2.zip: Makefile lab2 lab2.c
	zip $@ $^ *.c *.exe

lab2: lab2.c
	gcc -o lab2 lab2.c

clean:
	rm -rf *.o lab2 lab2.zip
