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
package com.jaspersoft.studio.property.descriptor;

import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.engine.type.JREnum;

public class I18nTypesEnum {

	public static int LINE_SPACING 	= 1;
	public static int LINE_STYLE 		= 2;
	public static int POSITION_TYPE = 3;
	public static int ROTATION 			= 4;
	public static int STRETCH_TYPE 	= 5;
	
	// KEEP AN EYE ON ANY CHANGE IN net.sf.jasperreports.engine.type
	
	private enum LineSpacing implements JREnum {
		
		SINGLE((byte)0, Messages.common_single), ONE_AND_HALF((byte)1, "1_1_2"), DOUBLE((byte)2, Messages.common_double);

		private final transient byte value;
		private final transient String name;

		private LineSpacing(byte value, String name) {
			this.value = value;
			this.name = name;
		}
		
		public String getName() {
			return name;
		}

		public byte getValue() {
			return value;
		}

		public Byte getValueByte() {
			return new Byte(value);
		}
	}
	
	private enum LineStyle implements JREnum {
		
		SOLID((byte)0, Messages.common_solid), DASHED((byte)1, Messages.common_dashed), DOTTED((byte)2, Messages.common_dotted), DOUBLE((byte)3, Messages.common_double);

		private final transient byte value;
		private final transient String name;

		private LineStyle(byte value, String name) {
			this.value = value;
			this.name = name;
		}
		
		public String getName() {
			return name;
		}

		public byte getValue() {
			return value;
		}

		public Byte getValueByte() {
			return new Byte(value);
		}
	}
	
	private enum PositionType implements JREnum {
		
		FLOAT((byte)1, Messages.common_float), FIX_RELATIVE_TO_TOP((byte)2, Messages.common_fix_relative_to_top), FIX_RELATIVE_TO_BOTTOM((byte)3, Messages.common_fix_relative_to_bottom);

		private final transient byte value;
		private final transient String name;

		private PositionType(byte value, String name) {
			this.value = value;
			this.name = name;
		}
		
		public String getName() {
			return name;
		}

		public byte getValue() {
			return value;
		}

		public Byte getValueByte() {
			return new Byte(value);
		}
	}
	
	private enum Rotation implements JREnum {
		
		NONE((byte)0, Messages.common_none), LEFT((byte)1, Messages.common_left), RIGHT((byte)2, Messages.common_right), UPSIDE_DOWN((byte)3, Messages.common_upside_down);

		private final transient byte value;
		private final transient String name;

		private Rotation(byte value, String name) {
			this.value = value;
			this.name = name;
		}
		
		public String getName() {
			return name;
		}

		public byte getValue() {
			return value;
		}

		public Byte getValueByte() {
			return new Byte(value);
		}
	}

	private enum StretchType implements JREnum {
		
		NO_STRETCH((byte)0, Messages.common_no_stretch), RELATIVE_TO_TALLEST_OBJECT((byte)1, Messages.common_relative_to_tallest_object), RELATIVE_TO_BAND_HEIGHT((byte)2, Messages.common_relative_to_band_height);

		private final transient byte value;
		private final transient String name;

		private StretchType(byte value, String name) {
			this.value = value;
			this.name = name;
		}
		
		public String getName() {
			return name;
		}

		public byte getValue() {
			return value;
		}

		public Byte getValueByte() {
			return new Byte(value);
		}
	}

	public static JREnum[] getValues(int Type) {
		
		if (Type == LINE_SPACING) {
			return LineSpacing.values();
		}
		if (Type == LINE_STYLE) {
			return LineStyle.values();
		}
		if (Type == POSITION_TYPE) {
			return PositionType.values();
		}
		if (Type == ROTATION) {
			return Rotation.values();
		}
		if (Type == STRETCH_TYPE) {
			return StretchType.values();
		}
		return null;
	}
}
