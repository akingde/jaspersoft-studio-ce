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
package com.jaspersoft.studio.wizards.obj2text;

import net.sf.jasperreports.engine.type.CalculationEnum;

import org.eclipse.jface.wizard.Wizard;

public class Obj2TextWizard extends Wizard {
	private CalculationEnum calculation;

	private Obj2TextPage step1;

	public Obj2TextWizard() {
		super();
		setWindowTitle("TextField Wizard");
	}

	public CalculationEnum getCalculation() {
		return calculation;
	}

	@Override
	public void addPages() {
		step1 = new Obj2TextPage();
		addPage(step1);
	}

	@Override
	public boolean performFinish() {
		calculation = step1.getCalculation();
		return true;
	}

}
