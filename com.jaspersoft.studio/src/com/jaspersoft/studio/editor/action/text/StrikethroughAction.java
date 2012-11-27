package com.jaspersoft.studio.editor.action.text;

import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.text.MTextElement;

public class StrikethroughAction extends ABooleanPropertyAction {
	public static String ID = "com.jaspersoft.studio.editor.action.text.strike";

	public StrikethroughAction(IWorkbenchPart part) {
		super(part);
		setId(ID);
	}

	protected boolean checkSelection(Object obj) {
		return obj instanceof MTextElement;
	}

	protected boolean getBooleanValue(Object obj) {
		Object res = ((APropertyNode) obj).getPropertyValue(getPropertyName());
		if (res instanceof Boolean)
			return (Boolean) res;
		return false;
	}

	@Override
	protected Object getPropertyName() {
		return JRDesignStyle.PROPERTY_STRIKE_THROUGH;
	}
}
