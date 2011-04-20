/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.messages.Messages;

public class PatternEditor extends Wizard {
	private String value;
	private PatternPage page0;

	public String getValue() {
		if (page0 != null)
			return page0.getValue();
		return value;
	}

	public void setValue(String value) {
		if (page0 != null)
			page0.setValue(value);
		this.value = value;
	}

	public PatternEditor() {
		super();
		setWindowTitle(Messages.common_pattern);
		setNeedsProgressMonitor(false);
	}

	@Override
	public void addPages() {
		page0 = new PatternPage(Messages.common_pattern);
		page0.setValue(value);
		page0.setDatePatterns(datePatterns);
		page0.setNumberPatterns(numberPatterns);
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	private boolean datePatterns = true;
	private boolean numberPatterns = true;

	public boolean isDatePatterns() {
		return datePatterns;
	}

	public void setDatePatterns(boolean datePatterns) {
		this.datePatterns = datePatterns;
	}

	public boolean isNumberPatterns() {
		return numberPatterns;
	}

	public void setNumberPatterns(boolean numberPatterns) {
		this.numberPatterns = numberPatterns;
	}
}
