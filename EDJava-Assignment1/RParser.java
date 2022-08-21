import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class RParser extends StringParser{
    RParser(){
        this.opDict = new HashMap<>(Map.of(
                '*', Pair.of(3,'L'),
                '+', Pair.of(3,'L'),
                '.', Pair.of(2,'L'),
                '|', Pair.of(1,'L')
        ));
    }

    protected boolean toConcat;

    @Override
    public void initialise(){
        super.initialise();
        this.toConcat=false;
    }

    @Override
    protected void parseSubroutine(char c) {
        if (isSpace(c)) {
            return;
        } else if (isAlphanumeric(c)) {
            if (!toConcat){
                toConcat=true;
            }else {
                parseSubroutine('.');
            }
            outputQueue.offer(c);
        } else if (isOperator(c)) {
            while (!opStack.isEmpty()) {
                char op2 = opStack.peek();
                if (!isLeftParenthesis(op2) && (
                        (opDict.get(op2).first > opDict.get(c).first) ||
                                ((opDict.get(op2).first == opDict.get(c).first) &&
                                        (opDict.get(c).second == 'L')))) {
                    outputQueue.offer(opStack.pop());
                } else {
                    break;
                }
            }
            opStack.push(c);
            if (c=='|'){
                toConcat=false;
            }
        } else if (isLeftParenthesis(c)) {
            if (toConcat){
                parseSubroutine('.');
                toConcat=false;
            }
            opStack.push(c);
        } else if (isRightParenthesis(c)) {
            boolean mismatch = true;
            while (!opStack.isEmpty()) {
                if (isLeftParenthesis(opStack.peek())) {
                    opStack.pop();
                    mismatch = false;
                    break;
                } else {
                    outputQueue.offer(opStack.pop());
                }
            }
            if (mismatch) {
                throw new InputMismatchException("Missing parenthesis");
            }
        } else {
            throw new InputMismatchException("Not a valid language symbol");
        }
    }

    public static void main(String[] args) {
        RParser regexParser = new RParser();
        System.out.println(regexParser.parse("abc|xyz",false));
        System.out.println(regexParser.parse("ab(xy)+|(c|d)at",false));
        System.out.println(regexParser.parse("((a|b)(c|d)*e)+|f",false));
    }
}
