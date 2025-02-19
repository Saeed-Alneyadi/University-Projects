# BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I STRICTLY ADHERED TO THE 
# TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY.
# THIS IS THE readrec.s FILE FOR LAB 7.
# Name: Saeed Alneyadi.11

.file "readrec.s"
.section .rodata
.data
r_letter:
.string "r"
.align 8
.globl main
        .type main, @function
.text

main:
    popq    %r8
    popq    %r9
    popq    %r10
    popq    %rdi
    movq    $r_letter, %rsi

    pushq   %rdi
    pushq   %r10
    pushq   %r9
    pushq   %r8
    call    fopen
    popq    %r8
    popq    %r9
    popq    %r10
    popq    %rdi

    movq    %rax, %rbx
    movq    %rdi, %r11
    movq    $1, %rax
    mulq    %r10
    movq    $36, %r12
    mulq    %r12
    movq    %rax, %rdi

    pushq   %r11
    pushq   %r10
    pushq   %r9
    pushq   %r8
    call    malloc
    popq    %r8
    popq    %r9
    popq    %r10
    popq    %r11

    movq    %rbx, %rdi
    movq    %rax, %rsi
    movq    %r11, %rdx

    pushq   %r10
    pushq   %r9
    pushq   %r8
    call    readlines
    call    fclose
    popq    %r8
    popq    %r9
    popq    %r10

    movq    %rsi, %rdi
    movq    %rdx, %rsi

    pushq   %r10
    pushq   %r9
    pushq   %r8
    call    compute
    call    printlines
    popq    %r8
    popq    %r9
    popq    %r10

    leave           # Necessary for the stack.
    ret             # Returning the result %rax (key).

.size main, .-main
