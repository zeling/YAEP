package me.zeling.parser.grammar;

import java.util.HashMap;

/**
 * Created by zeling on 16/5/3.
 *
 * @author zeling
 */
public class NonTerminal extends Term {
    String name;

    private static HashMap<String, NonTerminal> map = new HashMap<String, NonTerminal>();

    public static NonTerminal nonTerminal(String name) {
        NonTerminal ret = new NonTerminal(name);
        map.put(name, ret);
        return ret;
    }

    private NonTerminal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NonTerminal that = (NonTerminal) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
