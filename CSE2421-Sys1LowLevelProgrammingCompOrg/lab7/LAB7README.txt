BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I STRICTLY ADHERED TO THE 
TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
THIS IS THE README FILE FOR LAB 7.

1. Student Name: Saeed Alneyadi.11

2. Total amount of time (effort) it took for you to complete the lab: 
ANSWER: 30 Hours

3. Short description of any concerns, interesting problems or discoveries encountered, or 
comments in general about the contents of the lab: 
ANSWER: One of the problems that I faced is linkage problem. I was not able to link the files together. 
This is fixed by doing some correction to the makefile. I forget to write about the .h file.

4. Describe how you used gdb to find a bug in your x86-64 programs. Include details 
with respect to what breakpoints you set what registers you looked at and how you 
determined what the bug was.
ANSWER: The gdb was so usefal where it helps me catch some errors. One of the errors it help catchs 
is forgetting to increamenting the register responsible of stuct data position. This lead to program 
faliure. I disvocered this error by set a breakpoint inside main function at the begining. After discovering 
that the error occur inside the readlines function, I set a new breakpoint inside this function which then I 
add the necessery instuctions to correct this error.
