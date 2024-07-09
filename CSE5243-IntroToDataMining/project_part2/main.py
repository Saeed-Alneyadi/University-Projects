import numpy as np
import re
import time
from sklearn.metrics import accuracy_score
from sklearn.naive_bayes import GaussianNB
from sklearn import svm

# HW2 START

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

# HW2 Priting Statements
'''
print("\nThe number of sentences from the dataset M is: " + str(M))
print("The number of words in Dictionary N is: " + str(N))
print("The resulting frequency matrix D is: \n" + str(D))
print("The number of non-zero features for the first five sentences are: \n")
# print(lines[23]) #DEBUG
# print(sentences[23][0]) #DEBUG
for i in range(5):
    non_zero_features = D[i][D[i] != 0]
    print("\"" + sentences[i][0] + "\" --> " + str(non_zero_features))
'''

# HW2 END

# HW3 START

# HW3.1.2.2 START

# Initializing the spliting ratios for the training, validation, and testing sets
p = 0.70
q = 0.15
r = 0.15

# Calculating the sizes of each training, validation, and testing sets
M = D.shape[0]
M_train = int(p * M)
M_val = int(q * M)
M_test = int(r * M)

# Spliting the matrix D into training, validation, and testing matrices
D_train = D[:M_train]
D_val = D[M_train:M_train + M_val]
D_test = D[M_train + M_val:]

# print(M_train) #DEBUG
# print(M_val) #DEBUG
# print(M_test) #DEBUG

# HW3.1.2.2 END

# HW3.1.2.3 START

# Initializing the frequency-based selection variable k
top_k = 500
# top_k = 20 #DEBUG

# Removing the word 'the' and 'a' from the D matrix
the_index = dict.index('the')
a_index = dict.index('a')
D_temp = np.delete(D, [the_index, a_index], axis=1)

# Summing the frequency of each word for all the sentences
tot_freq_per_word = np.sum(D_temp, axis=0)

# print(tot_freq_per_word) #DEBUG

# Keep the k most frequent words indexes
k_most_freq_words = np.argsort(tot_freq_per_word)[-top_k:]

# print(k_most_freq_words)  # DEBUG

# Selecting the k most frequent words from the D_temp matrix
D_prun = D_temp[:, k_most_freq_words]

# print(D_prun)  # DEBUG
# print(D_the_a_removed.shape)  # DEBUG
# print(D_prun.shape)  # DEBUG

# Calculating the sizes of each training, validation, and testing sets
M = D_prun.shape[0]
M_train = int(p * M)
M_val = int(q * M)
M_test = int(r * M)

# Spliting the matrix D_prun into training, validation, and testing matrices
D_train_prun = D_prun[:M_train]
D_val_prun = D_prun[M_train:M_train + M_val]
D_test_prun = D_prun[M_train + M_val:]

# print("\n") #DEBUG
# print(M_train) #DEBUG
# print(M_val) #DEBUG
# print(M_test) #DEBUG

# hW3.1.2.3 END

# HW3.1.2.4 START

# KNN Classifier START

print("\nKNN Classifier:")
print("-----------------------")

# Gives the accuracy of KNN model at k
def KNN(D_train, D_val, sentences_train, sentences_val, k):
    # Initializing the predictions list
    predictions = []

    # Looping through the validation set
    for i in range(D_val.shape[0]):
        # Initializing the distances list
        distances = []

        # Looping through the training set
        for j in range(D_train.shape[0]):
            # Calculating the Euclidean distance between the validation sentence and the training sentence
            distance = np.linalg.norm(D_val[i] - D_train[j])

            # Appending the distance to the distances list
            distances.append([distance, j])

        # print(distances) #DEBUG
        # exit() #DEBUG

        # Getting the labels of the k nearest neighbors from the second pair element of distances list pairs
        distances.sort()
        k_nearest = np.zeros(k)
        for j in range(k):
            k_nearest[j] = sentences_train[distances[j][1]][1]

        # Getting the most frequent label from the k nearest neighbors
        prediction = np.bincount(k_nearest.astype(int)).argmax()

        # Appending the prediction to the predictions list
        predictions.append(prediction)
    
    # Calculating the number of correct predictions
    corrects = np.zeros_like(predictions)
    for i in range(len(predictions)):
        if predictions[i] == sentences_val[i][1]:
            corrects[i] = 1

    # Returning the accuracy of the KNN model at K
    return np.sum(corrects) / len(corrects)

# Returns the best K and the best accuracy of the KNN model
def best_KNN(D_train, D_val, sentences_train, sentences_val):
    # Initializing the best K and the best accuracy
    best_k = 0
    best_accuracy = 0

    # Looping through the range of Ks
    for k in range(1, D_val.shape[0] + 1):
        # Getting the accuracy of the KNN model at k
        accuracy = KNN(D_train, D_val, sentences_train, sentences_val, k)

        # print(k)  #DEBUG

        # Updating the best K and the best accuracy
        if accuracy > best_accuracy:
            best_k = k
            best_accuracy = accuracy

    # Returning the best K and the best accuracy
    return best_k, best_accuracy

# Dvidie the dataset matrix named sentences into training, validation, and testing matrices that holds the sentences and their labels
sentences_train = sentences[:M_train]
sentences_val = sentences[M_train:M_train + M_val]
sentences_test = sentences[M_train + M_val:]

# Building the best KNN model for the original D dataset
orig_start_time = time.time()
orig_k, orig_accuracy = best_KNN(D_train, D_val, sentences_train, sentences_val)
orig_end_time = time.time()
print("The total building time for the best KNN model of the original D dataset is: " + str(orig_end_time - orig_start_time) + " seconds")

# Building the best KNN model for the pruned D dataset
prun_start_time = time.time()
prun_k, prun_accuracy = best_KNN(D_train_prun, D_val_prun, sentences_train, sentences_val)
prun_end_time = time.time()
print("The total building time for the best KNN model of the pruned D dataset is: " + str(prun_end_time - prun_start_time) + " seconds")

#DEBUG
# new_tuple = ["I love the movie", 1]
# new_tuple_words = re.split('\W+', new_tuple[0])
# new_tuple_freq_orig = np.zeros((1, N))
# new_tuple_freq_prun = np.zeros((1, top_k))
# for i in range(N):
#     new_tuple_freq_orig[0][i] = new_tuple_words.count(dict[i])

# for i in range(top_k):
#     new_tuple_freq_prun[0][i] = new_tuple_words.count(dict[k_most_freq_words[i]])


# print(new_tuple_freq_orig)  # DEBUG
# print(new_tuple_freq_prun)  # DEBUG

# print(D_test[:1])  # DEBUG
# print(setences_test[:1])  # DEBUG
    
# Calculating the classifier time of the new tuple for the original D dataset
orig_start_time = time.time()
orig_prediction = KNN(D_train, D_test[:1], sentences_train, sentences_test[:1], orig_k)
orig_end_time = time.time()
print("The classifier time of the new tuple for the original D dataset is: " + str(orig_end_time - orig_start_time) + " seconds")

# Calculating the classifier time of the new tuple for the pruned D dataset
prun_start_time = time.time()
prun_prediction = KNN(D_train_prun, D_test_prun[:1], sentences_train, sentences_test[:1], prun_k)
prun_end_time = time.time()
print("The classifier time of the new tuple for the pruned D dataset is: " + str(prun_end_time - prun_start_time) + " seconds")

# Calculating the training accuracy of the best KNN model for the original D dataset
print("The training accuracy of the best KNN model for the original D dataset is: " + str(KNN(D_train, D_train, sentences_train, sentences_train, orig_k)))

# Calculating the training accuracy of the best KNN model for the pruned D dataset
print("The training accuracy of the best KNN model for the pruned D dataset is: " + str(KNN(D_train_prun, D_train_prun, sentences_train, sentences_train, prun_k)))

# Calculating the validation accuracy of the best KNN model for the original D dataset
print("The validation accuracy of the best KNN model for the original D dataset is: " + str(KNN(D_train, D_val, sentences_train, sentences_val, orig_k)))

# Calculating the validation accuracy of the best KNN model for the pruned D dataset
print("The validation accuracy of the best KNN model for the pruned D dataset is: " + str(KNN(D_train_prun, D_val_prun, sentences_train, sentences_val, prun_k)))

# Calculating the testing accuracy of the best KNN model for the original D dataset
print("The testing accuracy of the best KNN model for the original D dataset is: " + str(KNN(D_train, D_test, sentences_train, sentences_test, orig_k)))

# Calculating the testing accuracy of the best KNN model for the pruned D dataset
print("The testing accuracy of the best KNN model for the pruned D dataset is: " + str(KNN(D_train_prun, D_test_prun, sentences_train, sentences_test, prun_k)))

# Note: Advanatages/Disadvantages of the feature-base selection are included in the report "report1.pdf"

# KNN Classifier END

# Naive Bayes Classifier START

print("\nNaive Bayes Classifier:")
print("-----------------------")

# Initializing the Gaussian Naive Bayes model
nb_orig = GaussianNB()
nb_prun = GaussianNB()

# print([sentence[1] for sentence in sentences_train]) #DEBUG

# Measuring the time to train (offline efficiency cost) of the Gaussian Naive Bayes model for the original D dataset
orig_start_time = time.time()
nb_orig.fit(D_train, [sentence[1] for sentence in sentences_train])
orig_end_time = time.time()
print("The total training time for the Gaussian Naive Bayes model of the original D dataset is: " + str(orig_end_time - orig_start_time) + " seconds")

# Meassuring the time to train (offline efficiency cost) of the Gaussian Naive Bayes model for the pruned D dataset
prun_start_time = time.time()
nb_prun.fit(D_train_prun, [sentence[1] for sentence in sentences_train])
prun_end_time = time.time()
print("The total training time for the Gaussian Naive Bayes model of the pruned D dataset is: " + str(prun_end_time - prun_start_time) + " seconds")

# Measuring the time to classify (online efficiency cost) of the Gaussian Naive Bayes model for the original D dataset
orig_start_time = time.time()
orig_prediction = nb_orig.predict(D_val)
orig_end_time = time.time()
print("The total classification time for the Gaussian Naive Bayes model of the original D dataset is: " + str(orig_end_time - orig_start_time) + " seconds")

# Measuring the time to classify (online efficiency cost) of the Gaussian Naive Bayes model for the pruned D dataset
prun_start_time = time.time()
prun_prediction = nb_prun.predict(D_val_prun)
prun_end_time = time.time()
print("The total classification time for the Gaussian Naive Bayes model of the pruned D dataset is: " + str(prun_end_time - prun_start_time) + " seconds")

# Calculating the training accuracy of the Gaussian Naive Bayes model for the original D dataset
print("The training accuracy of the Gaussian Naive Bayes model for the original D dataset is: " + str(accuracy_score([sentence[1] for sentence in sentences_train], nb_orig.predict(D_train))))

# Calculating the training accuracy of the Gaussian Naive Bayes model for the pruned D dataset
print("The training accuracy of the Gaussian Naive Bayes model for the pruned D dataset is: " + str(accuracy_score([sentence[1] for sentence in sentences_train], nb_prun.predict(D_train_prun))))
      
# Calculating the validation accuracy of the Gaussian Naive Bayes model for the original D dataset
print("The validation accuracy of the Gaussian Naive Bayes model for the original D dataset is: " + str(accuracy_score([sentence[1] for sentence in sentences_val], orig_prediction)))

# Calculating the validation accuracy of the Gaussian Naive Bayes model for the pruned D dataset
print("The validation accuracy of the Gaussian Naive Bayes model for the pruned D dataset is: " + str(accuracy_score([sentence[1] for sentence in sentences_val], prun_prediction)))

# Calculating the testing accuracy of the Gaussian Naive Bayes model for the original D dataset
print("The testing accuracy of the Gaussian Naive Bayes model for the original D dataset is: " + str(accuracy_score([sentence[1] for sentence in sentences_test], nb_orig.predict(D_test))))

# Calculating the testing accuracy of the Gaussian Naive Bayes model for the pruned D dataset
print("The testing accuracy of the Gaussian Naive Bayes model for the pruned D dataset is: " + str(accuracy_score([sentence[1] for sentence in sentences_test], nb_prun.predict(D_test_prun))))

# Note: Advanatages/Disadvantages of the feature-base selection are included in the report "report1.pdf"

# SVM Classifier START

print("\nSVM Classifier:")
print("-----------------------")

# Initializing the SVM model
svm_orig = svm.SVC()
svm_prun = svm.SVC()

# Measuring the time to train (offline efficiency cost) of the SVM model for the original D dataset
orig_start_time = time.time()
svm_orig.fit(D_train, [sentence[1] for sentence in sentences_train])
orig_end_time = time.time()
print("The total training time for the SVM model of the original D dataset is: " + str(orig_end_time - orig_start_time) + " seconds")

# Meassuring the time to train (offline efficiency cost) of the SVM model for the pruned D dataset
prun_start_time = time.time()
svm_prun.fit(D_train_prun, [sentence[1] for sentence in sentences_train])
prun_end_time = time.time()
print("The total training time for the SVM model of the pruned D dataset is: " + str(prun_end_time - prun_start_time) + " seconds")

# Measuring the time to classify (online efficiency cost) of the SVM model for the original D dataset
orig_start_time = time.time()
orig_prediction = svm_orig.predict(D_val)
orig_end_time = time.time()
print("The total classification time for the SVM model of the original D dataset is: " + str(orig_end_time - orig_start_time) + " seconds")

# Measuring the time to classify (online efficiency cost) of the SVM model for the pruned D dataset
prun_start_time = time.time()
prun_prediction = svm_prun.predict(D_val_prun)
prun_end_time = time.time()
print("The total classification time for the SVM model of the pruned D dataset is: " + str(prun_end_time - prun_start_time) + " seconds")

# Calculating the training accuracy of the SVM model for the original D dataset
print("The training accuracy of the SVM model for the original D dataset is: " + str(accuracy_score([sentence[1] for sentence in sentences_train], svm_orig.predict(D_train))))

# Calculating the training accuracy of the SVM model for the pruned D dataset
print("The training accuracy of the SVM model for the pruned D dataset is: " + str(accuracy_score([sentence[1] for sentence in sentences_train], svm_prun.predict(D_train_prun))))

# Calculating the validation accuracy of the SVM model for the original D dataset
print("The validation accuracy of the SVM model for the original D dataset is: " + str(accuracy_score([sentence[1] for sentence in sentences_val], orig_prediction)))

# Calculating the validation accuracy of the SVM model for the pruned D dataset
print("The validation accuracy of the SVM model for the pruned D dataset is: " + str(accuracy_score([sentence[1] for sentence in sentences_val], prun_prediction)))

# Calculating the testing accuracy of the SVM model for the original D dataset
print("The testing accuracy of the SVM model for the original D dataset is: " + str(accuracy_score([sentence[1] for sentence in sentences_test], svm_orig.predict(D_test))))

# Calculating the testing accuracy of the SVM model for the pruned D dataset
print("The testing accuracy of the SVM model for the pruned D dataset is: " + str(accuracy_score([sentence[1] for sentence in sentences_test], svm_prun.predict(D_test_prun))))

# Note: Advanatages/Disadvantages of the feature-base selection are included in the report "report1.pdf"

print("\n")

# SVM Classifier END

# HW3.1.2.4 END

# HW3 ENDS HERE
