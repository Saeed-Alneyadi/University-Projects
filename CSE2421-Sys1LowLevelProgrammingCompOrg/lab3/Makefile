# comments in a Makefile start with sharp 

gcc_opt = -ansi -pedantic -Wimplicit-function-declaration -Wreturn-type -g -c
# target all means all targets currently defined in this file
all: lab3.zip lab3

# this target is the .zip file that must be submitted to Carmen
lab3.zip: Makefile lab3main.o get_ingredients.o get_item.o get_thispizza.o save_info.o free_dmem.o LAB3README
	zip $@ $^ *.c *.h *.exe

# this target is the binary converter executable that doesn't prompt for input
lab3: lab3main.o get_ingredients.o get_item.o get_thispizza.o save_info.o free_dmem.o
	gcc $^ -o $@

# this target is the dependency for lab3
# we use "-o lab3.o" here so that the .o file doesn't have the same name as the .c file
# most of the time we can use the default .o file, but since in this instance we are creating
# two separate .o files using the same .c file, we have to specify a name for the .o file
lab3main.o: lab3main.c lab3.h
	gcc $(gcc_opt) -o $@ $<

get_ingredients: get_ingredients.o
	gcc $< -o $@

get_ingredients.o: get_ingredients.c lab3.h
	gcc $(gcc_opt) -o $@ $<

get_item: get_item.o
	gcc $< -o $@

get_item.o: get_item.c lab3.h
	gcc $(gcc_opt) -o $@ $<

get_thispizza: get_thispizza.o
	gcc $< -o $@

get_thispizza.o: get_thispizza.c lab3.h
	gcc $(gcc_opt) -o $@ $<

save_info: save_info.o
	gcc $< -o $@

save_info.o: save_info.c lab3.h
	gcc $(gcc_opt) -o $@ $<

free_dmem: free_dmem.o
	gcc $< -o $@

free_dmem.o: free_dmem.c lab3.h
	gcc $(gcc_opt) -o $@ $<

# this target deletes all files produced from the Makefile
# so that a completely new compile of all items is required
clean:
	rm -rf *.o lab3 lab3.zip
