/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.model.SectionPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ColorPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ComboItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.FilePropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.FloatPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.IntegerPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.TextPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.TransparentColorPropertyDescription;

/**
 * Factory used to build the {@link ItemPropertyDescription} from a {@link WidgetPropertyDescriptor}. It handle also
 * the contributed widgets and offer some utility methods for the ui
 * 
 * @author Orlandin Marco
 *
 */
public class WidgetFactory {
	
	/**
	 * List of {@link ItemPropertyDescription} contributed by other plusing. The key is the type of widget the {@link ItemPropertyDescription} implement
	 */
	private static Map<String, ItemPropertyDescription<?>> contributedWidgets = null;

	/**
	 * Return the List of {@link ItemPropertyDescription}, used to build the custom widgets
	 * 
	 * @return A not null map of {@link ItemPropertyDescription}, the key is the type handled from the widget
	 */
	protected static Map<String, ItemPropertyDescription<?>> getContributedWidgets() {
		if (contributedWidgets == null) {
			IConfigurationElement[] config = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "dynamicWidgetDefinitions");

			contributedWidgets = new HashMap<String, ItemPropertyDescription<?>>();
			List<IConfigurationElement> configList = new ArrayList<IConfigurationElement>(Arrays.asList(config));
			for (IConfigurationElement el : configList) {
				Object defaultSupportClazz;
				try {
					String widgetName = el.getAttribute("widgetName").toLowerCase();
					defaultSupportClazz = el.createExecutableExtension("widgetClass");
					if (defaultSupportClazz instanceof IExportedResourceHandler) {
						ItemPropertyDescription<?> widgetClass = (ItemPropertyDescription<?>) defaultSupportClazz;
						contributedWidgets.put(widgetName, widgetClass);
					}
				} catch (CoreException e) {
					JaspersoftStudioPlugin.getInstance().getLog().log(new Status(IStatus.ERROR, JaspersoftStudioPlugin.PLUGIN_ID,
							"An error occurred while trying to create the new class.", e));
				}
			}
		}
		return contributedWidgets;
	}

	/**
	 * Create a {@link ItemPropertyDescription} from a widget definition
	 * 
	 * @param cd the container of all the widgets, used for the localization
	 * @param cpd the descriptor of the widget, the type and the other informations will be used to build the {@link ItemPropertyDescription}
	 * @param jConfig the current {@link JasperReportsConfiguration}
	 * @param editor a not null {@link IPropertyEditor} that will be used inside the widget to provide the read/write logic between the widget and the element
	 * @return the {@link ItemPropertyDescription} to handle the type defined by the WidgetPropertyDescription, or null if the type can not be resolved
	 */
	public static ItemPropertyDescription<?> createItemPropertyDescriptor(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig, IPropertyEditor editor) {
		Assert.isNotNull(editor);
		ItemPropertyDescription<?> desc = null;
		if (cpd.getType().equalsIgnoreCase("path")) {
			desc = new FilePropertyDescription().getInstance(cd, cpd, jConfig, editor);
		} else if (cpd.getType().equalsIgnoreCase("combo")) {
				desc = new ComboItemPropertyDescription<String>().getInstance(cd, cpd, jConfig, editor);
		} else if (cpd.getType().equalsIgnoreCase("color")) {
			desc = new ColorPropertyDescription<String>().getInstance(cd, cpd, jConfig, editor);
		} else if (cpd.getType().equalsIgnoreCase("transparent_color")) {
			desc = new TransparentColorPropertyDescription<String>().getInstance(cd, cpd, jConfig, editor);
		} else if (cpd.getType().equalsIgnoreCase("float")) {
			desc = new FloatPropertyDescription().getInstance(cd, cpd, jConfig, editor);
		} else if (cpd.getType().equalsIgnoreCase("integer")) {
			desc = new IntegerPropertyDescription().getInstance(cd, cpd, jConfig, editor);
		} else if (cpd.getType().equalsIgnoreCase("double")) {
			desc = new FloatPropertyDescription().getInstance(cd, cpd, jConfig, editor);
		} else if (cpd.getType().equalsIgnoreCase("text")){
			desc = new TextPropertyDescription<String>().getInstance(cd, cpd, jConfig, editor);
		} else {
			//Build the contributed widget if any
			ItemPropertyDescription<?> contribuitedType = getContributedWidgets().get(cpd.getType().toLowerCase());
			desc = contribuitedType.getInstance(cd, cpd, jConfig, editor);
		}
		return desc;
	}
	
	/**
	 * Creates a label widget for the specified property.
	 * 
	 * @param parent the parent composite
	 * @param pDescriptor the property descriptor
	 * @return the label
	 */
	public static Label createLabelForProperty(Composite parent, WidgetPropertyDescriptor pDescriptor) {
		Label lbl = new Label(parent, SWT.NONE);
		lbl.setText(pDescriptor.getLabel());
		lbl.setToolTipText(pDescriptor.getDescription());
		return lbl;
	}
	
	/**
	 * Creates a section widget for the specified property.
	 * 
	 * @param parent the parent composite
	 * @param widgetsDesc the {@link WidgetsDescriptor} containing the section definitio, used for localisation
	 * @param sectionDesc the property descriptor
	 * @param columns the section is created with a grid layout and this specify the number of columns in it
	 * @return the section
	 */
	public static Composite createSection(Composite parent, WidgetsDescriptor widgetsDesc, SectionPropertyDescriptor sectionDesc, int columns) {
		int style = Section.EXPANDED;
		if (sectionDesc.isExpandable())
			style = style | Section.TREE_NODE;
		Section section = new Section(parent, style);
		section.titleBarTextMarginWidth = 0;

		section.setFont(SWTResourceManager.getBoldFont(section.getFont()));

		if (parent.getLayout() instanceof GridLayout) {
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = ((GridLayout)parent.getLayout()).numColumns;
			section.setLayoutData(gd);
		}
		section.setText(widgetsDesc.getLocalizedString(Misc.nvl(sectionDesc.getName())));
		section.setSeparatorControl(new Label(section, SWT.SEPARATOR | SWT.HORIZONTAL));
		Composite cmp = new Composite(section, SWT.NONE);
		GridLayout layout = new GridLayout(columns, false);
		layout.marginHeight = 4;
		layout.marginWidth = 2;
		cmp.setLayout(layout);

		section.setClient(cmp);
		return cmp;
	}
	
	
}
