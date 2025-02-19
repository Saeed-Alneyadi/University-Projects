# BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I STRICTLY ADHERED TO THE 
# TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY.
# Name: Saeed Alneyadi.11

.file "rotate_right.s"
.section .rodata
.data
.align 8
.globl rotate_right
        .type	rotate_right, @function

.text
rotate_right:
    movq    %rdi, %rax      # Moving the first function parameter to %rax (returned value) to make some calculations.
    movq    %rdi, %rdx      # Moving the first function parameter to %rdx to make some calculations.
    shrb    $1, %al         # Shifiting %al (returned value) 1 bit to the right.
    andq    $0x01, %rdx     # Taking the AND of the 0x01 and %rdx and stores it in %rdx.
    shlb    $7, %dl         # Shifiting %dl 7 bit to the left.
    orq     %rdx, %rax      # Taking the OR of the %rdx and %rax and stores it in %rax.

    ret                     # Returning the result %rax (key).

.size	rotate_right, .-rotate_right
