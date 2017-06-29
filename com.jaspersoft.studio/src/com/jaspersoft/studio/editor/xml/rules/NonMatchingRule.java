/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.xml.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class NonMatchingRule implements IPredicateRule {

	public NonMatchingRule() {
		super();
	}

	public IToken getSuccessToken() {
		return Token.UNDEFINED;
	}

	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		return Token.UNDEFINED;
	}

	public IToken evaluate(ICharacterScanner scanner) {
		return Token.UNDEFINED;
	}

}
