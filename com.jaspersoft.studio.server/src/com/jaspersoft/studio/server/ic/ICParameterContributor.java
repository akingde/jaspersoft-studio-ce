/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.ic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ResourceMediaType;
import com.jaspersoft.studio.data.designer.AQueryDesigner;
import com.jaspersoft.studio.data.designer.IParameterICContributor;
import com.jaspersoft.studio.property.dataset.fields.table.widget.AWidget;
import com.jaspersoft.studio.property.metadata.PropertyMetadataRegistry;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.selector.SelectServerWizard;
import com.jaspersoft.studio.server.wizard.find.FindResourceJob;

import net.sf.jasperreports.annotations.properties.PropertyScope;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.properties.PropertyMetadata;
import net.sf.jasperreports.properties.StandardPropertyMetadata;

public class ICParameterContributor implements IParameterICContributor {

	public static final String ICPATH = "icpath"; //$NON-NLS-1$
	public static final String PROPERTY_JS_INPUTCONTROL_PATH = "com.jaspersoft.studio.js.ic.path"; //$NON-NLS-1$

	public static void initMetadata() {
		AWidget.addControlValueType(ICPATH, WInputControlPathSelector.class);

		List<PropertyMetadata> pm = new ArrayList<PropertyMetadata>();
		StandardPropertyMetadata spm = new StandardPropertyMetadata();
		spm.setName(PROPERTY_JS_INPUTCONTROL_PATH);
		spm.setLabel(Messages.ICParameterContributor_2);
		spm.setDescription(Messages.ICParameterContributor_3);
		spm.setValueType(ICPATH);
		List<PropertyScope> scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.PARAMETER);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.jrs.category:JasperReports.server"); //$NON-NLS-1$

		pm.add(spm);
		PropertyMetadataRegistry.addMetadata(pm);
	}

	private Text tpath;
	private JRDesignParameter prm;
	private Button bpath;
	private ControlDecoration decorator;

	@Override
	public void createUI(Composite parent, JRDesignParameter prm, final AQueryDesigner designer) {
		final JRDesignDataset dataset = designer.getjDataset();
		if (!dataset.isMainDataset())
			return;
		String servURL = dataset.getPropertiesMap().getProperty(AExporter.PROP_SERVERURL);
		String servUser = dataset.getPropertiesMap().getProperty(AExporter.PROP_USER);
		if (servURL == null || servUser == null)
			return;

		this.prm = prm;
		new Label(parent, SWT.NONE).setText(Messages.ICParameterContributor_5);

		Composite cmp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		cmp.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		tpath = new Text(cmp, SWT.BORDER | SWT.READ_ONLY);
		tpath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tpath.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.character == SWT.DEL && e.stateMask == 0) || e.keyCode == SWT.BS) {
					JRPropertiesMap pmap = ICParameterContributor.this.prm.getPropertiesMap();
					pmap.removeProperty(PROPERTY_JS_INPUTCONTROL_PATH);
					refresh(ICParameterContributor.this.prm);
				}
			}
		});

		bpath = new Button(cmp, SWT.PUSH);
		bpath.setText("..."); //$NON-NLS-1$
		bpath.addSelectionListener(new SelectionAdapter() {

			private MServerProfile msp;

			@Override
			public void widgetSelected(SelectionEvent e) {
				JRDesignDataset jd = dataset;
				String servURL = jd.getPropertiesMap().getProperty(AExporter.PROP_SERVERURL);
				String servUser = jd.getPropertiesMap().getProperty(AExporter.PROP_USER);

				msp = ServerManager.getServerByUrl(servURL, servUser);
				if (msp == null) {
					SelectServerWizard wizard = new SelectServerWizard();
					WizardDialog w = new WizardDialog(bpath.getShell(), wizard);
					if (w.open() == Dialog.OK) {
						msp = wizard.getValue();
						try {
							jd.setProperty(AExporter.PROP_SERVERURL, msp.getValue().getUrl());
							jd.setProperty(AExporter.PROP_USER,
									msp.getValue().getUser() + (msp.getValue().getOrganisation() != null
											? "|" + msp.getValue().getOrganisation() : "")); //$NON-NLS-1$ //$NON-NLS-2$
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							e1.printStackTrace();
						}
					}
				}
				if (msp != null)
					showFindDialog(msp);
			}

			protected void showFindDialog(MServerProfile msp) {
				JRPropertiesMap pmap = ICParameterContributor.this.prm.getPropertiesMap();
				if (msp.isSupported(Feature.SEARCHREPOSITORY)) {
					String[] incl = new String[] { ResourceMediaType.INPUT_CONTROL_CLIENT_TYPE };
					ResourceDescriptor rd = FindResourceJob.doFindResource(bpath.getShell(), msp, incl, null, true,
							ICParameterContributor.this.prm.getName());
					if (rd != null) {
						if (rd.getName().equals(ICParameterContributor.this.prm.getName()))
							pmap.setProperty(PROPERTY_JS_INPUTCONTROL_PATH, rd.getUriString());
						else
							UIUtils.showWarning(Messages.ICParameterContributor_7);
					}
				} else {
					RepositoryDialog rd = new RepositoryDialog(bpath.getShell(), msp) {

						@Override
						public boolean isResourceCompatible(AMResource r) {
							return r.getValue().getWsType().equals(ResourceDescriptor.TYPE_INPUT_CONTROL)
									&& r.getValue().getName().equals(ICParameterContributor.this.prm.getName());
						}
					};
					if (rd.open() == Dialog.OK) {
						AMResource rs = rd.getResource();
						if (rs != null)
							pmap.setProperty(PROPERTY_JS_INPUTCONTROL_PATH, rs.getValue().getUriString());
						else
							pmap.removeProperty(PROPERTY_JS_INPUTCONTROL_PATH);
					}
				}
				refresh(ICParameterContributor.this.prm);
			}
		});
		decorator = new ControlDecoration(tpath, SWT.CENTER);
		decorator.setDescriptionText(Messages.ICParameterContributor_7);
		decorator.setImage(
				FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage());
		refresh(ICParameterContributor.this.prm);
	}

	@Override
	public void refresh(JRDesignParameter prm) {
		this.prm = prm;
		tpath.setEnabled(prm != null);
		bpath.setEnabled(prm != null);
		String p = null;
		if (prm != null && prm.getPropertiesMap() != null)
			p = prm.getPropertiesMap().getProperty(PROPERTY_JS_INPUTCONTROL_PATH);
		if (tpath.isDisposed())
			return;
		tpath.setText(Misc.nvl(p));
		String tt = Messages.ICParameterContributor_9;
		if (!Misc.isNullOrEmpty(tpath.getText()))
			tt = tpath.getText() + "\n\n" + tt; //$NON-NLS-1$
		tpath.setToolTipText(tt);
		if (!Misc.isNullOrEmpty(p) && prm != null) {
			int ind = p.lastIndexOf("/");
			if (ind >= 0)
				p = p.substring(ind + 1);
			if (!p.equals(prm.getName())) {
				decorator.show();
				return;
			}
		}
		decorator.hide();
	}

}
