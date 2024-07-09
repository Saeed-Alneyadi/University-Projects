import numpy as np
import re

file_name = "dataset.txt"
lines = []
with open(file_name, 'r') as file:
    # Read the file contents
    file_contents = file.read()
    # Spliting the file lines into a lines list
    lines = file_contents.split('\n')

toknized = []
sentences = []
for line in lines:
    if line != "":
        # Spliting the line into a sentence and a number string
        sentence, num_str = line.rsplit('\t', 1)

        # Spliting the sentence into a tokenzied words list
        words = re.split('\W+', sentence)

        # print(line + "/////" + number_str) #DEBUG

        # Convert the number from a string to an integer
        num = int(num_str)

        # Append the sentence to the sentences list
        sentences.append([sentence, num])

        # Add the sentence and number as a pair to the toknized list
        toknized.append([words, num])

# print(toknized[0]) #DEBUG
# print(toknized[1]) #DEBUG

# Build a dictionary of all the words in the text file
dict = []
for sentence in toknized:
    for word in sentence[0]:
        if word.lower() not in dict:
            dict.append(word.lower())

# print(dict) #DEBUG
# print(len(dict)) #DEBUG

# print(len(toknized)) #DEBUG
# print(len(dict)) #DEBUG

# Build a matrix of the words and their frequency
M = len(toknized)
N = len(dict)
D = np.zeros((M, N))
for i in range(M):
    for j in range(N):
        D[i][j] = toknized[i][0].count(dict[j])

print("\nThe number of sentences from the dataset M is: " + str(M))
print("The number of words in Dictionary N is: " + str(N))
print("The resulting frequency matrix D is: \n" + str(D))
print("The number of non-zero features for the first five sentences are: \n")
# print(lines[23]) #DEBUG
# print(sentences[23][0]) #DEBUG
for i in range(5):
    non_zero_features = D[i][D[i] != 0]
    print("\"" + sentences[i][0] + "\" --> " + str(non_zero_features))
