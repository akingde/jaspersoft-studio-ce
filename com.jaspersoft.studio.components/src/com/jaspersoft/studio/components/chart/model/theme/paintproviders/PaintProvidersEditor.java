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

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.components.chart.messages.Messages;

public class PaintProvidersEditor extends Wizard {
	private List<PaintProvider> value;
	private PaintProvidersPage page0;

	public List<PaintProvider> getValue() {
		if (page0 != null)
			return page0.getValue();
		return value;
	}

	public void setValue(List<PaintProvider> value) {
		if (page0 != null)
			page0.setValue(value);
		this.value = value;
	}

	public PaintProvidersEditor() {
		super();
		setWindowTitle(Messages.common_color);
		setNeedsProgressMonitor(false);
	}

	@Override
	public void addPages() {
		page0 = new PaintProvidersPage(Messages.common_color);
		page0.setValue(value);
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
