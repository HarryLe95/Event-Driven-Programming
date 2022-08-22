package src.FrontEnd;
import src.utils.Pair;

import java.util.*;

public class StringParser {
    protected Queue<Character> outputQueue;
    protected Stack<Character> opStack;
    protected HashMap<Character, Pair<Integer, Character>> opDict;

    StringParser(){}
    StringParser(HashMap<Character, Pair<Integer, Character>> opDict) {
        this.opDict = opDict;
    }

    public HashMap<Character, Pair<Integer, Character>> getOpDict() {
        return opDict;
    }

    public void initialise() {
        this.outputQueue = new LinkedList<>();
        this.opStack = new Stack<>();
    }

    public boolean isAlphanumeric(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public boolean isOperator(char c) {
        return opDict.containsKey(c);
    }

    public boolean isLeftParenthesis(char c) {
        return (c == '(');
    }

    public boolean isRightParenthesis(char c) {
        return (c == ')');
    }

    public static boolean isSpace(char c) {
        return (c == ' ');
    }

    protected void parseSubroutine(char c) {
        if (isSpace(c)) {
            return;
        } else if (isAlphanumeric(c)) {
            outputQueue.offer(c);
        } else if (isOperator(c)) {
            char op1 = c;
            while (!opStack.isEmpty()) {
                char op2 = opStack.peek();
                if (!isLeftParenthesis(op2) && (
                        (opDict.get(op2).first > opDict.get(op1).first) ||
                                ((opDict.get(op2).first == opDict.get(op1).first) &&
                                        (opDict.get(op1).second == 'L')))) {
                    outputQueue.offer(opStack.pop());
                } else {
                    break;
                }
            }
            opStack.push(op1);
        } else if (isLeftParenthesis(c)) {
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

    public Queue<Character> parse(String string, boolean debug) {
        initialise();
        for (char c : string.toCharArray()) {
            parseSubroutine(c);
            if (debug) {
                System.out.println("Token: " + c + ", opStack: " + opStack + ", outputQueue: " + outputQueue);
            }
        }
        while (!opStack.isEmpty()) {
            outputQueue.offer(opStack.pop());
        }
        if (debug) {
            System.out.println("opStack: " + opStack + ", outputQueue: " + outputQueue);
        }return outputQueue;
    }

    public static void main(String[] args) {
        HashMap<Character, Pair<Integer, Character>> regExMap = new HashMap<>(Map.of(
                '*', Pair.of(2, 'L'),
                '+', Pair.of(2, 'L'),
                '|', Pair.of(1, 'L')
        ));
        StringParser regexParser = new StringParser(regExMap);
        System.out.println(regexParser.parse("abc|xyz",false));


        HashMap<Character, Pair<Integer, Character>> arithmeticMap = new HashMap<>(Map.of(
                '^', Pair.of(4, 'R'),
                '*', Pair.of(3, 'L'),
                '/', Pair.of(3, 'L'),
                '+', Pair.of(2, 'L'),
                '-', Pair.of(2, 'L')
        ));
        StringParser parser = new StringParser(arithmeticMap);
        System.out.println(parser.parse("3+4*2/(1-5)^2^3",false));
    }
}
