# target all means all FINAL targets currently defined in this file 
# all: <any files you want created. For this class, typically one executable and a .zip file>
gcc_Copt = -ansi -pedantic -g -Wimplicit-function-declaration -Wreturn-type -c
gcc_Sopt = -lc -m64 -c -g
all: lab6.zip bit_decode

# you must have a subsequent target for each file listed above on the right side of the colon
# that would be lab6.zip and lab6 here 
lab6.zip: Makefile LAB6README.txt bit_decode.c create_key.s rotate_left.s rotate_right.s
	zip lab6.zip $^

bit_decode: bit_decode.o create_key.o rotate_left.o rotate_right.o
	gcc $^ -o $@
# -ansi -pedantic -g, etc. not needed

# now you must have a target for each .o file listed above
bit_decode.o: bit_decode.c
	gcc $(gcc_Copt) -o $@ $<

create_key: create_key.o
	gcc $^ -o $@

create_key.o: create_key.s
	gcc $(gcc_Sopt) -o $@ $<

rotate_left: rotate_left.o
	gcc $^ -o $@

rotate_left.o: rotate_left.s
	gcc $(gcc_Sopt) -o $@ $<

rotate_right: rotate_right.o
	gcc $^ -o $@

rotate_right.o: rotate_right.s
	gcc $(gcc_Sopt) -o $@ $<

# in this class, .c files would use $(gcc_Copt)
# .s files would use $(gcc_Sopt)
# if you wish to override the default .o file the output goes to, then you can use –o option 
# but you don’t have to, you can use the default .o

# this target deletes all files produced from the Makefile
# so that a completely new compile of all items is required

clean:
	rm -rf *.o bit_decode lab6.zip