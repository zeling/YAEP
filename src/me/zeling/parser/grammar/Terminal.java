package me.zeling.parser.grammar;

/**
 * Created by zeling on 16/5/3.
 *
 * @author zeling
 */
public class Terminal extends Term {
    String value;

    public Terminal(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return '\'' + value + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Terminal terminal = (Terminal) o;

        return !(value != null ? !value.equals(terminal.value) : terminal.value != null);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
