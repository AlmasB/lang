package com.almasb.lang;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class MemCommand implements Expression {
    @Override
    public boolean matches(String line) {
        return "mem".equals(line);
    }

    @Override
    public void eval(String line, Env env) {
        env.getMemory().forEach((id, value) -> {
            env.getOutput().accept(id + ": " + value);
        });
    }
}
