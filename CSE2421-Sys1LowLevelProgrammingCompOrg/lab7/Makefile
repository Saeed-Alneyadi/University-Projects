# BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I STRICTLY ADHERED TO THE 
# TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY.
# THIS IS THE readlines.s FILE FOR LAB 7.
# Name: Saeed Alneyadi.11

# shortcut

gcc_Sopt = -lc -m64 -c -g

# all target

all: lab7.zip readrec

lab7.zip: Makefile LAB7README.txt lab7.h readrec.s readlines.s compute.s printlines.s
	zip lab7.zip $^

# readrec target

readrec: readrec.o readlines.o compute.o printlines.o
	gcc $^ -o $@

readrec.o: readrec.s lab7.h
	gcc $(gcc_Sopt) -o $@ $<

# readlines target

readlines: readlines.o
	gcc $^ -o $@

readlines.o: readlines.s lab7.h
	gcc $(gcc_Sopt) -o $@ $<

# compute target

compute: compute.o
	gcc $^ -o $@

compute.o: compute.s lab7.h
	gcc $(gcc_Sopt) -o $@ $<

# printlines target

printlines: printlines.o
	gcc $^ -o $@

printlines.o: printlines.s lab7.h
	gcc $(gcc_Sopt) -o $@ $<

# Cleaning target

clean:
	rm -rf *.o readrec lab7.zip