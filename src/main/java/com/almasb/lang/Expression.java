package com.almasb.lang;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public interface Expression {

    boolean matches(String line);

    void eval(String line, Env env);
}
