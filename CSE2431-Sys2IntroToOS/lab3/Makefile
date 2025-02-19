
all: lab3.zip lab3

lab3.zip: Makefile lab3 lab3.c
	zip $@ $^ *.c *.exe *.txt

lab3: lab3.c
	gcc -o lab3 lab3.c

clean:
	rm -rf *.o lab3 lab3.zip
