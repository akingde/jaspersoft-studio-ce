/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.runtime.IProgressMonitor;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

public class FieldTypeGuesser {
	public static final int SAMPLESIZE = 1000;
	private static final Class<?>[] types = new Class<?>[] { Date.class, Time.class, Timestamp.class, Integer.class,
			Long.class, BigInteger.class, BigDecimal.class };

	public static void guessTypes(JRDataSource ds, List<JRDesignField> columns, boolean hasNext,
			IProgressMonitor monitor) throws JRException {
		List<JRDesignField> cols = new CopyOnWriteArrayList<>(columns);
		for (int i = 0; i < SAMPLESIZE && hasNext && !cols.isEmpty(); i++) {
			if (monitor != null && monitor.isCanceled())
				return;
			for (JRDesignField f : cols) {
				try {
					Object v = ds.getFieldValue(f);
					if (v == null)
						continue;
					if (f.getValueClass().equals(String.class)) {
						String sv = (String) v;
						if (sv.trim().isEmpty())
							continue;
						findType(types, f, ds, cols, sv);
					}
				} catch (Throwable e1) {
					f.setValueClass(String.class);
					cols.remove(f);
				}
			}
			hasNext = ds.next();
		}
	}

	private static void findType(Class<?>[] types, JRDesignField f, JRDataSource ds, List<JRDesignField> cols,
			String sv) {
		for (Class<?> type : types)
			try {
				f.setValueClass(type);
				Object v = ds.getFieldValue(f);
				if (v == null)
					f.setValueClass(String.class);
				else {
					if (v instanceof Number && !sv.matches("-?+\\d+(\\.0*)?")) {
						f.setValueClass(String.class);
						continue;
					}
				}
				return;
			} catch (Throwable e) {
				f.setValueClass(String.class);
			}
		cols.remove(f);
	}
}
