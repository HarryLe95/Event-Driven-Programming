import java.util.*;

public class StringParser {
    public static HashMap<Character, Pair<Integer, Character>> getOpDict() {
        return opDict;
    }

    public static void setOpDict(HashMap<Character, Pair<Integer, Character>> opDict) {
        StringParser.opDict = opDict;
    }

    public static HashMap<Character, Pair<Integer, Character>> opDict = new HashMap<>(Map.of(
            '*', Pair.of(2, 'L'),
            '+', Pair.of(2, 'L'),
            '|', Pair.of(1, 'L')
    ));

    public static boolean isAlphanumeric(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static boolean isOperator(char c) {
        return opDict.containsKey(c);
    }

    public static boolean isLeftParenthesis(char c) {
        return (c == '(');
    }

    public static boolean isRightParenthesis(char c) {
        return (c == ')');
    }

    public static boolean isSpace(char c) {
        return (c == ' ');
    }

    StringParser() {
    }

    public Queue<Character> parse(String string) {
        Queue<Character> outputQueue = new LinkedList<>();
        Stack<Character> opStack = new Stack<>();

        for (char c : string.toCharArray()) {
            if (isSpace(c)) {
                continue;
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
            System.out.println("Token: " + c + ", opStack: " + opStack + ", outputQueue: " + outputQueue);
        }
        while (!opStack.isEmpty()) {
            outputQueue.offer(opStack.pop());
        }
        System.out.println("opStack: " + opStack + ", outputQueue: " + outputQueue);
        return outputQueue;
    }

    public static void main(String[] args) {
        StringParser parser = new StringParser();
        HashMap<Character, Pair<Integer, Character>> opPred = new HashMap<>(Map.of(
                '^', Pair.of(4, 'R'),
                '*', Pair.of(3, 'L'),
                '/', Pair.of(3, 'L'),
                '+', Pair.of(2, 'L'),
                '-', Pair.of(2, 'L')
        ));
        parser.setOpDict(opPred);
        System.out.println(parser.parse("3+4*2/(1-5)^2^3"));
    }
}
