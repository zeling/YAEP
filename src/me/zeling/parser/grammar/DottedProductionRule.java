package me.zeling.parser.grammar;

/**
 * Created by zeling on 16/5/3.
 *
 * @author zeling
 */
@Deprecated
public class DottedProductionRule {
    ProductionRule rule;
    int dot;

    public DottedProductionRule(ProductionRule rule) {
        this.rule = rule;
        this.dot = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int size = rule.tail.terms.size();
        sb.append(rule.head);
        sb.append(" ->");
        while (start != dot && start < size) {
            sb.append(" ");
            sb.append(rule.tail.terms.get(start++));
        }
        sb.append(" .");
        while (start < size) {
            sb.append(" ");
            sb.append(rule.tail.terms.get(start++));
        }
        return sb.toString();
    }

    public void advanceDot() {
        if (!completed()) ++dot;
    }

    private boolean completed() {
        return (dot < rule.tail.terms.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DottedProductionRule that = (DottedProductionRule) o;

        if (dot != that.dot) return false;
        return !(rule != null ? !rule.equals(that.rule) : that.rule != null);

    }

    @Override
    public int hashCode() {
        int result = rule != null ? rule.hashCode() : 0;
        result = 31 * result + dot;
        return result;
    }
}
