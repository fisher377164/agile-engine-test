package agile.engine.test.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.util.function.Supplier;

public class WhereClauseBuilder {

    private final BooleanBuilder where;

    public WhereClauseBuilder() {
        this.where = new BooleanBuilder();
    }

    private WhereClauseBuilder(Predicate predicate) {
        this.where = new BooleanBuilder(predicate);
    }

    public WhereClauseBuilder optionalAnd(final Object value, final Supplier<Predicate> predicateSupplier) {
        if (value != null) {
            return new WhereClauseBuilder(this.where.and(predicateSupplier.get()));
        }
        return this;
    }

    public BooleanBuilder where() {
        return where;
    }
}
