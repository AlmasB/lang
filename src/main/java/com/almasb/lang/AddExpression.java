package com.almasb.lang;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class AddExpression implements Expression {
    @Override
    public boolean matches(String line) {
        return line.contains("+");
    }

    @Override
    public void eval(String line, Env env) {
        String tokens[] = line.split(" +");

        String left = tokens[0];
        String right = tokens[2];

        int l = Integer.parseInt(left);
        int r = Integer.parseInt(right);

        env.getOutput().accept(String.valueOf(l + r));
    }
}
