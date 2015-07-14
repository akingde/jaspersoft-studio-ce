/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Entry for a tool entry inside the palette
 * 
 * @author Orlandin Marco
 *
 */
public class ToolTemplateCreationEntry extends CombinedTemplateCreationEntry {

	/**
	 * The viewer of the palette where the tool entry is created. 
	 */
	private PaletteDrawer container;
	
	/**
	 * Create the entry for the palette of a custom tool and automatically add it to 
	 * the palette
	 * 
	 * @param label the name of the tool
	 * @param shortDesc the description\tooltip of the tool
	 * @param template the tool associated with this palette entry
	 * @param factory the factory to create the tool
	 * @param iconSmall the small icon for the tool
	 * @param iconLarge the large icon of the tool
	 * @param container the viewer of the palette where this entry will be created
	 */
	public ToolTemplateCreationEntry(String label, String shortDesc, MCustomTool template, CreationFactory factory,
			ImageDescriptor iconSmall, ImageDescriptor iconLarge, PaletteDrawer container) {
		super(label, shortDesc, template, factory, iconSmall, iconLarge);
		this.container = container;
		container.add(this);
	}
	
	/**
	 * Remove the entry from the palette
	 */
	protected void delete(){
		if (container != null) container.remove(this);
	}
	
	/**
	 * Return the custom tool asscoaited to this palette entry
	 */
	@Override
	public MCustomTool getTemplate() {
		return (MCustomTool)super.getTemplate();
	}
}
