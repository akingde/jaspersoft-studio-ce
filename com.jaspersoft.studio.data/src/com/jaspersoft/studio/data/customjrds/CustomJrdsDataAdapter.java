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
package com.jaspersoft.studio.data.customjrds;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.utils.Misc;

public class CustomJrdsDataAdapter extends DataAdapter {
	
	private String factoryClass;
	private String methodToCall;
	private String factoryClassKey = "FactoryClass";
	private String methodToCallKey = "MethodToCall";
	
	public DataAdapterEditor getEditor() {
		return new CustomJrdsDataAdapterEditor();
	}

	public Map<String, String> getProperties() {
		
		Map<String, String> map = super.getProperties();
		map.put(factoryClassKey, Misc.nvl(getFactoryClass(), ""));
		map.put(methodToCallKey, Misc.nvl(getMethodToCall(), ""));
		
		return map;
	}

	public void loadProperties(Map<String, String> map) {
		
		setFactoryClass(map.get(factoryClassKey));
		setMethodToCall(map.get(methodToCallKey));
	}
	
	public void contributeParameters(Map<String, Object> parameters) throws JRException 
	{
		JRDataSource ds = null;
		try 
		{
            Class clazz = Class.forName( getFactoryClass(), true, Thread.currentThread().getContextClassLoader() );
            ds = (JRDataSource) clazz.getMethod( getMethodToCall(), new Class[0]).invoke(null,new Object[0]);
        }
		catch (ClassNotFoundException e)
		{
			throw new JRException(e);			
		} 
		catch (NoSuchMethodException e)
		{
			throw new JRException(e);			
		} 
		catch (InvocationTargetException e)
		{
			throw new JRException(e);			
		} 
		catch (IllegalAccessException e)
		{
			throw new JRException(e);			
		} 

		parameters.put(JRParameter.REPORT_DATA_SOURCE, ds);
	}
	
	/*
	 * GETTERS AND SETTERS
	 */
	public String getFactoryClass() {
		return factoryClass;
	}

	public void setFactoryClass(String factoryClass) {
		this.factoryClass = factoryClass;
	}

	public String getMethodToCall() {
		return methodToCall;
	}

	public void setMethodToCall(String methodToCall) {
		this.methodToCall = methodToCall;
	}
}
