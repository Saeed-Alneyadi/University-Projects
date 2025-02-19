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
    - In.java: Non-terminal "in" Production Class
    - Main.java: Main class where calling recursive descent parser, printer, and semantics checker. Edited file from project 1 (ImpeccableProject1.zip).
    - Out.java: Non-terminal "out" Production Class
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
    - run_correct.sh: Created by me. Used for testing by printing all test cases in Correct directory to stdout.
    - run_error.sh: Created by me. Used for testing by printing all test cases in Error directory to stdout.

Any special features or comments on your project:
    No special features added by me. It just to do what it tends to do. The project works perfect.

A description of the overall design of the parser, including the parse tree representation:
    The parser funciton is called by the procedure function, which also run other productions class parsing functions.
    These functions will also run another productions class parsing and so on. Along this journey, it will keep checking
    parsing errors where if one of the tokens exist on the wrong place it will detect it and print error message. The
    parse tree is presented by the Java object or instance in Production class, which is P that is instaniated in Main.java, 
    specifically main method. This object contains other non-terminal objects from other productions classes, declSeq and stmtSeq. 
    These objects contains other instances from other productions classes and so on. These object refrences and tree is based on 
    the grammer given in the project2.pdf

A brief description of how you tested the parser and a list of known remaining bugs (if any):
    I tested my program through using run_correct.sh and run_error.sh. Both of these files run all the test cases
    in Correct and Error Directory. So, I check the correct test cases by comparing the printed program with the real program.
    And, I check the error test cases by detecting whether the parser or semantics methods prints any error message. After 
    successfully checking these, I ran the given tester.sh file, where it shown a great result.