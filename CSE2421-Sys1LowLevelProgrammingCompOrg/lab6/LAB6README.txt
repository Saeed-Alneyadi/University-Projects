BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I STRICTLY ADHERED TO THE 
TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY.
THIS IS THE README FILE FOR LAB 6.

1. Student Name: Saeed Alneyadi.11

2. Total amount of time (effort) it took for you to complete the lab: 
ANSWER: 14 hours

3. Short description of any concerns, interesting problems or discoveries encountered, or 
comments in general about the contents of the lab: 
ANSWER: One of the problems that I faced while coding is linking problem. The issue was that
when compiling using the makefile, the gcc show that there is undefined reference to the three 
functions. The reason behind that is the makefile. The makefile wasn’t able to create .o files 
for the three functions which cause this whole mess.

4. Describe how you used gdb to find work with your C language program if there were 
any. Include how you set breakpoints, variables you printed out, what values they had, 
what you found.
ANSWER: There was no error with the c program. the c program was working well. I used gdb to just move through it.

5. Describe how you used gdb to find a bug in your x86-64 programs. Include details 
with respect to what breakpoints you set what registers you looked at and how you 
determined what the bug was.
ANSWER: I found an syntax error using gdb that causes the usage of another register which cause a logical error. 
I've set the break point at line 13 of bit_decode and then used the step command to enter the code of the create_key function.
The register that I was wanting to use is %rbx while I enter %rdx, which is hard to find this error.

6. Did you have to jump between your C language/x86-64 language programs to fix any bugs?
ANSWER: Yes, I’ve set a break point at some functions call in .c file and then moved through 
the gdb to the .s file where there is bug inside my .s file code.

