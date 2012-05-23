package com.jaspersoft.studio.editor.jrexpressions.validation;

import net.sf.jasperreports.expressions.functions.util.FunctionsLibraryUtil;

import org.eclipse.xtext.validation.Check;

import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.FullMethodName;
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
				else{
					// TODO - Check for parameters
					// We should add the verification for the number of parameters.
					// Keep in mind that the JRExprFunctionBean contains a list of 
					// parameters, and each of them can be multi/optional.
				}
			}
		}
	}

}
