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

public class ToolItemsSet {
	private String id;
	private String name;
	private String description;
	private String toolbarUri;
	private String menuUri;
	private boolean visibility;
	private List<ToolItem> toolItems = new ArrayList<ToolItem>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getToolbarUri() {
		return toolbarUri;
	}

	public void setToolbarUri(String toolbarUri) {
		this.toolbarUri = toolbarUri;
	}

	public String getMenuUri() {
		return menuUri;
	}

	public void setMenuUri(String menuUri) {
		this.menuUri = menuUri;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public List<ToolItem> getToolItems() {
		return toolItems;
	}

	public void addToolItems(ToolItem ti) {
		toolItems.add(ti);
	}
}
