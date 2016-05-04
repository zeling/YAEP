package me.zeling.parser.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by zeling on 16/5/3.
 *
 * @author zeling
 */
public class Production {
    ArrayList<Term> terms;

    public Production(Term... terms) {
        this.terms = new ArrayList<Term>(Arrays.asList(terms));
    }

    @Override
    public String toString() {
        return terms.stream().map(Term::toString).collect(Collectors.joining(" "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Production that = (Production) o;

        return terms.equals(that.terms);

    }

    @Override
    public int hashCode() {
        return terms.hashCode();
    }
}
