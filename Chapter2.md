# Non-Deterministic automata (NFA)

An NFA can be in more than one states at the same time. Every NFA can be converted to an equivalent DFA. The appeal is NFA can express desired behaviours much more easilly. 

Let's return to the `zeroOne` example, we can set up the following transition table: 

| States            | 0              | 1        |
|-------------------|----------------|----------|
| $\rightarrow q_0$ | $\{q_0, q_1\}$ |    $q_0$ |
|             $q_1$ |       $\empty$ |    $q_2$ |
|             $*q_2$|       $\empty$ | $\empty$ |

Let's see what happen when the string `01101` is provided
| Input | Previous       | Next          |
|-------|----------------|---------------|
|     0 |          $q_0$ | $\{q_0,q_1\}$ |
|     1 |  $\{q_0,q_1\}$ | $\{q_0,q_2\}$ |
|     1 |  $\{q_0,q_2\}$ |         $q_0$ |
|     0 | $q_0$          | $\{q_0,q_1\}$ |
|     1 | $\{q_0, q_1\}$ | $\{q_0,q_2\}$ |

After `0` is provided, the NFA changes from $q_0$ to the set of states $\{q_0,q_1\}$. When the current state is $\{q_0, q_2\}$ and `1` is provided, $q_0$ is changed to $q_0$, while $q_2$ is changed to $\empty$, leaving the result to be $q_0$. 

## I. Formal Definition of NFA

$$M = (Q, \Sigma, \delta, Q_0, F) $$

where 

- $Q$ is the set of possible states 
- $\Sigma$ is the alphabet
- $\delta: Q \times \Sigma \rightarrow \{Q\}$ is the transition function that takes a state and a symbol and returns a set of states. 
- $Q_0$ is the set of initial states.
- $F$ is the set of accepting states.

## II. Extending the transition function
### 2.1. Multi-state inputs:
$$\Delta: P \times \Sigma \rightarrow P$$

where $P$ is a set of states. $P \subset Q$. Given a set of current states $P = \{p_1, p_2,p_3,\dots\}$, $\Delta$ is computed by taking the union of all $\delta(p_i$,s):

$$\Delta(P,s) = \bigcup_{p \in P} \delta(p,s)$$

### 2.2. String input: 
Extending $\Delta$ for string input is the same as for DFA, in which the string version is defined recursively. 

$$\hat{\Delta}:P\times \Sigma^* \rightarrow P$$

#### Base-case:
$$\hat{\Delta}(P,\epsilon)=P$$

#### Recursive-case:
$$R_i = \Delta(R_{i-1},s_i)$$

#### Termination: 
We see that one the NFA has ingested all input symbols, subsequent steps revert to the base case. If one of the final states is in the accepting set, the input string is accepted. 

## III. Language of NFA:
$$L(M) = \{w\in \Sigma*|\hat{\Delta}(Q_0,w) \cap F \neq \emptyset \}$$

## IV. Converting between NFA and DFA: 
- Enumerate all possible states that the DFA can reach. If $|Q|=n$, $Q_N=P(Q)=2^n$ where $P$ is the power set operator. Excluding the empty state, we are left with $Q_N=2^m-1$ possible states. In most practical scenarios, the set of reachable state is much smaller. 
- Normalise the NFA transition function by enumeration. 
- Normalise the accepting set. 