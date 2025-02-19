# BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I STRICTLY ADHERED TO THE 
# TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY.
# THIS IS THE readlines.s FILE FOR LAB 7.
# Name: Saeed Alneyadi.11

.file "readlines.s"
.section .rodata
scan_line:
.string "%i %i\n"
.data
.align 8
.globl readlines
        .type readlines, @function
.text

readlines:
    movq    %rdx, %r10
    movq    %rsi, %r11

    # Loop
    jmp     test
    loop:
    # Calling fscanf
    pushq   %rax
    pushq   %rsi
    pushq   %rdx
    pushq   %r11
    movq    (%r11), %rdx
    movq    4(%r11), %rcx
    movq    $scan_line, %rsi
    call    fscanf
    popq    %r11
    popq    %rdx
    popq    %rsi
    popq    %rax
    
    decq    %r10
    addq    $32, %r11
    test:
    # Loop Condition
    cmpq    $0x0, %r10
    jne     loop

    leave           # Necessary for the stack.
    ret             # Returning the result %rax (key).

.size	readlines, .-readlines
