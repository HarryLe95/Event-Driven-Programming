import src.BackEnd.ENonDeterministicAutomata;
import src.FrontEnd.FrontEnd;
import src.FrontEnd.StateContainer;

/*
TODO: Add README.md
Add comments to the model. Set an event loop - while keyPressed != CTRL+C then continue
Write unit tests
Write whole program test
Run on submission
Modify so that when model receives incorrect error, it returns false instead of shutting down
Scenario with white space
 */
public class RegexEngine {
    private FrontEnd frontEnd;
    private ENonDeterministicAutomata backEnd;

    public RegexEngine(String string, boolean debug) {
        frontEnd = new FrontEnd(string, debug);
        StateContainer container = frontEnd.getModelContainer();
        backEnd = new ENonDeterministicAutomata(container.getInitState(),
                container.getFinalState(), container.getStateSet(), container.getSymbolSet(),
                container.getTransitionFunction(), container.getETransitionFunction());
    }

    public void accept(String string) {
        return backEnd.accept(string, false);
    }

    public static void main(String[] args) {
        RegexEngine engine = new RegexEngine("(ab)*|c+",false);
        System.out.println(engine.accept("abc"));
        System.out.println(engine.accept("ccc"));
    }
}
