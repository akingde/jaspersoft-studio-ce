/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.plugin;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

public class PaletteGroup {
	private String id;
	private String name;
	private ImageDescriptor image;
	private String afterGroup;

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

	public ImageDescriptor getImage() {
		return image;
	}

	public void setImage(ImageDescriptor image) {
		this.image = image;
	}

	public void setImage(String image) {
		if (image != null && !image.trim().isEmpty())
			setImage(JaspersoftStudioPlugin.getImageDescriptor(image));
		else
			setImage(JaspersoftStudioPlugin.getImageDescriptor("icons/resources/elementgroup-16.png"));
	}

	public String getAfterGroup() {
		return afterGroup;
	}

	public void setAfterGroup(String afterGroup) {
		this.afterGroup = afterGroup;
	}
}
