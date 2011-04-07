/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author gtoffoli
 * This class derives from JRCsvDataSource and is used to read the field names.
 */
public class JRCsvDataSourceInspector {
    
    private DateFormat dateFormat = new SimpleDateFormat();
	private char fieldDelimiter = ',';
	private String recordDelimiter = "\n";
	private HashMap columnNames = new HashMap();

	private Vector fields;
	private Reader reader;
	private char buffer[] = new char[1024];
	private int position;
	private int bufSize;

	/**
	 * @param stream an input stream containing CSV data
	 */
	public JRCsvDataSourceInspector(InputStream stream)
	{
		this(new InputStreamReader(stream));
	}


	/**
	 * Builds a datasource instance.
	 * @param file a file containing CSV data
	 */
	public JRCsvDataSourceInspector(File file) throws FileNotFoundException
	{
		this(new FileReader(file));
	}


	/**
	 * Builds a datasource instance.
	 * @param reader a <tt>Reader</tt> instance, for reading the stream
	 */
	public JRCsvDataSourceInspector(Reader reader)
	{
		this.reader = reader;
	}


	/**
	 * Parses a row of CSV data and extracts the fields it contains
	 */
    @SuppressWarnings("unchecked")
	private boolean parseRow() throws IOException
	{
		int pos = 0;
		int startFieldPos = 0;
		boolean insideQuotes = false;
		boolean hadQuotes = false;
		boolean misplacedQuote = false;
		char c;
		fields = new Vector();

		String row = getRow();
		if (row == null || row.length() == 0)
			return false;

		while (pos < row.length()) {
			c = row.charAt(pos);

			if (c == '"') {
				// already inside a text containing quotes
				if (!insideQuotes) {
					if (!hadQuotes) {
						insideQuotes = true;
						hadQuotes = true;
					}
					else // the field contains a bad string, like "fo"o", instead of "fo""o"
						misplacedQuote = true;
				}
				// found a quote when already inside quotes, expecting two consecutive quotes, otherwise it means
				// it's a closing quote
				else {
					if (pos+1 < row.length() && row.charAt(pos+1) == '"')
						pos++;
					else
						insideQuotes = false;
				}
			}
			// field delimiter found, copy the field contents to the field array
			if (c == fieldDelimiter && !insideQuotes) {
				String field = row.substring(startFieldPos, pos);
				// if an illegal quote was found, the entire field is considered illegal
				if (misplacedQuote) {
					misplacedQuote = false;
					hadQuotes = false;
					field = "";
				}
				// if the field was between quotes, remove them and turn any escaped quotes inside the text into normal quotes
				else if (hadQuotes) {
					field = field.trim();
					if (field.startsWith("\"") && field.endsWith("\"")) {
						field = field.substring(1, field.length() - 1);
						field = replaceAll(field, "\"\"", "\"");
					}
					else
						field = "";
					hadQuotes = false;
				}

				fields.add(field);
				startFieldPos = pos + 1;
			}

			pos++;
			// if the record delimiter was found inside a quoted field, it is not an actual record delimiter,
			// so another line should be read
			if ((pos == row.length()) && insideQuotes) {
				row = row + recordDelimiter + getRow();
			}
		}

		// end of row was reached, so the final characters form the last field in the record
		String field = row.substring(startFieldPos, pos);
		if (field == null || field.length() == 0)
			return true;

		if (misplacedQuote)
			field = "";
		else if (hadQuotes) {
			field = field.trim();
			if (field.startsWith("\"") && field.endsWith("\"")) {
				field = field.substring(1, field.length() - 1);
				field = replaceAll(field, "\"\"", "\"");
			}
			else
				field = "";
		}
		fields.add(field);

		return true;
	}


	/**
	 * Reads a row from the stream. A row is a sequence of characters separated by the record delimiter.
	 */
	private String getRow() throws IOException
	{
		StringBuffer row = new StringBuffer();
		char c;

		while (true) {
			try {
				c = getChar();

				// searches for the first character of the record delimiter
				if (c == recordDelimiter.charAt(0)) {
					int i;
					char[] temp = new char[recordDelimiter.length()];
					temp[0] = c;
					boolean isDelimiter = true;
					// checks if the following characters in the stream form the record delimiter
					for (i = 1; i < recordDelimiter.length() && isDelimiter; i++) {
						temp[i] = getChar();
						if (temp[i] != recordDelimiter.charAt(i))
							isDelimiter = false;
					}

					if (isDelimiter)
						return row.toString();

					row.append(temp, 0, i);
				}

				row.append(c);
			} catch (JRException e) {
				return row.toString();
			}

		} // end while
	}


	/**
	 * Reads a character from the stream.
	 * @throws IOException if any I/O error occurs
	 * @throws JRException if end of stream has been reached
	 */
	private char getChar() throws IOException, JRException
	{
		// end of buffer, fill a new buffer
		if (position + 1 > bufSize) {
			bufSize = reader.read(buffer);
			position = 0;
			if (bufSize == -1)
				throw new JRException("No more chars");
		}

		return buffer[position++];
	}


	/**
	 * Gets the date format that will be used to parse date fields
	 */
	public DateFormat getDateFormat()
	{
		return dateFormat;
	}


	/**
	 * Sets the desired date format to be used for parsing date fields
	 */
	public void setDateFormat(DateFormat dateFormat)
	{
		this.dateFormat = dateFormat;
	}


	/**
	 * Returns the field delimiter character.
	 */
	public char getFieldDelimiter()
	{
		return fieldDelimiter;
	}


	/**
	 * Sets the field delimiter character. The default is comma. If characters such as comma or quotes are specified,
	 * the results can be unpredictable.
	 * @param fieldDelimiter
	 */
	public void setFieldDelimiter(char fieldDelimiter)
	{
		this.fieldDelimiter = fieldDelimiter;
	}


	/**
	 * Returns the record delimiter string.
	 */
	public String getRecordDelimiter()
	{
		return recordDelimiter;
	}


	/**
	 * Sets the record delimiter string. The default is line feed (\n).
	 * @param recordDelimiter
	 */
	public void setRecordDelimiter(String recordDelimiter)
	{
		this.recordDelimiter = recordDelimiter;
	}


	/**
	 * Specifies an array of strings representing column names matching field names in the report template
	 */
    @SuppressWarnings("unchecked")
	public void setColumnNames(String[] columnNames)
	{
                this.columnNames.clear();
		for (int i = 0; i < columnNames.length; i++)
			this.columnNames.put(columnNames[i], new Integer(i));
	}

    public Vector getColumnNames() throws java.io.IOException
    {
        parseRow();
        return fields;
    }

	private String replaceAll(String string, String substring, String replacement)
	{
		StringBuffer result = new StringBuffer();
		int index = string.indexOf(substring);
		int oldIndex = 0;
		while (index >= 0) {
			result.append(string.substring(oldIndex, index));
			result.append(replacement);
			index += substring.length();
			oldIndex = index;

			index = string.indexOf(substring, index);
		}

		if (oldIndex <  string.length())
			result.append(string.substring(oldIndex, string.length()));

		return result.toString();
	}
    
    
}
