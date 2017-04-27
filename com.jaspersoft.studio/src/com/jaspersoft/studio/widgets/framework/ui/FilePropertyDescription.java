/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.jface.dialogs.FileSelectionDialog;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;

public class FilePropertyDescription extends AbstractExpressionPropertyDescription<String> {

	public FilePropertyDescription() {
		super();
	}
	
	public FilePropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}

	@Override
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lazyCreateExpressionControl(wiProp, cmp);
		
		cmp.getSecondContainer().setLayout(WidgetFactory.getNoPadLayout(2));
		final Text simpleControl =  new Text(cmp.getSecondContainer(), SWT.BORDER);
		cmp.getSecondContainer().setData(simpleControl);
		cmp.setSimpleControlToHighlight(simpleControl);
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.verticalAlignment = SWT.CENTER;
		textData.grabExcessVerticalSpace = true;
		simpleControl.setLayoutData(textData);
		simpleControl.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				if (wiProp.isRefresh())
					return;
				handleEdit(simpleControl, wiProp);
			}
		});
		// Flag used to overcome the problem of focus events in Mac OS X
		// - JSS Bugzilla 42999
		// - Eclipse Bug 383750
		// It makes sense only on E4 platform and Mac OS X operating systems.
		simpleControl.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (UIUtil.isMacAndEclipse4()) {
					if (((Text) e.getSource()).isDisposed())
						return;
					wiProp.updateWidget();
				}
			}

		});
		createToolbarButton(cmp.getSecondContainer(), wiProp);

		if (isReadOnly()){
			simpleControl.setEnabled(false);
		}
		
		setupContextMenu(simpleControl, wiProp);
		cmp.switchToFirstContainer();
		return cmp;
	}
	
	/**
	 * Override the method to add the contextual menu also on the expression control
	 */
	@Override
	protected void lazyCreateExpressionControl(IWItemProperty wiProp, DoubleControlComposite cmp) {
		if (wiProp.isExpressionMode() && cmp.getFirstContainer().getChildren().length == 0){
			cmp.getFirstContainer().setLayout(WidgetFactory.getNoPadLayout(2));
			Control expressionControl = createExpressionControl(wiProp, cmp.getFirstContainer());
			cmp.getFirstContainer().setData(expressionControl);
			cmp.setExpressionControlToHighlight(expressionControl);
			createToolbarButton(cmp.getFirstContainer(), wiProp);
			setupContextMenu(expressionControl, wiProp);
		}
	}
	
	/**
	 * Create the toolbutton where to open the file selection dialog
	 * 
	 * @param parent the parent of the button
	 * @param wiProp the {@link IWItemProperty} to handle the setValue operation if the dialog is closed 
	 * correctly
	 */
	protected void createToolbarButton(Composite parent, final IWItemProperty wiProp){
		ToolBar toolBar = new ToolBar(parent, SWT.NONE);
		ToolItem b = new ToolItem(toolBar, SWT.FLAT);
		b.setImage(getButtonImage());
		b.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if (wiProp.isRefresh())
					return;
				wiProp.setRefresh(true);
				JRDesignExpression exp = null;
				try{
					exp = openSelectionDialog();
				} finally {
					if (exp != null) {
						wiProp.setValue(null, exp);
					}
					wiProp.setRefresh(false);
				}
			}

		});
		GridData data = new GridData();
		data.verticalAlignment = SWT.TOP;
		toolBar.setLayoutData(data);
		if (isReadOnly()){
			toolBar.setEnabled(false);	
		}
	}

	/**
	 * Return the image that will be shown on the button
	 * 
	 * @return an {@link Image}, should be not null
	 */
	protected Image getButtonImage(){
		return JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/obj16/fldr_obj.gif");
	}
	
	/**
	 * Open the file selection dialog
	 * 
	 * @return the expression of the selected file if the dialog was closed with ok, null otherwise
	 */
	protected JRDesignExpression openSelectionDialog(){
		FileSelectionDialog fsd = new FileSelectionDialog(UIUtils.getShell());
		fsd.configureDialog(jConfig);
		if (fsd.open() == Dialog.OK) {
			JRDesignExpression exp = fsd.getFileExpression();
			return exp;
		}
		return null;
	}
	
	@Override
	public void handleEdit(Control txt, IWItemProperty wiProp) {
		if (wiProp == null)
			return;
		if (!wiProp.isExpressionMode() && txt instanceof Text){
			String tvalue = ((Text) txt).getText();
			if (tvalue != null && tvalue.isEmpty())
				tvalue = null;
			wiProp.setValue(tvalue, null);
		} else super.handleEdit(txt, wiProp);
	}
	
	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()) {
			lazyCreateExpressionControl(wip, cmp);
			Text expressionControl = (Text) cmp.getFirstContainer().getData();
			super.update(expressionControl, wip);
			cmp.switchToFirstContainer();
		} else {
			Text txtValue = (Text)cmp.getSecondContainer().getData();
			String txt;
			boolean isFallback = false;
			if (wip.getStaticValue() != null){
				txt = wip.getStaticValue();
			} else if (wip.getFallbackValue() != null){
				txt = Misc.nvl(wip.getFallbackValue().toString());
				isFallback = true;
			} else {
				txt = "";
			}
		
			Point oldSelection = txtValue.getSelection();
			txtValue.setText(txt);
			changeFallbackForeground(isFallback, txtValue);
			oldSelection.x = Math.min(txt.length(), oldSelection.x);
			oldSelection.y = Math.min(txt.length(), oldSelection.y);
			txtValue.setSelection(oldSelection);
			
			txtValue.setToolTipText(getToolTip());
			cmp.switchToSecondContainer();
		}
	}
	
	@Override
	public ItemPropertyDescription<String> clone(){
		FilePropertyDescription result = new FilePropertyDescription();
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
		FilePropertyDescription fileDesc = new FilePropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue());
		fileDesc.setjConfig(jConfig);
		fileDesc.setReadOnly(cpd.isReadOnly());
		fileDesc.setFallbackValue(cpd.getFallbackValue());
		return fileDesc;
	}

}
