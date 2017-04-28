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
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.selector.SelectServerWizard;
import com.jaspersoft.studio.server.wizard.find.FindResourceJob;
import com.jaspersoft.studio.utils.Misc;

import net.sf.jasperreports.annotations.properties.PropertyScope;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.properties.PropertyMetadata;
import net.sf.jasperreports.properties.StandardPropertyMetadata;

public class ICParameterContributor implements IParameterICContributor {

	public static final String ICPATH = "icpath";
	public static final String PROPERTY_JS_INPUTCONTROL_PATH = "com.jaspersoft.studio.js.ic.path";

	public static void initMetadata() {
		AWidget.addControlValueType(ICPATH, WInputControlPathSelector.class);

		List<PropertyMetadata> pm = new ArrayList<PropertyMetadata>();
		StandardPropertyMetadata spm = new StandardPropertyMetadata();
		spm.setName(PROPERTY_JS_INPUTCONTROL_PATH);
		spm.setLabel("Input Control Path");
		spm.setDescription("This input control on the server.");
		spm.setValueType(ICPATH);
		List<PropertyScope> scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.PARAMETER);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.jrs.category:JasperReports.server");

		pm.add(spm);
		PropertyMetadataRegistry.addMetadata(pm);
	}

	private Text tpath;
	private JRDesignParameter prm;
	private Button bpath;
	private ControlDecoration decorator;

	@Override
	public void createUI(Composite parent, JRDesignParameter prm, final AQueryDesigner designer) {
		if (!designer.getjDataset().isMainDataset())
			return;
		JasperDesign jDesign = designer.getjDesign();
		String servURL = jDesign.getProperty(AExporter.PROP_SERVERURL);
		String servUser = jDesign.getProperty(AExporter.PROP_USER);
		if (servURL == null || servUser == null)
			return;

		this.prm = prm;
		new Label(parent, SWT.NONE).setText("Input Control");

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
		bpath.setText("...");
		bpath.addSelectionListener(new SelectionAdapter() {

			private MServerProfile msp;

			@Override
			public void widgetSelected(SelectionEvent e) {
				JasperDesign jDesign = designer.getjDesign();
				String servURL = jDesign.getProperty(AExporter.PROP_SERVERURL);
				String servUser = jDesign.getProperty(AExporter.PROP_USER);

				msp = ServerManager.getServerByUrl(servURL, servUser);
				if (msp == null) {
					SelectServerWizard wizard = new SelectServerWizard();
					WizardDialog w = new WizardDialog(UIUtils.getShell(), wizard);
					if (w.open() == Dialog.OK) {
						msp = wizard.getValue();
						try {
							jDesign.setProperty(AExporter.PROP_SERVERURL, msp.getValue().getUrl());
							jDesign.setProperty(AExporter.PROP_USER,
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
					ResourceDescriptor rd = FindResourceJob.doFindResource(msp, incl, null, true,
							ICParameterContributor.this.prm.getName());
					if (rd != null) {
						if (rd.getName().equals(ICParameterContributor.this.prm.getName()))
							pmap.setProperty(PROPERTY_JS_INPUTCONTROL_PATH, rd.getUriString());
						else
							UIUtils.showWarning(
									"Input Control name must be the same as parameter name to work on Jaspersoft Server.");
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
		decorator.setDescriptionText(
				"Input Control name must be the same as parameter name to work on Jaspersoft Server.");
		decorator.setImage(
				FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage());
		refresh(ICParameterContributor.this.prm);
	}

	@Override
	public void refresh(JRDesignParameter prm) {
		this.prm = prm;
		String p = prm.getPropertiesMap().getProperty(PROPERTY_JS_INPUTCONTROL_PATH);
		if (tpath.isDisposed())
			return;
		tpath.setText(Misc.nvl(p));
		String tt = "This input control on the server.";
		if (!Misc.isNullOrEmpty(tpath.getText()))
			tt = tpath.getText() + "\n\n" + tt;
		tpath.setToolTipText(tt);
		if (!Misc.isNullOrEmpty(p) && !p.equals(prm.getName()))
			decorator.show();
		else
			decorator.hide();

	}

}
