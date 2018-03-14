/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.ic;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.designer.IFilterQuery;
import com.jaspersoft.studio.data.designer.IParameterICContributor;
import com.jaspersoft.studio.data.designer.SelectParameterDialog;
import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.property.dataset.fields.table.TColumnFactory;
import com.jaspersoft.studio.property.dataset.fields.table.widget.AWidget;
import com.jaspersoft.studio.property.dataset.fields.table.widget.WJRProperty;
import com.jaspersoft.studio.property.metadata.PropertyMetadataRegistry;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.messages.Messages;

import net.sf.jasperreports.annotations.properties.PropertyScope;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.properties.PropertyMetadata;
import net.sf.jasperreports.properties.StandardPropertyMetadata;

public class ICParameterContributor implements IParameterICContributor {

	public static final String PROPERTY_JS_INPUTCONTROL_PATH = "com.jaspersoft.studio.js.ic.path"; //$NON-NLS-1$
	public static final String PROPERTY_JS_INPUTCONTROL_LABEL = "com.jaspersoft.studio.js.ic.label"; //$NON-NLS-1$
	public static final String PROPERTY_JS_INPUTCONTROL_TYPE = "com.jaspersoft.studio.js.ic.type"; //$NON-NLS-1$
	public static final String PROPERTY_JS_INPUTCONTROL_VALUE = "com.jaspersoft.studio.js.ic.value"; //$NON-NLS-1$
	public static final String PROPERTY_JS_INPUTCONTROL_DATASOURCE = "com.jaspersoft.studio.js.ic.ds"; //$NON-NLS-1$

	public static void initMetadata() {
		AWidget.addControlValueType(Activator.ICPATH, WInputControlPathSelector.class);
		AWidget.addControlValueType(Activator.DSPATH, WDatasourcePathSelector.class);
		AWidget.addControlValueType(Activator.RSPATH, WResourcePathSelector.class);
		AWidget.addControlValueType(Activator.REPPATH, WReportPathSelector.class);
		AWidget.addControlValueType(Activator.RUPATH, WReportUnitPathSelector.class);
		AWidget.addControlValueType(PROPERTY_JS_INPUTCONTROL_VALUE, WICValueSelector.class);

		List<PropertyMetadata> pm = new ArrayList<>();
		StandardPropertyMetadata spm = new StandardPropertyMetadata();
		spm.setName(PROPERTY_JS_INPUTCONTROL_PATH);
		spm.setLabel(Messages.ICParameterContributor_2);
		spm.setDescription(Messages.ICParameterContributor_3);
		spm.setValueType(Activator.ICPATH);
		List<PropertyScope> scopes = new ArrayList<>();
		scopes.add(PropertyScope.PARAMETER);
		spm.setScopes(scopes);
		spm.setCategory(Activator.SERVER_CATEGORY); // $NON-NLS-1$
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(PROPERTY_JS_INPUTCONTROL_DATASOURCE);
		spm.setLabel(Messages.ICParameterContributor_14);
		spm.setDescription(Messages.ICParameterContributor_14);
		spm.setValueType(Activator.DSPATH);
		spm.setScopes(scopes);
		spm.setCategory(Activator.SERVER_CATEGORY); // $NON-NLS-1$
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(PROPERTY_JS_INPUTCONTROL_TYPE);
		spm.setLabel(Messages.ICParameterContributor_0);
		spm.setDescription(Messages.ICParameterContributor_1);
		spm.setValueType(ICTypes.class.getName());
		spm.setScopes(scopes);
		spm.setCategory(Activator.SERVER_CATEGORY); // $NON-NLS-1$
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(PROPERTY_JS_INPUTCONTROL_VALUE);
		spm.setLabel(Messages.ICParameterContributor_4);
		spm.setDescription(Messages.ICParameterContributor_6);
		spm.setValueType(PROPERTY_JS_INPUTCONTROL_VALUE);
		spm.setScopes(scopes);
		spm.setCategory(Activator.SERVER_CATEGORY); // $NON-NLS-1$
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(PROPERTY_JS_INPUTCONTROL_LABEL);
		spm.setLabel(Messages.ICParameterContributor_8);
		spm.setDescription(Messages.ICParameterContributor_10);
		spm.setValueType(String.class.getName());
		spm.setScopes(scopes);
		spm.setCategory(Activator.SERVER_CATEGORY); // $NON-NLS-1$
		pm.add(spm);

		pm.add(spm);
		PropertyMetadataRegistry.addMetadata(pm);
	}

	private JRDesignParameter prm;
	private SelectParameterDialog pm;

	@Override
	public void createUI(Composite parent, JRDesignParameter prm, final SelectParameterDialog pm,
			final IFilterQuery fq) {
		this.pm = pm;
		final JRDesignDataset dataset = pm.getDesigner().getjDataset();
		if (!dataset.isMainDataset())
			return;
		String servURL = dataset.getPropertiesMap().getProperty(AExporter.PROP_SERVERURL);
		String servUser = dataset.getPropertiesMap().getProperty(AExporter.PROP_USER);
		if (servURL == null || servUser == null)
			return;

		this.prm = prm;

		parent = new Composite(parent, SWT.NONE);
		parent.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		parent.setLayoutData(gd);

		wPath = new WJRProperty(parent,
				TColumnFactory.getTColumn(
						PropertyMetadataRegistry.getPropertiesMetadata().get(PROPERTY_JS_INPUTCONTROL_PATH)),
				prm, pm.getDesigner().getjConfig());

		wLabel = new WJRProperty(parent,
				TColumnFactory.getTColumn(
						PropertyMetadataRegistry.getPropertiesMetadata().get(PROPERTY_JS_INPUTCONTROL_LABEL)),
				prm, pm.getDesigner().getjConfig());

		TColumn c = TColumnFactory
				.getTColumn(PropertyMetadataRegistry.getPropertiesMetadata().get(PROPERTY_JS_INPUTCONTROL_TYPE));
		c.setLabelEditable(true);
		c.setDefaultValue(ICTypes.VALUE.getValue());
		wType = new WJRProperty(parent, c, prm, pm.getDesigner().getjConfig());

		c = TColumnFactory
				.getTColumn(PropertyMetadataRegistry.getPropertiesMetadata().get(PROPERTY_JS_INPUTCONTROL_VALUE));
		c.setReadOnly(true);
		c.setValue1(fq);
		wValue = new WJRProperty(parent, c, prm, pm.getDesigner().getjConfig());

		wDs = new WJRProperty(parent,
				TColumnFactory.getTColumn(
						PropertyMetadataRegistry.getPropertiesMetadata().get(PROPERTY_JS_INPUTCONTROL_DATASOURCE)),
				prm, pm.getDesigner().getjConfig());

		refresh(prm);
		parent.addDisposeListener(e -> {
			if (prm != null && prm.getPropertiesMap() != null)
				prm.getPropertiesMap().getEventSupport().removePropertyChangeListener(pmapListener);
		});
	}

	private WJRProperty wValue;
	private WJRProperty wDs;

	PropertyChangeListener pmapListener = evt -> {
		if (evt.getPropertyName().equals(PROPERTY_JS_INPUTCONTROL_TYPE)
				|| evt.getPropertyName().equals(PROPERTY_JS_INPUTCONTROL_PATH))
			setWidgetsState();
		pm.setDirty(prm);
	};
	private WJRProperty wType;
	private WJRProperty wLabel;
	private WJRProperty wPath;

	public void setWidgetsState() {
		boolean en = prm != null;
		String path = prm != null ? prm.getPropertiesMap().getProperty(PROPERTY_JS_INPUTCONTROL_PATH) : "";
		en = en && Misc.isNullOrEmpty(path);

		wLabel.getControl().setEnabled(en);
		wType.getControl().setEnabled(en);
		String v = prm != null ? prm.getPropertiesMap().getProperty(PROPERTY_JS_INPUTCONTROL_TYPE) : "";
		en = en && !Misc.isNullOrEmpty(v);
		wValue.getControl().setEnabled(en && (v.equals(ICTypes.MULTI_LOV.name()) || v.equals(ICTypes.SINGLE_LOV.name())
				|| v.equals(ICTypes.MULTI_QUERY.name()) || v.equals(ICTypes.SINGLE_QUERY.name())));
		wDs.getControl()
				.setEnabled(en && (v.equals(ICTypes.MULTI_QUERY.name()) || v.equals(ICTypes.SINGLE_QUERY.name())));
	}

	@Override
	public void refresh(JRDesignParameter prm) {
		this.prm = prm;
		if (prm != null && prm.getPropertiesMap() != null) {
			prm.getPropertiesMap().getEventSupport().removePropertyChangeListener(pmapListener);
			prm.getPropertiesMap().getEventSupport().addPropertyChangeListener(pmapListener);
		}
		wDs.setElement(prm);
		wValue.setElement(prm);
		wPath.setElement(prm);
		wType.setElement(prm);
		wLabel.setElement(prm);
		setWidgetsState();
	}

}
