/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.preferences.fonts.wizard;

import net.sf.jasperreports.engine.fonts.FontFamily;

import org.eclipse.jface.wizard.Wizard;

public class FontConfigWizard extends Wizard {
	private FontFamily font;

	private FontFamilyPage step1;
	private FontMappingPage step2;
	private FontLocalesPage step3;

	public FontConfigWizard() {
		super();
		setWindowTitle("Font Family");
	}

	public FontFamily getFont() {
		return font;
	}

	public void setFont(FontFamily font) {
		this.font = font;
	}

	@Override
	public void addPages() {
		step1 = new FontFamilyPage(font);
		addPage(step1);

		step2 = new FontMappingPage(font);
		addPage(step2);

		step3 = new FontLocalesPage(font);
		addPage(step3);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
