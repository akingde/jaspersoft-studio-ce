/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.text.MessageFormat;
import java.util.HashSet;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * Custom {@link SPRWCombo} to be used for the language of the report.
 * Created to mark the beanshell language as deprecated
 * 
 * @author Orlandin Marco
 *
 */
public class SPRWLanguageCombo<T extends IPropertyDescriptor> extends SPRWCombo<T> {
	
	/**
	 * map of the deprecated languages
	 */
	private static final HashSet<String> DEPRECATED_LANGUAGES = new HashSet<String>();
	
	private static final String ERROR_STATE_KEY ="error_state";
	
	static {
		//initialize the map
		DEPRECATED_LANGUAGES.add("beanshell");
	}
	
	public SPRWLanguageCombo(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
	}
	
	private boolean isErrorState() {
		Object value = combo.getData(ERROR_STATE_KEY);
		if (value != null) {
			return (Boolean)value;
		}
		return false;
	}
	
	private void setErrorState(boolean value) {
		combo.setData(ERROR_STATE_KEY, value);
	}
	
	@Override
	protected void setComboSelection(String str, boolean isCaseSensitive) {
		super.setComboSelection(str, isCaseSensitive);
		if (str != null) {
			str = str.trim().toLowerCase();
		}
		boolean isErrorState = isErrorState();
		boolean isLanguageDeprecated = DEPRECATED_LANGUAGES.contains(str);
		if (isLanguageDeprecated && !isErrorState) { //$NON-NLS-1$
			combo.setBackground(ColorConstants.orange);
			combo.setToolTipText(MessageFormat.format(Messages.SPRWLanguageCombo_languageDeprecated, str));
			setErrorState(true);
		} else if (!isLanguageDeprecated && isErrorState) {
			combo.setBackground(null);
			combo.setToolTipText(pDescriptor.getDescription());
			setErrorState(false);
		}
		
	}
}
