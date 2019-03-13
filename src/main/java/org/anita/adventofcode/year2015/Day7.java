package org.anita.adventofcode.year2015;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {

    Map<String, Integer> gateValues = new HashMap<>();

    public void evaluate(List<Operation> operations) {
        while (operations.size() > 0) {
            Iterator<Operation> iterator = operations.iterator();
            while (iterator.hasNext()) {
                Operation next = iterator.next();
                if (next.canEvaluate()) {
                    int result = next.evaluate();
                    gateValues.putIfAbsent(next.result, result);
                    iterator.remove();
                }
            }
        }
    }

    public int getValue(String gate) {
        return gateValues.get(gate);
    }

    public void setGate(String gate, int value) {
        gateValues.put(gate, value);
    }

    public static String doubleArgOperationRegex = "^(\\S+) (\\S+) (\\S+) -> (\\S+)$";
    private Pattern doubleArgOperationPattern = Pattern.compile(doubleArgOperationRegex);

    public static String singleArgOperationRegex = "^(\\S+) (\\S+) -> (\\S+)$";
    private Pattern singleArgOperationPattern = Pattern.compile(singleArgOperationRegex);

    public static String noArgOperationRegex = "^(\\S+) -> (\\S+)$";
    private Pattern noArgOperationPattern = Pattern.compile(noArgOperationRegex);

    public Operation parseOperation(String line) {
        if (line.contains("AND") || line.contains("OR") || line.contains("RSHIFT") || line.contains("LSHIFT")) {
            Matcher matcher = doubleArgOperationPattern.matcher(line);
            matcher.find();
            return new Operation(matcher.group(1), matcher.group(3), matcher.group(2), matcher.group(4));
        } else if (line.contains("NOT")) {
            Matcher matcher = singleArgOperationPattern.matcher(line);
            matcher.find();
            return new Operation(matcher.group(2), null, matcher.group(1), matcher.group(3));
        } else {
            Matcher matcher = noArgOperationPattern.matcher(line);
            matcher.find();
            return new Operation(matcher.group(1), null, "NOOP", matcher.group(2));
        }
    }

    public class Operation {
        String reg1, reg2, operand, result;

        public Operation(String reg1, String reg2, String operand, String result) {
            this.reg1 = reg1;
            this.reg2 = reg2;
            this.operand = operand;
            this.result = result;
        }

        public boolean canEvaluate() {
            if ("NOOP".equals(operand)) {
                return gateValues.containsKey(reg1) || isNumber(reg1);
            } else if ("NOT".equals(operand)) {
                return gateValues.containsKey(reg1) || isNumber(reg1);
            } else {
                return (gateValues.containsKey(reg1) || isNumber(reg1)) && (gateValues.containsKey(reg2) || isNumber(reg2));
            }
        }

        public int evaluate() {
            if ("NOOP".equals(operand)) {
                if (isNumber(reg1)) {
                    return Integer.parseInt(reg1);
                } else {
                    return gateValues.get(reg1);
                }
            } else if ("NOT".equals(operand)) {
                if (isNumber(reg1)) {
                    return ~Integer.parseInt(reg1);
                } else {
                    return ~gateValues.get(reg1);
                }
            } else {
                Integer operand1 = 0;
                if (isNumber(reg1)) {
                    operand1 = Integer.parseInt(reg1);
                } else {
                    operand1 = gateValues.get(reg1);
                }

                Integer operand2 = 0;
                if (isNumber(reg2)) {
                    operand2 = Integer.parseInt(reg2);
                } else {
                    operand2 = gateValues.get(reg2);
                }

                switch (operand) {
                    case "AND":
                        return operand1 & operand2;
                    case "OR":
                        return operand1 | operand2;
                    case "LSHIFT":
                        return operand1 << operand2;
                    case "RSHIFT":
                        return operand1 >> operand2;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }

        public boolean isNumber(String reg) {
            return reg.matches("\\d+");
        }
    }
}
