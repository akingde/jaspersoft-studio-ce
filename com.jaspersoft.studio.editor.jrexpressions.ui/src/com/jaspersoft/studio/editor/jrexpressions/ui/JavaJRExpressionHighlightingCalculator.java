/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.editor.jrexpressions.ui;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;

import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.FullMethodName;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JRFieldObj;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JRParameterObj;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JRVariableObj;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.StringLiteral;
import com.jaspersoft.studio.editor.jrexpressions.util.JRExpressionsModelUtil;

/**
 * Custom class for semantic highlighting of the expression.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JavaJRExpressionHighlightingCalculator implements ISemanticHighlightingCalculator {
 
	public void provideHighlightingFor(XtextResource resource,
			IHighlightedPositionAcceptor acceptor) {
		if (resource == null)
			return;
		IParseResult parseResult = resource.getParseResult();
		if(parseResult==null)
			return;
		INode root = parseResult.getRootNode();
		Iterator<ILeafNode> leafNodesIt = root.getLeafNodes().iterator();
			
		while (leafNodesIt.hasNext()){
			ILeafNode nextLeaf = leafNodesIt.next();
			EObject semanticElement = nextLeaf.getSemanticElement();
			if(semanticElement instanceof StringLiteral){
				acceptor.addPosition(nextLeaf.getOffset(), nextLeaf.getLength(), JavaJRExpressionHighlightingConfiguration.STRING_ID);
			}
			else if(semanticElement instanceof JRParameterObj){
				acceptor.addPosition(nextLeaf.getOffset(), nextLeaf.getLength(), JavaJRExpressionHighlightingConfiguration.PARAM_TOKEN);
			}
			else if(semanticElement instanceof JRVariableObj){
				acceptor.addPosition(nextLeaf.getOffset(), nextLeaf.getLength(), JavaJRExpressionHighlightingConfiguration.VARIABLE_TOKEN);
			}
			else if(semanticElement instanceof JRFieldObj){
				acceptor.addPosition(nextLeaf.getOffset(), nextLeaf.getLength(), JavaJRExpressionHighlightingConfiguration.FIELD_TOKEN);
			}
			else if(semanticElement instanceof FullMethodName){
				if(JRExpressionsModelUtil.isFunctionLibrary((FullMethodName)semanticElement)){
					acceptor.addPosition(nextLeaf.getOffset(), nextLeaf.getLength(), JavaJRExpressionHighlightingConfiguration.FUNCTION_METHOD);
				}
			}
		}
	}

}
