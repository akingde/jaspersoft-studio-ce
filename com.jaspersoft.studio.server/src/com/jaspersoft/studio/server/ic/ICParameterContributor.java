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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.data.designer.IFilterQuery;
import com.jaspersoft.studio.data.designer.IParameterICContributor;
import com.jaspersoft.studio.data.designer.SelectParameterDialog;
import com.jaspersoft.studio.property.dataset.fields.table.TColumnFactory;
import com.jaspersoft.studio.property.dataset.fields.table.widget.AWidget;
import com.jaspersoft.studio.property.dataset.fields.table.widget.WJRProperty;
import com.jaspersoft.studio.property.metadata.PropertyMetadataRegistry;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.messages.Messages;

import net.sf.jasperreports.annotations.properties.PropertyScope;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
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
	private Composite cmp;
	private WJRProperty wValue;
	private WJRProperty wDs;
	private IFilterQuery fq;
	private WJRProperty wLabel;
	private WJRProperty wPath;
	private Combo cOpt;
	private boolean refresh = false;

	@Override
	public void createUI(Composite parent, JRDesignParameter prm, final SelectParameterDialog pm,
			final IFilterQuery fq) {
		this.fq = fq;
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

		new Label(parent, SWT.NONE).setText("Input Control");

		cOpt = new Combo(parent, SWT.READ_ONLY);
		cOpt.setItems(new String[] { "", "Existing From Repository", Messages.ICTypes_0, Messages.ICTypes_1,
				"List Of Values", "Query" });
		cOpt.addModifyListener(e -> {
			if (refresh)
				return;
			refresh = true;
			try {
				cmp.dispose();
				prm.getPropertiesMap().removeProperty(PROPERTY_JS_INPUTCONTROL_TYPE);
				prm.getPropertiesMap().removeProperty(PROPERTY_JS_INPUTCONTROL_PATH);
				prm.getPropertiesMap().removeProperty(PROPERTY_JS_INPUTCONTROL_DATASOURCE);
				prm.getPropertiesMap().removeProperty(PROPERTY_JS_INPUTCONTROL_VALUE);
				prm.getPropertiesMap().removeProperty(PROPERTY_JS_INPUTCONTROL_LABEL);
				switch (cOpt.getSelectionIndex()) {
				case 1:
					buildCmp();
					buildRepositoryIC();
					break;
				case 2:
					prm.getPropertiesMap().setProperty(PROPERTY_JS_INPUTCONTROL_TYPE, ICTypes.BOOLEAN.name());
					buildIC();
					break;
				case 3:
					prm.getPropertiesMap().setProperty(PROPERTY_JS_INPUTCONTROL_TYPE, ICTypes.VALUE.name());
					buildIC();
					break;
				case 4:
					prm.getPropertiesMap().setProperty(PROPERTY_JS_INPUTCONTROL_TYPE, ICTypes.SINGLE_LOV.name());
					buildIC();
					break;
				case 5:
					prm.getPropertiesMap().setProperty(PROPERTY_JS_INPUTCONTROL_TYPE, ICTypes.SINGLE_QUERY.name());
					buildIC();
					break;
				}
				cOpt.getParent().update();
				cOpt.getParent().layout(true);
				UIUtils.relayoutDialogHeight(cOpt.getShell(), -1);
			} finally {
				refresh = false;
			}
		});
		buildIC();
		refresh(prm);
		parent.addDisposeListener(e -> {
			if (prm != null && prm.getPropertiesMap() != null)
				prm.getPropertiesMap().getEventSupport().removePropertyChangeListener(pmapListener);
		});
	}

	private void buildIC() {
		if (prm == null)
			return;
		buildCmp();

		String path = prm != null ? prm.getPropertiesMap().getProperty(PROPERTY_JS_INPUTCONTROL_PATH) : "";
		if (!Misc.isNullOrEmpty(path)) {
			buildRepositoryIC();
			return;
		}

		wLabel = new WJRProperty(cmp,
				TColumnFactory.getTColumn(
						PropertyMetadataRegistry.getPropertiesMetadata().get(PROPERTY_JS_INPUTCONTROL_LABEL)),
				prm, pm.getDesigner().getjConfig());

		String v = Misc.nvl(prm != null ? prm.getPropertiesMap().getProperty(PROPERTY_JS_INPUTCONTROL_TYPE) : "");
		if (v.equals(ICTypes.BOOLEAN.name()))
			buildBooleanIC();
		else if (v.equals(ICTypes.VALUE.name()))
			buildValueIC();
		else if (v.equals(ICTypes.MULTI_LOV.name()) || v.equals(ICTypes.SINGLE_LOV.name()))
			buildLOV();
		else if (v.equals(ICTypes.MULTI_QUERY.name()) || v.equals(ICTypes.SINGLE_QUERY.name()))
			buildQuery();
	}

	private void buildCmp() {
		cmp = new Composite(cOpt.getParent(), SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);
	}

	private void buildRepositoryIC() {
		wPath = new WJRProperty(cmp,
				TColumnFactory.getTColumn(
						PropertyMetadataRegistry.getPropertiesMetadata().get(PROPERTY_JS_INPUTCONTROL_PATH)),
				prm, pm.getDesigner().getjConfig());
	}

	private void buildBooleanIC() {
		// nothing to do
	}

	private void buildValueIC() {
		// here we could put pattern, etc.
	}

	private void buildLOV() {
		Composite c = new Composite(cmp, SWT.NONE);
		c.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		c.setLayoutData(gd);

		LovComposite lovc = new LovComposite(
				Misc.nvl(prm != null ? prm.getPropertiesMap().getProperty(PROPERTY_JS_INPUTCONTROL_VALUE) : "")) {
			@Override
			protected void handleValueChanged() {
				prm.getPropertiesMap().setProperty(PROPERTY_JS_INPUTCONTROL_VALUE, getValue());
			}
		};
		lovc.createComposite(c);
	}

	private void buildQuery() {
		Composite c = new Composite(cmp, SWT.NONE);
		c.setLayout(new GridLayout());
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		c.setLayoutData(gd);

		QueryComposite qc = new QueryComposite(
				Misc.nvl(prm != null ? prm.getPropertiesMap().getProperty(PROPERTY_JS_INPUTCONTROL_VALUE) : ""), fq) {
			@Override
			protected void handleValueChanged() {
				prm.getPropertiesMap().setProperty(PROPERTY_JS_INPUTCONTROL_VALUE, getValue());
			}
		};
		qc.createComposite(c);
	}

	PropertyChangeListener pmapListener = evt -> {
		if (refresh)
			return;
		if (evt.getPropertyName().equals(PROPERTY_JS_INPUTCONTROL_TYPE)
				|| evt.getPropertyName().equals(PROPERTY_JS_INPUTCONTROL_PATH))
			setWidgetsState();
		pm.setDirty(prm);
	};

	public void setWidgetsState() {
		if (refresh)
			return;
		refresh = true;
		String path = prm != null ? prm.getPropertiesMap().getProperty(PROPERTY_JS_INPUTCONTROL_PATH) : "";
		String v = Misc.nvl(prm != null ? prm.getPropertiesMap().getProperty(PROPERTY_JS_INPUTCONTROL_TYPE) : "");

		if (!Misc.isNullOrEmpty(path))
			cOpt.select(1);
		else if (v.equals(ICTypes.BOOLEAN.name()))
			cOpt.select(2);
		else if (v.equals(ICTypes.VALUE.name()))
			cOpt.select(3);
		else if (v.equals(ICTypes.SINGLE_LOV.name()) || v.equals(ICTypes.MULTI_LOV.name()))
			cOpt.select(4);
		else if (v.equals(ICTypes.SINGLE_QUERY.name()) || v.equals(ICTypes.MULTI_QUERY.name()))
			cOpt.select(5);
		else
			cOpt.select(0);
		refresh = false;
	}

	@Override
	public void refresh(JRDesignParameter prm) {
		this.prm = prm;
		if (prm != null && prm.getPropertiesMap() != null) {
			prm.getPropertiesMap().getEventSupport().removePropertyChangeListener(pmapListener);
			prm.getPropertiesMap().getEventSupport().addPropertyChangeListener(pmapListener);
		}
		if (wDs != null)
			wDs.setElement(prm);
		if (wValue != null)
			wValue.setElement(prm);
		if (wPath != null)
			wPath.setElement(prm);
		if (wLabel != null)
			wLabel.setElement(prm);
		setWidgetsState();
	}

}
