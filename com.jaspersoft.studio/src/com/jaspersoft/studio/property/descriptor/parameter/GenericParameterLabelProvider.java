/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.parameter;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.jface.viewers.LabelProvider;

import com.jaspersoft.studio.messages.Messages;
/*
 * @author Chicu Veaceslav
 * 
 */
public class GenericParameterLabelProvider extends LabelProvider {

	public GenericParameterLabelProvider() {
		super();
	}

	@Override
	public String getText(Object element) {
		if (element == null)
			return ""; //$NON-NLS-1$
		if (element.getClass().isArray()){
			int lenght = ((Object[]) element).length;
			return MessageFormat.format(Messages.GenericParameterLabelProvider_parametersCountLabel, new Object[]{lenght});
		}
		if (element instanceof List<?>){
			int lenght = ((List<?>)element).size();
			return MessageFormat.format(Messages.GenericParameterLabelProvider_parametersCountLabel, new Object[]{lenght});
		}
		return element.toString();
	}

}
