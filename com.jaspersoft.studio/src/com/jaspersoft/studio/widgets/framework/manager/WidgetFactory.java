/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.model.SectionPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.BigDecimalPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.CheckboxItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ClassItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ColorPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ComboItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.DoublePropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.FilePropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.FixedMeasurePropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.FixedNumberMesurePropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.FloatPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.FontFamilyComboPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.FontStylePropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.IntegerPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.LocaleComboPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.LongPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.MeasureUnitPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.SelectableComboItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.TextPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.TimezoneComboPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.TransparentColorPropertyDescription;

import net.sf.jasperreports.eclipse.util.Misc;

/**
 * Factory used to build the {@link ItemPropertyDescription} from a {@link WidgetPropertyDescriptor}. It handle also
 * the contributed widgets and offer some utility methods for the ui
 * 
 * @author Orlandin Marco
 *
 */
public class WidgetFactory {
	
	/**
	 * List of {@link ItemPropertyDescription} contributed by other plugins. The key is the type of widget the {@link ItemPropertyDescription} implement
	 */
	private static Map<String, ItemPropertyDescription<?>> contributedWidgets = null;
	
	/**
	 * List of {@link ItemPropertyDescription} hardcoded inside the application, they are stored into a map to be retrieved fast.
	 * The key is the type of widget the {@link ItemPropertyDescription} implement
	 */
	private static Map<String, ItemPropertyDescription<?>> hardcodedWidgets = null;

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
					if (defaultSupportClazz instanceof ItemPropertyDescription<?>) {
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
	 * Return the map of the hardcoded widgets, if it was yet not created it is creted 
	 * on the first call
	 * 
	 * @return a not null map of all the hardcoded widgets
	 */
	protected static Map<String, ItemPropertyDescription<?>> getHardcodedWidgets() {
		if (hardcodedWidgets == null){
			hardcodedWidgets = new HashMap<String, ItemPropertyDescription<?>>();
			
			//populate the map
			hardcodedWidgets.put("path", new FilePropertyDescription());		
			hardcodedWidgets.put("combo", new ComboItemPropertyDescription<String>());
			hardcodedWidgets.put("color", new ColorPropertyDescription<String>());
			hardcodedWidgets.put("transparent_color", new TransparentColorPropertyDescription<String>());
			hardcodedWidgets.put("float", new FloatPropertyDescription());
			hardcodedWidgets.put("integer", new IntegerPropertyDescription());
			hardcodedWidgets.put("double", new DoublePropertyDescription());
			hardcodedWidgets.put("long", new LongPropertyDescription());
			hardcodedWidgets.put("bigdecimal", new BigDecimalPropertyDescription());
			hardcodedWidgets.put("text", new TextPropertyDescription<String>());
			hardcodedWidgets.put("selectable_combo", new SelectableComboItemPropertyDescription<String>());
			hardcodedWidgets.put("class_combo", new ClassItemPropertyDescription());
			hardcodedWidgets.put("fontfamily_combo", new FontFamilyComboPropertyDescription());
			hardcodedWidgets.put("fontstyle_selector", new FontStylePropertyDescription());
			hardcodedWidgets.put("checkbox_selector", new CheckboxItemPropertyDescription());
			hardcodedWidgets.put("measureunit", new MeasureUnitPropertyDescription());
			hardcodedWidgets.put("fixedmeasureunit", new FixedMeasurePropertyDescription());
			hardcodedWidgets.put("fixednumbermeasureunit", new FixedNumberMesurePropertyDescription());
			hardcodedWidgets.put("timezone_combo", new TimezoneComboPropertyDescription());
			hardcodedWidgets.put("locale_combo", new LocaleComboPropertyDescription());
		}
		return hardcodedWidgets;
	}
	
	/**
	 * Return the type of all the available widgets
	 * 
	 * @return a not null string containing the type of every widget
	 */
	protected static String getValidTypes(){
		StringBuilder result = new StringBuilder();
		for(String hardcodedType : getHardcodedWidgets().keySet()){
			result.append(hardcodedType);
			result.append(", ");
		}
		for(String contributedType : getContributedWidgets().keySet()){
			result.append(contributedType);
			result.append(", ");
		}
		return result.substring(0, result.length() - 2);
	}

	/**
	 * Create a {@link ItemPropertyDescription} from a widget definition. If the widgets can not be resolved it log an error
	 * 
	 * @param cd the container of all the widgets, used for the localization
	 * @param cpd the descriptor of the widget, the type and the other informations will be used to build the {@link ItemPropertyDescription}
	 * @param jConfig the current {@link JasperReportsConfiguration}
	 * @param editor a not null {@link IPropertyEditor} that will be used inside the widget to provide the read/write logic between the widget and the element
	 * @return the {@link ItemPropertyDescription} to handle the type defined by the WidgetPropertyDescription, or null if the type can not be resolved
	 */
	public static ItemPropertyDescription<?> createItemPropertyDescriptor(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		ItemPropertyDescription<?> hardcodedType = getHardcodedWidgets().get(cpd.getType().toLowerCase());	
		ItemPropertyDescription<?> desc = null;
		if (hardcodedType != null){
			 desc = hardcodedType.getInstance(cd, cpd, jConfig);
		} else {
			//Build the contributed widget if any
			ItemPropertyDescription<?> contribuitedType = getContributedWidgets().get(cpd.getType().toLowerCase());
			if (contribuitedType == null){
				String errorString = "Invalid widget type {0}, valid types are: {1}";
				String errorMessage = MessageFormat.format(errorString, new Object[]{cpd.getType().toLowerCase(), getValidTypes()});
				JaspersoftStudioPlugin.getInstance().logError(errorMessage, new Exception(errorMessage));
			} else { 	
				desc = contribuitedType.getInstance(cd, cpd, jConfig);
			}
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
	
	/**
	 * Create a grid layout with no margins around it
	 * 
	 * @param colNumber the number of columns of the GridLayout
	 * @return a not null {@link GridLayout}
	 */
	public static GridLayout getNoPadLayout(int colNumber){
		GridLayout result = new GridLayout(colNumber, false);
		//result.horizontalSpacing = 0;
		result.verticalSpacing = 0;
		result.marginWidth = 0;
		result.marginHeight = 0;
		return result;
	}
		
}
