from operator import truediv
from typing import Sequence

delta = {(0,'0'):1,
         (0,'1'):0,
         (1,'0'):1,
         (1,'1'):2,
         (2,'0'):1,
         (2,'1'):0}

class FA:
    """
    ABC for finite automata. This class handles input validation check and 
    is inherited by DFA and NFA. 
    """
    def __init__(self, 
                 init_state:int|Sequence[int], 
                 state_set: Sequence[int],
                 transition_function:dict,
                 alphabet: Sequence[str],
                 final_state:int|Sequence[int]):
        """FA Class constructor

        Args:
            init_state (int|Sequence[int]): intial state
            state_set (Sequence[int]): set of acceptable states
            alphabet (Sequence[str]): set of valid input
            symbol_transition (dict): transition function as a LUT 
            final_state (int): final accepting state
        """
        self.init_state = init_state
        self.final_state = final_state
        self.state_set = set(state_set) 
        self.transition_function = transition_function
        self.alphabet = alphabet 
        self.validateStates()
        
    def isValidState(self, state: int|Sequence[int]):
        """Helper function to validate if input state belongs to the set of acceptable states

        Args:
            state (int | Sequence[int]): input state

        Returns:
            bool: True if state belongs to acceptable state set
        """
        if isinstance(state,int):
            return state in self.state_set
        states = set(state)
        return states.issubset(self.state_set)
    
    def validateStates(self):
        """Function to perform input state validation
        
        Steps:
            1. validate init_state
            2. validate final_state
            3. validate begin state in transition function table
            4. validate end state in transition function table 
    
        Raises:
            ValueError: if one of the input states are invalid
        """
        valid_init = self.isValidState(self.init_state)
        valid_final = self.isValidState(self.final_state)
        begin = self.transition_function.keys()
        begin_state = [item[0] for item in begin]
        end_state = self.transition_function.values()
        valid_begin = self.isValidState(begin_state)
        valid_end = self.isValidState(end_state)
        if not (valid_init and valid_final and valid_begin and valid_end):
            raise ValueError("An input state does not belong to the input state set.")
        
    def isValidSymbol(self, symbol:str):
        """Check whether the input symbol is in the alphabet

        Args:
            symbol (str): symbol from input string
        """
        return symbol in self.alphabet 
        
class DFA(FA):
    def __init__(self, 
                 init_state:int, 
                 state_set:Sequence[int], 
                 alphabet:Sequence[str],
                 transition_function: dict,
                 final_state:int):
        """DFA Class constructor

        Args:
            init_state (int): intial state
            state_set (Sequence[int]): set of acceptable states
            alphabet (Sequence[str]): set of valid input
            symbol_transition (dict): transition function as a LUT 
            final_state (int): final accepting state
        """
        super().__init__(init_state = init_state,
                         alphabet=alphabet,
                         state_set=state_set,
                         transition_function=transition_function,
                         final_state = final_state)
  
    def symbolTransition(self, state:int, symbol:str):
        """Perform a table look up to determine the transition state

        Args:
            state (int): current state
            symbol (str): input symbol

        Raises:
            ValueError: if symbol does not belong to the alphabet

        Returns:
            int: next state
        """
        if not self.isValidSymbol(symbol):
            raise ValueError(f"Input symbol {symbol} must be in the alphabet {self.alphabet}.")
        return self.transition_function[(state,symbol)]
    
    def stringTransition(self, string:str, state:int=-1):
        """Recursive state transition on a string sequence

        Args:
            string (str): input string
            state (int, optional): current state. Defaults to -1, which selects the init_state

        Returns:
            int: end state after all symbols are ingested.
        """
        state = self.init_state if state == -1 else state
        if string == '':
            return state
        symbol, string = string[0], string[1:]
        state=self.symbolTransition(state,symbol)
        return self.stringTransition(string,state)
    
    def __call__(self, string:str):
        """Perform DFA operation on an input string. Return True if string results in the transition to the final state

        Args:
            string (str): a finite sequence of symbols drawn from the alphabet

        Returns:
            bool: accept/reject
        """
        end_state = self.stringTransition(string)
        if end_state == self.final_state:
            return True
        return False
    
if __name__ == "__main__":
    #Test zeroOne DFA
    init_state = 0
    final_state = 2
    state_set = {0,1,2}
    symbol_transition=delta
    alphabet = {'0','1'}
    zeroOne = DFA(init_state,state_set,alphabet,symbol_transition,final_state)
    
    #Check str 
    print(zeroOne('0110101'))
    
    #Check str 
    print(zeroOne('01101010'))