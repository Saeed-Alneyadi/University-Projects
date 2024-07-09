import numpy as np
import math
import argparse
import os
import os.path as osp
import matplotlib.pyplot as plt

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
    X_1st = X[Y == 1, 1:].transpose()
    X_2nd = X[Y == 9, 1:].transpose()
    N_1st = X_1st.shape[1]
    N_2nd = X_2nd.shape[1]
    Y_1st = 0 * np.ones(N_1st).reshape(-1,1)
    Y_2nd = 1 * np.ones(N_2nd).reshape(-1,1)
    X_1st_test = X_1st[:, int(0.7 * N_1st):]
    X_1st_train = X_1st[:, :int(0.7 * N_1st)]
    X_2nd_test = X_2nd[:, int(0.7 * N_2nd):]
    X_2nd_train = X_2nd[:, :int(0.7 * N_2nd)]
    Y_1st_test = Y_1st[int(0.7 * N_1st):, :]
    Y_1st_train = Y_1st[:int(0.7 * N_1st), :]
    Y_2nd_test = Y_2nd[int(0.7 * N_2nd):, :]
    Y_2nd_train = Y_2nd[:int(0.7 * N_2nd), :]
    
    

    return np.concatenate((X_1st_train, X_2nd_train), 1), np.concatenate((X_1st_test, X_2nd_test), 1),\
           np.concatenate((Y_1st_train, Y_2nd_train), 0), np.concatenate((Y_1st_test, Y_2nd_test), 0)


def distance(x_test, x_train, dis_metric):
    """
    Input:
        x_test: a D-by-1 test data instance
        x_train: a D-by-1 training data instance
        dis_metric: a string = "L1", "L2", or "cosine"
    Output:
        dist: a distance value
    """
    x_test = np.copy(x_test)
    x_train = np.copy(x_train)

    ### Your job 1 starts here ###
    if dis_metric == "L1":
        dist = np.sum(np.absolute(x_test - x_train))
    elif dis_metric == "L2":
        dist = np.sum((x_test - x_train) ** 2) ** 0.5
    elif dis_metric == "cosine":
        dist = 1 - np.matmul(x_test.transpose(), x_train) /\
               (np.matmul(x_train.transpose(), x_train)**0.5) /\
               (np.matmul(x_test.transpose(), x_test)**0.5)
    ### Your job 1 ends here ###
    # print("distance: ", dist) #DEBUG
    return dist


def evaluate(y_true, y_clusters):
    return 1 - min(sum(np.abs(y_true - y_clusters)),sum(np.abs((1-y_true) - y_clusters)))/y_true.shape[0]


def initiate_centroids(X, K, dis_metric):
    """
    Input:
        X: a D-by-N matrix (numpy array) of the data
        K: the number of centers
    Output:
        selected_points: the selected K data points as centroids, where each point
    is selected with the largest distance to the previously selected ones.
    """
    if K <= 0 or K > X.shape[0]:
        return []

    # Randomly select the first point
    selected_points = [X[0]]

    for _ in range(K - 1):
        max_distance = 0
        max_point = None

        # Find the point with the largest distance to the previously selected ones
        for point in X:
            min_distance = min(distance(point, selected_point, dis_metric) for selected_point in selected_points)
            if min_distance > max_distance:
                max_distance = min_distance
                max_point = point

        # Add the selected point to the list
        selected_points.append(max_point)

    # print(selected_points) #DEBUG

    return selected_points


def main(args):
    np.random.permutation(10)
    X, _, Y, _ = data_loader(args)
    print("dimension, number of data instances: ", X.shape)
    X = X.transpose()
    # print(X.shape) #DEBUG
    Y = Y.flatten()
    dis_metric_set = ["L1", "L2", "cosine"]
    dis_metric = dis_metric_set[1]
    K = 2
    
    ### Your job starts here ###
    ### You are supposed to implement Kmeans algorithm by yourself; 
    ###     pls donot call existing toolbox solvers for Kmeans such as from scikitlearn
    ### You can write Kmeans loop here, or define a function outside and then simply call it here
    ### But your Kmeans should include the following three steps. 
    ### Use the L2 distance. Set the maximum iterations as 100     
    
    # Step 1: Find the initial centroids: For simplicity, let just use X[0] as the first centroid, 
    #         and then find the other centroid with the largest distance to the first one.
    
    inits = initiate_centroids(X, K, dis_metric)
    centroids = np.array(inits)
    clusters = np.zeros(X.shape[0])

    # print(initiate_centroids(X, K, dis_metric)) #DEBUG
    # print(len(inits)) #DEBUG
    # print(inits) #DEBUG
    # print(len(centroids)) #DEBUG
    # print(centroids) #DEBUG

    for _ in range(100):
        # Step 2: Assign points to the nearest centroid
        for i in range(X.shape[0]):
            distances = [distance(X[i], centroid, dis_metric) for centroid in centroids]
            clusters[i] = np.argmin(distances)

        # print(centroids[0].shape) #DEBUG
        # print(clusters[i]) #DEBUG
 
        # Step 3: Update centroids
        temp = np.zeros((K, X.shape[1]))
        for i in range(K):
            temp[i] = np.mean(X[clusters == i], axis=0)

        # print(centroids[0].shape) #DEBUG

        # Check for convergence: when the centroids do not change
        if np.all(centroids == temp):
            break
        else:
            centroids = temp

    # # Plot one digital image
    # plt.title('Example image of digit 4') 
    # plt.imshow(X_1st[:,0].reshape((28,28)), cmap='gray')
    # plt.show()

    # 

    # Plot one digital image
    plt.title('Image of digit 1')
    plt.imshow(X[clusters == 0][0].reshape((28,28)), cmap='gray')
    plt.show()

    # Plot nine digital image
    plt.title('Image of digit 9')
    plt.imshow(X[clusters == 1][0].reshape((28,28)), cmap='gray')
    plt.show()

    ### Your job ends here ### 
    
    #evaluate the clustering accuracy
    # clusters contain the cluster information for each point
    accuracy = evaluate(Y,clusters)
    print("clustering accuracy: ", accuracy)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Running Kmeans")
    parser.add_argument('--path', default="data", type=str)
    args, unknown = parser.parse_known_args()
    main(args)

    # Fill in the other students you collaborate with:
    # e.g., Zhihui Zhu, zhu.3440
    # No one excpet me for this homework
    #
    #
