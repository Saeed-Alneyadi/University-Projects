# BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I STRICTLY ADHERED TO THE 
# TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY.
# THIS IS THE compute.s FILE FOR LAB 7.
# Name: Saeed Alneyadi.11

.file "compute.s"
.section .rodata
.data
.align 8
.globl compute
        .type compute, @function
.text

compute:
    pushq   %rbx

    movq    %rsi, %r11
    movq    %rdi, %r10

    jmp     test
    loop:

    # Taking the values from the stuct.
    leal    (%r10), %ecx
    leal    4(%r10), %ebx

    # Addition Calculations
    movq    %rbx, %rax
    addq    %rcx, %rax
    movq    %rax, 12(%r10)

    # Subtraction Calculations
    movq    %rbx, %rax
    subq    %rcx, %rax
    movq    %rax, 8(%r10)

    # Multiplication Calculations
    movq    %rbx, %rax
    imulq   %rcx
    movq    %rax, 20(%r10)

    # Division Calculations
    movq    %rbx, %rax
    cqto
    idivq   %rcx
    movq    %rax, 20(%r10)
    movq    %rdx, 32(%r10)

    decq    %r11
    addq    $32, %r10
    test:
    cmpq    $0x0, %r11
    jne     loop

    popq    %rbx

    leave           # Necessary for the stack.
    ret             # Returning the result %rax (key).

.size	compute, .-compute
