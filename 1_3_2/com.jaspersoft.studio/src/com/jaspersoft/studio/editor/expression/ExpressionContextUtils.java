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
package com.jaspersoft.studio.editor.expression;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;

/**
 * Utility methods related to the {@link ExpressionContext} object.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public final class ExpressionContextUtils {

	private ExpressionContextUtils(){
		// PREVENT INSTANTIATION 
	}
	
	/**
	 * Returns the list of JasperReports parameters that can be used 
	 * in the specified expression context.
	 * 
	 * @param exprContext the expression context
	 * @return the list of parameters
	 */
	public static List<JRParameter> getAllParameters(ExpressionContext exprContext){
		JRDesignDataset ds = exprContext.getDatasets().get(0);
		return (ds!=null) ? ds.getParametersList() : new ArrayList<JRParameter>();
	}
	
	/**
	 * Returns the list of JasperReports variables that can be used 
	 * in the specified expression context.
	 * 
	 * @param exprContext the expression context
	 * @return the list of variables
	 */
	public static List<JRVariable> getAllVariables(ExpressionContext exprContext){
		JRDesignDataset ds = exprContext.getDatasets().get(0);
		return (ds!=null) ? ds.getVariablesList() : new ArrayList<JRVariable>();
	}
	
	/**
	 * Returns the list of JasperReports fields that can be used 
	 * in the specified expression context.
	 * 
	 * @param exprContext the expression context
	 * @return the list of fields
	 */
	public static List<JRField> getAllFields(ExpressionContext exprContext){
		JRDesignDataset ds = exprContext.getDatasets().get(0);
		return (ds!=null) ? ds.getFieldsList() : new ArrayList<JRField>();
	}
}
