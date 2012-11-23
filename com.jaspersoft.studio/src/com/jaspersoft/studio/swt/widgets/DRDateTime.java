/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.swt.widgets;

import java.util.Date;

import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;

public class DRDateTime extends CDateTime {
	public DRDateTime(Composite parent, int style) {
		super(parent, style);
	}

	private boolean supportDateRange = true;

	public void setSupportDateRange(boolean supportDateRange) {
		this.supportDateRange = supportDateRange;
	}

	@Override
	public void setSelection(Date selection) {
		super.setSelection(selection);
		if (supportDateRange)
			removeTextListener();
	}

	@Override
	protected void addTextListener() {
		if (supportDateRange)
			removeTextListener();
		else
			super.addTextListener();
	}

	public void addModifyListener(ModifyListener listener) {
		text.getControl().addModifyListener(listener);
	}

	public void removeModifyListener(ModifyListener listener) {
		text.getControl().removeModifyListener(listener);
	}

	public void setText(String expr) {
		setSelection(null);
		text.getControl().setText(expr);
	}
}
