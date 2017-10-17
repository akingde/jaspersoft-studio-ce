/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

import net.sf.jasperreports.eclipse.util.Pair;
import net.sf.jasperreports.engine.base.JRBaseStyle;

/**
 * Font style property descriptor to show a toolbar with the buttons to set the font bold, italic,
 * underline or striketrough. The property as value and the property as expression uses different names.
 * For the property as value a static name is used that are {@link JRBaseStyle.PROPERTY_BOLD} for the bold
 * and so on for the other properties.
 * 
 * @author Orlandin Marco
 */
public class FontStylePropertyDescription extends AbstractExpressionPropertyDescription<String> {
	
	public FontStylePropertyDescription() {
		super();
	}
	
	public FontStylePropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}

	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lazyCreateExpressionControl(wiProp, cmp);

		Composite toolbarsContainer = new Composite(cmp.getSecondContainer(), SWT.NONE);
		GridLayout containerLayout = new GridLayout(4, false);
		containerLayout.horizontalSpacing = 0;
		containerLayout.verticalSpacing = 0;
		containerLayout.marginWidth = 0;
		containerLayout.marginHeight = 0;
		toolbarsContainer.setLayout(containerLayout);
		
		List<Pair<ToolItem, String>> toolItems = new ArrayList<Pair<ToolItem, String>>();
		ToolBar boldToolbar = new ToolBar(toolbarsContainer, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		ToolItem boldButton = createItem(boldToolbar, wiProp, JRBaseStyle.PROPERTY_BOLD, "icons/resources/edit-bold.png");
		toolItems.add(new Pair<ToolItem, String>(boldButton, JRBaseStyle.PROPERTY_BOLD));
		
		ToolBar italicToolbar = new ToolBar(toolbarsContainer, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		ToolItem italicButton = createItem(italicToolbar, wiProp, JRBaseStyle.PROPERTY_ITALIC, "icons/resources/edit-italic.png");
		toolItems.add(new Pair<ToolItem, String>(italicButton, JRBaseStyle.PROPERTY_ITALIC));
		
		ToolBar underlineToolbar = new ToolBar(toolbarsContainer, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		ToolItem underlineButton = createItem(underlineToolbar, wiProp, JRBaseStyle.PROPERTY_UNDERLINE, "icons/resources/edit-underline.png");
		toolItems.add(new Pair<ToolItem, String>(underlineButton, JRBaseStyle.PROPERTY_UNDERLINE));
		
		ToolBar strikeTroughtToolbar = new ToolBar(toolbarsContainer, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		ToolItem strikeTroughtButton = createItem(strikeTroughtToolbar, wiProp, JRBaseStyle.PROPERTY_STRIKE_THROUGH, "icons/resources/edit-strike.png");
		toolItems.add(new Pair<ToolItem, String>(strikeTroughtButton, JRBaseStyle.PROPERTY_STRIKE_THROUGH));
		
		cmp.getSecondContainer().setData(toolItems);
		cmp.setSimpleControlToHighlight(toolbarsContainer);
		
		cmp.switchToSecondContainer();
		return cmp;
	}

	/**
	 * Create a tool bar button
	 * 
	 * @param toolBar
	 *          the parent tool bar
	 * @param id
	 *          the id of the property changed by the button press
	 * @param image
	 *          the image of the tool button
	 * @return the created tool button
	 */
	private ToolItem createItem(ToolBar toolBar, final IWItemProperty wiProp, final String propertyName, String image) {

		final ToolItem item = new ToolItem(toolBar, SWT.CHECK);
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ToolItem item = (ToolItem)e.widget;
				wiProp.getPropertyEditor().createUpdateProperty(propertyName, String.valueOf(item.getSelection()), null);
			}
		});
		item.setImage(JaspersoftStudioPlugin.getInstance().getImage(image)); // $NON-NLS-1$
		setupContextMenu(toolBar, wiProp);
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()) {
			lazyCreateExpressionControl(wip, cmp);
			Text txt = (Text) cmp.getFirstContainer().getData();
			super.update(txt, wip);
			cmp.switchToFirstContainer();
		} else {
			List<Pair<ToolItem, String>> toolItems = (List<Pair<ToolItem, String>>)cmp.getSecondContainer().getData();
			IPropertyEditor editor = wip.getPropertyEditor();
			for(Pair<ToolItem, String> toolItem : toolItems){
				String value = editor.getPropertyValue(toolItem.getValue());
				if (value != null){
					boolean selected = Boolean.parseBoolean(value);
					toolItem.getKey().setSelection(selected);
				}
			}
		}
	}
	
	@Override
	public ItemPropertyDescription<String> clone(){
		FontStylePropertyDescription result = new FontStylePropertyDescription();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.fallbackValue = fallbackValue;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		FontStylePropertyDescription fileDesc = new FontStylePropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue());
		fileDesc.setReadOnly(cpd.isReadOnly());
		fileDesc.setFallbackValue(cpd.getFallbackValue());
		return fileDesc;
	}

}
