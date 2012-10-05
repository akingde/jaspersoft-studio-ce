/*******************************************************************************
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
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
