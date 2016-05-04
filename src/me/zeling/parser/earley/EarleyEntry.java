package me.zeling.parser.earley;

import me.zeling.parser.grammar.DottedProduction;
import me.zeling.parser.grammar.NonTerminal;
import me.zeling.parser.grammar.Production;

/**
 * Created by zeling on 16/5/4.
 *
 * @author zeling
 */
public class EarleyEntry {
    NonTerminal head;
    DottedProduction production;
    int start;

    public EarleyEntry(NonTerminal head, DottedProduction production, int start) {
        this.head = head;
        this.production = production;
        this.start = start;
    }

    public EarleyEntry(NonTerminal head, Production production, int start) {
        this(head, new DottedProduction(production), start);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarleyEntry entry = (EarleyEntry) o;

        if (start != entry.start) return false;
        if (head != null ? !head.equals(entry.head) : entry.head != null) return false;
        return !(production != null ? !production.equals(entry.production) : entry.production != null);

    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + (production != null ? production.hashCode() : 0);
        result = 31 * result + start;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(head);
        sb.append(" ->");
        sb.append(production);
        sb.append(" (");
        sb.append(start);
        sb.append(")");
        return sb.toString();
    }
}
