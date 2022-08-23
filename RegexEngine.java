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
import src.FrontEnd.FrontEnd;
import src.FrontEnd.StateContainer;

import java.util.Scanner;

public class RegexEngine {
    private final FrontEnd frontEnd;
    private final ENonDeterministicAutomata backEnd;

    public RegexEngine(String string, boolean debug) {
        frontEnd = new FrontEnd(string, debug);
        StateContainer container = frontEnd.getModelContainer();
        backEnd = new ENonDeterministicAutomata(container.getInitState(),
                container.getFinalState(), container.getStateSet(), container.getSymbolSet(),
                container.getTransitionFunction(), container.getETransitionFunction());
    }



    public void parse(String string) {
        System.out.println(backEnd.accept(string, false));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegexEngine engine = new RegexEngine(scanner.nextLine(),false);
        while (true){
            engine.parse(scanner.nextLine());
        }
    }
}
