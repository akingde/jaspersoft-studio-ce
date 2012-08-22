/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.subreport.command.wizard;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JRDesignSubreportParameter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlDigesterFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.wizards.AWizardNode;
import com.jaspersoft.studio.wizards.JSSWizardSelectionPage;
import com.jaspersoft.studio.wizards.ReportNewWizard;

public class NewSubreportPage extends JSSWizardSelectionPage implements IExpressionContextSetter {

	private Button useReport;
	private WTextExpression useReportPath;
	private Button useReportB;
	private Button newReport;
	private ExpressionContext expContext;

	protected NewSubreportPage() {
		super("newsubreportpage");
		setTitle(Messages.common_subreport);
		setDescription(Messages.WizardNewSubreportPage_description);
		setImageDescriptor(MSubreport.getIconDescriptor().getIcon32());
	}

	private MSubreport subreport;

	public MSubreport getSubreport() {
		return subreport;
	}

	public void setSubreport(MSubreport subreport) {
		this.subreport = subreport;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		setControl(composite);
		composite.setLayout(new GridLayout(2, false));

		newReport = new Button(composite, SWT.RADIO);
		newReport.setText("Create a new Report");
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		newReport.setLayoutData(gd);

		useReport = new Button(composite, SWT.RADIO);
		useReport.setText("Use an existing Report");
		gd = new GridData();
		gd.horizontalSpan = 2;
		useReport.setLayoutData(gd);
		useReport.setSelection(true);
		setPageComplete(false);

		useReportPath = new WTextExpression(composite, SWT.NONE);
		useReportPath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		useReportPath.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				handleDataChanged();
			}
		});
		if (expContext != null) {
			useReportPath.setExpressionContext(expContext);
		}

		useReportB = new Button(useReportPath, SWT.PUSH);
		useReportB.setText("Browse");
		useReportB.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				FilteredResourcesSelectionDialog wizard = new FilteredResourcesSelectionDialog(Display.getCurrent()
						.getActiveShell(), false, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
				wizard.setInitialPattern("*.jrxml");//$NON-NLS-1$
				if (wizard.open() == Dialog.OK)
					setUpSubreport((IFile) wizard.getFirstResult(), null);
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		newReport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setUseReportEnabled();
				if (newReport.getSelection()) {
					setSelectedNode(new AWizardNode() {
						public IWizard createWizard() {
							IWizard pwizard = NewSubreportPage.this.getWizard();
							ReportNewWizard w = new ReportNewWizard(pwizard, pwizard.getNextPage(NewSubreportPage.this));
							IWorkbench bench = PlatformUI.getWorkbench();
							IWorkbenchPage page = bench.getActiveWorkbenchWindow().getActivePage();
							w.init(bench, (IStructuredSelection) page.getSelection());
							return w;
						}
					});
					setPageComplete(true);
				}
			}
		});
		useReport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setSelectedNode(null);
				setUseReportEnabled();
				setPageComplete(!(useReportPath.getExpression() == null || useReportPath.getExpression().getText().isEmpty()));
			}
		});

		Button empty = new Button(composite, SWT.RADIO);
		empty.setText("Just create component");
		gd = new GridData();
		gd.horizontalSpan = 2;
		empty.setLayoutData(gd);
		empty.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setSelectedNode(null);
				setUseReportEnabled();
				setPageComplete(true);
				handleDataChanged();
			}
		});

		handleDataChanged();
		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");//$NON-NLS-1$
	}

	private void setUseReportEnabled() {
		boolean enabled = useReport.getSelection();
		useReportPath.setEnabled(enabled);
		useReportB.setEnabled(enabled);
		handleDataChanged();
	}

	public void setUpSubreport(IFile file, JasperDesign newjd) {
		if (file == null)
			return;
		JRDesignExpression jre = new JRDesignExpression();
		IFile contextfile = (IFile) subreport.getJasperConfiguration().get(IEditorContributor.KEY_FILE);
		String filepath = null;
		if (contextfile != null && file.getProject().equals(contextfile.getProject())) {
			filepath = file.getProjectRelativePath().toPortableString().replaceAll(file.getProject().getName() + "/", "");
		} else {
			filepath = file.getRawLocationURI().toASCIIString();
		}

		filepath = filepath.replaceAll(".jrxml", ".jasper");
		jre.setText("\"" + filepath + "\"");//$NON-NLS-1$ //$NON-NLS-2$
		subreport.setPropertyValue(JRDesignSubreport.PROPERTY_EXPRESSION, jre);

		JRDesignSubreport s = (JRDesignSubreport) subreport.getValue();
		if (newjd == null) {
			InputStream in = null;
			try {
				in = file.getContents();
				InputSource is = new InputSource(new InputStreamReader(in, "UTF-8"));

				newjd = new JRXmlLoader(JRXmlDigesterFactory.createDigester()).loadXML(is);
			} catch (JRException e) {
				UIUtils.showError(e);
			} catch (CoreException e) {
				UIUtils.showError(e);
			} catch (UnsupportedEncodingException e) {
				UIUtils.showError(e);
			} catch (ParserConfigurationException e) {
				UIUtils.showError(e);
			} catch (SAXException e) {
				UIUtils.showError(e);
			} finally {
				if (in != null)
					try {
						in.close();
					} catch (IOException e) {
						UIUtils.showError(e);
					}
			}
		}
		if (newjd != null) {
			List<JRParameter> prms = newjd.getParametersList();
			for (JRParameter p : prms) {
				if (!p.isSystemDefined()) {
					JRDesignSubreportParameter sp = new JRDesignSubreportParameter();
					sp.setName(p.getName());

					JRDesignExpression jrep = new JRDesignExpression();
					jrep.setText("");
					sp.setExpression(jrep);

					try {
						s.addParameter(sp);
					} catch (JRException e) {
						e.printStackTrace();
					}
				}
			}

			useReportPath.setExpression(jre);
		}
	}

	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
		if (useReportPath != null) {
			useReportPath.setExpressionContext(this.expContext);
		}
	}

	public void handleDataChanged() {
		setErrorMessage(null);
		setMessage(Messages.WizardNewSubreportPage_description);
		if (useReport.getSelection()) {
			boolean complete = !(useReportPath.getExpression() == null || useReportPath.getExpression().getText().isEmpty());
			if (!complete)
				setErrorMessage("Please add an expression for subreport path");
			setPageComplete(complete);
		}
	}
}
