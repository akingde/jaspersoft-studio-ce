/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.palette;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.palette.SelectionToolEntry;

import com.jaspersoft.studio.ExtensionManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.MBarcode;
import com.jaspersoft.studio.model.MBreak;
import com.jaspersoft.studio.model.MChart;
import com.jaspersoft.studio.model.MCrossTab;
import com.jaspersoft.studio.model.MEllipse;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MImage;
import com.jaspersoft.studio.model.MLine;
import com.jaspersoft.studio.model.MRectangle;
import com.jaspersoft.studio.model.MSubreport;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.model.textfield.MDate;
import com.jaspersoft.studio.model.textfield.MPageNumber;
import com.jaspersoft.studio.model.textfield.MPageXofY;
import com.jaspersoft.studio.model.textfield.MPercentage;
import com.jaspersoft.studio.model.textfield.MTime;
import com.jaspersoft.studio.model.textfield.MTotalPages;

/**
 * A factory for creating JDPalette objects.
 */
public class JDPaletteFactory {

	/**
	 * Creates a new JDPalette object.
	 * 
	 * @return the palette root
	 */
	public static PaletteRoot createPalette() {
		PaletteRoot paletteRoot = new PaletteRoot();
		createToolBar(paletteRoot);
		createElements(paletteRoot);
		createFields(paletteRoot);

		return paletteRoot;
	}

	/**
	 * Creates a new JDPalette object.
	 * 
	 * @param iconDescriptor
	 *          the icon descriptor
	 * @param aclass
	 *          the aclass
	 * @return the palette entry
	 */
	private static PaletteEntry createJDEntry(IIconDescriptor iconDescriptor, Class<?> aclass) {
		return new CombinedTemplateCreationEntry(iconDescriptor.getTitle(), iconDescriptor.getDescription(), aclass,
				new JDPaletteCreationFactory(aclass), iconDescriptor.getIcon16(), iconDescriptor.getIcon16());
	}

	/**
	 * Creates a new JDPalette object.
	 * 
	 * @param paletteRoot
	 *          the palette root
	 */
	public static void createElements(PaletteRoot paletteRoot) {
		PaletteDrawer drawer = new PaletteDrawer("Elements",
				JaspersoftStudioPlugin.getImageDescriptor("icons/resources/elementgroup-16.png"));
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		entries.add(createJDEntry(MEllipse.getIconDescriptor(), MEllipse.class));
		entries.add(createJDEntry(MRectangle.getIconDescriptor(), MRectangle.class));
		entries.add(createJDEntry(MLine.getIconDescriptor(), MLine.class));
		entries.add(createJDEntry(MImage.getIconDescriptor(), MImage.class));
		entries.add(createJDEntry(MTextField.getIconDescriptor(), MTextField.class));
		entries.add(createJDEntry(MStaticText.getIconDescriptor(), MStaticText.class));
		entries.add(createJDEntry(MChart.getIconDescriptor(), MChart.class));
		entries.add(createJDEntry(MFrame.getIconDescriptor(), MFrame.class));
		entries.add(createJDEntry(MBreak.getIconDescriptor(), MBreak.class));
		entries.add(createJDEntry(MBarcode.getIconDescriptor(), MBarcode.class));
		entries.add(createJDEntry(MCrossTab.getIconDescriptor(), MCrossTab.class));
		entries.add(createJDEntry(MSubreport.getIconDescriptor(), MSubreport.class));
		entries.add(createJDEntry(MGraphicElement.getIconDescriptor(), MGraphicElement.class));

		ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
		List<Class<?>> lp = m.getPaletteEntries();
		for (Class<?> mn : lp) {
			if (ANode.class.isAssignableFrom(mn)) {
				try {
					entries.add(createJDEntry(
							(IIconDescriptor) mn.getDeclaredMethod("getIconDescriptor", new Class[0]).invoke(mn, new Object[0]), mn));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}

		drawer.addAll(entries);
		paletteRoot.add(drawer);
	}

	/**
	 * Creates a new JDPalette object.
	 * 
	 * @param paletteRoot
	 *          the palette root
	 */
	public static void createFields(PaletteRoot paletteRoot) {
		PaletteDrawer drawer = new PaletteDrawer("Fields",
				JaspersoftStudioPlugin.getImageDescriptor("icons/resources/fields-16.png"));
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();
		entries.add(createJDEntry(MPageNumber.getIconDescriptor(), MPageNumber.class));
		entries.add(createJDEntry(MTotalPages.getIconDescriptor(), MTotalPages.class));
		entries.add(createJDEntry(MDate.getIconDescriptor(), MDate.class));
		entries.add(createJDEntry(MTime.getIconDescriptor(), MTime.class));
		entries.add(createJDEntry(MPercentage.getIconDescriptor(), MPercentage.class));
		entries.add(createJDEntry(MPageXofY.getIconDescriptor(), MPageXofY.class));

		drawer.addAll(entries);
		paletteRoot.add(drawer);
	}

	/**
	 * Creates a new JDPalette object.
	 * 
	 * @param paletteRoot
	 *          the palette root
	 */
	public static void createToolBar(PaletteRoot paletteRoot) {
		PaletteToolbar paletteToolbar = new PaletteToolbar("toolbar");
		paletteToolbar.add(new SelectionToolEntry());
		paletteToolbar.add(new MarqueeToolEntry());
		paletteRoot.add(paletteToolbar);
	}
}
