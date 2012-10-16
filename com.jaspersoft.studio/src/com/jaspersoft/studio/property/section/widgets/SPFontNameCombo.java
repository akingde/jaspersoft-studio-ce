/*******************************************************************************
 * ---------------------------------------------------------------------
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
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
 * ---------------------------------------------------------------------
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.base.JRBaseFont;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboItemAction;
import com.jaspersoft.studio.property.combomenu.ComboItemSeparator;
import com.jaspersoft.studio.property.combomenu.ComboMenuViewer;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.ResourceManager;

/**
 * A combo popup menu that could be used to represent a font
 * @author Orlandin Marco
 *
 */
public class SPFontNameCombo extends ASPropertyWidget {
	
	/**
	 * The combo popup
	 */
	protected ComboMenuViewer combo;
	
	/**
	 * True if the combo popup was already initialized with the data, false otherwise
	 */
	protected boolean dataSetted;
	
	public SPFontNameCombo(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
		dataSetted = false;
	}
	
	/**
	 * Create a sample image for a font. when an image is created it is cashed, so future request for that sample 
	 * doesn't Require the image recreation.
	 * @param fontName name of the font for the requested sample
	 * @return image of the sample
	 */
	/*public static Image createFontImage(final String fontName){
    Display display = Display.getCurrent();
    Color TRANSPARENT_COLOR = display.getSystemColor(SWT.COLOR_WHITE);
    Color DRAWING_COLOR = display.getSystemColor(SWT.COLOR_BLACK);
    Image stringImage = ResourceManager.getImage(fontName);
    if (stringImage == null){
	    PaletteData paletteData = new PaletteData( new RGB[]{
	    	   TRANSPARENT_COLOR.getRGB(),
	    	   DRAWING_COLOR.getRGB()
	    	   });
	    	 
	    ImageData imageData = new ImageData( 55, 15, 4, paletteData);
	    imageData.transparentPixel = 0; // index of the palette
	    stringImage = new Image( display, imageData);
	    ResourceManager.addImage(fontName, stringImage);
    }
    GC stringGc = new GC(stringImage);
    stringGc.setForeground(DRAWING_COLOR);
    stringGc.setBackground(TRANSPARENT_COLOR);
    
    stringGc.setFont(ResourceManager.getFont(fontName, 10, 0));
  
    stringGc.drawText("Sample", 0, 0,SWT.DRAW_TRANSPARENT);

    stringGc.dispose();
    return stringImage;
	}*/
	
	private static Image getBaseImage(){
		Image backGround = ResourceManager.getImage("baseFontBackGroundImage");
    if (backGround == null){
    	backGround = new Image(null, 55, 15);
    	ResourceManager.addImage("baseFontBackGroundImage", backGround);
    }
    return backGround;
	}
	
	public static Image createFontImage(final String fontName){
		Image stringImage = ResourceManager.getImage(fontName);
		//Check if the image is cached
    if (stringImage == null){
	    ImageData imageData = getBaseImage().getImageData();
	    imageData.transparentPixel = imageData.getPixel(0, 0);
	    stringImage = new Image(null, imageData);
	    GC stringGc = new GC(stringImage);
	    stringGc.setFont(ResourceManager.getFont(fontName, 10, 0));
	    stringGc.setTextAntialias(SWT.ON);
	    stringGc.drawText("Sample", 0, 0,SWT.DRAW_TRANSPARENT);
	    stringGc.dispose();
	    ResourceManager.addImage(fontName, stringImage);
    }
    return stringImage;
	}


	/** 
	 * Set the data of the combo popup, and if it wasn't initialized the fonts will be added
	 */
	@Override
	public void setData(APropertyNode pnode, Object b) {
		if (pnode != null) {
			if (!dataSetted){
				List<String[]> fontsList = ModelUtils.getFontNames(pnode.getJasperConfiguration());
				List<ComboItem> itemsList = new ArrayList<ComboItem>();
				int i = 0;
				for(int index = 0; index<fontsList.size(); index++){
					String[] fonts = fontsList.get(index);
					for(String element : fonts){
						itemsList.add(new ComboItem(element, true,  createFontImage(element), i, element,  element));
						i++;
					}
					if (index+1 != fontsList.size() && fonts.length>0){
						itemsList.add(new ComboItemSeparator(i));
						i++;
					}
				}
				combo.setItems(itemsList);
				combo.addSelectionListener(new ComboItemAction() {
						/**
						 * The action to execute when an entry is selected
						 */
						@Override
						public void exec() {
								propertyChange(section, JRBaseFont.PROPERTY_FONT_NAME, combo.getSelectionValue() != null ? combo.getSelectionValue().toString() : null);			
						}
				});
				dataSetted = true;
			}
			combo.setText(b.toString());
		}
	}

	public void propertyChange(AbstractSection section, String property, String value) {
		section.changeProperty(property, value);
	}

	
	@Override
	protected void createComponent(Composite parent) {
			if (combo == null){
				combo = new ComboMenuViewer(parent, ComboMenuViewer.NO_IMAGE, "SampleSample");
			}
	}

	@Override
	public Control getControl() {
		return combo != null ? combo.getControl() : null;
	}

}
