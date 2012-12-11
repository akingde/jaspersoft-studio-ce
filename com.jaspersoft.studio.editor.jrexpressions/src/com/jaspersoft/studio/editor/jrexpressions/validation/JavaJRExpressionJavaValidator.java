/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
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
package com.jaspersoft.studio.editor.jrexpressions.validation;

import java.util.List;

import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;

import org.eclipse.xtext.validation.Check;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionContextUtils;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.editor.expression.FunctionsLibraryUtil;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.FullMethodName;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JRFieldObj;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JRParameterObj;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JRVariableObj;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JavaJRExpressionPackage;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.MethodInvocation;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.MethodsExpression;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.ObjectCreation;
 
/**
 * Expression validator class for Java JasperReports expressions.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JavaJRExpressionJavaValidator extends AbstractJavaJRExpressionJavaValidator {

	/**
	 * Performs some validation checks on a potential library function. 
	 * 
	 * @param name the {@link FullMethodName} that could contain the function
	 */
	@Check
	public void checkLibraryFunctionName(FullMethodName name) {
		MethodInvocation method=(MethodInvocation)name.eContainer();
		// Need to check because it could be a ObjectCreation expression.
		if(method.eContainer() instanceof ObjectCreation){
			if(FunctionsLibraryUtil.existsFunction(name.getMethodName())){
				error("Function library methods can not be used as constructor", name,
						JavaJRExpressionPackage.Literals.FULL_METHOD_NAME__METHOD_NAME, JavaJRExpressionPackage.FULL_METHOD_NAME__METHOD_NAME);				
			}
		}
		else if(method.eContainer() instanceof MethodsExpression){
			MethodsExpression expression=(MethodsExpression) method.eContainer();
			// TODO - Improve this check --> SEE also the method JRExpressionsModelUtil.isFunctionLibrary(name)		
			// For sake of simplicity assume the library functions are the first method invocation
			if(expression.getObjectExpression()==null && 
					method.equals(expression.getMethodInvocations().get(0))){
				// Look in the function library
				if(!FunctionsLibraryUtil.existsFunction(name.getMethodName())){
					error("The specified function was not found in the contributed library", name,
							JavaJRExpressionPackage.Literals.FULL_METHOD_NAME__METHOD_NAME, JavaJRExpressionPackage.FULL_METHOD_NAME__METHOD_NAME);
				}
//				else{
//					// TODO - Check for parameters
//					// We should add the verification for the number of parameters.
//					// Keep in mind that the JRExprFunctionBean contains a list of 
//					// parameters, and each of them can be multi/optional.
//				}
			}
		}
	}
	
	/**
	 * Checks if the field name is suitable for the current expression context.
	 * 
	 * @param field the field to validate
	 */
	@Check
	public void checkFieldName(JRFieldObj field){
		ExpressionContext currExpContext = ExpressionEditorSupportUtil.safeGetCurrentExpressionContext();
		List<JRField> allFields = ExpressionContextUtils.getAllFields(currExpContext);
		for(JRField f : allFields){
			if(("{" + f.getName() + "}").equals(field.getBracedIdentifier())) return;
		}
		error("The specified field can not be found in the current expression context", field,
				JavaJRExpressionPackage.Literals.JR_FIELD_OBJ__BRACED_IDENTIFIER, JavaJRExpressionPackage.JR_FIELD_OBJ__BRACED_IDENTIFIER);				
	}

	/**
	 * Checks if the variable name is suitable for the current expression context.
	 * 
	 * @param variable the variable to validate
	 */
	@Check
	public void checkVariableName(JRVariableObj variable){
		ExpressionContext currExpContext = ExpressionEditorSupportUtil.safeGetCurrentExpressionContext();
		List<JRVariable> allVariables = ExpressionContextUtils.getAllVariables(currExpContext);
		for(JRVariable v : allVariables){
			if(("{" + v.getName() + "}").equals(variable.getBracedIdentifier())) return;
		}
		error("The specified variable can not be found in the current expression context", variable,
				JavaJRExpressionPackage.Literals.JR_VARIABLE_OBJ__BRACED_IDENTIFIER, JavaJRExpressionPackage.JR_VARIABLE_OBJ__BRACED_IDENTIFIER);				
	}
	
	/**
	 * Checks if the parameter name is suitable for the current expression context.
	 * 
	 * @param parameter the parameter to validate
	 */
	@Check
	public void checkParameterName(JRParameterObj parameter){
		ExpressionContext currExpContext = ExpressionEditorSupportUtil.safeGetCurrentExpressionContext();
		List<JRParameter> allParameters = ExpressionContextUtils.getAllParameters(currExpContext);
		for(JRParameter p : allParameters){
			if(("{" + p.getName() + "}").equals(parameter.getBracedIdentifier())) return;
		}
		error("The specified parameter can not be found in the current expression context", parameter,
				JavaJRExpressionPackage.Literals.JR_PARAMETER_OBJ__BRACED_IDENTIFIER, JavaJRExpressionPackage.JR_PARAMETER_OBJ__BRACED_IDENTIFIER);				
	}
}
