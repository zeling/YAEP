package me.zeling.parser.grammar;

/**
 * Created by zeling on 16/5/3.
 *
 * @author zeling
 */
public class DottedProduction {
    Production production;
    int dot;
    int size;

    public DottedProduction(Production production) {
        this(production, 0);
    }

    public DottedProduction(Production production, int dot) {
        this.production = production;
        this.dot = dot;
        this.size = production.terms.size();
    }

    public boolean isCompleted() {
        return dot >= size;
    }

    public DottedProduction advanceDot() {
        if (!isCompleted()) {
            return new DottedProduction(production, dot + 1);
        }
        return new DottedProduction(production, dot);
    }

//    public Term peekTermAtDot() {
//        return production.terms.get(dot - 1);
//    }

    public Term peekTermAfterDot() {
        return production.terms.get(dot);
    }
//
//    public Term peekTermBeforeDot() {
//        return production.terms.get(dot - 2);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DottedProduction that = (DottedProduction) o;

        if (dot != that.dot) return false;
        if (size != that.size) return false;
        return production.equals(that.production);

    }

    @Override
    public int hashCode() {
        int result = production.hashCode();
        result = 31 * result + dot;
        result = 31 * result + size;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int size = production.terms.size();
        while (start != dot && start < size) {
            sb.append(" ");
            sb.append(production.terms.get(start++));
        }
        sb.append(" .");
        while (start < size) {
            sb.append(" ");
            sb.append(production.terms.get(start++));
        }
        return sb.toString();
    }
}
