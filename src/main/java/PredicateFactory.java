/**
 * @author timur.shakurov
 */
public abstract class PredicateFactory {
    public static Predicate createPredicate(final int x0) {
        return new Predicate() {
            @Override
            public boolean P(int x) {
                return x == x0;
            }
        };
    }
}
