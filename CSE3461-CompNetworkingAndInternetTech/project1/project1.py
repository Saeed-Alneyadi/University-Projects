# Instructor: Jim Vickory
# Student: Saeed Alneyadi
# Class Section: 7018

# Importing libraries
import configparser
import logging

# Creating log file with name "project1.log" with specific format and filemode 'w' which means write.
# Each time we print something to the console, a following logger's info command will record the printed statement.
logging.basicConfig(filename="project1.log", format='%(asctime)s: %(levelname)s %(message)s', filemode='w')
logger = logging.getLogger()

# Sets the Log level to INFO.
logger.setLevel(logging.INFO)
logger.info("project starting")
logger.info("Logging to project1.log")
logger.info("Log level set to INFO")

# Creates new instace of configparser class named "config".
config = configparser.ConfigParser()

# Stores "3461-Project1.conf" configuration file data in "config" instance.
config.read("3461-Project1.conf")

# Printing statement.
print("Configuration file contents:")
logger.info("Configuration file contents:")

# Prints the configuration file info to the console and info the log file.
for sec in config.sections():
    print("  Section: " + sec)
    logger.info("  Section: " + sec)

    for name,value in config.items(sec):
        print("    option " + name + " = " + value)
        logger.info("    option " + name + " = " + value)

# Varaibles Initialization
words = [""]
firstWord = ""
newWords = ""
count = 1

# This WHILE loop is responsible to keep prompting for a string and print the enterd string along  
# with same string but the first word capitalized. The loop stop running when the user enter a string
# with "quit" as the first word
while firstWord != "QUIT":
    # Prompting Statement
    userInput = input("project1 " + str(count) + " > ")

    words = userInput.split()
    firstWord = words.pop(0).upper()
    newWords = firstWord

    # This IF statement concatenates the rest of the words in the input with first word 
    # (uppercased) if words is greater than 0.
    if len(words) > 0:
        for word in words:
            newWords += " " + word

    # Printing statements that show what you enter and the what is the result.
    print("Entered string   = " + userInput)
    logger.info("Entered string   = " + userInput)
    print("Processed string = " + newWords)
    logger.info("Processed string = " + newWords)
    
    # Increamenting count since it used for the prompting statement.
    count += 1

# Printing statement that indicates the end of the program run.
print("Shutting down ...")
logger.info("Shutting down ...")
