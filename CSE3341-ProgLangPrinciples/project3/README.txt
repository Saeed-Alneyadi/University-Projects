Name: Saeed Alneyadi

Files Submitted:
    - Assign.java: Non-terminal "assign" Production Class
    - Cmpr.java: Non-terminal "cmpr" Production Class
    - Cond.java: Non-terminal "cond" Production Class
    - Core.java: Holds enum core. File from project 1 (ImpeccableProject1.zip)
    - Decl.java: Non-terminal "decl" Production Class
    - DeclArray.java: Non-terminal "declArray" Production Class
    - DeclInteger.java: Non-terminal "declInteger" Production Class
    - DeclSeq.java: Non-terminal "declSeq" Production Class
    - Expr.java: Non-terminal "expr" Production Class
    - Factor.java: Non-terminal "factor" Production Class
    - If.java: Non-terminal "if" Production Class
    - Input.java: Non-terminal "in" Production Class
    - Main.java: Main class where calling recursive descent parser, printer, and semantics checker. Edited file from project 1 (ImpeccableProject1.zip).
    - Output.java: Non-terminal "out" Production Class
    - Procedure.java: Non-terminal "in" Production Class
    - README.txt: This file.
    - Scanner.java: Scanner class that tokenize the input program. File from project 1 (ImpeccableProject1.zip)
    - Stmt.java: Non-terminal "stmt" Production Class
    - StmtSeq.java: Non-terminal "StmtSeq" Production Class
    - Term.java: Non-terminal "term" Production Class
    - tester.sh: Given from project2.zip.
    - While.java: Non-terminal "while" Production Class
    - Correct (Directory): Contains correct test cases.
    - Error (Directory): Contains error test cases.
    - run.sh: Created by me. Used for testing by printing all test cases in Correct directory to stdout.

Any special features or comments on your project:
    No special features added by me. It just to do what it tends to do. The project works perfect.

A description of the overall design of the parser, including the parse tree representation:
    The execute funciton is called by the main function, which also run other productions class execute functions.
    These functions will also run another productions class execution and so on. Along this journey, it will keep checking
    exectuing and checking for some errors:
    
    1) Input stream reaches the end and the input function ask for more input.
    2) Division by zero
    3) Array Errors: Accessing any index of a null array and outside of the valid indexes.
    
    Also, about P object, this object contains other non-terminal objects from other productions classes, declSeq and stmtSeq. 
    These objects contains other instances from other productions classes and so on. These object refrences and tree is based on 
    the grammer given in the project3.pdf. Because of this tree structure, I was able to run recursive descent algorithm by calling 
    execute which run the program and detect any runtime error that will stop the execution.

A brief description of how you tested the interpreter and a list of known remaining bugs (if any):
    No remaining bugs. Everything works well. I've tested the interpreter manually by running "run.sh". And then check for 
    every test case by running "tester.sh". I've added 9 test cases where each show the semantics indicated by the "project3.pdf".
    One of the bugs encounterd is the one related to scopes. The bug was if there is a variable decleared inside the nested scope, 
    it can be accessed even outside the scope (after the scope script). It happens because I used the firstElement() function 
    to access the current scope map from the stack but it access the first nested local scope even if there deeper ones. To fix this, 
    I used the correct function to access the map on top of the stack which is the correct map to aceess. This fixed the issue.