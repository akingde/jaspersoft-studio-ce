package com.jaspersoft.studio.data.hive.querydesigner;

import java.util.Arrays;
import java.util.List;

import com.jaspersoft.studio.data.querydesigner.sql.SQLScanner;

/**
 * Class implementing a simple fuzzy scanner for HQL.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 */
public class HiveQLScanner extends SQLScanner {

	private static List<String> hqlKeywords;

	@Override
	protected List<String> getSQLKeywords() {
		if (hqlKeywords == null) {
			hqlKeywords = Arrays.asList(new String[] { "between", "class",
					"delete", "desc", "distinct", "elements", "escape",
					"exists", "false", "fetch", "from", "full", "group",
					"having", "in", "indices", "inner", "insert", "into", "is",
					"join", "left", "like", "new", "not", "null", "or",
					"order", "outer", "properties", "right", "select", "set",
					"some", "true", "union", "update", "versioned", "where",
					"and", "or", "as", "on", "with", "by", "both", "empty",
					"leading", "member", "object", "of", "trailing" });
			// built-in fuctions
			hqlKeywords.addAll(Arrays.asList(new String[] {
					// standard sql92 functions
					"round", "floor", "ceil", "rand", "concat", "substr",
					"upper", "ucase", "lower", "lcase", "trim", "ltrim",
					"rtrim", "regexp_replace", "size", "cast", "from_unixtime",
					"to_date", "month", "year", "day",

					// misc functions - based on oracle dialect
					"get_json_object", "count", "sum", "avg", "min", "max",
					"min" }));
		}
		return hqlKeywords;
	}

}