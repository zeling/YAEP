package me.zeling.parser.grammar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * Created by zeling on 16/5/2.
 *
 * @author zeling
 */
public class Grammar implements Iterable<ProductionRule> {
    HashSet<ProductionRule> rules;
    HashMap<NonTerminal, HashSet<Production>> mapping;
    NonTerminal start;

    public Grammar(NonTerminal start, ProductionRule... rules) {
        this.start = start;
        this.rules = new HashSet<ProductionRule>();
        for (ProductionRule rule : rules) {
            this.rules.add(rule);
        }
    }

    public NonTerminal getStart() {
        return start;
    }

    public HashMap<NonTerminal, HashSet<Production>> getGrammarMap() {
        if (mapping == null) {
            mapping = new HashMap<>();

            for (ProductionRule rule : rules) {
                mapping.putIfAbsent(rule.head, new HashSet<>());
                mapping.get(rule.head).add(rule.tail);
            }
        }
        return mapping;
    }

    @Override
    public String toString() {
        return rules.stream().map(ProductionRule::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public Iterator<ProductionRule> iterator() {
        return rules.iterator();
    }
}

