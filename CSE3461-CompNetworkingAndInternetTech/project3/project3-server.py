# Instructor: Jim Vickory
# Student: Saeed Alneyadi
# Class Section: 7018

# Importing libraries
import configparser
import logging
import socket
import json
import os
import sys

# Creates new instace of configparser class named (config).
config = configparser.ConfigParser()

# Stores "3461-project3-server.conf" configuration file data in "config" instance.
config.read("3461-project3-server.conf")

# Getting log file info from configuration file
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

# Creating log file with name "alneyadi.11-project3-server.log" with specific format and filemode.
# Each time we print something to the console, a following logger's info command will record the printed statement.
logging.basicConfig(filename=logFile, format='%(asctime)s: %(levelname)s %(message)s', filemode=logFileMode)
logger = logging.getLogger()

# Sets the Log level to INFO.
logger.setLevel(logLevel)
logger.info("···············································································")
logger.info("project3-server starting")
logger.debug("Logging to 3461-project3-server.log")
logger.debug("Log level set to DEBUG")

# Logging the server IP and server port
serverHost = ""
serverHost = config.get("project3", "serverHost")
logger.debug("serverIP is " + serverHost)
serverPort = ""
serverPort = config.get("project3", "serverPort")
logger.debug("serverPort is " + serverPort)

logger.debug("Python version is " + sys.version[:3] + " (" + sys.version[:3] + ")")

# Creating server socket
serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
logger.info("Opend TCP socket")
serverSocket.bind((serverHost,int(serverPort)))

# Printing statement that indicates the start of the server run.
print("Server starting on " + serverHost)
logger.info("Server starting on " + serverHost)
logger.info("Bound to port " + serverPort)

# Listening ...
serverSocket.listen()
logger.info("Listening for client connections")

# Connection Acceptance
(clientConn, clientAdr) = serverSocket.accept()
logger.info("Accepted connection from " + str(clientAdr))

# Create new list or loading saved list in "list.json" file
listExists = False
listName = "Unnamed"
list = []
if os.path.exists("list.json"):
    with open("list.json", "r") as file:
        list = json.load(file)
        listName = list.pop(0)
        listExists = list.pop(0)

# Variables Initalization
clientReqStr = ""
clientReq = ""
clientPar = ""
serverResStr = ""
serverRes = ""
serverPar = ""
while (True):
    # Client Request
    clientReqStr = clientConn.recv(1024)
    clientReqStr = clientReqStr.decode("utf-8")

    # Logging Client Request
    logger.info("Client request received " + clientReqStr)

    # Extracting the request and the parameter of the Client Request
    separators = ["{", "}", "request", "parameter", "\"", ":"]
    for sep in separators:
        clientReqStr = clientReqStr.replace(sep, "")
    clientArr = clientReqStr.split(",")
    clientReq = clientArr[0]
    if (len(clientArr) > 1):
        clientPar = clientArr[1]

    # Logging the processed Client Request
    logger.info("Processing client request " + clientReq.upper())

    # Quitting code
    if (clientReq.upper() == "QUIT"):
        break

    # Request Command Code handler
    if (clientReq.upper() == "CREATE"):

        if (listExists):
            serverRes = "WARNING"
            serverPar = "List " + listName + " already created"
            logger.debug("List " + listName + " already created")
        else:
            listExists = True
            listName = clientPar
            list = []
            logger.debug("List " + listName + " created")
            serverRes = clientReq.upper()
            serverPar = "\nCreated list " + clientPar + "\n"
    
    elif (clientReq.upper() == "DELETE"):

        if (listExists and (listName == clientPar)):
            serverRes = clientReq.upper()
            serverPar = "\nList " + listName + " deleted\n"
            logger.debug("List " + listName + " deleted")
            listExists = False
            listName = "Unnamed"
            list = []
        else:
            serverRes = "WARNING"
            serverPar = "List " + clientPar + " does not exist"
            logger.debug("List " + clientPar + " does not exist")

    elif (clientReq.upper() == "ADD"):

        if (listExists):
            list.append(clientPar)
            serverRes = clientReq.upper()
            serverPar = "\nItem " + clientPar + " added\n"
            logger.debug("Item " + clientPar + " added")
        else:
            serverRes = "ERROR"
            serverPar = "No list to add item to"
            logger.warning("No list to add item to")

    elif (clientReq.upper() == "REMOVE"):

        if (listExists):
            if (clientPar in list):
                list.remove(clientPar)
                serverRes = clientReq.upper()
                serverPar = "\nItem " + clientPar + " removed\n"
                logger.debug("List item " + clientPar + " removed")
            else:
                serverRes = "WARNING"
                serverPar = "Item " + clientPar + " not in the list"
                logger.debug("List item " + clientPar + " not in the list")
        else:
            logger.warning("No list to remove item from")
            serverRes = "ERROR"
            serverPar = "No list to remove item from"

    elif (clientReq.upper() == "SHOW"):

        if (listExists and len(list) > 0):
            serverRes = clientReq.upper()
            serverPar = ','.join(list)
            logger.debug("List items gathered")
        else:
            serverRes = "WARNING"
            serverPar = "List"

            if (len(list) == 0):
                serverPar = serverPar + " empty"
                logger.debug(serverPar)
            else:
                serverPar = " " + serverPar + listName + " does not exist"
                logger.debug(serverPar)
    else:

        serverRes = "ERROR"
        serverPar = "Request is not supported"
        logger.error("Request is not supported")
        
    serverResStr = "{\"response\":\"" + serverRes + "\",\"parameter\":\"" + serverPar + "\"}"

    logger.info("Sending server response " + serverResStr)

    clientConn.sendall(str.encode(serverResStr, encoding = "utf-8"))

# Saving the list if it is 
list.insert(0, listExists)
list.insert(0, listName)
with open("list.json", "w") as f:
    json.dump(list, f)

# Setting the server response and parameter.
serverRes = clientReq
serverPar = "Server shutting down"
serverResStr = "{\"request\":\"" + serverRes + "\",\"parameter\":\"" + serverPar + "\"}"

# Sending the server response to the client
clientConn.sendall(str.encode(serverResStr, encoding = "utf-8"))

# Logging server response
logger.info("Sending server response " + serverResStr)

# Closing Sockets
clientConn.close()
serverSocket.close()

# Printing statement that indicates the end of the server run.
print("Server Shutting down ...")
logger.info("Server Shutting down ...")
