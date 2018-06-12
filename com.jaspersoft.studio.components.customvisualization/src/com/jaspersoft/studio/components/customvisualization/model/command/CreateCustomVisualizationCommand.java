/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.model.command;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.customvisualization.CVComponentUtil;
import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.components.customvisualization.model.MCustomVisualization;
import com.jaspersoft.studio.components.customvisualization.ui.UIManager;
import com.jaspersoft.studio.components.customvisualization.ui.framework.CVCWidgetsDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.framework.DatasetPropertyDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.widgets.framework.model.SectionPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;

import net.sf.jasperreports.components.items.StandardItem;
import net.sf.jasperreports.components.items.StandardItemData;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.customvisualization.design.CVDesignComponent;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.viewer.BrowserUtils;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

/**
 * Create command for the Custom Visualization component element.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class CreateCustomVisualizationCommand extends CreateElementCommand {

	public CreateCustomVisualizationCommand(ANode destNode,
			MGraphicElement srcNode, Rectangle position, int index) {
		super(destNode, srcNode, position, index);
	}

	public CreateCustomVisualizationCommand(MBand destNode,
			MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

	public CreateCustomVisualizationCommand(MElementGroup destNode,
			MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

	public CreateCustomVisualizationCommand(MFrame destNode,
			MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

	public CreateCustomVisualizationCommand(MFrame destNode,
			MGraphicElement srcNode, Rectangle position, int index) {
		super(destNode, srcNode, position, index);
	}

	@Override
	protected void createObject() {
		if (jConfig != null) {
			try {
				Runtime.getRuntime().exec(
						CVComponentUtil.PHANTOMJS_VERSIONCHECK_CMD);
			} catch (IOException e) {
				// PhantomJS is not configured, show warning if necessary
				Boolean showWarning = jConfig.getPropertyBoolean(
						CVComponentUtil.PHANTOJS_WARNING_PROPERTY, true);
				if (Boolean.TRUE.equals(showWarning)) {
					String phantomJSInstallPath = jConfig
							.getProperty(CVComponentUtil.PHANTOMJS_INSTALLATION_PATH_PROPERTY);
					if (StringUtils.isBlank(phantomJSInstallPath)) {
						new HyperlinkInfoDialog(
								UIUtils.getShell(),
								Messages.CreateCustomVisualizationCommand_WarningTitle,
								NLS.bind(
										Messages.CreateCustomVisualizationCommand_WarningMessage,
										CVComponentUtil.PHANTOMJS_INSTALLATION_PATH_PROPERTY),
								CVComponentUtil.PHANTOMJS_URL_DOWNLOAD).open();
					}
				}
			}
		}
		if (jrElement == null) {
			srcNode = new MCustomVisualization();
			jrElement = srcNode.createJRElement(jasperDesign);

			// let's check in our preferences if we have some modules
			// if yes, let's start a wizard
			List<CVCWidgetsDescriptor> modules = UIManager.getModules(jConfig);
			if (!Misc.isNullOrEmpty(modules)) {
				CVCWizard wizard = new CVCWizard(modules);
				wizard.setConfig(jConfig, false);
				WizardDialog d = new WizardDialog(UIUtils.getShell(), wizard);
				if (d.open() == Dialog.OK) {
					CVDesignComponent cvComp = (CVDesignComponent) ((JRDesignComponentElement) jrElement)
							.getComponent();
					
					CVCWidgetsDescriptor m = wizard.getModule();
					if (m != null && m.getSections() != null)
					{
						for (SectionPropertyDescriptor csd : m.getSections()) {
							for (WidgetPropertyDescriptor cpd : csd.getProperties()) {
								if (cpd.getDefaultValue() != null) {
									cvComp.addItemProperty(new StandardItemProperty(
											cpd.getName(), cpd
													.getDefaultValue(),
											null));
									
									// If the type of the property is a path, we copy the resource in the report
									// folder...
									if (cpd.getType().equalsIgnoreCase("path"))
									{
										try {
											String fileName = cpd.getDefaultValue();
											if (fileName != null)
											{
												UIManager.copyFile(m, jConfig, cpd.getDefaultValue());
											}
											else
											{
												JaspersoftStudioPlugin.getInstance().logWarning("File for property '" + cpd.getLabel() + "' called '"  + fileName  + "' not found or not specified");
											}
										} catch (Exception ex)
										{
											JaspersoftStudioPlugin.getInstance().logWarning("File " + cpd.getLabel() + " not found or not specified");
										}
									}
								}
							}
						}
					
						// build default item data with default values
						List<DatasetPropertyDescriptor> ds = m.getDatasets();
						for (DatasetPropertyDescriptor cdd : ds) {
							if (cdd.getCardinality() >= 0){
								for (int i = 0; i < cdd.getCardinality(); i++) {
									StandardItemData id = new StandardItemData();
									for (SectionPropertyDescriptor csd : cdd.getSections()){
										if (!Misc.isNullOrEmpty(csd.getProperties())) {
											StandardItem item = new StandardItem();
											id.addItem(item);
											for (WidgetPropertyDescriptor cpd : csd.getProperties()){
												if (cpd.getDefaultValue() != null){
													item.addItemProperty(new StandardItemProperty(
															cpd.getName(),
															cpd.getDefaultValue(),
															null));
												}
											}
										}
									}
									cvComp.addItemData(id);
									if (ds.get(ds.size() - 1) == cdd){
										break;
									}
								}
							}
						}				
					}
				} else {
					jrElement = null;
				}
			}
		}
		if (jrElement != null)
			setElementBounds();
	}

	class HyperlinkInfoDialog extends MessageDialog {

		private String url;

		public HyperlinkInfoDialog(Shell parentShell, String dialogTitle,
				String dialogMessage, String url) {
			super(parentShell, dialogTitle, null, dialogMessage,
					MessageDialog.WARNING,
					new String[] { IDialogConstants.OK_LABEL }, 0);
			this.url = url;
		}

		@Override
		protected Control createCustomArea(Composite parent) {
			final StyledText issueLink = new StyledText(parent, SWT.READ_ONLY);
			issueLink.setText(url);
			issueLink.setBackground(parent.getBackground());
			issueLink.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true,
					false, 2, 1));

			StyleRange style = new StyleRange();
			style.underline = true;
			style.underlineStyle = SWT.UNDERLINE_LINK;
			int[] ranges = { 0, url.length() };
			StyleRange[] styles = { style };
			issueLink.setStyleRanges(ranges, styles);

			issueLink.addListener(SWT.MouseDown, new Listener() {
				@Override
				public void handleEvent(Event event) {
					try {
						int offset = issueLink.getOffsetAtLocation(new Point(
								event.x, event.y));
						StyleRange style = issueLink
								.getStyleRangeAtOffset(offset);
						if (style != null && style.underline
								&& style.underlineStyle == SWT.UNDERLINE_LINK) {
							BrowserUtils.openExternalBrowser(url);
						}
					} catch (IllegalArgumentException e) {
						// no character under event.x, event.y
					}
				}
			});

			final Button doNotShowAgainBtn = new Button(parent, SWT.CHECK);
			doNotShowAgainBtn
					.setText(Messages.CreateCustomVisualizationCommand_ShowOrNot);
			doNotShowAgainBtn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (doNotShowAgainBtn.getSelection()) {
						jConfig.setProperty(
								CVComponentUtil.PHANTOJS_WARNING_PROPERTY,
								"false"); //$NON-NLS-1$
					} else {
						jConfig.setProperty(
								CVComponentUtil.PHANTOJS_WARNING_PROPERTY,
								"true"); //$NON-NLS-1$
					}
				}
			});

			return issueLink;
		}

	}
}
