import numpy as np
import matplotlib.pyplot as plt
from matplotlib.colors import ListedColormap
from sklearn import datasets 
from math import exp
import time

# the logistic function
def logistic_func(theta, x):
    t = x.dot(theta)
    g = np.zeros(t.shape)
    # split into positive and negative to improve stability
    g[t>=0.0] = 1.0 / (1.0 + np.exp(-t[t>=0.0])) 
    g[t<0.0] = np.exp(t[t<0.0]) / (np.exp(t[t<0.0])+1.0)
    return g

# function to compute log-likelihood
def neg_log_like(theta, x, y):
    g = logistic_func(theta,x)
    return -sum(np.log(g[y>0.5])) - sum(np.log(1-g[y<0.5]))

# function to compute the gradient of the negative log-likelihood
def log_grad(theta, x, y):
    g = logistic_func(theta,x)
    return -x.T.dot(y-g)
    
# implementation of gradient descent for logistic regression
def grad_desc(theta, x, y, alpha, tol, maxiter):
    nll_vec = []
    nll_vec.append(neg_log_like(theta, x, y))
    nll_delta = 2.0*tol
    iter = 0
    while (nll_delta > tol) and (iter < maxiter):
        theta = theta - (alpha * log_grad(theta, x, y)) 
        nll_vec.append(neg_log_like(theta, x, y))
        nll_delta = nll_vec[-2]-nll_vec[-1]
        iter += 1
    return theta, np.array(nll_vec)


# implementation of the Newton's method for logistic regression
    ### Your job 2 part 1 starts here ###

def newton_method(X, y, maxiter, tol):
    theta = np.zeros(X.shape[1]) # Initialization theta
    nll_vec = [] # Initialization nll_vec
    
    for idx in range(maxiter):
        # Calculating the predicted probabilities using the @logistic_func
        g = logistic_func(theta, X)

        # Negative Log-likelihood Calculation
        cost = -np.sum(y * np.log(g) + (1 - y) * np.log(1 - g))
        nll_vec.append(cost)
        
        # log-likelihood Gradient
        gradient = np.dot(X.T, (y - g))
        
        # log-likelihood Hessian matrix
        W = np.diag(g * (1 - g))
        H = X.T.dot(W).dot(X)
        
        # Updating theta
        delta = np.linalg.solve(H, gradient)
        theta += delta
        
        # Convergence Check
        if np.linalg.norm(delta) < tol:
            break

    return theta, nll_vec # Returning Statement

# function to compute output of LR classifier
def lr_predict(theta,x):
    # form Xtilde for prediction
    shape = x.shape
    Xtilde = np.zeros((shape[0],shape[1]+1))
    Xtilde[:,0] = np.ones(shape[0])
    Xtilde[:,1:] = x
    return logistic_func(theta,Xtilde)

## Generate dataset    
np.random.seed(2020) # Set random seed so results are repeatable
# x,y = datasets.make_blobs(n_samples=100,n_features=2,centers=2,cluster_std=6.0)
x,y = datasets.make_blobs(n_samples=100000,n_features=2,centers=2,cluster_std=6.0)

## build classifier
# form Xtilde
shape = x.shape
xtilde = np.zeros((shape[0],shape[1]+1))
xtilde[:,0] = np.ones(shape[0])
xtilde[:,1:] = x

# Initialize theta to zero
theta = np.zeros(shape[1]+1)

# Run gradient descent (job 1)
alpha = 0.05
tol = 1e-3
maxiter = 10000
t_1 = time.time()
theta,cost = grad_desc(theta,xtilde,y,alpha,tol,maxiter)
t_2 = time.time()

# Printing Results Statements
print("Gradient Descent Result: ")
print("Alpha = ", alpha)
print("Maxiter (the number of iterations required for convergence) = ", maxiter)
print("Last element of cost (the final negative log-likelihood) = ", cost[-1])
print("Final Loss Function = ", cost)
print("Running Time for Gradient Descent = ", t_2 - t_1)
# print("Theta (Gradient Descant Result) = ", theta) 

#Run Newton's method
    ### Your job 2 part 2 starts here ###

t_3 = time.time()
newton_theta, newton_cost = newton_method(xtilde, y, maxiter, tol)
t_4 = time.time()

# Printing Results Statements
print("Newton Method Result: ")
print("Maxiter (the number of iterations required for convergence) = ", maxiter)
print("Last element of cost (the final negative log-likelihood) = ", newton_cost[-1])
print("Final Loss Function = ", newton_cost)
print("Running Time for Newton Method = ", t_4 - t_3)
# print("Theta (Newton Method Result) = ", newton_theta) 

    ### Your job 2 part 2 ends here ###
""" #DEBUG
## Plot the decision boundary. 
# Begin by creating the mesh [x_min, x_max]x[y_min, y_max].
h = .02  # step size in the mesh
x_delta = (x[:, 0].max() - x[:, 0].min())*0.05 # add 5% white space to border
y_delta = (x[:, 1].max() - x[:, 1].min())*0.05
x_min, x_max = x[:, 0].min() - x_delta, x[:, 0].max() + x_delta
y_min, y_max = x[:, 1].min() - y_delta, x[:, 1].max() + y_delta
xx, yy = np.meshgrid(np.arange(x_min, x_max, h), np.arange(y_min, y_max, h))
Z = lr_predict(theta,np.c_[xx.ravel(), yy.ravel()])

# Create color maps
cmap_light = ListedColormap(['#FFAAAA', '#AAFFAA'])
cmap_bold = ListedColormap(['#FF0000', '#00FF00'])

# Put the result into a color plot
Z = Z.reshape(xx.shape)
plt.figure()
plt.pcolormesh(xx, yy, Z, cmap=cmap_light)

## Plot the training points
plt.scatter(x[:, 0], x[:, 1], c=y, cmap=cmap_bold)

## Show the plot
plt.xlim(xx.min(), xx.max())
plt.ylim(yy.min(), yy.max())
plt.title("Logistic regression classifier")
plt.show()
""" #DEBUG



    # Fill in the other students you collaborate with:
    # e.g., Zhihui Zhu, zhu.3440
    # No one
    #
    #