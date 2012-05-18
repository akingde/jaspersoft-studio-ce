/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.utils;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.type.EnumUtil;
import net.sf.jasperreports.engine.type.JREnum;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.NullEnum;

public class EnumHelper {

	public static String[] getEnumNames(Class<? extends JREnum> jrEnum, NullEnum type) {
		JREnum[] enums = jrEnum.getEnumConstants();
		return getEnumNames(enums, type);
	}

	public static String[] getEnumNames(JREnum[] jrEnum, NullEnum nullable) {
		List<String> res = new ArrayList<String>();
		if (nullable != NullEnum.NOTNULL)
			res.add(nullable.getName());
		for (int i = 0; i < jrEnum.length; i++) {
			res.add(Messages.getString(jrEnum[i].getName()));
		}
		return res.toArray(new String[res.size()]);
	}

	/**
	 * 
	 * @param jrEnum
	 * @param nullable
	 * @param skipPositions
	 *          - 0-based index of the JREnum to be skipped
	 * @return
	 */
	public static String[] getEnumNames(JREnum[] jrEnum, NullEnum nullable, Integer... skipPositions) {
		List<String> res = new ArrayList<String>();
		if (nullable != NullEnum.NOTNULL)
			res.add(nullable.getName());
		for (int i = 0; i < jrEnum.length; i++) {
			boolean skip = false;

			for (int j = 0; j < skipPositions.length; j++) {
				if (i == skipPositions[j]) {
					skip = true;
				}
			}

			if (!skip) {
				res.add(Messages.getString(jrEnum[i].getName()));
			}
		}
		return res.toArray(new String[res.size()]);
	}

	public static Integer getValue(JREnum jrEnum) {
		return getValue(jrEnum, 1, false);
	}

	public static Integer getValue(JREnum jrEnum, int offset, boolean isNull) {
		if (jrEnum == null)
			return new Integer(0);
		if (isNull && offset == 0)
			offset = -1;
		else if (!isNull && offset == 1)
			offset = 1;
		else
			offset = 0;

		return new Integer(jrEnum.getValue() - offset);
	}

	public static byte getSetValue(Integer value) {
		return getSetValue(value, 1);
	}

	public static byte getSetValue(Integer value, int offset) {
		if (value == null)
			return 0;
		return (byte) (value.intValue() + 1);
	}

	public static JREnum getSetValue(JREnum[] values, Object value, int offset, boolean isNull) {
		if (value != null) {
			if (value instanceof JREnum)
				return (JREnum) value;
			else if (value instanceof Number) {
				if (isNull && offset == 0)
					offset = -1;
				else if (!isNull && offset == 1)
					offset = 1;
				else
					offset = 0;
				return EnumUtil.getByValue(values, ((Number) value).intValue() + offset);
			}
		}
		return null;
	}
}
