/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfigurationAcceptor;
import org.eclipse.xtext.ui.editor.utils.TextStyle;

/**
 * Custom class containing the list of available configurations for the
 * JRExpression(s) elements.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class SqlHighlightingConfiguration extends DefaultHighlightingConfiguration {

	public static final String XEXPRESSION_TOKEN = "XExpression"; //$NON-NLS-1$
	public static final String PARAM_TOKEN = "Parameter"; //$NON-NLS-1$ 

	@Override
	public void configure(IHighlightingConfigurationAcceptor acceptor) {
		super.configure(acceptor);
		addElementConfiguration(acceptor, PARAM_TOKEN, 187, 29, 29, SWT.NONE);
		addElementConfiguration(acceptor, XEXPRESSION_TOKEN, 0, 178, 0, SWT.NONE);
	}

	public void addElementConfiguration(IHighlightingConfigurationAcceptor acceptor, String s, int r, int g, int b, int style) {
		TextStyle textStyle = new TextStyle();
		textStyle.setBackgroundColor(new RGB(255, 255, 255));
		textStyle.setColor(new RGB(r, g, b));
		textStyle.setStyle(style);
		acceptor.acceptDefaultHighlighting(s, s, textStyle);
	}
}
