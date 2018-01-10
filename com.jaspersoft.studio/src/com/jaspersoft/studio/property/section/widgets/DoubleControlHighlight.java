/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.swt.graphics.Color;

import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;

/**
 * Highlighter for the {@link DoubleControlComposite}, it will highlight the 
 * first and second control at the same time
 * 
 * @author Orlandin Marco
 *
 */
public class DoubleControlHighlight implements IHighlightControl {
	
	private IHighlightControl firstControlHighlighter;
	
	private IHighlightControl secondControlHighlighter;
	
	public DoubleControlHighlight(DoubleControlComposite control) {
		firstControlHighlighter = ASPropertyWidget.getControlHighlight(control.getExpressionControlToHighlight());
		secondControlHighlighter = ASPropertyWidget.getControlHighlight(control.getSimpleControlToHighlight());
	}
	
	@Override
	public void highLightControl() {
		if (firstControlHighlighter != null){
			firstControlHighlighter.highLightControl();
		}
		if (secondControlHighlighter != null){
			secondControlHighlighter.highLightControl();
		}
	}

	@Override
	public void deHighLightControl() {
		if (firstControlHighlighter != null){
			firstControlHighlighter.deHighLightControl();
		}
		if (secondControlHighlighter != null){
			secondControlHighlighter.deHighLightControl();
		}
	}

	@Override
	public void deHighLightControl(Color oldColor) {
		if (firstControlHighlighter != null){
			firstControlHighlighter.deHighLightControl(oldColor);
		}
		if (secondControlHighlighter != null){
			secondControlHighlighter.deHighLightControl(oldColor);
		}
	}

}
