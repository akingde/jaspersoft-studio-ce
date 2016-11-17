/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Entry for a composite element entry inside the palette
 * 
 * @author Orlandin Marco
 *
 */
public class CompositeElementTemplateCreationEntry extends CombinedTemplateCreationEntry {

	/**
	 * The viewer of the palette where the composite element entry is created. 
	 */
	private PaletteDrawer container;
	
	/**
	 * Create the entry for the palette of a composite element and automatically add it to 
	 * the palette
	 * 
	 * @param label the name of the composite element
	 * @param shortDesc the description\tooltip of the composite element
	 * @param template the composite element associated with this palette entry
	 * @param factory the factory to create the composite element
	 * @param iconSmall the small icon for the composite element
	 * @param iconLarge the large icon of the composite element
	 * @param container the viewer of the palette where this entry will be created
	 */
	public CompositeElementTemplateCreationEntry(String label, String shortDesc, MCompositeElement template, CreationFactory factory,
			ImageDescriptor iconSmall, ImageDescriptor iconLarge, PaletteDrawer container) {
		super(label, shortDesc, template, factory, iconSmall, iconLarge);
		this.container = container;
		container.add(this);
	}
	
	/**
	 * Create the entry for the palette of a composite element and automatically add it to 
	 * the palette
	 * 
	 * @param label the name of the composite element
	 * @param shortDesc the description\tooltip of the composite element
	 * @param template the composite element associated with this palette entry
	 * @param factory the factory to create the composite element
	 * @param iconSmall the small icon for the composite element
	 * @param iconLarge the large icon of the composite element
	 * @param container the viewer of the palette where this entry will be created
	 * @param index index of the elment inside the palette children
	 */
	public CompositeElementTemplateCreationEntry(String label, String shortDesc, MCompositeElement template, CreationFactory factory,
			ImageDescriptor iconSmall, ImageDescriptor iconLarge, PaletteDrawer container, int index) {
		super(label, shortDesc, template, factory, iconSmall, iconLarge);
		this.container = container;
		container.add(index, this);
	}
	
	/**
	 * Remove the entry from the palette
	 */
	protected void delete(){
		if (container != null) container.remove(this);
	}
	
	/**
	 * Return the composite element binded to this palette entry
	 */
	@Override
	public MCompositeElement getTemplate() {
		return (MCompositeElement)super.getTemplate();
	}
}
