/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.widgets.framework.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.components.widgets.framework.ui.dialogs.ShapeDefinitionWizard;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.AbstractExpressionPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.customizers.shape.Point;
import net.sf.jasperreports.customizers.shape.ShapePoints;
import net.sf.jasperreports.customizers.shape.ShapeTypeEnum;

/**
 * Property description to define a shape for a chart. It allow to define the shape points
 * that compose the shape
 * 
 * @author Orlandin Marco
 *
 */
public class ShapePointsDescription extends AbstractExpressionPropertyDescription<String> {
	
	public static final String SHAPE_TYPE_PROPERTY = "shapeType";
	
	public static final String SHAPE_POINTS_PROPERTY = "shapePoints";

	public ShapePointsDescription() {
	}
	
	public ShapePointsDescription(String name, String label, String description, boolean mandatory, String defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}

	@Override
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lazyCreateExpressionControl(wiProp, cmp);

		cmp.getSecondContainer().setLayout(WidgetFactory.getNoPadLayout(2));
		final Text simpleControl = new Text(cmp.getSecondContainer(), SWT.BORDER);
		cmp.getSecondContainer().setData(simpleControl);
		cmp.setSimpleControlToHighlight(simpleControl);
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.verticalAlignment = SWT.CENTER;
		textData.grabExcessVerticalSpace = true;
		simpleControl.setLayoutData(textData);
		simpleControl.setEnabled(false);
		
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
		
		//Create the button to open the wizard to define the shape information
		Button openWizardButton = new Button(cmp.getSecondContainer(), SWT.PUSH);
		openWizardButton.setText("...");
		openWizardButton.setEnabled(!isReadOnly());
		GridData buttonData = new GridData();
		buttonData.verticalAlignment = SWT.CENTER;
		buttonData.grabExcessVerticalSpace = true;
		openWizardButton.setLayoutData(buttonData);
		openWizardButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				String currentValue = wiProp.getStaticValue();
				ShapePoints currentPoints = ShapePoints.decode(currentValue);
				String shapeType = wiProp.getPropertyEditor().getPropertyValue(SHAPE_TYPE_PROPERTY);
				ShapeTypeEnum shapeTypeEnum = ShapeTypeEnum.POLYLINE;
				if (shapeType != null){
					shapeTypeEnum =  NamedEnumPropertyDescriptor.getEnumValue(ShapeTypeEnum.ELLIPSE, NullEnum.NOTNULL, shapeType);
				}
				ShapeDefinitionWizard wizard = new ShapeDefinitionWizard(shapeTypeEnum, currentPoints);
				WizardDialog dialog = new WizardDialog(simpleControl.getShell(), wizard);
				if (dialog.open() == Dialog.OK){
					wiProp.setValue(wizard.getEncodedPoints(), null);
				}
			}
		});
		
		setupContextMenu(simpleControl, wiProp);
		cmp.switchToFirstContainer();
		return cmp;
	}
	
	/**
	 * Set the value inside the correct control, if the editor is in 
	 * expression mode or not
	 */
	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()) {
			lazyCreateExpressionControl(wip, cmp);
			Text expressionControl = (Text) cmp.getFirstContainer().getData();
			super.update(expressionControl, wip);
			cmp.switchToFirstContainer();
		} else {
			boolean isFallback = false;
			Text simpleControl = (Text)cmp.getSecondContainer().getData();
			String v = wip.getStaticValue();
			if (v == null && wip.getFallbackValue() != null){
				v = wip.getFallbackValue().toString();
				isFallback = true;
			}		
			
			ShapePoints points = ShapePoints.decode(v);
			if (points != null && points.getPoints() != null && !points.getPoints().isEmpty()){
				simpleControl.setText(getPointsAsString(points));	
			} else {
				//if the shape can not be decoded show this message
				simpleControl.setText("No points defined");
			}
			changeFallbackForeground(isFallback, simpleControl);
			cmp.switchToSecondContainer();
		}
	}

	protected String getPointsAsString(ShapePoints points){
		StringBuilder result = new StringBuilder();
		for(Point point: points.getPoints()){
			result.append("(");
			result.append(point.getX());
			result.append(",");
			result.append(point.getY());
			result.append("),");
		}
		return result.substring(0, result.length() -1);
	}
	
	@Override
	public ShapePointsDescription clone(){
		ShapePointsDescription result = new ShapePointsDescription();
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
		ShapePointsDescription result = new ShapePointsDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue());
		result.setReadOnly(cpd.isReadOnly());
		result.setFallbackValue(cpd.getFallbackValue());
		return result;
	}

}
