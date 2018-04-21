package by.client.android.railwayapp.support.database;

import java.util.Stack;

/**
 * Created by PanteleevRV on 13.03.2018.
 *
 * <see>https://github.com/lexruee/Android-QueryBuilder</see>
 *
 * @author PRV
 */
public class SqlRequestBuilder {

    private static final String STRING_COMMA = ", ";
    private static final String STING_WHITESPACE = " ";
    private static final String STRING_SELECT = "SELECT";
    private static final String STRING_STAR = "* ";
    private static final String STRING_FROM = "FROM";
    private static final String STRING_WHERE = "WHERE";
    private static final String STRING_OR = "OR";
    private static final String STRING_AND = "AND";
    private static final String STRING_EQUALS = "=";
    private static final String STRING_NOT_EQUALS = "!=";
    private static final String STRING_INNER_JOIN = "INNER JOIN";
    private static final String STRING_ON = "ON";
    private static final String STRING_ORDER_BY = "ORDER BY";
    private static final String STRING_GROUP_BY = "GROUP BY";
    private static final String STRING_LIKE = "LIKE";
    private static final String STRING_HAVING = "HAVING";
    private static final String STRING_GREATER_OR_EQUALS = ">=";
    private static final String STRING_LESS_OR_EQUALS = "<=";
    private static final String STRING_GREATER = ">";
    private static final String STRING_LESS = "<";

    private Stack<String> expressions = new Stack<>();

    public String build() {
        StringBuilder sb = new StringBuilder();
        for (String e : this.expressions) {
            sb.append(e);
        }
        return sb.toString();
    }

    public SqlRequestBuilder select(String... cols) {
        this.expressions.add(STRING_SELECT);
        this.expressions.add(STING_WHITESPACE);
        if (cols.length == 0) {
            this.expressions.add(STRING_STAR);
        } else {
            for (String col : cols) {
                this.expressions.add(col);
                this.expressions.add(STRING_COMMA);
            }
            this.expressions.pop(); //remove last comma separator
            this.expressions.add(STING_WHITESPACE);
        }
        return this;
    }

    public SqlRequestBuilder from(String... tables) {
        this.expressions.add(STRING_FROM);
        this.expressions.add(STING_WHITESPACE);
        if (tables.length == 0) {
            throw new RuntimeException("table is missing!");
        }
        for (String table : tables) {
            this.expressions.add(table);
            this.expressions.add(STRING_COMMA);
        }
        this.expressions.pop(); //remove last comma separator
        return this;
    }

    public SqlRequestBuilder where(String string) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_WHERE);
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(string);
        return this;
    }

    public SqlRequestBuilder eq(Object obj) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_EQUALS);
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(obj.toString());
        return this;
    }

    public SqlRequestBuilder neq(Object obj) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_NOT_EQUALS);
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(obj.toString());
        return this;
    }

    public SqlRequestBuilder greaterOrEquals(Object obj) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_GREATER_OR_EQUALS);
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(obj.toString());
        return this;
    }

    public SqlRequestBuilder greater(Object obj) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_GREATER);
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(obj.toString());
        return this;
    }

    public SqlRequestBuilder lessOrEquals(Object obj) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_LESS_OR_EQUALS);
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(obj.toString());
        return this;
    }

    public SqlRequestBuilder less(Object obj) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_LESS);
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(obj.toString());
        return this;
    }

    public SqlRequestBuilder and(String and) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_AND);
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(and);
        return this;
    }

    public SqlRequestBuilder or(String or) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_OR);
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(or);
        return this;
    }

    public SqlRequestBuilder innerJoin(String joinTable) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_INNER_JOIN);
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(joinTable);
        return this;
    }

    public SqlRequestBuilder on(String column) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_ON);
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(column);
        return this;
    }

    public SqlRequestBuilder orderBy(String... orderBys) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_ORDER_BY);
        this.expressions.add(STING_WHITESPACE);
        for (String order : orderBys) {
            this.expressions.add(order);
            this.expressions.add(STRING_COMMA);
        }
        this.expressions.pop(); //remove last comma
        return this;
    }

    public SqlRequestBuilder like(String like) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_LIKE);
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add("'" + like + "'");
        return this;
    }

    public SqlRequestBuilder groupBy(String... groupBys) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_GROUP_BY);
        this.expressions.add(STING_WHITESPACE);
        for (String groupBy : groupBys) {
            this.expressions.add(groupBy);
            this.expressions.add(STRING_COMMA);
        }
        this.expressions.pop(); //remove last comma
        return this;
    }

    public SqlRequestBuilder having(String having) {
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(STRING_HAVING);
        this.expressions.add(STING_WHITESPACE);
        this.expressions.add(having);
        return this;
    }
}
