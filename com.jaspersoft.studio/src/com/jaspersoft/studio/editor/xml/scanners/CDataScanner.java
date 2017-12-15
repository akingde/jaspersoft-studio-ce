/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.xml.scanners;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.wb.swt.ColorManager;

import com.jaspersoft.studio.editor.xml.IXMLColorConstants;
import com.jaspersoft.studio.editor.xml.rules.CDataRule;


public class CDataScanner extends RuleBasedScanner
{

	public IToken ESCAPED_CHAR;
	public IToken CDATA;
	
	public CDataScanner(ColorManager colorManager)
	{
		
		CDATA = new Token(new TextAttribute(colorManager.getColor(IXMLColorConstants.CDATA)));

		IRule[] rules = new IRule[2];

		// Add rule to pick up start of c section
		rules[0] = new CDataRule(CDATA, true);
		// Add a rule to pick up end of CDATA sections
		rules[1] = new CDataRule(CDATA, false);

		setRules(rules);
	}
	
	
	
	public IToken nextToken()
	{
		return super.nextToken();
	}
}
