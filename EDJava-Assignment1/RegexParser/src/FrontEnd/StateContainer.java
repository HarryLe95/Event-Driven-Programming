package src.FrontEnd;


import src.utils.FiniteSet;
import src.utils.Pair;
import src.utils.TransitionFunction;

import java.util.HashMap;
import java.util.Map;

public class StateContainer {
    protected State start;
    protected State end;

    protected HashMap<Integer, FiniteSet<Integer>> eTransitionFunction;

    protected TransitionFunction<Integer, Character> transitionFunction;

    protected FiniteSet<Integer> stateSet;

    protected FiniteSet<Character> symbolSet;

    protected FiniteSet<Integer> initState;

    protected FiniteSet<Integer> finalState;

    StateContainer(State start, State end) {
        this.start = start;
        this.end = end;
        this.eTransitionFunction = new HashMap<>();
        this.transitionFunction = new TransitionFunction<>();
        this.stateSet = new FiniteSet<>();
        this.symbolSet = new FiniteSet<>();
        this.initState = new FiniteSet<>();
        this.finalState = new FiniteSet<>();
    }

    public static StateContainer fromSymbol(char c) {
        State start = new State();
        State end = new State();
        start.add(end, c);
        return new StateContainer(start, end);
    }

    public static StateContainer fromSequence(StateContainer first, StateContainer second) {
        first.end.add(second.start);
        return new StateContainer(first.start, second.end);
    }

    public static StateContainer fromAlternation(StateContainer first, StateContainer second) {
        State start = new State();
        State end = new State();
        start.add(first.start);
        start.add(second.start);
        first.end.add(end);
        second.end.add(end);
        return new StateContainer(start, end);
    }

    public static StateContainer fromRepetitionZero(StateContainer another) {
        State start = new State();
        start.add(another.start);
        another.end.add(start);
        return new StateContainer(start, start);
    }

    public static StateContainer fromRepetitionOne(StateContainer another) {
        State start = new State();
        start.add(another.start);
        another.end.add(start);
        return new StateContainer(another.start, start);
    }

    private void printSubroutine(State currentState, FiniteSet<State> excludedStates) {
        currentState.repr();
        if (!currentState.transition.isEmpty()) {
            for (Map.Entry<Character, FiniteSet<State>> entry : currentState.transition.entrySet()) {
                for (State state : entry.getValue()) {
                    if (!excludedStates.contains(state)) {
                        excludedStates.add(state);
                        printSubroutine(state, excludedStates);
                    }
                }
            }
        }
        if (!currentState.eTransition.isEmpty()) {
            for (State state : currentState.eTransition) {
                if (!excludedStates.contains(state)) {
                    excludedStates.add(state);
                    printSubroutine(state, excludedStates);
                }
            }
        }
    }

    public void printStates() {
        FiniteSet<State> excludedStates = FiniteSet.of(start);
        printSubroutine(start, excludedStates);
    }

    private void renameSubroutine(State currentState, FiniteSet<State> excludedStates, int[] newName) {
        currentState.name = newName[0];
        newName[0]++;
        if (!currentState.transition.isEmpty()) {
            for (Map.Entry<Character, FiniteSet<State>> entry : currentState.transition.entrySet()) {
                for (State state : entry.getValue()) {
                    if (!excludedStates.contains(state)) {
                        excludedStates.add(state);
                        renameSubroutine(state, excludedStates, newName);
                    }
                }
            }
        }
        if (!currentState.eTransition.isEmpty()) {
            for (State state : currentState.eTransition) {
                if (!excludedStates.contains(state)) {
                    excludedStates.add(state);
                    renameSubroutine(state, excludedStates, newName);
                }
            }
        }
    }

    private void renameStates() {
        FiniteSet<State> excludedStates = FiniteSet.of(start);
        int[] newName = {0};
        renameSubroutine(start, excludedStates, newName);
    }

    private void updateSymbolSet() {
        if (!this.transitionFunction.isEmpty()) {
            for (Map.Entry<Pair<Integer, Character>, FiniteSet<Integer>> entry : transitionFunction.entrySet()) {
                char symbol = entry.getKey().second;
                this.symbolSet.add(symbol);
            }
        }
    }

    private void updateStateSet() {
        if (!this.transitionFunction.isEmpty()) {
            for (Map.Entry<Pair<Integer, Character>, FiniteSet<Integer>> entry : transitionFunction.entrySet()) {
                int state = entry.getKey().first;
                this.stateSet.add(state);
                this.stateSet.addAll(entry.getValue());
            }
        }
        if (!this.eTransitionFunction.isEmpty()) {
            for (Map.Entry<Integer, FiniteSet<Integer>> entry: eTransitionFunction.entrySet()){
                this.stateSet.add(entry.getKey());
                this.stateSet.addAll(entry.getValue());
            }
        }
    }

    private void updateTransitionFunctionSubroutine(State currentState, FiniteSet<State> excludedStates) {
        if (!currentState.transition.isEmpty()) {
            TransitionFunction<Integer, Character> transitionSet = new TransitionFunction<>();
            for (Map.Entry<Character, FiniteSet<State>> entry : currentState.transition.entrySet()) {
                FiniteSet<Integer> transitionSetName = new FiniteSet<>();
                for (State state : entry.getValue()) {
                    transitionSetName.add(state.name);
                    if (!excludedStates.contains(state)) {
                        excludedStates.add(state);
                        updateTransitionFunctionSubroutine(state, excludedStates);
                    }
                }
                transitionSet.put(Pair.of(currentState.name, entry.getKey()), transitionSetName);
                this.transitionFunction.putAll(transitionSet);
            }
        }
        if (!currentState.eTransition.isEmpty()) {
            FiniteSet<Integer> eTransitionSet = new FiniteSet<>();
            for (State state : currentState.eTransition) {
                eTransitionSet.add(state.name);
                if (!excludedStates.contains(state)) {
                    excludedStates.add(state);
                    updateTransitionFunctionSubroutine(state, excludedStates);
                }
            }
            this.eTransitionFunction.put(currentState.name, eTransitionSet);
        }
    }

    private void updateTransitionFunction() {
        renameStates();
        FiniteSet<State> excludedStates = FiniteSet.of(start);
        updateTransitionFunctionSubroutine(start, excludedStates);
    }

    private void updateInitState(){
        this.initState.add(start.name);
    }

    private void updateFinalState(){
        this.finalState.add(end.name);
    }
    public void compile(){
        renameStates();
        updateTransitionFunction();
        updateStateSet();
        updateSymbolSet();
        updateInitState();
        updateFinalState();
    }

    public String toString(){
        //Table header
        String repr = String.format("%-8s|%-20s|","State","eps");
        for (char c: symbolSet){
            String fString = String.format("%-20s|",c);
            repr=repr+fString;
        }
        int underlineLength = repr.length();
        repr=repr+"\n";

        //A dotted line under header
        String underline = "";
        for (int i = 0; i < underlineLength;i++){
            underline+="-";
        }
        underline+="\n";

        repr = underline + repr;
        repr = repr + underline;
        //Table Entries
        for (int s: stateSet){
            String signSymbol = "";
            if (initState.contains(s)){
                signSymbol+=">";
            }
            if (finalState.contains(s)){
                signSymbol+="*";
            }
            String fString = String.format("%-8s|",signSymbol+"q"+s);
            String eString = "";
            if (eTransitionFunction.get(s)!=null){
                for (int eState: eTransitionFunction.get(s)){
                    eString+= "q"+eState+" ";
                }
            }fString+= String.format("%-20s|",eString);
            String cString="";
            for (char c: symbolSet){
                String subCString="";
                if (transitionFunction.containsKey(Pair.of(s,c))){
                    for (int cState: transitionFunction.get(Pair.of(s,c))){
                        subCString+="q"+cState+" ";
                    }
                }cString+= String.format("%-20s|",subCString);
            }fString+=cString;
            fString+="\n";
            repr+=fString;
        }
        repr=repr+underline;
        return repr;
    }

    public static void main(String[] args) {
        StateContainer s0 = fromSymbol('0');
        StateContainer s1 = fromSymbol('1');
        StateContainer s1RepZ = fromRepetitionZero(s1);
        StateContainer s0s1RepZ = fromSequence(s0, s1RepZ);
        StateContainer u0 = fromSymbol('0');
        StateContainer u1 = fromSymbol('1');
        StateContainer u1u0 = fromSequence(u1, u0);
        StateContainer model = fromAlternation(s0s1RepZ, u1u0);
        model.compile();
        System.out.println(model.transitionFunction);
        System.out.println(model.eTransitionFunction);
        System.out.println(model.stateSet);
        System.out.println(model.symbolSet);
        System.out.println(model.initState);
        System.out.println(model.finalState);
        System.out.println(model);
    }

}
