# search.py
# ---------
# Licensing Information: Please do not distribute or publish solutions to this
# project. You are free to use and extend these projects for educational
# purposes. The Pacman AI projects were developed at UC Berkeley, primarily by
# John DeNero (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# For more info, see http://inst.eecs.berkeley.edu/~cs188/sp09/pacman.html

"""
In search.py, you will implement generic search algorithms which are called
by Pacman agents (in searchAgents.py).
"""

import util
from util import heappush, heappop, Stack, Queue, PriorityQueue
class SearchProblem:
    """
    This class outlines the structure of a search problem, but doesn't implement
    any of the methods (in object-oriented terminology: an abstract class).

    You do not need to change anything in this class, ever.
    """

    def getStartState(self):
      """
      Returns the start state for the search problem
      """
      util.raiseNotDefined()

    def isGoalState(self, state):
      """
      state: Search state

      Returns True if and only if the state is a valid goal state
      """
      util.raiseNotDefined()

    def getSuccessors(self, state):
      """
      state: Search state

      For a given state, this should return a list of triples,
      (successor, action, stepCost), where 'successor' is a
      successor to the current state, 'action' is the action
      required to get there, and 'stepCost' is the incremental
      cost of expanding to that successor
      """
      util.raiseNotDefined()

    def getCostOfActions(self, actions):
      """
      actions: A list of actions to take

      This method returns the total cost of a particular sequence of actions.  The sequence must
      be composed of legal moves
      """
      util.raiseNotDefined()


def tinyMazeSearch(problem):
    """
    Returns a sequence of moves that solves tinyMaze.  For any other
    maze, the sequence of moves will be incorrect, so only use this for tinyMaze
    """
    from game import Directions
    s = Directions.SOUTH
    w = Directions.WEST
    return  [s,s,w,s,w,w,s,w]

def depthFirstSearch(problem):
    """
    Search the deepest nodes in the search tree first.
    Your search algorithm needs to return a list of actions that reaches
    the goal. Make sure that you implement the graph search version of DFS,
    which avoids expanding any already visited states. 
    Otherwise your implementation may run infinitely!
    To get started, you might want to try some of these simple commands to
    understand the search problem that is being passed in:
    print("Start:", problem.getStartState())
    print("Is the start a goal?", problem.isGoalState(problem.getStartState()))
    print("Start's successors:", problem.getSuccessors(problem.getStartState()))
    """
    # stack to manage the nodes to be explored
    fringe = Stack()
     # pushing the initial state to the stack
    fringe.push((problem.getStartState(), [], 0))
    closedSet = set()

    while not fringe.isEmpty(): # continue until there are nodes to explore
        # Pop a node from the stack fringe
        state, path, _ = fringe.pop()
        
        # Checking whether if the popped node is the goal
        if problem.isGoalState(state):
            return path
        
        # If the node is already visited, then skip
        if state in closedSet:
            continue
        
        # Marking the Current Node as Visited
        closedSet.add(state)
        
        # Iterate over the Current Node Neighbors
        for successor, action, cost in problem.getSuccessors(state):
            fringe.push((successor, path + [action], cost))

    

def breadthFirstSearch(problem):
    # Initialize a queue for storing the nodes for Exploration
    fringe = Queue()
    # Pushing the start state to the fringe stack, along with an Empty Path and a cumulative cost of zero.
    fringe.push((problem.getStartState(), [], 0))
    closedSet = set()
    
    # Continue seraching until the Stack Fringe is Empty
    while not fringe.isEmpty():
        # Dequeue a node from the fringe along with its path and cost
        state, path, _ = fringe.pop()
        
        # Checking if the dequeued node is the goal state or not
        if problem.isGoalState(state):
            return path
        
        # If it has already been visited, skip the node
        if state in closedSet:
            continue
        
        closedSet.add(state)
        
        for successor, action, cost in problem.getSuccessors(state):
            fringe.push((successor, path + [action], cost))


def uniformCostSearch(problem):
    # Initialize Priority Queue for fringe
    fringe = PriorityQueue()
    # Push start state to the fringe with priority 0
    fringe.push((problem.getStartState(), [], 0), 0)
    closedSet = set()
    
    # Loop until the stack fringe is empty
    while not fringe.isEmpty():
        state, path, cost = fringe.pop()
        
        # If the current state is the goal state, then return the path
        if problem.isGoalState(state):
            return path
        
        # If the state has already been visited, skip the current state
        if state in closedSet:
            continue
        
        closedSet.add(state)
        
        # Iterate over current state successors
        for successor, action, stepCost in problem.getSuccessors(state):
            newCost = cost + stepCost
            fringe.push((successor, path + [action], newCost), newCost)


def nullHeuristic(state, problem=None):
    """
    A heuristic function estimates the cost from the current state to the nearest
    goal in the provided SearchProblem.  This heuristic is trivial.
    """
    return 0

def aStarSearch(problem, heuristic=nullHeuristic):
    # Initialize Priority Queue for fringe
    fringe = PriorityQueue()
    # Get the problem start state
    startState = problem.getStartState()
    # Push start state to fringe with priority as the heuristic value of the start state
    fringe.push((startState, [], 0), heuristic(startState, problem))
    closedSet = set()
    
    # Continue until there are no states left in the stack fringe
    while not fringe.isEmpty():
        # Pop the state with the lowest total cost (cost + heuristic) from fringe
        state, path, cost = fringe.pop()
        
        # If the current state is the goal state, then return the path
        if problem.isGoalState(state):
            return path
        
        # If it has already been visited, then skip the current state
        if state in closedSet:
            continue
        
        closedSet.add(state)
        
        # Iterate over the current state successors
        for successor, action, stepCost in problem.getSuccessors(state):
            newCost = cost + stepCost
            fringe.push((successor, path + [action], newCost), newCost + heuristic(successor, problem))



# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch
ucs = uniformCostSearch
