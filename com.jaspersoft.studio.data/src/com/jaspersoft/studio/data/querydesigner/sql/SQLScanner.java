/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.sql;

import java.util.Arrays;
import java.util.List;


/**
 * Class implementing a simple fuzzy scanner for SQL.
 * <p>
 * Clients can easily extends this class and provide similar simple support to
 * other SQL-based language. Usually, they will only need to rewrite the methods
 * {@link #getSQLKeywords()} and {@link #getSQLSymbols()} to provide
 * they stuff additionally or instead of the current specified one.
 * <p>
 * NOTE: Re-used code and idea from JavaViewer SWT Example. 
 *  
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * @see SQLLineStyler
 */
public class SQLScanner {
    public static final int EOF_CHAR=-1;
    public static final int EOL_CHAR=10;
	private static List<String> sqlKeywords;
	private static List<String> sqlSymbols;
	
    private StringBuffer fBuffer = new StringBuffer();
    private String fDoc;
    private int fPos;
    private int fEnd;
    private int fStartToken;
    
    public SQLScanner(){
    }
    
	/**
	 * Gets next token type in order to decide how to "style it".
	 * 
	 * @return the token type
	 */
	public SQLTokensType nextToken() {
		int c;
		fStartToken = fPos;
		while (true) {
			switch (c = read()) {
			case EOF_CHAR:
				return SQLTokensType.EOF;
			case '/': // comment
				c = read();
				if (c == '/') {
					while (true) {
						c = read();
						if ((c == EOF_CHAR) || (c == EOL_CHAR)) {
							unread(c);
							return SQLTokensType.COMMENT;
						}
					}
				} else {
					unread(c);
				}
				return SQLTokensType.OTHER;
			case '-': // comment
				c = read();
				if (c == '-') {
					while (true) {
						c = read();
						if ((c == EOF_CHAR) || (c == EOL_CHAR)) {
							unread(c);
							return SQLTokensType.COMMENT;
						}
					}
				} else {
					unread(c);
				}
				return SQLTokensType.OTHER;
			case '$':
				c = read();
				SQLTokensType jrbaseExprType=null;
				if(c=='P'){
					jrbaseExprType=SQLTokensType.JRPARAMETER;
				}
				else if(c=='F'){
					jrbaseExprType=SQLTokensType.JRFIELD;
				}
				else if(c=='V'){
					jrbaseExprType=SQLTokensType.JRVARIABLE;
				}
				else {
					break;
				}
				c = read();
				if(c=='{'){
					for (;;) {
						c = read();
						switch (c) {
						case '}':
							return jrbaseExprType;
						case EOF_CHAR:
							unread(c);
							return jrbaseExprType;
						case '\\':
							c = read();
							break;
						}
					}
				}
			case '\'':
				for (;;) {
					c = read();
					switch (c) {
					case '\'':
						return SQLTokensType.QUOTED_LITERAL;
					case EOF_CHAR:
						unread(c);
						return SQLTokensType.QUOTED_LITERAL;
					case '\\':
						c = read();
						break;
					}
				}
			case '"':
				for (;;) {
					c = read();
					switch (c) {
					case '"':
						return SQLTokensType.QUOTED_LITERAL;
					case EOF_CHAR:
						unread(c);
						return SQLTokensType.QUOTED_LITERAL;
					case '\\':
						c = read();
						break;
					}
				}
			case '[':
				for (;;) {
					c = read();
					switch (c) {
					case ']':
						return SQLTokensType.QUOTED_LITERAL;
					case EOF_CHAR:
						unread(c);
						return SQLTokensType.QUOTED_LITERAL;
					case '\\':
						c = read();
						break;
					}
				}
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				do {
					c = read();
				} while (Character.isDigit((char) c));
				unread(c);
				return SQLTokensType.NUMBER;
			default:
				if (getSQLSymbols().contains(Character.toString((char)c))){
					return SQLTokensType.SYMBOL;
				}
				if (Character.isWhitespace((char) c)) {
					do {
						c = read();
					} while (Character.isWhitespace((char) c));
					unread(c);
					return SQLTokensType.SPACE;
				}
				if (Character.isJavaIdentifierStart((char) c)) {
					fBuffer.setLength(0);
					do {
						fBuffer.append((char) c);
						c = read();
					} while (Character.isJavaIdentifierPart((char) c));
					unread(c);
					
					if(getSQLKeywords().contains(fBuffer.toString().toLowerCase())){
						return SQLTokensType.KEYWORD;
					}
					return SQLTokensType.STANDARD_TEXT;
				}
				return SQLTokensType.OTHER;
			}
		}
	}

	private int read() {
		if (fPos <= fEnd) {
			return fDoc.charAt(fPos++);
		}
		return EOF_CHAR;
	}
	
	private void unread(int c) {
		if (c != EOF_CHAR)
			fPos--;
	}

	public void setRange(String text) {
		fDoc = text;
		fPos = 0;
		fEnd = fDoc.length() - 1;
	}
	
	public int getStartOffset() {
		return fStartToken;
	}

	public int getLength() {
	      return fPos - fStartToken;
	}

	/**
	 * Initializes, if needed, the SQL keywords used by the scanner instance.
	 * 
	 * @return the list of SQL keywords
	 */
	protected List<String> getSQLKeywords(){
		if(sqlKeywords==null){
			sqlKeywords=Arrays.asList(		
					new String[]{
							"add", "all", "alter",
							"and", "any", "as", "asc", "authorization", "backup", "begin",
							"between", "body", "break", "browse", "bulk", "by", "cascade",
							"case", "check", "checkpoint", "close", "clustered",
							"coalesce", "column", "commit", "committed", "compute",
							"confirm", "constraint", "contains", "containstable",
							"continue", "controlrow", "create", "cross", "current",
							"current_date", "current_time", "current_timestamp",
							"current_user", "cursor", "database", "dbcc", "deallocate",
							"declare", "default", "delete", "deny", "desc", "disk",
							"distinct", "distributed", "double", "drop", "dummy",
							"dynamic", "else", "encryption", "end", "errlvl", "errorexit",
							"escape", "except", "exec", "exit", "fast_forward", "file",
							"fillfactor", "floppy", "for", "foreign", "forward_only",
							"freetext", "freetexttable", "from", "full", "function",
							"goto", "grant", "group", "having", "holdlock", "identity",
							"identity_insert", "identitycol", "if", "in", "index", "inner",
							"insensitive", "insert", "instead", "intersect", "into", "is",
							"isolation", "join", "key", "keyset", "kill", "left", "level",
							"like", "lineno", "load", "mirrorexit", "national", "nocheck",
							"nonclustered", "not", "null", "nullif", "of", "off",
							"offsets", "on", "once", "only", "open", "opendatasource",
							"openquery", "openrowset", "optimistic", "option", "or",
							"order", "outer", "over", "package", "percent", "perm",
							"permanent", "pipe", "plan", "precision", "prepare", "primary",
							"print", "privileges", "proc", "procedure", "processexit",
							"public", "raiserror", "read", "read_only", "readtext",
							"reconfigure", "references", "repeatable", "replication",
							"restore", "restrict", "return", "returns", "revoke", "right",
							"rollback", "row", "rowcount", "rowguidecol", "rule", "save",
							"schema", "schemabinding", "scroll_locks", "select",
							"serializable", "session_user", "set", "setuser", "shutdown",
							"some", "sql_variant", "static", "statistics", "system_user",
							"table", "tape", "temp", "temporary", "textsize", "then", "to",
							"top", "tran", "transaction", "trigger", "truncate", "tsequal",
							"type_warning", "uncommitted", "union", "unique", "update",
							"updatetext", "use", "values", "varying", "view", "waitfor",
							"when", "where", "while", "with", "work", "writetext", "xml"
				});
		}
		return sqlKeywords;
	}
	
	/**
	 * Initializes, if needed, the SQL symbols used by the scanner instance.
	 * 
	 * @return the list of SQL symbols
	 */
	protected List<String> getSQLSymbols(){
		if(sqlSymbols==null){
			sqlSymbols=Arrays.asList(
					new String[]{
						";", "=", "+", "-", "/",
						"*", "&", "|", "^", "(", ")", "[", "]", ",", ".", ":", "!",
						"~", "<", ">", "%", "{", "}", "?", "#", "@"	
			});
		}
		return sqlSymbols;
	}
	
}

