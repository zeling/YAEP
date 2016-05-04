package me.zeling.parser.grammar;

import static me.zeling.parser.grammar.NonTerminal.nonTerminal;

/**
 * Created by zeling on 16/5/3.
 *
 * @author zeling
 */
public class ProductionRule {
    NonTerminal head;
    Production tail;

    public NonTerminal getHead() {
        return head;
    }

    public Production getTail() {
        return tail;
    }

    public ProductionRule(NonTerminal head, Production tail) {
        this.head = head;
        this.tail = tail;
    }

    public ProductionRule(String head, Term... terms) {
        this(nonTerminal(head), new Production(terms));
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", head, tail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductionRule that = (ProductionRule) o;

        if (head != null ? !head.equals(that.head) : that.head != null) return false;
        return !(tail != null ? !tail.equals(that.tail) : that.tail != null);

    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + (tail != null ? tail.hashCode() : 0);
        return result;
    }
}
