/* Implement the Shunting Yard Algorithm for RegexParser.
 *
 * The only add-on is the invisible operator '.' to represent the concatenation
 * operation.
 *
 */

package src.FrontEnd;

import src.utils.Pair;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Queue;

public class RParser extends StringParser{

    private int opCounter;
    RParser(){
        this.opDict = new HashMap<>(Map.of(
                '*', Pair.of(3,'L'),
                '+', Pair.of(3,'L'),
                '.', Pair.of(2,'L'),
                '|', Pair.of(1,'L')
        ));
    }

    protected boolean toConcat;

    private void opCheck(char op){
        if (op=='|'||op=='.'){
            opCounter-=2;
        }
        if (op =='+'||op=='*'){
            opCounter-=1;
        }
        if (opCounter<0){
            throw new RuntimeException("Insufficient operand(s) for operation");
        }
    }

    @Override
    public void initialise(){
        super.initialise();
        this.toConcat=false;
        opCounter=0;
    }

    @Override
    protected char parseSubroutine(char c, char p) {
        if (isAlphanumeric(c) || isSpace(c)) {
            if (!toConcat){
                toConcat=true;
            }else {
                parseSubroutine('.', p);
            }
            outputQueue.offer(c);
            opCounter++;
        } else if (isOperator(c)) {
            if (c=='+'||c=='*'){
                if (!(isAlphanumeric(p)||isSpace(p)||isRightParenthesis(p))){
                    throw new RuntimeException("Invalid operator placement");
                }
            }
            while (!opStack.isEmpty()) {
                char op2 = opStack.peek();
                if (!isLeftParenthesis(op2) && (
                        (opDict.get(op2).first > opDict.get(c).first) ||
                                ((opDict.get(op2).first == opDict.get(c).first) &&
                                        (opDict.get(c).second == 'L')))) {
                    opCheck(op2);
                    outputQueue.offer(opStack.pop());
                    opCounter++;
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
                parseSubroutine('.', p);
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
        return c;
    }

    @Override
    public Queue<Character> parse(String string, boolean debug) {
        initialise();
        char p = '\0';
        for (char c : string.toCharArray()) {
            p = parseSubroutine(c,p);
            if (debug) {
                System.out.println("Token: " + c + ", opStack: " + opStack + ", outputQueue: " + outputQueue);
            }
        }
        while (!opStack.isEmpty()) {
            char op = opStack.pop();
            if (isLeftParenthesis(op)){
                throw new InputMismatchException("Extra parenthesis");
            }
            opCheck(op);
            outputQueue.offer(op);
            opCounter++;
        }
        if (debug) {
            System.out.println("opStack: " + opStack + ", outputQueue: " + outputQueue);
        }return outputQueue;
    }

    public static void main(String[] args) {
        RParser regexParser = new RParser();
        System.out.println(regexParser.parse("abc|xyz",false));
        System.out.println(regexParser.parse("ab(xy)+|(c|d)at",false));
        System.out.println(regexParser.parse("((a|b)(c|d)*e)+|f",false));
        System.out.println(regexParser.parse("a|b",false));
        System.out.println(regexParser.parse(" a | +c|",false));
    }
}
