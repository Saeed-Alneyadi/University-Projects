# Instructor: Jim Vickory
# Student: Saeed Alneyadi
# Class Section: 7018

# Importing libraries
import configparser
import logging
import socket

# Creates new instace of configparser class named "config".
config = configparser.ConfigParser()

# Stores "3461-project2-client.conf" configuration file data in "config" instance.
config.read("3461-project2-client.conf")

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

# Creating log file with name "3461-project2-client.log" with specific format and filemode 'w' which means write.
# Each time we print something to the console, a following logger's info command will record the printed statement.
logging.basicConfig(filename=logFile, format='%(asctime)s: %(levelname)s %(message)s', filemode=logFileMode)
logger = logging.getLogger()

# Sets the Log level to INFO.
logger.setLevel(logLevel)
logger.info("···············································································")
logger.info("project2-client starting")
logger.debug("Logging to 3461-project2-client.log")
logger.debug("Log level set to " + logLevel)

serverHost = ""
serverHost = config.get("project2", "serverHost")
logger.debug("serverIP is " + serverHost)

serverPort = ""
serverPort = config.get("project2", "serverPort")
logger.debug("serverPort is " + serverPort)

clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
logger.info("Client socket object created")

clientSocket.connect((serverHost, int(serverPort)))
logger.info("Connected to Project 2 Server at ('" + serverHost + "', " + serverPort + ")")

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
    userInput = input("project2-client " + str(count) + " > ")

    logger.info("Command received: " + userInput)

    words = userInput.split(" ")
    clientReq = words.pop(0).upper()
    clientPar = " ".join(words)

    if (clientReq == ""):
        print("No command entered ...")
        logger.info("No command entered ...")
        count += 1
        continue

    clientReqStr = "{\"request\":\"" + clientReq + "\",\"parameter\":\"" + clientPar + "\"}"
    logger.info("Sending client request: " + clientReqStr)
    # "{\n\t\"request\" : \"" + request + "\",\n\t\"parameter\" : \"" + parameter + "\"\n}"

    clientSocket.sendall(str.encode(clientReqStr, encoding = "utf-8"))
    
    serverResStr = clientSocket.recv(1024).decode("utf-8")
    logger.info("Server response recieved " + serverResStr)

    separators = ["{", "}", "request", "parameter", "\"", ":"]
    for sep in separators:
        serverResStr = serverResStr.replace(sep, "")
    
    serverArr = serverResStr.split(",")
    serverRes = serverArr[0]
    if (len(serverArr) > 1):
        serverPar = serverArr[1]

    print("Response received: " + serverPar)

    if (serverRes.upper() == "QUIT"):
        break
    # Increamenting count since it used for the prompting statement.
    count += 1

clientSocket.close()

# Printing statement that indicates the end of the program run.
print("Client shutting down ...")
logger.info("Client Shutting down ...")
