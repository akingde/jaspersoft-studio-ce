/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.utils;

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.studio.property.descriptor.NullEnum;

import net.sf.jasperreports.engine.type.EnumUtil;
import net.sf.jasperreports.engine.type.JREnum;

public class EnumHelper {

	public static String[] getEnumNames(JREnum[] jrEnum, NullEnum nullable) {
		List<String> res = new ArrayList<String>();
		if (nullable != NullEnum.NOTNULL)
			res.add(nullable.getName());
		for (int i = 0; i < jrEnum.length; i++) {
			res.add(jrEnum[i].getName());
		}
		return res.toArray(new String[res.size()]);
	}

	public static Integer getValue(JREnum jrEnum) {
		return getValue(jrEnum, 1, false);
	}

	public static Integer getValue(JREnum jrEnum, int offset, boolean isNull) {
		if (jrEnum == null)
			return new Integer(0);
		if ((isNull && offset == 0) || (!isNull && offset == 1))
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
		return (byte) (((Integer) value).intValue() + 1);
	}

	public static Object getSetValue(JREnum[] values, Object value, int offset, boolean isNull) {
		if (value != null) {
			if (value instanceof JREnum)
				return value;
			else if (value instanceof Integer) {
				if ((isNull && offset == 0) || (!isNull && offset == 1))
					offset = 1;
				else
					offset = 0;
				return EnumUtil.getByValue(values, ((Integer) value).intValue() + offset);
			}
		}
		return null;
	}
}
