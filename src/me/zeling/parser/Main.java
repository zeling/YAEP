package me.zeling.parser;

import me.zeling.parser.earley.Earley;
import me.zeling.parser.grammar.Grammar;
import me.zeling.parser.grammar.ProductionRule;
import me.zeling.parser.grammar.Terminal;

import java.util.ArrayList;
import java.util.Iterator;

import static me.zeling.parser.grammar.NonTerminal.nonTerminal;

/**
 * Created by zeling on 16/5/4.
 *
 * @author zeling
 */
public class Main {
    public static void main(String[] args) {
        Grammar g = new Grammar(nonTerminal("S"),
                new ProductionRule("S", nonTerminal("N"), new Terminal("("), nonTerminal("S"), new Terminal("\\and"), nonTerminal("S"), new Terminal(")")),
                new ProductionRule("S", new Terminal("("), nonTerminal("S"), new Terminal("\\or"), nonTerminal("S"), new Terminal(")")),
                new ProductionRule("S", new Terminal("("), nonTerminal("S"), new Terminal("\\impl"), nonTerminal("S"), new Terminal(")")),
                new ProductionRule("S", new Terminal("("), new Terminal("\\not"), nonTerminal("S"), new Terminal(")")),
                new ProductionRule("S", new Terminal("p")),
                new ProductionRule("N")
        );
        Earley<Terminal> parser = new Earley<>(g);
        System.out.println(parser.parse(new StringTokenizer("( p \\and ( \\not p ) )")));
    }


}

class StringTokenizer implements Iterable<Terminal> {
    ArrayList<Terminal> terminals = new ArrayList<>();

    public StringTokenizer(String string) {
        for (String token : string.split(" ")) {
            terminals.add(new Terminal(token));
        }
        terminals.add(new Terminal("EOF"));
    }

    @Override
    public Iterator<Terminal> iterator() {
        return terminals.iterator();
    }
}
