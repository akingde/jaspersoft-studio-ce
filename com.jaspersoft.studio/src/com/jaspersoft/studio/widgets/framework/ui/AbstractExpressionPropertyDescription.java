/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.inputhistory.InputHistoryCache;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.ui.menu.IMenuProvider;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * Superclass of every {@link ItemPropertyDescription}. Since a widget is composed of both expression part
 * and simple control part, this class implement the part regardig the expression, since its logic is shared between
 * the widgets
 * 
 * @author Orlandin Marco
 */
public abstract class AbstractExpressionPropertyDescription<T> implements ItemPropertyDescription<T> {
	
	protected String name;
	
	protected String label;
	
	protected String description;
	
	protected boolean mandatory;
	
	protected T defaultValue;
	
	protected T fallbackValue;
	
	protected boolean readOnly;
	
	protected JasperReportsConfiguration jConfig;
	
	public AbstractExpressionPropertyDescription() {
	}

	public AbstractExpressionPropertyDescription(String name, String description, boolean mandatory) {
		this(name, name, description, mandatory, null);
	}

	public AbstractExpressionPropertyDescription(String name, String label, String description, boolean mandatory) {
		this(name, label, description, mandatory, null);
	}

	public AbstractExpressionPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue) {
		super();
		this.name = name;
		this.label = label;
		this.description = description;
		this.mandatory = mandatory;
		this.defaultValue = defaultValue;
	}

	public void setjConfig(JasperReportsConfiguration jConfig) {
		this.jConfig = jConfig;
	}

	@Override
	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	@Override
	public String getDefaultValueString() {
		if (defaultValue != null)
			return defaultValue.toString();
		return ""; //$NON-NLS-1$
	}

	@Override
	public T getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	@Override
	public T getFallbackValue() {
		return fallbackValue;
	}
	
	public void setFallbackValue(T fallbackValue){
		this.fallbackValue = fallbackValue;
	}

	public void handleEdit(Control txt, IWItemProperty wiProp) {
		if (wiProp == null)
			return;
		if (wiProp.isExpressionMode() && txt instanceof Text){
			String tvalue = ((Text) txt).getText();
			if (tvalue != null && tvalue.isEmpty())
				tvalue = null;
			wiProp.setValue(null, new JRDesignExpression(Misc.nvl(tvalue)));
		}
	}
	
	/**
	 * This is used to created the expression controls in a lazy way, doing this the expression 
	 * control can be created only when the expression mode should be shown. It also check to 
	 * avoid to create it multiple times
	 */
	protected void lazyCreateExpressionControl(IWItemProperty wiProp, DoubleControlComposite cmp){
		if (wiProp.isExpressionMode() && cmp.getFirstContainer().getChildren().length == 0){
			Control expressionControl = createExpressionControl(wiProp, cmp.getFirstContainer());
			cmp.getFirstContainer().setData(expressionControl);
			cmp.setExpressionControlToHighlight(expressionControl);
		}
	}
	
	/**
	 * Create the control to input the expression
	 */
	protected Control createExpressionControl(final IWItemProperty wiProp, Composite parent){
		Text textExpression = new Text(parent, SWT.BORDER | SWT.WRAP);
		//The expression control always fill the available area in both directions
		GridData textData = new GridData(GridData.FILL_BOTH); 
		textExpression.setLayoutData(textData);
		InputHistoryCache.bindText(textExpression, name);
		textExpression.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (UIUtil.isMacAndEclipse4()) {
					if (((Text) e.getSource()).isDisposed())
						return;
					wiProp.updateWidget();
				}
			}

		});
		textExpression.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (wiProp.isRefresh())
					return;
				Point p = ((Text) e.getSource()).getSelection();

				handleEdit(((Text) e.getSource()), wiProp);
				((Text) e.getSource()).setSelection(p);
			}
		});
		
		if (isReadOnly()){
			textExpression.setEnabled(false);
		}

		return textExpression;
	}

	public Control createControl(IWItemProperty wiProp, Composite parent) {
		return createExpressionControl(wiProp, parent);
	}

	protected void setupContextMenu(final Control c, final IWItemProperty wiProp) {
		IMenuProvider provider = wiProp.getContextualMenuProvider();
		if (provider != null){
			provider.setupMenu(wiProp, this, c);
		}
	}

	public void update(Control c, IWItemProperty wip) {
		if (c instanceof Text) {
			Text txtExpr = (Text) c;
			if (wip.isExpressionMode()){
				JRExpression expression = wip.getExpressionValue();
				String txt = Misc.nvl(expression != null ? expression.getText() : null);
				
				Point oldSelection = txtExpr.getSelection();

				txtExpr.setText(txt);

				oldSelection.x = Math.min(txt.length(), oldSelection.x);
				oldSelection.y = Math.min(txt.length(), oldSelection.y);
				txtExpr.setSelection(oldSelection);

				String tooltip = "";
				if (!Misc.isNullOrEmpty(txt))
					tooltip += "\n\n" + txt;
				tooltip += "\n" + getToolTip();
				txtExpr.setToolTipText(tooltip.trim());
			} 
		}
	}
	
	protected void changeFallbackForeground(boolean isUsingFallback, Control control){
		if (isUsingFallback && !ModelUtils.safeEquals(control.getForeground(), ColorConstants.gray)){
			control.setForeground(ColorConstants.gray);
		} else if (!isUsingFallback && !ModelUtils.safeEquals(control.getForeground(), ColorConstants.black)){
			control.setForeground(ColorConstants.black);
		}
	}
	
	public String getToolTip() {
		String tt = Misc.nvl(getDescription());
		tt += "\n" + (isMandatory() ? "Mandatory" : "Optional");
		if (!Misc.isNullOrEmpty(getDefaultValueString()))
			tt += "\nDefault: " + getDefaultValueString();
		return tt;
	}
	
	/**
	 * Clone the current descriptor 
	 * 
	 * @return the cloned {@link ItemPropertyDescription}
	 */
	public abstract ItemPropertyDescription<T> clone();
}
