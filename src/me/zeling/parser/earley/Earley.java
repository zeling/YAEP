package me.zeling.parser.earley;

import me.zeling.parser.grammar.*;

import java.util.ArrayList;

/**
 * Created by zeling on 16/5/2.
 *
 * @author zeling
 */
public class Earley<Token extends Terminal> {
    ArrayList<ParseState> states;
    Grammar g;
    int currState;

    public Earley(Grammar g) {
        this.g = g;
        this.currState = 0;
        this.states = new ArrayList<>();
        ParseState initial = new ParseState();
        for (Production p : g.getGrammarMap().get(g.getStart())) {
            initial.add(new EarleyEntry(g.getStart(), new DottedProduction(p), 0));
        }
        states.add(initial);
    }

    private ParseState getNextState() {
        while (currState > states.size() - 2) {
            states.add(new ParseState());
        }
        return states.get(currState + 1);
    }

    private ParseState getCurrState() {
        while (currState > states.size() - 1) {
            states.add(new ParseState());
        }
        return states.get(currState);
    }

    public boolean parse(Iterable<Token> tokens) {

        for (Token token : tokens) {

            ParseState st = getCurrState();

            for (int i = 0; i < st.size(); i++) {
                EarleyEntry entry = st.get(i);
                DottedProduction p = entry.production;
                if (p.isCompleted()) {
                    /* completed */
                    ParseState parent = states.get(entry.start);
                    for (EarleyEntry pentry : parent) {
                        if (!pentry.production.isCompleted()
                                && pentry.production.peekTermAfterDot().equals(entry.head)) {
                            st.add(new EarleyEntry(pentry.head, pentry.production.advanceDot(), pentry.start));
                        }
                    }
                } else if (p.peekTermAfterDot() instanceof NonTerminal) {
                    /* predict */
                    NonTerminal nonTerminal = (NonTerminal) p.peekTermAfterDot();
                    for (Production production : g.getGrammarMap().get(nonTerminal)) {
                        st.add(new EarleyEntry(nonTerminal, production, currState));
                    }
                } else if (token.equals(p.peekTermAfterDot())) {
                    /* scan */
                    ParseState st1 = getNextState();
                    st1.add(new EarleyEntry(entry.head, entry.production.advanceDot(), entry.start));
                }
            }

            currState++;
        }

        for (EarleyEntry entry : states.get(states.size() - 1)) {
            if (entry.start == 0 && entry.production.isCompleted() && entry.head.equals(g.getStart())) {
                return true;
            }
        }

        return false;
    }

}
