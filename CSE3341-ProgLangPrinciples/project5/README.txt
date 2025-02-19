Name: Saeed Alneyadi.11

Files Submitted:
    - Correct (Directory): Contains correct test cases (New test cases added by me).
    - Assign.java: Non-terminal "assign" Production Class
    - Call.java: Non-terminal "call" Production Class
    - Cmpr.java: Non-terminal "cmpr" Production Class
    - Cond.java: Non-terminal "cond" Production Class
    - Core.java: Holds enum core.
    - Decl.java: Non-terminal "decl" Production Class
    - DeclArray.java: Non-terminal "declArray" Production Class
    - DeclInteger.java: Non-terminal "declInteger" Production Class
    - DeclSeq.java: Non-terminal "declSeq" Production Class
    - Expr.java: Non-terminal "expr" Production Class
    - Factor.java: Non-terminal "factor" Production Class
    - Function.java: Non-terminal "factorunction" Production Class
    - Id.java: Non-terminal "id" Production Class
    - If.java: Non-terminal "if" Production Class
    - In.java: Non-terminal "in" Production Class
    - Loop.java: Non-terminal "loop" Production Class
    - Main.java: Main class where calling recursive descent parser, printer, semantics checker, and execution.
    - Out.java: Non-terminal "out" Production Class
    - Parameters.java: Non-terminal "parameters" Production Class
    - Parser.java: Class used for parsing and building the parsing tree.
    - Procedure.java: Non-terminal "procedure" Production Class
    - README.txt: This file.
    - run.sh: Created by me. Used for testing by printing all test cases wrttien in Correct directory to stdout.
    - Scanner.java: Scanner class that tokenize the input program.
    - Stmt.java: Non-terminal "stmt" Production Class
    - StmtSeq.java: Non-terminal "StmtSeq" Production Class
    - Term.java: Non-terminal "term" Production Class
    - tester.sh: Given from project2.zip.

Any special features or comments on your project:
    No special features added by me. It just to do what it tends to do. The project works perfect.

A brief description of how you tested the interpreter and a list of known remaining bugs (if any):
    No remaining bugs. Everything works well. I've tested the interpreter manually by running "run.sh". And then check for 
    every test case by running "tester.sh". I've added 5 test cases where each ensure the rules indicated in "3341_Project_5.pdf".
    One of the bugs incounterd is the one related to the @totalObjCount. This variable show wrong results because of incermenting 
    and decermenting in the wrong code section and it is the one printed when the program runs. To fix this, I run through test 
    cases line by line to see how this variable is affected along or anything else that could result in wrong result. Also, I've 
    went to the code section where indiviual reference count of different variables changes. Usually, the @totalObjCount changes 
    when changes happen to the invidual reference count. By doing these ways, i was able to fix this bug and show great results. 
    It was helpful to learn how to write ".sh" file where I created "run.sh".