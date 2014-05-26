package com.jaspersoft.hadoop.hbase;

import java.util.Iterator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.log4j.Logger;

import com.jaspersoft.hadoop.hbase.connection.ThriftQueryWrapper;

/**
 * An implementation of a data source that uses an JSON like query and
 * parameters
 * 
 * @author Eric Diaz
 * 
 */
public class ThriftDataSource implements JRDataSource {
	private boolean sorted;

	private Iterator<String> recordsIterator;

	public static final String CONNECTION = "com.jaspersoft.hbase.thrift.connection";

	public static final String QUERY_LANGUAGE = "HBaseThriftQuery";

	private final static Logger logger = Logger.getLogger(ThriftDataSource.class);

	private ThriftQueryWrapper wrapper;

	public ThriftDataSource(ThriftQueryWrapper wrapper) {
		this.wrapper = wrapper;
		sorted = wrapper.sortedMap != null;
		if (sorted) {
			recordsIterator = wrapper.sortedMap.keySet().iterator();
		}
		logger.info("New ThriftDataSource");
	}

	/**
	 * Gets the field value for the current position.
	 */
	@Override
	public Object getFieldValue(JRField field) throws JRException {
		if (field.getName().equals(wrapper.idField)) {
			return wrapper.deserializer.deserializeRowId(wrapper.currentResult.getRow());
		}
		return wrapper.getColumnFieldValue(field.getName());
	}

	/**
	 * Tries to position the cursor on the next element in the data source.
	 */
	public boolean next() throws JRException {
		boolean next = false;
		if (sorted) {
			if (next = recordsIterator.hasNext()) {
				wrapper.currentResult = wrapper.sortedMap.get(recordsIterator.next());
				wrapper.processResult();
			}
		} else {
			wrapper.moveNext();
			next = wrapper.currentResult != null;
		}
		return next;
	}
}