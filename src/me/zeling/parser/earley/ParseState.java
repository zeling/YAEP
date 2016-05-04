package me.zeling.parser.earley;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by zeling on 16/5/3.
 *
 * @author zeling
 */
public class ParseState implements Iterable<EarleyEntry> {
    ArrayList<EarleyEntry> entries;
    HashSet<EarleyEntry> membership;

    public ParseState() {
        entries = new ArrayList<>();
        membership = new HashSet<>();
    }

    public void add(EarleyEntry entry) {
        if (!membership.contains(entry)) {
            membership.add(entry);
            entries.add(entry);
        }
    }

    public int size() {
        return entries.size();
    }

    public EarleyEntry get(int i) {
        return entries.get(i);
    }

    @Override
    public Iterator<EarleyEntry> iterator() {
        return entries.iterator();
    }
}

