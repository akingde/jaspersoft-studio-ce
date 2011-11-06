/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.properties.action;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.server.properties.ASection;

public abstract class AAction extends Action {
	protected Set<ASection> sections = new HashSet<ASection>();

	public AAction(String name) {
		super(name);
	}

	public void addSection(ASection section) {
		sections.add(section);
	}

	public void removeSection(ASection section) {
		sections.remove(section);
	}
}
