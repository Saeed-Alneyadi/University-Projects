import numpy as np
import math
import argparse
import os
import os.path as osp
import matplotlib.pyplot as plt
from sklearn import svm
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.decomposition import PCA

## Data loader and data generation functions
def data_loader(args):
    """
    Output:
        X_train: the data matrix (numpy array) of size D-by-N_train
        Y_train: the label matrix (numpy array) of size N_train-by-1
        X_test: the data matrix (numpy array) of size D-by-N_test
        Y_test: the label matrix (numpy array) of size N_test-by-1
    """
    print("Using MNIST")
    X_train, X_test, Y_train, Y_test = data_MNIST(args)

    return  X_train, X_test, Y_train, Y_test


def data_MNIST(args):
    X = np.loadtxt(osp.join(args.path, "mnist_test.csv"), delimiter=",")
    X = X.astype('float64')
    Y = X[:, 0]
    X_1st = X[Y == 4, 1:].transpose()
    X_2nd = X[Y == 9, 1:].transpose()
    N_1st = X_1st.shape[1]
    N_2nd = X_2nd.shape[1]
    Y_1st = -1 * np.ones(N_1st).reshape(-1,1)
    Y_2nd = 1 * np.ones(N_2nd).reshape(-1,1)
    X_1st_test = X_1st[:, int(0.7 * N_1st):]
    X_1st_train = X_1st[:, :int(0.7 * N_1st)]
    X_2nd_test = X_2nd[:, int(0.7 * N_2nd):]
    X_2nd_train = X_2nd[:, :int(0.7 * N_2nd)]
    Y_1st_test = Y_1st[int(0.7 * N_1st):, :]
    Y_1st_train = Y_1st[:int(0.7 * N_1st), :]
    Y_2nd_test = Y_2nd[int(0.7 * N_2nd):, :]
    Y_2nd_train = Y_2nd[:int(0.7 * N_2nd), :]

    # plot one digital image
 #   plt.title('Example image of digit 4') 
 #   plt.imshow(X_1st[:,0].reshape((28,28)), cmap='gray')
 #   plt.show()
       

    return np.concatenate((X_1st_train, X_2nd_train), 1), np.concatenate((X_1st_test, X_2nd_test), 1),\
           np.concatenate((Y_1st_train, Y_2nd_train), 0), np.concatenate((Y_1st_test, Y_2nd_test), 0)



def main(args):
    np.random.permutation(10)
    X_train, X_test, Y_train, Y_test = data_loader(args)
    
    # reshape the data matrix (numpy array) of size N_train-by-D
    X_train = X_train.transpose() 
    X_test = X_test.transpose()
    Y_train = Y_train.flatten()
    Y_test = Y_test.flatten()
    
    ### preprocessing the traing and testing data
    sc = StandardScaler().fit(X_train)
    X_train = sc.transform(X_train)
    X_test = sc.transform(X_test)
    ###

 
    ### Task 1: split the training into base and holdout, 
    ###         then find the best parameters of C and gagmma for SVM with rbf kernel,
    ###         finally evaluate SVM on the testing dataset

    # Training data and Testing data are scaled using fit() and transform() functions
    sc = StandardScaler().fit(X_train)
    X_train = sc.transform(X_train)
    X_test = sc.transform(X_test)

    # print('X_train shape:', X_train.shape) #DEBUG
    # print('X_test shape:', X_test.shape) #DEBUG

    # train_test_split function is called to split the training data into base and holdout variables
    X_base, X_holdout, Y_base, Y_holdout = train_test_split(X_train, Y_train, test_size=0.2)

    # Varaibles and Lists Initializations and Declarations
    c_list = [0.001, 0.01, 0.1, 1, 10, 100, 1000]
    gamma_list = [0.001, 0.01, 0.1, 1, 10, 100, 1000]
    c_best = 0
    gamma_best = 0
    error_best = 1000000
    sv_count = 0

    # Finding the best parameters of C and gamma for SVM with rbf kernel
    for c in c_list:
        for g in gamma_list:
            # SVM model is trained using SVC() function and fit() function
            clf = svm.SVC(kernel='rbf', C=c, gamma=g)
            clf.fit(X_base, Y_base)
            Pe = 1 - clf.score(X_test,Y_test)

            # Checks for the best error rate and update all the best values of C, gamma, error rate, and number of support vectors
            if Pe < error_best:
                c_best = c
                gamma_best = g
                error_best = Pe
                sv_count = clf.support_vectors_.shape[0]

    # print(c_best) #DEBUG
    # print(gamma_best) #DEBUG
    # print(error_best) #DEBUG
    # print(sv_count) #DEBUG

    # Printing results of Task 1
    print('Task 1: SVM on Original Data')
    print('---------------------------------------------')
    print('C Best value:', c_best)
    print('Gamma Best value:', gamma_best)
    print('Error Best value:', error_best)
    print('Number of Support Vectors:', sv_count)

    # Line Breaker
    print('')

    ### end of Task 1

    ### Task 2: perform PCA for the traing and testing data

    # Using PCA() function and fit() function, the training data is transformed
    pca = PCA(n_components=784)
    pca.fit(X_train)

    # Covariance matrix Eigenvalues Calculations using the explained_variance_ratio_ attribute
    eig_vals = pca.explained_variance_ratio_

    # Choosing the number of eigenvalues such that 80% of the energy of the eigenvalues are retained
    sum = 0
    k = 0
    for i in range(len(eig_vals)):
        sum += eig_vals[i]
        k += 1
        if sum >= 0.8:
            break

    # print(k) #DEBUG

    # Printing results of Task 2
    print('Task 2: PCA on Original Data')
    print('---------------------------------------------')
    print('New Feature Vector Size \'k\':', k)

    # Line Breaker
    print('')

    # Using the PCA object transform() function, the training and testing data are transformed
    pca = PCA(n_components=k)
    pca.fit(X_train)
    Theta_train = pca.transform(X_train)
    Theta_test = pca.transform(X_test)

    # print('Theta_train shape:', Theta_train.shape) #DEBUG
    # print('Theta_test shape:', Theta_test.shape) #DEBUG

    ### end of Task 2

    ### Task 3: repeat Task 1 on the transformed data ###
 
    # The train_test_split() function splits the transformed training data into base and holdout variables
    Theta_base, Theta_holdout, Y_base, Y_holdout = train_test_split(Theta_train, Y_train, test_size=0.2)

    # print('Theta_base shape:', Theta_base.shape) #DEBUG
    # print('Theta_holdout shape:', Theta_holdout.shape) #DEBUG
    # print('Y_base shape:', Y_base.shape) #DEBUG
    # print('Y_holdout shape:', Y_holdout.shape) #DEBUG

    # Varaibles and Lists Initializations
    c_list = [0.001, 0.01, 0.1, 1, 10, 100, 1000]
    gamma_list = [0.001, 0.01, 0.1, 1, 10, 100, 1000]
    c_best = 0
    gamma_best = 0
    error_best = 1000000
    sv_count = 0

    # Finding the best parameters of C and gamma for SVM with rbf kernel
    for c in c_list:
        for g in gamma_list:
            # Training the SVM model using SVC() function and fit() function
            clf = svm.SVC(kernel='rbf', C=c, gamma=g)
            clf.fit(Theta_base, Y_base)
            Pe = 1 - clf.score(Theta_test,Y_test)

            # Check for the best error rate and update all the best values of C, gamma, error rate, and number of support vectors
            if Pe < error_best:
                c_best = c
                gamma_best = g
                error_best = Pe
                sv_count = clf.support_vectors_.shape[0]

    # print(c_best) #DEBUG
    # print(gamma_best) #DEBUG
    # print(error_best) #DEBUG
    # print(sv_count) #DEBUG

    # Printing results of Task 3
    print('Task 3: SVM on Transformed Data')
    print('---------------------------------------------')
    print('C Best value:', c_best)
    print('Gamma Best value :', gamma_best)
    print('Error Best value:', error_best)
    print('Number of Support Vectors:', sv_count)

    # Line Breaker
    print('')

    ### end of Task 3

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Running SVM")
    parser.add_argument('--path', default="data", type=str)
    args = parser.parse_args()
    main(args)

    # Fill in the other students you collaborate with:
    # e.g., Zhihui Zhu, zhu.3440
    # Only Me
    #
    #
