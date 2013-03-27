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
package com.jaspersoft.studio.components.chart.model.theme.stroke;

import java.awt.Stroke;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.components.chart.messages.Messages;

public class StrokeEditor extends Wizard {
	private Stroke value;
	private StrokePage page0;

	public Stroke getValue() {
		if (page0 != null)
			return page0.getValue();
		return value;
	}

	public void setValue(Stroke value) {
		if (page0 != null)
			page0.setValue(value);
		this.value = value;
	}

	public StrokeEditor() {
		super();
		setWindowTitle(Messages.common_color);
		setNeedsProgressMonitor(false);
	}

	@Override
	public void addPages() {
		page0 = new StrokePage(Messages.common_color);
		page0.setValue(value);
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
