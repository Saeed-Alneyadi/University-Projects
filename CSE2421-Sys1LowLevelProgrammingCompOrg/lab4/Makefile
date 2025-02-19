# BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE STRICTLY ADHERED TO THE TENURES OF THE 
# OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY.

# Student Name: Saeed Alneyadi.11

gcc_opt = -ansi -pedantic -Wimplicit-function-declaration -Wreturn-type -g -c

all: lab4.zip lab4

# lab4.zip

lab4.zip: Makefile lab4main.o LAB4README insert_node.o delete_node.o date_diff.o build_list.o print_list.o tot_rev.o tot_WS_cost.o tot_WS_sale.o tot_profit.o tot_groc_sold.o avg_profit.o groc_in_stk.o out_of_stk_groc.o dep_groc_list.o add_groc.o remove_groc.o print_list.o
	zip $@ $^ *.c *.h *.exe

# lab4main.o

lab4: lab4main.o insert_node.o delete_node.o date_diff.o build_list.o print_list.o tot_rev.o tot_WS_cost.o tot_WS_sale.o tot_profit.o tot_groc_sold.o avg_profit.o groc_in_stk.o out_of_stk_groc.o dep_groc_list.o add_groc.o remove_groc.o print_list.o
	gcc $^ -o $@

lab4main.o: lab4main.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# insert_node.o

insert_node: insert_node.o
	gcc $< -o $@

insert_node.o: insert_node.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# delete_node.o

delete_node: delete_node.o
	gcc $< -o $@

delete_node.o: delete_node.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# date_diff.o

date_diff: date_diff.o
	gcc $< -o $@

date_diff.o: date_diff.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# build_list.o

build_list: build_list.o
	gcc $< -o $@

build_list.o: build_list.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# print_list.o

print_list: print_list.o
	gcc $< -o $@

print_list.o: print_list.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# tot_rev.o

tot_rev: tot_rev.o
	gcc $< -o $@

tot_rev.o: tot_rev.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# tot_WS_cost.o

tot_WS_cost: tot_WS_cost.o
	gcc $< -o $@

tot_WS_cost.o: tot_WS_cost.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# tot_WS_sale.o

tot_WS_sale: tot_WS_sale.o
	gcc $< -o $@

tot_WS_sale.o: tot_WS_sale.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# tot_profit.o

tot_profit: tot_profit.o
	gcc $< -o $@

tot_profit.o: tot_profit.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# tot_groc_sold.o

tot_groc_sold: tot_groc_sold.o
	gcc $< -o $@

tot_groc_sold.o: tot_groc_sold.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# avg_profit.o

avg_profit: avg_profit.o
	gcc $< -o $@

avg_profit.o: avg_profit.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# groc_in_stk.o

groc_in_stk: groc_in_stk.o
	gcc $< -o $@

groc_in_stk.o: groc_in_stk.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# out_of_stk_groc.o

out_of_stk_groc: out_of_stk_groc.o
	gcc $< -o $@

out_of_stk_groc.o: out_of_stk_groc.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# dep_groc_list.o

dep_groc_list: dep_groc_list.o
	gcc $< -o $@

dep_groc_list.o: dep_groc_list.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# add_groc.o

add_groc: add_groc.o
	gcc $< -o $@

add_groc.o: add_groc.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# remove_groc.o

remove_groc: remove_groc.o
	gcc $< -o $@

remove_groc.o: remove_groc.c lab4.h
	gcc $(gcc_opt) -o $@ $<

# print_list.o

print_list: print_list.o
	gcc $< -o $@

print_list.o: print_list.c lab4.h
	gcc $(gcc_opt) -o $@ $<

clean:
	rm -rf *.o lab4 lab4.zip
