/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;
import com.jaspersoft.studio.property.descriptor.combo.FontNamePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.swt.widgets.NumericCombo;

import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignFont;
import net.sf.jasperreports.engine.util.StyleResolver;

/**
 * This class implement the subsection into the chart property tab, for the font name is used a standard combo.
 * 
 * @author Chicu Veaceslav & Orlandin Marco
 * 
 */
public class SPFont extends ASPropertyWidget<IPropertyDescriptor> {
	private final class PreferenceListener implements IPropertyChangeListener {

		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			if (event.getProperty().equals(FontsPreferencePage.FPP_FONT_LIST)) {
				if (parentNode != null)
					fontName.setItems(parentNode.getJasperConfiguration().getFontList());
			}
		}
	}

	private PreferenceListener preferenceListener;

	/**
	 * The combo popup with the font names
	 */
	private Combo fontName;

	/**
	 * The combo with the font size
	 */
	private NumericCombo fontSize;

	/**
	 * Buttom for the attribute bold
	 */
	private ToolItem boldButton;
	
	/**
	 * Toolbar used for the bold button
	 */
	private ToolBar boldToolbar;

	/**
	 * Button for the attribute italic
	 */
	private ToolItem italicButton;
	
	/**
	 * Toolbar used for the italic button
	 */
	private ToolBar italicToolbar;

	/**
	 * Button for the attribute underline
	 */
	private ToolItem underlineButton;
	
	/**
	 * Toolbar used for the underlne button
	 */
	private ToolBar underlineToolbar;

	/**
	 * Button for the attribute striketrought
	 */
	private ToolItem strikeTroughtButton;
	
	/**
	 * Toolbar used for the strike trough button
	 */
	private ToolBar strikeTroughtToolbar;

	/**
	 * Flag to check if the font name was already been inserted into the combo popup
	 */
	private boolean itemsSetted;

	/**
	 * Font model
	 */
	private MFont mfont;

	/**
	 * Node represented
	 */
	private APropertyNode parentNode;
	
	/**
	 * Main container where all the other controls are created 
	 */
	private Composite mainContainer;

	/**
	 * Section where the control will be placed
	 */
	private Composite group;

	public SPFont(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
		preferenceListener = new PreferenceListener();
		JaspersoftStudioPlugin.getInstance().addPreferenceListener(preferenceListener);
		itemsSetted = false;
	}

	@Override
	public Control getControl() {
		return mainContainer;
	}

	/**
	 * Property change action
	 * 
	 * @param section
	 * @param property
	 * @param value
	 * @param pd
	 */
	public void propertyChange(AbstractSection section, String property, String value, FontNamePropertyDescriptor pd) {
		changeProperty(section, pDescriptor.getId(), pd.getId(), value);
	}

	/**
	 * The increment\decrement font size button, adapted to the chart font structure
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	private class SPChartButtom<T extends IPropertyDescriptor> extends SPButton<T> {


		public SPChartButtom(Composite parent, AbstractSection section, T pDescriptor, APropertyNode fontValue,
				String fontNameProperty) {
			super(parent, section, pDescriptor, fontValue);
		}

		/**
		 * The ovverrided version first change the font into a temp. mfont item, the use this object to replace the font
		 * size inside the chart model
		 */
		@Override
		protected void createCommand(boolean increment) {
			Float currentFont = fontSize.getValueAsFloat();
			Float newValue = 2f;
			if (currentFont != null) {
				newValue = currentFont;
				Float plus = null;
				if (increment) {
					plus = (float) (Math.round((new Float(newValue) / 100) * SPButton.factor) + 1);
				} else {
					plus = (float) (Math.round((new Float(newValue) / 100) * -SPButton.factor) - 1);
				}
				if ((newValue + plus) > 99) {
					newValue = 99f;
				} else if ((newValue + plus) > 0) {
					newValue += plus;
				}
				changeProperty(section, SPFont.this.pDescriptor.getId(), JRBaseFont.PROPERTY_FONT_SIZE, newValue);
			}
		}
	}

	/**
	 * Given a combo and and a string return the index of the string in the combo
	 * 
	 * @param combo
	 * @param searchedString
	 * @return the index of the string in the combo, or 0 if the string is not found
	 */
	private int indexOf(Combo combo, String searchedString) {
		String[] elements = combo.getItems();
		for (int i = 0; i < elements.length; i++) {
			if (elements[i].equals(searchedString)) {
				return i;
			}
		}
		return 0;
	}

	protected void createComponent(Composite parent) {
		mainContainer = new Composite(parent, SWT.NONE);
		GridLayout mainContainerLayout = new GridLayout(1, false);
		mainContainerLayout.horizontalSpacing = 0;
		mainContainerLayout.verticalSpacing = 0;
		mainContainerLayout.marginHeight = 0;
		mainContainerLayout.marginWidth = 0;
		mainContainer.setLayout(mainContainerLayout);
		
		mfont = new MFont(new JRDesignFont(null));
		mfont.setJasperConfiguration(section.getElement().getJasperConfiguration());
		group = section.getWidgetFactory().createSection(mainContainer, pDescriptor.getDisplayName(), true, 3);
		group.getParent().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final FontNamePropertyDescriptor pd = (FontNamePropertyDescriptor) mfont
				.getPropertyDescriptor(JRBaseStyle.PROPERTY_FONT_NAME);
		fontName = new Combo(group, SWT.NONE);
		fontName.setToolTipText(pd.getDescription());
		fontName.addModifyListener(new ModifyListener() {

			private int time = 0;

			public void modifyText(ModifyEvent e) {
				if (e.time - time > 100) {
					String value = fontName.getText();
					if (!value.equals(FontUtils.separator))
						propertyChange(section, JRBaseFont.PROPERTY_FONT_NAME, value, pd);
					else
						fontName.select(indexOf(fontName, (String) mfont.getPropertyActualValue(JRBaseFont.PROPERTY_FONT_NAME)));
					int stringLength = fontName.getText().length();
					fontName.setSelection(new Point(stringLength, stringLength));
				}
				time = e.time;
			}
		});
		fontName.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				JaspersoftStudioPlugin.getInstance().removePreferenceListener(preferenceListener);
			}
		});

		final RWComboBoxPropertyDescriptor pd1 = (RWComboBoxPropertyDescriptor) mfont
				.getPropertyDescriptor(JRBaseStyle.PROPERTY_FONT_SIZE);

		Composite fontSizeLayout = new Composite(group, SWT.NONE);
		GridData fontSizeData = new GridData();
		fontSizeData.widthHint = 65;
		fontSizeData.minimumWidth = 65;
		fontSizeLayout.setLayout(new GridLayout(1, false));
		fontSizeLayout.setLayoutData(fontSizeData);
		fontSize = new NumericCombo(fontSizeLayout, SWT.FLAT, 0, 6);
		fontSize.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		fontSize.setItems(pd1.getItems());
		fontSize.addSelectionListener(new SelectionAdapter() {

			private int time = 0;

			public void widgetSelected(SelectionEvent e) {
				if (e.time - time > 100) {
					Float value = fontSize.getValueAsFloat();
					changeProperty(section, pDescriptor.getId(), pd1.getId(), value);
					int stringLength = fontSize.getText().length();
					fontSize.setSelection(new Point(stringLength, stringLength));
				}
				time = e.time;
			};

		});
		fontSize.setToolTipText(pd1.getDescription());

		/*
		 * Button to increment\decrement the font size
		 */
		new SPChartButtom<IPropertyDescriptor>(group, section, pd1, mfont, pDescriptor.getId().toString());
		
		Composite toolbarsConainer = new Composite(group, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		toolbarsConainer.setLayoutData(gd);
		GridLayout containerLayout = new GridLayout(4, false);
		containerLayout.horizontalSpacing = 0;
		containerLayout.verticalSpacing = 0;
		containerLayout.marginWidth = 0;
		containerLayout.marginHeight = 0;
		toolbarsConainer.setLayout(containerLayout);
		

		boldToolbar = new ToolBar(toolbarsConainer, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		boldButton = createItem(boldToolbar, JRBaseStyle.PROPERTY_BOLD, "icons/resources/edit-bold.png");

		italicToolbar = new ToolBar(toolbarsConainer, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		italicButton = createItem(italicToolbar, JRBaseStyle.PROPERTY_ITALIC, "icons/resources/edit-italic.png");

		underlineToolbar = new ToolBar(toolbarsConainer, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		underlineButton = createItem(underlineToolbar, JRBaseStyle.PROPERTY_UNDERLINE, "icons/resources/edit-underline.png");

		strikeTroughtToolbar = new ToolBar(toolbarsConainer, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		strikeTroughtButton = createItem(strikeTroughtToolbar, JRBaseStyle.PROPERTY_STRIKE_THROUGH, "icons/resources/edit-strike.png");
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
	private ToolItem createItem(ToolBar toolBar, Object id, String image) {
		final IPropertyDescriptor ipd = mfont.getPropertyDescriptor(id);

		final ToolItem item = new ToolItem(toolBar, SWT.CHECK);
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(section, pDescriptor.getId(), ipd.getId(), new Boolean(item.getSelection()));
			}
		});
		item.setImage(JaspersoftStudioPlugin.getInstance().getImage(image)); // $NON-NLS-1$
		item.setToolTipText(ipd.getDescription());
		return item;
	}

	private void changeProperty(AbstractSection section, Object property, Object prop, Object value) {
		section.changePropertyOn(prop, value, mfont);
		if (property != null && parentNode != null){
			JRDesignFont oldFont = (JRDesignFont)mfont.getValue();
			section.changePropertyOn(property, new MFont((JRFont)oldFont.clone()), parentNode);
		}
	}

	/**
	 * Set the font size value on the widget, setting also the information if it is inherited or not
	 * 
	 * @param resolvedNumber
	 *          the font size value resolved
	 * @param ownNumber
	 *          the font size value of the element selected
	 */
	public void setFontSizeNumber(Number resolvedNumber, Number ownNumber) {
		fontSize.setValue(resolvedNumber);
		fontSize.setInherited(ownNumber == null);
		createContextualMenu(mfont, fontSize, JRBaseFont.PROPERTY_FONT_SIZE);
	}

	/**
	 * Set the font name, the font size and the font attribute in the respective controls
	 */
	public void setData(APropertyNode pnode, Object value) {
		this.parentNode = pnode;
		this.mfont = (MFont) value;
		if (mfont != null) {

			JRFont fontValue = (JRFont) mfont.getValue();

			if (!itemsSetted) {
				fontName.setItems(parentNode.getJasperConfiguration().getFontList());
				createContextualMenu(mfont, fontName, JRBaseFont.PROPERTY_FONT_NAME);
				itemsSetted = true;
			}
			String strfontname = StyleResolver.getInstance().getFontName(fontValue);
			fontName.setText(strfontname);
			if (fontValue.getOwnFontName() != null){
				fontName.setForeground(ColorConstants.black);
			} else {
				fontName.setForeground(ColorConstants.gray);
			}

			setFontSizeNumber(fontValue.getFontsize(), fontValue.getOwnFontsize());

			Boolean b = StyleResolver.getInstance().isBold(fontValue);
			boldButton.setSelection(b != null ? b.booleanValue() : false);
			createContextualMenu(mfont, boldToolbar, JRBaseFont.PROPERTY_BOLD);
			b = StyleResolver.getInstance().isItalic(fontValue);
			italicButton.setSelection(b != null ? b.booleanValue() : false);
			createContextualMenu(mfont, italicToolbar, JRBaseFont.PROPERTY_ITALIC);
			b = StyleResolver.getInstance().isUnderline(fontValue);
			underlineButton.setSelection(b != null ? b.booleanValue() : false);
			createContextualMenu(mfont, underlineToolbar, JRBaseFont.PROPERTY_UNDERLINE);
			b = StyleResolver.getInstance().isStrikeThrough(fontValue);
			strikeTroughtButton.setSelection(b != null ? b.booleanValue() : false);
			createContextualMenu(mfont, strikeTroughtToolbar, JRBaseFont.PROPERTY_STRIKE_THROUGH);
		}
	}
	
	/**
	 * Create the contextual menu for a specific control calling the correct API to set the value
	 * from a chart
	 */
	@Override
	protected void createContextualMenu(final APropertyNode node, Control control, final String propertyID){
		if (node != null && control != null && !control.isDisposed()){
		
			//MacOS fix, the combo on MacOS doesn't have a contextual menu, so we need to handle this listener manually
			boolean handleComboListener = Util.isMac() && control.getClass() == Combo.class;
			if (handleComboListener){
				control.removeMouseListener(macComboMenuOpener);
			}
			
			boolean entryCreated = false;
			Map<String, DefaultValue> defaultMap = node.getDefaultsPropertiesMap();
			if (defaultMap != null){
				final DefaultValue defaultEntry = defaultMap.get(propertyID);
				if (defaultEntry != null && (defaultEntry.isNullable() || defaultEntry.hasDefault())){
					Menu controlMenu = new Menu(control);
					
					//Create the reset entry if necessary
					if (defaultEntry.hasDefault()){
						MenuItem resetItem = new MenuItem(controlMenu, SWT.NONE);
						entryCreated = true;
						resetItem.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								changeProperty(section, pDescriptor.getId(), propertyID, defaultEntry.getValue());
							}
						});
				    resetItem.setText(Messages.ASPropertyWidget_0);
					}
					
					//Create the null entry if necessary
					if (defaultEntry.isNullable()){
						MenuItem nullItem = new MenuItem(controlMenu, SWT.NONE);
						entryCreated = true;
						nullItem.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								changeProperty(section, pDescriptor.getId(), propertyID, null);
							}
						});
				    nullItem.setText(Messages.ASPropertyWidget_1);
					}
					
					//if the control already have a menu dispose it first, since it is a swt widget
					//it is not disposed automatically by the garbage collector
					if (control.getMenu() != null){
						control.getMenu().dispose();
					}
					
					//set the new menu
					control.setMenu(controlMenu);
					
					//add the combo listener for mac
					if (handleComboListener){
						control.addMouseListener(macComboMenuOpener);
					}
				}
			}
			if (!entryCreated) {
				//if no entry was created remove the contextual menu
				control.setMenu(null);
			}
		}
	}
}
