## Eclosure for e-transition graph 
---

Input: a directed graph G = (V,E) whose 

- V is the state set 
- E is the edge set containing the epsilon transition, 
implemented as a dictionary/hashmap with the keys being the states 
and value being the set of states reachable under the first transition.

Ouput: the closure for each state, which include the state itself and all states reachable 
under the epsilon transition. Formally: 

$$\text{ECLOSURE}(v) = \{q \neq v|q.\pi \in \text{ECLOSURE}(v)\}\cup \{v\} $$

where $q.\pi$ is define as the parent of $q$, or more formally, 
$$q \in E[q.\pi]$$

## Algorithm

The above formalism gives us some ideas on how to construct the ECLOSURE of a state $v$ in a step by step manner: 

- Initially, ECLOSURE($v$) contains $v$ itself.
- Then ECLOSURE($v$) adds all of $V_1$, consisting of all states whose parent is $v$, or more precisely, $V_1 = E[v]$
- Then ECLOSURE($v$) adds all of $V_2$, consisting of all states whose parents are $q \in V_1$, or more precisely, $V_2 = \bigcup_{q \in V_1} E[q]$ 
- This loop continues until all children states have been added. 

Now the described procedure sounds exactly like BFS/DFS algorithms. Here, I will implement the DFS in Python that produces the eclosure for every vertex in the graph. 

```Python
def DFS(V:Sequence[int], E:dict):
    """Run a depth first search on a graph G specified by the input.

    Produce a dictionary that maps every vertice and all vertices reachable from it.
    
    Args:
        V (Sequence[int]): vertice set
        E (dict): edge set
    """
    
    connection = {v:{v} for v in V}
    for v in V:
        try:
            connection[v].update(E[v])
        except KeyError:
            continue
        
    eclose = deepcopy(connection)
    status = {v:0 for v in V}
    
    def DFS_VISIT(u:int):
        status[u] = 1
        for v in connection[u]:
            if status[v]==0:
                DFS_VISIT(v)
                eclose[u].update(eclose[v])
    
    for u in V:
        if status[u] == 0:
            DFS_VISIT(u)
    return eclose
```