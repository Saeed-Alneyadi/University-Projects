import numpy as np
from sklearn import tree
import matplotlib.pyplot as plt

# Loading the X and Y data from Problem 4
X = [
    [24, 40000],
    [53, 52000],
    [23, 25000],
    [25, 77000],
    [32, 48000],
    [52, 110000],
    [22, 38000],
    [43, 44000],
    [52, 27000],
    [48, 65000]
]
Y = [1, 0, 0, 1, 1, 1, 1, 0, 0, 1]

# Creating a decision tree classifier
clf_gini = tree.DecisionTreeClassifier(criterion = 'gini')
clf_entropy = tree.DecisionTreeClassifier(criterion = 'entropy')
clf_gini = clf_gini.fit(X, Y)
clf_entropy = clf_entropy.fit(X, Y)

# Plotting the decision trees
tree.plot_tree(clf_gini)
plt.title('Gini Index Decision Tree')
plt.show()
tree.plot_tree(clf_entropy)
plt.title('Entropy Decision Tree')
plt.show()
