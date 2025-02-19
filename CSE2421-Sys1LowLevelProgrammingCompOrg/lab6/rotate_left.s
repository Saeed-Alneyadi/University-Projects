# BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I STRICTLY ADHERED TO THE 
# TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY.
# Name: Saeed Alneyadi.11

.file "rotate_left.s"
.section .rodata
.data
.align 8
.globl rotate_left
        .type	rotate_left, @function

.text
rotate_left:
    movq    %rdi, %rax      # Moving the first function parameter to %rax (returned value) to make some calculations.
    movq    %rdi, %rdx      # Moving the first function parameter to %rdx to make some calculations.
    shlb    $1, %al         # Shifiting %al (returned value) 1 bit to the left.
    andq    $0x08, %rdx     # Taking the AND of the 0x08 and %rdx and stores it in %rdx.
    shrb    $7, %dl         # Shifiting %dl 7 bit to the right.
    orq     %rdx, %rax      # Taking the OR of the %rdx and %rax and stores it in %rax.

    ret                     # Returning the result %rax (key).

.size	rotate_left, .-rotate_left
