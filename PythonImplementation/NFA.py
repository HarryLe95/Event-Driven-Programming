from re import X
from DFA import FA, DFA
from typing import Sequence

delta = {(0,'0'):{0,1},
         (0,'1'):0,
         (1,'1'):2
         }

class NFA(DFA):
    """NFA - non-deterministic finite automate, inherits from DFA class
    
    States in the NFA can be a set of state in conventional DFA setting.
    
    TODO: add methods to convert and NFA to an equivalent DFA
    """
    def __init__(self,
                init_state:int|Sequence[int], 
                state_set: Sequence[int],
                transition_function:dict,
                alphabet: Sequence[str],
                final_state:int|Sequence[int]):
        super().__init__(init_state=init_state,state_set=state_set,
                         transition_function=transition_function, alphabet=alphabet,
                         final_state=final_state)
        
    def symbolTransition(self, state: Sequence[int]|int, symbol:str):
        """Perform a table look up to determine the transition states

        Args:
            state (int|Sequence[int]): current state
            symbol (str): input symbol

        Raises:
            ValueError: if symbol does not belong to the alphabet

        Returns:
            int|Sequence[int]: next state or next set of states
        """
        if not self.isValidState(state):
            raise ValueError(f"Input state(s) {state} must belong to the state set {self.state_set}")
        if isinstance(state,int):
            return super().symbolTransition(state=state, symbol=symbol)
        new_states = set()
        for s in state:
            if (s,symbol) in self.transition_function:
                temp_ = NFA.makeSet(super().symbolTransition(state=s,symbol=symbol))
                new_states.update(temp_)
        return new_states
    
if __name__ == "__main__":
    init_state = 0
    final_state = 2
    state_set = {0,1,2}
    symbol_transition=delta
    alphabet = {'0','1'}
    zeroOne = NFA(init_state=init_state,
                  state_set=state_set,
                  alphabet=alphabet,
                  transition_function=symbol_transition,
                  final_state=final_state)
    
    #Check str 0101
    print(zeroOne('0101'))