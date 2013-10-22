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
package com.jaspersoft.studio.editor.toolitems;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IContributionItem;

public class ToolItem {
	private String id;

	private String actionID;
	private IContributionItem contributionItem;

	private String label;
	private String tooltip;
	private String icon;

	private List<Class<?>> classes = new ArrayList<Class<?>>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActionID() {
		return actionID;
	}

	public void setActionID(String actionID) {
		this.actionID = actionID;
	}

	public IContributionItem getContributionItem() {
		return contributionItem;
	}

	public void setContribClass(IContributionItem contribClass) {
		this.contributionItem = contribClass;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Class<?>> getClasses() {
		return classes;
	}

	public void addClass(String clazz) {
		try {
			classes.add(Class.forName(clazz));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
