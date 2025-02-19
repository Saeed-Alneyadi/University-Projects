Name: Saeed Alneyadi

Files Submitted:
    - Correct (Directory): Contains correct test cases (New test cases added by me).
    - Error (Directory): Contains error test cases (New test cases added by me).
    - 3341_Project_4.pdf: The given document to explain the project.
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
    - Input.java: Non-terminal "in" Production Class
    - Loop.java: Non-terminal "loop" Production Class
    - Main.java: Main class where calling recursive descent parser, printer, and semantics checker.
    - Output.java: Non-terminal "out" Production Class
    - Parameters.java: Non-terminal "parameters" Production Class
    - Parser.java: Class used for parsing and building the parsing tree.
    - Procedure.java: Non-terminal "procedure" Production Class
    - README.txt: This file.
    - run.sh: Created by me. Used for testing by printing all test cases in Correct directory to stdout.
    - Scanner.java: Scanner class that tokenize the input program.
    - Stmt.java: Non-terminal "stmt" Production Class
    - StmtSeq.java: Non-terminal "StmtSeq" Production Class
    - Term.java: Non-terminal "term" Production Class
    - tester.sh: Given from project2.zip.

Any special features or comments on your project:
    No special features added by me. It just to do what it tends to do. The project works perfect.

A description of the overall design of the parser, including the parse tree representation:
    The parse and execute funcitosn are called by the main function, which also run other productions class parse and execute functions.
    These functions will also run another productions class parsing and execution and so on. Along this journey, these rules will be checked:
    
    By parse():-
    1) Ensure the syntax rules of the new grammar are being followed.

    By execute():-
    1) Every function should have a unique name (no overloading).
    2) Each function call has a valid target.
    3) Your parser should check that the formal parameters of a function are distinct from each other. 
       You do not need to implement checks to verify the arguments are all distinct or are all array variables, 
       we will consider using integer variables as arguments as undefined by the language.
    
    Also, about p object, this object contains other non-terminal objects from other productions classes, declSeq and stmtSeq. 
    These objects contains other instances from other productions classes and so on. These object refrences and tree is based on 
    the grammer given in the project4.pdf. Because of this tree structure, I was able to run recursive descent algorithm by calling 
    parsing and execute functions. Parse funciton create the parsing tree and check for semantics. Execite function run the program 
    and detect any runtime error that will stop the execution.

A brief description of how you tested the interpreter and a list of known remaining bugs (if any):
    No remaining bugs. Everything works well. I've tested the interpreter manually by running "run.sh". And then check for 
    every test case by running "tester.sh". I've added 8 test cases where each ensure the rules indicated in "3341_Project_4.pdf".
    One of the bugs incounterd is the one related to stack of frames. When there is recursion inside the inputted Core code, 
    The execution run successful until the phase when popping the stack inside the stack of frames where one of the functions
    in memory is resposible to popping the first stack inside frames stack. Instead, it pops the frames which cause Java Runtime Error 
    (empty stack exception). Fixed by added the work peek() after local which the led running in the intended way.