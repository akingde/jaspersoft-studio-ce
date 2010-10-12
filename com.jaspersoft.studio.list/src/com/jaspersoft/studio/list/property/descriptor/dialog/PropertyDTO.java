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
package com.jaspersoft.studio.list.property.descriptor.dialog;

import net.sf.jasperreports.engine.JRDatasetParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * @author Chicu Veaceslav
 * 
 */
public class PropertyDTO {
	public PropertyDTO() {
		super();
	}

	public PropertyDTO(String property, String description) {
		super();
		this.property = property;
		this.description = description;
	}

	private String property;
	private String description;
	private JRDatasetParameter[] value;
	private JasperDesign jd;

	public void setJasperDesign(JasperDesign jd) {
		this.jd = jd;
	}

	public JasperDesign getJasperDesign() {
		return jd;
	}

	public JRDatasetParameter[] getValue() {
		return value;
	}

	public void setValue(JRDatasetParameter[] value) {
		this.value = value;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
