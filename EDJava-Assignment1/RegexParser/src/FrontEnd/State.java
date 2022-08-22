package src.FrontEnd;


import src.utils.FiniteSet;

import java.util.HashMap;

public class State {
    public static int counter;
    public int name;

    public void initialise(){
        this.name = counter;
        counter++;
    }

    State(){
        initialise();
        this.eTransition=new FiniteSet<>();
        this.transition=new HashMap<>();
    }

    public FiniteSet<State> eTransition;
    public HashMap<Character,FiniteSet<State>> transition;

    public FiniteSet<State> next(){
        return eTransition;
    }

    public void add(State another){
        eTransition.add(another);
    }

    public void add(State another, char c){
        FiniteSet<State> anotherSet;
        if (transition.get(c)==null){
            anotherSet = FiniteSet.of(another);
        }else {
            anotherSet = transition.get(c).getUnion(FiniteSet.of(another));
        }
        transition.put(c,anotherSet);
    }

    public FiniteSet<State> next(char c){
        return transition.get(c);
    }

    public boolean isEndState(){
        return (eTransition.isEmpty() && transition.isEmpty());
    }

    @Override
    public String toString() {
        return "q"+name;
    }

    public void repr(){
        String output = "State: q"+name+", transition: "+transition+", eTransition: "+eTransition;
        System.out.println(output);
    }



    public static void main(String[] args) {
        State q0 = new State();
        State q1 = new State();
        State q2 = new State();
        q0.add(q1);
        q0.add(q2,'a');
        System.out.println(q0.next());
        System.out.println(q0.next('a'));
        System.out.println(q0.isEndState());
        System.out.println(q1.isEndState());
        System.out.println(q2.isEndState());
        q0.repr();
        q1.repr();
        q2.repr();

    }

}
