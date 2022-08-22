from typing import Sequence
from copy import deepcopy

def DFS(V:Sequence[int], E:dict):
    """Run a depth first search on a graph G specified by the input

    Produce a dictionary that maps every vertice and all vertices reachable
    by it. Work for both directed and undirected graphs. 
    
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

if __name__ == "__main__":
    V = [1,2,3,4,5,6,7]
    E = {1:[2,4,7],
         2:[3,5],
         3:[5,6],
         5:[4]}
    connection, status = DFS(V,E)
    print(connection)