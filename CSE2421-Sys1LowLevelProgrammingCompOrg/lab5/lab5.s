.file "lab5.s"
.globl main
	.type	main, @function

.text
main:
	pushq %rbp		#stack housekeeping
	movq %rsp, %rbp

Label1:
#as you go through this program note the changes to %rip
	movq	$0x8877665544332211, %rax	# the value of %rax is:

		# Recall that -1 is represented as 0xff, 0xffff, etc. depending upon the size of the value
	movb	$-1, %al			# the value of %rax is:
	movw	$-1, %ax			# the value of %rax is:
	movl	$-1, %eax			# the value of %rax is:
	movq	$-1, %rax			# the value of %rax is:

	movl	$-1, %eax			# the value of %rax is:
	cltq					# the value of %rax is:

	movl	$0x7fffffff, %eax		# the value of %rax is:
	cltq					# the value of %rax is:
	movl	$0x8fffffff, %eax		# the value of %rax is:
	cltq					# the value of %rax is:
						# what do you think the cltq instruction does?

	movq	$0x8877665544332211, %rax	# the value of %rax is:

						# the value of %rdx *before* movb $0xAA, %dl executes is:
	# Note the value of the 8-byte register values vs the 1, 2, or 4-byte register values
	# How does each size instruction suffix affect the 8-byte register?  Don't write answers here; you'll need this info later.
	movb	$0xAA, %dl			# the value of %rdx is:
	movb	%dl, %al			# the value of %rax is:
	movsbw	%dl, %ax			# the value of %rax is:
	movzbw	%dl, %ax			# the value of %rax is:

	movq	$0x8877665544332211, %rax	# the value of %rax is:
	movb 	%dl, %al			# the value of %rax is:
	movsbl	%dl, %eax			# the value of %rax is:
	movzbl	%dl, %eax			# the value of %rax is:

	movq	$0x8877665544332211, %rax	# the value of %rax is:
	movb 	%dl, %al			# the value of %rax is:
	movsbq	%dl, %rax			# the value of %rax is:
	movzbq	%dl, %rax			# the value of %rax is:

	movq	$0x8877665544332211, %rax	# the value of %rax is:
	# the value of %rdx *before* movb $0x55, %dl executes is:
	movb	$0x55, %dl			# the value of %rdx is:
	movb	%dl, %al			# the value of %rax is:
	movsbw	%dl, %ax			# the value of %rax is:
	movzbw	%dl, %ax			# the value of %rax is:

	movq	$0x8877665544332211, %rax	# the value of %rax is:
	movb 	%dl, %al			# the value of %rax is:
	movsbl	%dl, %eax			# the value of %rax is:
	movzbl	%dl, %eax			# the value of %rax is:

	movq	$0x8877665544332211, %rax	# the value of %rax is:
	movb 	%dl, %al			# the value of %rax is:
	movsbq	%dl, %rax			# the value of %rax is:
	movzbq	%dl, %rax			# the value of %rax is:

#	movq	$0x8877665544332211, %rax	
#	pushb	%al
#	movq	$0, %rax
#	popb	%al				

	movq	$0x8877665544332211, %rax	# the value of %rax is:		the value of %rsp is:
	pushw	%ax				# the value of %rsp is:
						# the difference between the two values of %rsp is:
	movq	$0, %rax			# the value of %rax is:
	popw	%ax				# the value of %rax is:		How did the value of %rsp change?

	movq	$0x8877665544332211, %rax	# the value of %rax is:		the value of %rsp is:
	pushw	%ax				# the value of %rsp is:
						# the difference between the two values of %rsp is:
	movq	$-1, %rax			# the value of %rax is:
	popw	%ax				# the value of %rax is:		How did the value of %rsp change?


#	movq	$0x8877665544332211, %rax	
#	pushl	%eax
#	movq	$0, %rax
#	popl	%eax				

	movq	$0x8877665544332211, %rax	# the value of %rax is:		the value of %rsp is:
	pushq	%rax				# the value of %rsp is:
						# the difference between the two values of %rsp is:
	movq	$0, %rax			# the value of %rax is:
	popq	%rax				# the value of %rax is:		How did the value of %rsp change?

						# what rflags are set?

	movq	$0x500, %rax			# the value of %rax is:
	movq	$0x123, %rcx			# the value of %rcx is:
	# 0x123 - 0x500
	subq	%rax, %rcx			# the value of %rax is:
						# the value of %rcx is:

						# what rflags are set?

	movq 	$0x500, %rax			# the value of %rax is:
	movq	$0x123, %rcx			# the value of %rcx is:
	# 0x500 - 0x123
	subq	%rcx, %rax			# the value of %rax is:
						# what rflags are set?

	movq	$0x500, %rax			# the value of %rax is:
	movq	$0x500, %rcx			# the value of %rcx is:
	# 0x500 - 0x500
	subq	%rcx, %rax			# the value of %rax is:
						# what rflags are set?

	movb	$0xff, %al			# the value of %rax is:
	# 0xff +=1 (1 byte)
	incb	%al				# the value of %rax is:		what rflags are set?

	movb	$0xff, %al			# the value of %rax is:
	# 0xff +=1 (4 bytes)
	incl	%eax				# the value of %rax is:		what rflags are set?

	movq	$-1, %rax			# the value of %rax is:
	# 0xff +=1 (8 bytes)
	incq	%rax				# the value of %rax is:		what rflags are set?

	movq	$0x8877665544332211, %rax	# the value of %rax is:		
	movq	$0x8877665544332211, %rcx	# the value of %rax is:		what rflags are set?
	addq	%rcx, %rax			# the value of %rax is:		what rflags are set?

	movq 	$0x8877665544332211, %rax	# the value of %rax is:		
	andq	$0x1, %rax			# the value of %rax is:

	movq 	$0x8877665544332211, %rax	# the value of %rax is:		explain why the values for AND/OR/XOR are
	andq	%rax, %rax			# the value of %rax is:		what they are
	orq	%rax, %rax			# the value of %rax is:
	xorq	%rax, %rax			# the value of %rax is:

	movq 	$0x8877665544332211, %rax	# the value of %rax is:		
	andw	$0x3300, %ax			# the value of %rax is:		explain the value in the 8 byte register vs
									       #the value in the 2 byte register

	salq	$4, %rax			# the value of %rax is:		Why?

	movq	$0xff0000001f000000, %rax	# the value of %rax is:		to help you understand what's happening in this part of the code, write the value in %rax in binary
						#                               on a piece of scratch paper for the remaining instructions in this file
						#				and watch the bits move as each shift instruction occurs.
						#				You should notice how each of the 1-, 2-, 4-, and 8-byte shift instructions works
						#				within the 8-byte register.
	sall	$1, %eax			# the value of %rax is:		do these shift instructions do what you expected?
	sall	$1, %eax			# the value of %rax is:
	sall	$1, %eax			# the value of %rax is:
	sall	$1, %eax			# the value of %rax is:
	sall	$1, %eax			# the value of %rax is:

	movq	$0xff000000ff000000, %rax	# the value of %rax is:	
	salq	$1, %rax			# the value of %rax is:
	salq	$1, %rax			# the value of %rax is:
	salq	$1, %rax			# the value of %rax is:
	salq	$1, %rax			# the value of %rax is:
	salq	$1, %rax			# the value of %rax is:

	movq	$0xff000000000000ff, %rax	# the value of %rax is:	
	sarq	$1, %rax			# the value of %rax is:
	sarq	$1, %rax			# the value of %rax is:
	sarq	$1, %rax			# the value of %rax is:
	sarq	$1, %rax			# the value of %rax is:
	sarq	$1, %rax			# the value of %rax is:

	movq	$0xff000000000000ff, %rax	# the value of %rax is:
	shrq	$1, %rax			# the value of %rax is:
	shrq	$1, %rax			# the value of %rax is:
	shrq	$1, %rax			# the value of %rax is:
	shrq	$1, %rax			# the value of %rax is:
	shrq	$1, %rax			# the value of %rax is:

	movq	$0xff000000000000ff, %rax	# the value of %rax is:
	sarw	$1, %ax				# the value of %rax is:
	sarw	$1, %ax				# the value of %rax is:
	sarw	$1, %ax				# the value of %rax is:
	sarw	$1, %ax				# the value of %rax is:
	sarw	$1, %ax				# the value of %rax is:

	movq	$0xff000000000000ff, %rax	# the value of %rax is:
	shrw	$1, %ax				# the value of %rax is:
	shrw	$1, %ax				# the value of %rax is:
	shrw	$1, %ax				# the value of %rax is:
	shrw	$1, %ax				# the value of %rax is:
	shrw	$1, %ax				# the value of %rax is:


	leave					#post function stack cleanup
	ret

.size	main, .-main
