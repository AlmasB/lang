package com.almasb.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Env {

    private Map<String, Object> memory = new HashMap<>();

    private Consumer<String> output;

    public Env(Consumer<String> output) {
        this.output = output;
    }

    public Consumer<String> getOutput() {
        return output;
    }

    public Map<String, Object> getMemory() {
        return memory;
    }
}
