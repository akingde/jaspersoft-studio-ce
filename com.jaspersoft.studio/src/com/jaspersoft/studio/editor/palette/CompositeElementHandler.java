/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.palette;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;

import com.jaspersoft.studio.editor.tools.ICompositeElementModifyListener;
import com.jaspersoft.studio.editor.tools.MCompositeElement;
import com.jaspersoft.studio.editor.tools.CompositeElementManager;
import com.jaspersoft.studio.editor.tools.CompositeElementTemplateCreationEntry;
import com.jaspersoft.studio.plugin.IPaletteContributor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Class that both handle the creation of the composite elements in the appropriate
 * palette and keep them updated when one of this elements is added, modfied or removed
 * 
 * @author Orlandin Marco
 *
 */
public class CompositeElementHandler implements ICompositeElementModifyListener {

	/**
	 * The map of the palettes keep in sync with the set of composite elements
	 */
	private Map<String, PaletteDrawer> drawersMap = new HashMap<String, PaletteDrawer>();
	
	/**
	 * The root of all the palette section, actually not used but can be necessary for future improovements
	 */
	protected PaletteRoot root;
	
	/**
	 * Crate the clas
	 * 
	 * @param root the root of the palette
	 */
	public CompositeElementHandler(PaletteRoot root){
		this.root = root;
	}
	
	/**
	 * Add a monitored palette to the map
	 * 
	 * @param paletteId the id of the palette
	 * @param paletteDrawer the drawer of the palette
	 */
	public void addPaletteGroup(String paletteId, PaletteDrawer paletteDrawer){
		drawersMap.put(paletteId, paletteDrawer);
	}
	
	/**
	 * Return the palette drawer where the entry of the passed
	 * composite element should be created
	 * 
	 * @param compositeElement a  not null composite element 
	 * @return a not null palette drawer, if the palette requested by the element
	 * is not found it is returned the always available other elements palette
	 */
	protected PaletteDrawer getDrawer(MCompositeElement compositeElement){
		PaletteDrawer result = drawersMap.get(compositeElement.getGroupId());
		if (result == null){
			result = drawersMap.get(IPaletteContributor.KEY_COMMON_TOOLS);
		}
		return result;
	}
	
	/**
	 * Update the palette when a composite element is added, edited or removed
	 */
	@Override
	public void elementChanged(final MCompositeElement oldElement, final MCompositeElement newElement,final  OPERATION_TYPE operation) {
		//it works with the palette so it must executed in the graphic thread
		UIUtils.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				if (operation == OPERATION_TYPE.ADD){
					createElementEntry(newElement, getDrawer(newElement));
				} else if (operation == OPERATION_TYPE.DELETE){
					PaletteDrawer drawer = getDrawer(oldElement);
					for(Object entry : drawer.getChildren()){
						if (entry instanceof CompositeElementTemplateCreationEntry){
							CompositeElementTemplateCreationEntry factory = (CompositeElementTemplateCreationEntry)entry;
							if (factory.getTemplate() == oldElement){
								drawer.remove(factory);
								break;
							}
						}
					}
				} else if (operation == OPERATION_TYPE.EDIT){
					if (oldElement.getGroupId().equals(newElement.getGroupId())){
						PaletteDrawer drawer = getDrawer(oldElement);
						int index = 0;
						for(Object entry : drawer.getChildren()){
							if (entry instanceof CompositeElementTemplateCreationEntry){
								CompositeElementTemplateCreationEntry factory = (CompositeElementTemplateCreationEntry)entry;
								if (factory.getTemplate() == oldElement){
									drawer.remove(factory);
									createElementEntry(newElement, getDrawer(newElement), index);
									break;
								}
							}
							index ++;
						}
					} else {
						PaletteDrawer drawer = getDrawer(oldElement);
						for(Object entry : drawer.getChildren()){
							if (entry instanceof CompositeElementTemplateCreationEntry){
								CompositeElementTemplateCreationEntry factory = (CompositeElementTemplateCreationEntry)entry;
								if (factory.getTemplate() == oldElement){
									drawer.remove(factory);
									break;
								}
							}
						}
						createElementEntry(newElement, getDrawer(newElement));
					}
				}
			}
		});
		
	}
	
	/**
	 * Create all the composite elements entry on the 
	 * palettes
	 */
	public void createAllCompositeElements(){
		for(MCompositeElement element : CompositeElementManager.INSTANCE.getAvailableElements()){
			PaletteDrawer drawer = getDrawer(element);
			createElementEntry(element, drawer);
		}
	}

	/**
	 * Create an entry for a composite element and add it to the palette
	 * 
	 * @param compositeElement the composite element
	 * @param container the viewer of the palette where this composite element will be added
	 */
	protected void createElementEntry(MCompositeElement compositeElement, PaletteDrawer container) {
		CompositeElementTemplateCreationEntry paletteEntry = new CompositeElementTemplateCreationEntry(compositeElement.getName(),
			compositeElement.getDescription(), compositeElement,  new JDPaletteCreationFactory(compositeElement), compositeElement.getIconSmall(), compositeElement.getIconBig(), container);
		// Override default CreationTool class with ours
		paletteEntry.setToolClass(JDCreationTool.class);
	}
	
	/**
	 * Create an entry for a composite element and add it to the palette, into a specific position
	 * 
	 * @param compositeElement the composite element
	 * @param container the viewer of the palette where this composite element will be added
	 * @param index position where the new entry will be created
	 */
	protected static void createElementEntry(MCompositeElement compositeElement, PaletteDrawer container, int index) {
		CompositeElementTemplateCreationEntry paletteEntry = new CompositeElementTemplateCreationEntry(compositeElement.getName(),
			compositeElement.getDescription(), compositeElement,  new JDPaletteCreationFactory(compositeElement), compositeElement.getIconSmall(), compositeElement.getIconBig(), container, index);
		// Override default CreationTool class with ours
		paletteEntry.setToolClass(JDCreationTool.class);
	}
}
