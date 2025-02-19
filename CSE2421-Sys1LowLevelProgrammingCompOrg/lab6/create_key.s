# BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I STRICTLY ADHERED TO THE 
# TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY.
# Name: Saeed Alneyadi.11

.file "create_key.s"
.section .rodata
.data
.align 8
.globl create_key
.text

create_key:
    movq    $0, %rbx           # Set the %rbx to zero for storing the key.
    movq    $3, %rcx           # Set the %rcx to zero for loop usage.

    pushq   %rbx
    pushq   %rcx
    call    getchar            # Calling getchar function.
    popq    %rcx
    popq    %rbx

    subq    $48, %rax          # Subtracting getchar returned value by 48.
    addq    %rax, %rbx         # Adding value in register %rax to %rbx where we store key.

    jmp     test               # Jumping to test label to check loop codition.
    loop:
        shll    $1, %ebx           # Shifting the %edx (key) 1 bit to the left.

        pushq   %rbx
        pushq   %rcx
        call    getchar            # Calling getchar function.
        popq    %rcx
        popq    %rbx

        subq    $48, %rax          # Subtracting getchar returned value by 48.
        addq    %rax, %rbx         # Adding value in register %rax to %rbx where we store key.
        decq    %rcx               # decremnting the %rcx (counter) by 1.
    test:
        cmpq    $0, %rcx       # Subtracts %rcx (counter) value by zero which affect condition flags.
        jne     loop           # Jumps to loop label if %ecx is not zero.
        
    movq    %rbx, %rdx
    shll    $4, %ebx           # Shifting the %ebx (key) 4 bits to the left.
    xorq    %rdx, %rbx         # Xoring the %rbx (key) with itself.
    movq    %rbx, %rax         # Set the %rax to rbx value (key) to return it.

    ret                        # Returning the result %rax (key).
