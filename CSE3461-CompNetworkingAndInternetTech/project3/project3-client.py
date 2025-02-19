# Instructor: Jim Vickory
# Student: Saeed Alneyadi
# Class Section: 7018

# Importing libraries
import configparser
import logging
import socket
import sys

# Used to print the HELP commad results and ERROR or WARNING results
def printUsageMsg():
    print("\nusage:\n")
    print("\tadd <item>          - Add list item")
    print("\tcreate <list name>  - Create list")
    print("\tdelete <list name>  - Delete list")
    print("\thelp                - Help")
    print("\tquit                - Quit")
    print("\tremove <item>       - Remove list item")
    print("\tshow                - Show items\n")

# Used to check whether the request is given in a correct spelling
def reqCheck(req):
    exist = False
    commands = ["ADD", "CREATE", "DELETE", "HELP", "QUIT", "REMOVE", "SHOW"]

    for command in commands:
        if req in command:
            exist = True
            break

    return exist

# Used to check the format of the input
def reqWithParCheck(req):
    reqExist = False
    commands = ["ADD", "CREATE", "DELETE", "REMOVE"]

    for command in commands:
        if req in command:
            reqExist = True
            break

    return reqExist

# Creates new instace of configparser class named "config".
config = configparser.ConfigParser()

# Stores "3461-project3-client.conf" configuration file data in "config" instance.
config.read("3461-project3-client.conf")

# Log File Initialization
logFile = ""
logLevel = ""
logFileMode = ""
if config.has_section("logger"):
    if config.has_option("logger", "logFile"):
        logFile = config.get("logger", "logFile")
    if config.has_option("logger", "logLevel"):
        logLevel = config.get("logger", "logLevel")
    if config.has_option("logger", "logFileMode"):
        logFileMode = config.get("logger", "logFileMode")

# Creating log file with name "3461-project3-client.log" with specific format and filemode 'w' which means write.
# Each time we print something to the console, a following logger's info command will record the printed statement.
logging.basicConfig(filename=logFile, format='%(asctime)s: %(levelname)s %(message)s', filemode=logFileMode)
logger = logging.getLogger()

# Sets the Log level to INFO.
logger.setLevel(logLevel)
logger.info("···············································································")
logger.info("project3-client starting")
logger.debug("Logging to 3461-project3-client.log")
logger.debug("Log level set to " + logLevel)

# Logging the server IP and port to the log file
serverHost = ""
serverHost = config.get("project3", "serverHost")
logger.debug("serverIP is " + serverHost)
serverPort = ""
serverPort = config.get("project3", "serverPort")
logger.debug("serverPort is " + serverPort)

# Logging python version
logger.debug("Python version is " + sys.version[:3] + " (" + sys.version[:3] + ")")

# Creating Socket
clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
logger.info("Client socket object created")

# Connecting the Socket
clientSocket.connect((serverHost, int(serverPort)))
logger.info("Connected to Project 3 Server at ('" + serverHost + "', " + serverPort + ")")

# Varaibles Initialization
count = 1
clientReqStr = ""
clientReq = ""
clientPar = ""
serverResStr = ""
serverRes = ""
serverPar = ""

# This WHILE loop is responsible to keep prompting for a string and print the enterd string along  
# with same string but the first word capitalized. The loop stop running when the user enter a string
# with "quit" as the first word
while (True):
    # Prompting Statement
    userInput = input("project3-client " + str(count) + " > ")

    # Extracting the request and parameter from the user input
    words = userInput.split(" ")
    clientReq = words.pop(0).upper()
    clientPar = " ".join(words)

    # Logging the command entered
    logger.info("Command entered: " + userInput)

    # Check whether the input given have the correct command and format
    if (clientReq == ""):
        print("No command entered ...")
        logger.warning("No command entered ...")
        count += 1
        continue
    elif (clientReq.upper() == "HELP"):
        printUsageMsg()
        count += 1
        continue
    elif (not reqCheck(clientReq.upper())):
        print("Invalid command entered: " + clientReq)
        logger.warning("Invalid command entered: " + clientReq)
        printUsageMsg()
        count += 1
        continue
    elif (reqWithParCheck(clientReq) and clientPar == ""):
        print("Missing element in command: " + clientReq)
        logger.warning("Missing element in command: " + clientReq)
        printUsageMsg()
        count += 1
        continue

    # Builing client request of the request and parameter from the user to send it to the server
    clientReqStr = "{\"request\":\"" + clientReq + "\",\"parameter\":\"" + clientPar + "\"}"
    logger.info("Sending client request: " + clientReqStr)
    # "{\n\t\"request\" : \"" + request + "\",\n\t\"parameter\" : \"" + parameter + "\"\n}"

    # Sending the request
    clientSocket.sendall(str.encode(clientReqStr, encoding = "utf-8"))
    
    # Receiving the repsonse
    serverResStr = clientSocket.recv(1024).decode("utf-8")
    logger.info("Server response recieved " + serverResStr)

    # Extracitng the repsosne and the parameter from the server response
    separators = ["{", "}", "request", "parameter", "response", "\"", ":"]
    for sep in separators:
        serverResStr = serverResStr.replace(sep, "")
    serverArr = serverResStr.split(",", 1)
    serverRes = serverArr[0]
    if (len(serverArr) > 1):
        serverPar = serverArr[1]

    # Checking the sevrer response and print the appropriate result
    if (serverRes.upper() == "SHOW"):
        idx = 1
        print("\n", end="")

        for item in serverPar.split(","):
            print(str(idx) + ": " + item)
            idx += 1

        print("\n", end="")
    elif (serverRes.upper() == "WARNING" or serverRes.upper() == "ERROR"):
        print("Server " + serverRes.upper() + ": " + serverPar) # ISSUE Possibility: Solved
        logger.warning("Server " + serverRes.upper() + ": " + serverPar)
    elif (serverRes.upper() == "QUIT"):
        break
    else:
        print(serverPar) # ISSUE Possibility
        
    # Increamenting count since it used for the prompting statement.
    count += 1

# Closing Socket
clientSocket.close()

# Loggin the server shutdown message
print("\n" + serverPar)

# Printing statement that indicates the end of the program run.
print("\nClient shutting down ...")
logger.info("Client Shutting down ...")
