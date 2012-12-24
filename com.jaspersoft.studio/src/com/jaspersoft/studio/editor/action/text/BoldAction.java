package com.jaspersoft.studio.editor.action.text;

import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.text.MTextElement;

public class BoldAction extends ABooleanPropertyAction {
	public static String ID = "com.jaspersoft.studio.editor.action.text.bold";

	public BoldAction(IWorkbenchPart part) {
		super(part);
		setId(ID);
	}

	protected boolean checkSelection(Object obj) {
		return obj instanceof MTextElement;
	}

	@Override
	protected Object getPropertyName() {
		return JRDesignStyle.PROPERTY_BOLD;
	}
}
