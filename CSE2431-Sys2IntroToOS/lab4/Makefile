all: lab4.zip output

lab4.zip: Makefile createThreads parallel1 parallel2 parallel3 output
	zip $@ *

output: createThreads parallel1 parallel2 parallel3
	createThreads >> createThreadsOutput.txt
	parallel1 >> parallel1Output.txt
	parallel2 >> parallel2Output.txt
	parallel3 >> parallel3Output.txt

createThreads: createThreads.c
	gcc -g -Wall $^ -o $@ -lpthread

parallel1: parallel1.c
	gcc -g -Wall $^ -o $@ -lpthread

parallel2: parallel2.c
	gcc -g -Wall $^ -o $@ -lpthread

parallel3: parallel3.c
	gcc -g -Wall $^ -o $@ -lpthread

run:
	./createThreads
	./parallel1
	./parallel2
	./parallel3

test:
	./createThreads
	./parallel1
	./parallel2
	./parallel3

clean:
	rm -rf createThreads parallel1 parallel2 parallel3 lab4.zip *.txt



