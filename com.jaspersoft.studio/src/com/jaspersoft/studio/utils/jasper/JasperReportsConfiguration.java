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
package com.jaspersoft.studio.utils.jasper;

import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.FileResolver;
import net.sf.jasperreports.engine.util.LocalJasperReportsContext;

public class JasperReportsConfiguration extends LocalJasperReportsContext {
	public JasperReportsConfiguration(JasperReportsContext parent) {
		super(parent);
	}

	public void put(String key, Object value) {
		setValue(key, value);
	}

	public Object get(String key) {
		return getValue(key);
	}

	public void remove(String key) {
		removeValue(key);
	}

	private ClassLoader classLoader;
	private FileResolver fileResolver;

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	@Override
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
		super.setClassLoader(classLoader);
	}

	public FileResolver getFileResolver() {
		return fileResolver;
	}

	@Override
	public void setFileResolver(FileResolver fileResolver) {
		this.fileResolver = fileResolver;
		super.setFileResolver(fileResolver);
	}

	public static final String KEY_JASPERDESIGN = "JasperDesign";

	public JasperDesign getJasperDesign() {
		return (JasperDesign) get(KEY_JASPERDESIGN);
	}

	public void setJasperDesign(JasperDesign jd) {
		put(KEY_JASPERDESIGN, jd);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key, T def) {
		Object value = get(key);
		if (value != null && def != null && value.getClass().isAssignableFrom(def.getClass()))
			return (T) value;
		return def;
	}

}
