/*
* Implement the front end of a regex engine
* The main functionality is to build a postfix version of a regex pattern
* and build a Finite State Machine representation of it.
*
* The two public APIs are
*     The constructor which takes a regex pattern as an input and build the finite state machine modelContainer from it.
*     getModelContainer - which returns a StateContainer of the NFA equivalent to the regex pattern.
*
*/

package src.FrontEnd;

import java.util.Queue;
import java.util.Stack;

public class FrontEnd {
    private static RParser stringParser;
    private Queue<Character> outputQueue;
    private StateContainer modelContainer;

    public StateContainer getModelContainer(){
        return modelContainer;
    }

    public FrontEnd(){
        stringParser=new RParser();
    }

    public FrontEnd(String string, boolean debug){
        this.stringParser = new RParser();
        this.outputQueue = stringParser.parse(string, false);
        this.modelContainer = buildContainer();
        if (debug){
            System.out.println(modelContainer);
        }
        System.out.println("ready");
    }

    private StateContainer buildContainer(){
        Stack<StateContainer> container = new Stack<>();
        while(!outputQueue.isEmpty()){
            char c = outputQueue.remove();
            if (stringParser.isAlphanumeric(c)||
                    stringParser.isSpace(c)){ //Create new container and push to stack
                container.push(StateContainer.fromSymbol(c));
            }
            if (stringParser.isOperator(c)){
                if (c == '.'){
                    StateContainer secondOp = container.pop();
                    StateContainer firstOp = container.pop();
                    container.push(StateContainer.fromSequence(firstOp, secondOp));
                }
                if (c == '|'){
                    StateContainer secondOp = container.pop();
                    StateContainer firstOp = container.pop();
                    container.push(StateContainer.fromAlternation(firstOp, secondOp));
                }
                if (c == '*'){
                    StateContainer firstOp = container.pop();
                    container.push(StateContainer.fromRepetitionZero(firstOp));
                }
                if (c == '+'){
                    StateContainer firstOp = container.pop();
                    container.push(StateContainer.fromRepetitionOne(firstOp));
                }
            }
        }
        StateContainer result = container.pop();
        result.compile();

        return result;
    }
}
