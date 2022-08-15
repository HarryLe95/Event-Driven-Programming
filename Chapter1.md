# Nomenclature
- Alphabet - $\Sigma$ - set of acceptable symbol
- String - finite sequence of symbols drawn from an alphabet
- Empty string - $\epsilon$
- Power set of an alphabet - set of strings produced by a combination of symbols from an alphabet with a specified power $n$.
  - E.g. 
  - $\Sigma=\{0,1\}$ 
  - $\Sigma^0 = \{\epsilon\}$
  - $\Sigma^1 = \{0,1\}$
  - $\Sigma^2 = \{00,01,10,11 \}$
  - $\Sigma^* = \{\epsilon, 0,1,10,11,00,01,100,111,\dots\}$ - set of all possible strings from an alphabet.
  - $\Sigma^+ = \{0,1,10,11,00,01,100,111,\dots\}$ - set of all possible strings exceept $\epsilon$
- Language - $L$ - a subset of all possible strings: $L\subset \Sigma^*$ 

___
# Deterministic Finite Automata

## I. Example

A common language used as an example is the `zeroOne` DFA that accepts string of binary bits that end with `01`: 

$$L = \{s|s \text{ is a binary sequence that ends with 01}\}$$

The RegEx equivalent: `\d*01$`

## II.Transition Diagram

A state in DFA remembers things about symbols that the DFA has seen. In the `zeroOne` example, the DFA consists of three states:
- $q_0$ - no useful information received.
- $q_1$ - a $0$ has been received.
- $q_2$ - a $01$ has been received. 

State transitions can happen through events: suppose the DFA receives the string `0101` sequentially, we record the change in states as follows:
- Intially no data entered, the DFA is in $q_0$.
- $0$ received, the state is now changed to $q_1$.
- $0$ received, the state remains at $q_1$.
- $1$ received, the state is now changed to $q_2$.
- The string terminates.

Key take-aways:
- A state can be changed to another, or remain the same as a result of events. 
- There are accepting and non-accepting states, which determine the actions to take after the event ends. 

## III.Formal defintion of DFA:
DFA can be specified via a five-tuple definition, or a transition table.

### 3.1. Five tuple definition:
$$M=(Q,\Sigma,\delta,q_0,F)$$
where 
- $Q$: finite set of states.
- $\Sigma$: the alphabet of the DFA
- $\delta: Q \times \Sigma \rightarrow Q$ a transition function determining the transition mechanism. 
- $F$ set of accepting states: $F \subset Q$  

For the `zeroOne` example:
- $Q=\{q_0,q_1,q_2\}$
- $\Sigma=\{0,1\}$
- $F=q_2$

$$
\delta = 
\begin{cases}
\delta(q_0,0)=q_1\\
\delta(q_0,1)=q_0\\
\delta(q_1,0)=q_1\\
\delta(q_1,1)=q_2\\
\delta(q_2,0)=q_1\\
\delta(q_2,1)=q_0\\
\end{cases}
$$

As we see that $\delta$ can be expressed via enumeration, a natural representation is via table.

### 3.2. DFA Transition table:
| $\delta$           | 0     | 1     |
|--------------------|-------|-------|
| $\rightarrow q_0$  | $q_1$ | $q_0$ |
|              $q_1$ | $q_1$ | $q_2$ |
|             $*q_2$ | $q_1$ | $q_0$ |

## IV. Generalising the transition function to string 
$\delta$ outputs the transition state given the current state and an input simple. As we shall see, we can construct $\hat{\delta}$ as a function that provides the updated state based on a whole string, not just a single symbol:

$$\hat{\delta}: Q \times \Sigma^* \rightarrow Q$$

Recursive construction: 
- Base case: with no input, there is no change in state:

$$\hat{\delta}(q,\epsilon)=q$$

-Recurrence case: if the input is a string, take the first symbol, feed it to $\delta$ to receive a new state:

$$r=\delta(q,s_1)$$

Then use $\hat{\delta}$ to proceed with the remainder of the string starting at $r$:

$$\hat{\delta}(q,s_1s_2\dots s_n)=\hat{\delta}(r,s_2s_3\dots s_n)$$

This proceeds recursively until the last symbol is processed, until which the base case is reached. 

## V. The language of a DFA

Is the set of all input strings accepted by the DFA that takes it to the set of accepted states. Thus for DFA

$$M = (Q, \Sigma, \delta, q_0, F)$$

The language is defined as:

$$L(M) = \{w \in \Sigma^*|\hat{\delta}(q_0,w)\in F \}$$