package com.almasb.lang;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class AssignmentExpression implements Expression {
    @Override
    public boolean matches(String line) {
        return line.startsWith("var");
    }

    @Override
    public void eval(String line, Env env) {
        String[] tokens = line.split(" +");

        String id = tokens[1];
        String value = tokens[3];

        env.getMemory().put(id, value);
    }
}
