# BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I STRICTLY ADHERED TO THE 
# TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY.
# THIS IS THE readlines.s FILE FOR LAB 7.
# Name: Saeed Alneyadi.11

.file "printlines.s"
.section .rodata
add_line:
.string "value1 + value2 = %i\n"
sub_line:
.string "value1 – value2 = %i\n"
mul_line:
.string "value1 * value2 = \n"
div_line:
.string "value1/value2 = %i, %i\n"
.data
.align 8
.globl printlines
        .type printlines, @function
.text

printlines:
    movq    %rsi, %rcx
    movq    %rdi, %r10

    jmp     test
    loop:

    # Prinitng Addition line.
    pushq   %rax
    pushq   %rdi
    pushq   %rsi
    pushq   %r10
    leaq    12(%r10), %rsi
    movq    $add_line, %rsi
    movq    $0, %rax
    call    printf
    popq    %r10
    popq    %rsi
    popq    %rdi
    popq    %rax

    # Prinitng Subtraction line.
    pushq   %rax
    pushq   %rdi
    pushq   %rsi
    pushq   %r10
    leal    8(%r10), %esi
    movq    $sub_line, %rsi
    movq    $0, %rax
    call    printf
    popq    %r10
    popq    %rsi
    popq    %rdi
    popq    %rax

    # Prinitng Multiplication line.
    pushq   %rax
    pushq   %rdi
    pushq   %rsi
    pushq   %r10
    leaq    20(%r10), %rsi
    movq    $mul_line, %rsi
    movq    $0, %rax
    call    printf
    popq    %r10
    popq    %rsi
    popq    %rdi
    popq    %rax

    # Prinitng Division line.
    pushq   %rax
    pushq   %rdi
    pushq   %rsi
    pushq   %rdx
    pushq   %r10
    leal    28(%r10), %esi
    leal    32(%r10), %edx
    movq    $div_line, %rsi
    movq    $0, %rax
    call    printf
    popq    %r10
    popq    %rdx
    popq    %rsi
    popq    %rdi
    popq    %rax


    decq    %rcx
    addq    $32, %r10
    test:
    cmpq    $0x0, %rcx
    jne     loop

    leave           # Necessary for the stack.
    ret             # Returning the result %rax (key).
    
.size	printlines, .-printlines
