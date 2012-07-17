package com.jaspersoft.studio.data.querydesigner.hql;

import java.util.Arrays;
import java.util.List;

import com.jaspersoft.studio.data.querydesigner.sql.SQLLineStyler;
import com.jaspersoft.studio.data.querydesigner.sql.SQLScanner;
import com.jaspersoft.studio.data.querydesigner.sql.SimpleSQLQueryDesigner;

/**
 * Simple query designer for HQL (hibernate) that provides syntax highlighting.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class HQLQueryDesigner extends SimpleSQLQueryDesigner {
	private static List<String> hqlKeywords;
	
	@Override
	protected SQLLineStyler getSQLBasedLineStyler() {
		return new HQLLineStyler();
	}

	/*
	 * Class implementing a simple fuzzy scanner for HQL.
	 */
	private class HQLScanner extends SQLScanner {
		
		@Override
		protected List<String> getSQLKeywords() {
			if (hqlKeywords == null) {
				hqlKeywords = Arrays.asList(new String[] { "between", "class",
						"delete", "desc", "distinct", "elements", "escape",
						"exists", "false", "fetch", "from", "full", "group",
						"having", "in", "indices", "inner", "insert", "into",
						"is", "join", "left", "like", "new", "not", "null",
						"or", "order", "outer", "properties", "right",
						"select", "set", "some", "true", "union", "update",
						"versioned", "where", "and", "or", "as", "on", "with",
						"by", "both", "empty", "leading", "member", "object",
						"of", "trailing" });
				// built-in fuctions
				hqlKeywords.addAll(Arrays.asList(new String[] {
						// standard sql92 functions
						"substring",
						"locate",
						"trim",
						"length",
						"bit_length",
						"coalesce",
						"nullif",
						"abs",
						"mod",
						"sqrt",
						"upper",
						"lower",
						"cast",
						"extract",

						// time functions mapped to ansi extract
						"second",
						"minute",
						"hour",
						"day",
						"month",
						"year",
						"str",

						// misc functions - based on oracle dialect
						"sign", "acos", "asin", "atan", "cos", "cosh", "exp",
						"ln", "sin", "sinh", "stddev", "sqrt", "tan", "tanh",
						"variance", "round", "trunc", "ceil", "floor", "chr",
						"initcap", "lower", "ltrim", "rtrim", "soundex",
						"upper", "ascii", "length", "to_char", "to_date",
						"current_date", "current_time", "current_timestamp",
						"lastday", "sysday", "systimestamp", "uid", "user",
						"rowid", "rownum", "concat", "instr", "instrb", "lpad",
						"replace", "rpad", "substr", "substrb", "translate",
						"substring", "locate", "bit_length", "coalesce",
						"atan2", "log", "mod", "nvl", "nvl2", "power",
						"add_months", "months_between", "next_day", "max",
						"min" }));
			}
			return hqlKeywords;
		}
		
	}
	
	/*
	 * Line style for HQL language.
	 */
	private class HQLLineStyler extends SQLLineStyler {
		@Override
		protected SQLScanner getSQLBasedScanner() {
			return new HQLScanner();
		}
	}
}
