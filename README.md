Event Driven Programming Assignment 1 - Building a regex Engine.
---
### Project Overview
The RegexEngine consists of the frontend and the backend stages, 
The front end parse a regex pattern and build an equivalent e-NFA backbone
The backend converts the backbone the functional eNFA and provides further optimisation
including:

- Simplifying the state object representation to an integer
- Providing method for parsing string and validate string based on input regex pattern,
---

### Folders Structure
Front-end code is stored in `src/FrontEnd`, which consists of three main classes
- RParser - which produces a postfix expression from an input RegEx pattern
- StateContainer - which builds a graph of states that constructs the backbone of an NFA
- FrontEnd - which gets the postfix expression from RParser to build an NFA from StateContainer.

Back-end code is stored in `src/BackEnd`, which consists of the inheritance tree from FiniteStateMachine 
to DFA, NFA and eNFA. Due to time-constraints, and since this is not a requirement of the assignment,
methods to convert between the three structures have not been developed. The main class of 
BackEnd is 
- ENonDeterministicAutomata, which stores the graph produced by front end as a HashMap of Set of 
integer, which should provide faster inference.

Utility classes and methods are grouped together in the `src/utils` folder.

---
### Testing 
Test files are stored in the same folder as the classes they test on. For instance,
the test for `src/utils/DFS.java` is found in `src/utils/DFS_Test.java`. Since the assignment test
suites require the display of true/false on a terminal, some test cases for invalid symbols were 
tested manually.