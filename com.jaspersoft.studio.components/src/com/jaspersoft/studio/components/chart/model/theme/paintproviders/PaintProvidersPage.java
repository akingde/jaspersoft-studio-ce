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
package com.jaspersoft.studio.components.chart.model.theme.paintproviders;

import java.util.List;

import net.sf.jasperreports.chartthemes.simple.PaintProvider;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.messages.Messages;

public class PaintProvidersPage extends WizardPage {
	private List<PaintProvider> value;

	public List<PaintProvider> getValue() {
		return value;
	}

	public void setValue(List<PaintProvider> val) {
		value = val;
	}

	protected PaintProvidersPage(String pageName) {
		super(pageName);
		setTitle(Messages.common_color);
		setDescription(Messages.common_color);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new GridLayout(2, false));
		setControl(composite);

	}

}
