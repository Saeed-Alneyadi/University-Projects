# Instructor: Jim Vickory
# Student: Saeed Alneyadi
# Class Section: 7018

# Importing libraries
import configparser
import logging
import socket
import time

# Creates new instace of configparser class named (config).
config = configparser.ConfigParser()

# Stores "3461-project2-server.conf" configuration file data in "config" instance.
config.read("3461-project2-server.conf")

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

# Creating log file with name "alneyadi.11-project2-server.log" with specific format and filemode.
# Each time we print something to the console, a following logger's info command will record the printed statement.
logging.basicConfig(filename=logFile, format='%(asctime)s: %(levelname)s %(message)s', filemode=logFileMode)
logger = logging.getLogger()

# Sets the Log level to INFO.
logger.setLevel(logLevel)
logger.info("···············································································")
logger.info("project2-server starting")
logger.debug("Logging to 3461-project2-server.log")
logger.debug("Log level set to DEBUG")

serverHost = ""
serverHost = config.get("project2", "serverHost")
logger.debug("serverIP is " + serverHost)
serverPort = ""
serverPort = config.get("project2", "serverPort")
logger.debug("serverPort is " + serverPort)


# Creating server socket
serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
logger.info("Opend TCP socket")
serverSocket.bind((serverHost,int(serverPort)))
# Printing statement that indicates the start of the server run.
print("Server starting on " + serverHost)
logger.info("Server starting on " + serverHost)
logger.info("Bound to port " + serverPort)

serverSocket.listen()
logger.info("Listening for client connections")

(clientConn, clientAdr) = serverSocket.accept()
logger.info("Accepted connection from " + str(clientAdr))

clientReqStr = ""
clientReq = ""
clientPar = ""
serverResStr = ""
serverRes = ""
serverPar = ""
while (True):
    clientReqStr = clientConn.recv(1024)
    clientReqStr = clientReqStr.decode("utf-8")

    logger.info("Client request received " + clientReqStr)

    separators = ["{", "}", "request", "parameter", "\"", ":"]
    for sep in separators:
        clientReqStr = clientReqStr.replace(sep, "")

    clientArr = clientReqStr.split(",")
    clientReq = clientArr[0]
    if (len(clientArr) > 1):
        clientPar = clientArr[1]
    
    if (clientReq.upper() == "QUIT"):
        break

    serverRes = clientReq
    serverPar = clientPar + " (" + str(int(time.time())) + ")"
    serverResStr = "{\"request\":\"" + serverRes + "\",\"parameter\":\"" + serverPar + "\"}"

    logger.info("Sending server response " + serverResStr)

    clientConn.sendall(str.encode(serverResStr, encoding = "utf-8"))
    

serverResStr = "{\"request\":\"" + clientReq + "\",\"parameter\":\"Server shutting down\"}"
clientConn.sendall(str.encode(serverResStr, encoding = "utf-8"))

logger.info("Sending server response " + serverResStr)

clientConn.close()
serverSocket.close()

# Printing statement that indicates the end of the server run.
print("Server Shutting down ...")
logger.info("Server Shutting down ...")
