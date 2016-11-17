/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.events;

import net.sf.jasperreports.engine.JRExpression;

import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Widget;

import com.jaspersoft.studio.swt.widgets.WTextExpression;

/**
 * The event that should be notified/received when dealing with expression widgets, like for example the {@link WTextExpression}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 * @see ExpressionModifiedListener
 * @see WTextExpression
 *
 */
public class ExpressionModifiedEvent extends TypedEvent {

	private static final long serialVersionUID = -8192762845793694496L;
	
	/** The modified expression */
	public JRExpression modifiedExpression = null;
	
	public ExpressionModifiedEvent(Widget expressionWidget){
		super(expressionWidget);
	}

}
