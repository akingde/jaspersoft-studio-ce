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
package com.jaspersoft.studio.editor.preview.jive;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import com.jaspersoft.studio.utils.UIUtils;

public class JettyUtil {
	
	private static final String METHOD_NAME = "getInstance";
	private static final String PROPERTY_NAME = "utilityClass";
	private static final String JIVE_JETTYUTIL_PROPERTIES_FILE = "com/jaspersoft/studio/editor/preview/jive/jetty.properties";
	private static JiveJettyUtilitiesProvider jettyUtil=null;
	
	public static JiveJettyUtilitiesProvider getJettyUtilInstance(){
		if(jettyUtil==null) {
			try {
				InputStream propFileStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(JIVE_JETTYUTIL_PROPERTIES_FILE);
				Properties props=new Properties();
				props.load(propFileStream);
				String className = (String) props.get(PROPERTY_NAME);
				Class<?> clazz=Class.forName(className);
				Method instanceMethod=clazz.getDeclaredMethod(METHOD_NAME);
				jettyUtil=(JiveJettyUtilitiesProvider) instanceMethod.invoke(null, new Object[0]);
				propFileStream.close();
			} catch (Exception e) {
				UIUtils.showError(e);
			}
		}
		return jettyUtil;
	}
	
}
