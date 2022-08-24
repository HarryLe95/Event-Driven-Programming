/*
* Main API of the RegexEngine.
*
* The RegexEngine consists of the frontend and the backend stages,
* The front end parse a regex pattern and build an equivalent e-NFA backbone
* The backend converts the backbone the functional eNFA and provides further optimisation
* including:
*     Simplifying the state object representation to an integer
*     Providing method for parsing string and validate string based on input regex pattern,
*     Normalising the eNFA to a DFA for a more optimised inference (TODO)
*/

import src.BackEnd.ENonDeterministicAutomata;
import src.BackEnd.ENonDeterministicAutomataTest;
import src.FrontEnd.FrontEnd;
import src.FrontEnd.StateContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegexEngine {
    public final FrontEnd frontEnd;
    public final ENonDeterministicAutomata backEnd;


    public RegexEngine(String string, boolean debug) {
        frontEnd = new FrontEnd(string, debug);
        StateContainer container = frontEnd.getModelContainer();
        backEnd = new ENonDeterministicAutomata(container.getInitState(),
                container.getFinalState(), container.getStateSet(), container.getSymbolSet(),
                container.getTransitionFunction(), container.getETransitionFunction(),
                debug);
    }



    public void parse(String string) {
        backEnd.accept(string);
    }

    public static void main(String[] args) {
        boolean verbose = false;
        if (args.length > 0){
            if (args[0].equals("-v")){
                verbose=true;
            }
        }
        Scanner scanner = new Scanner(System.in);
        RegexEngine engine = new RegexEngine(scanner.nextLine(),verbose);
        while (true){
            String nextLine = scanner.nextLine();
            if (nextLine.isEmpty()){
                engine.parse("\n");
            }else{
                engine.parse(nextLine);
            }
        }
    }
}
