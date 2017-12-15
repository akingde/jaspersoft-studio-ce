/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.subreport.command.wizard;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.property.dataset.wizard.WizardConnectionPage;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.GenericJSSParameter;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSWizard;
import com.jaspersoft.studio.wizards.ReportNewWizard;
import com.jaspersoft.studio.wizards.datasource.StaticWizardDataSourcePage;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileExtension;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.StringUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JRSubreportParameter;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.repo.RepositoryUtil;

public class SubreportWizard extends JSSWizard {
	private NewSubreportPage step0;
	private WizardConnectionPage step2;
	private SubreportParameterPage step3;
	private MSubreport subreport;

	public SubreportWizard() {
		super();
		setWindowTitle(Messages.common_subreport);
	}

	@Override
	public void addPages() {
		this.subreport = new MSubreport();
		subreport.setValue(subreport.createJRElement(getConfig().getJasperDesign()));
		subreport.setPropertyValue(JRDesignSubreport.PROPERTY_CONNECTION_EXPRESSION, "$P{REPORT_CONNECTION}");

		step0 = getSubreportPage();
		step0.setSubreport(subreport);
		addPage(step0);

		subreport.setJasperConfiguration(getConfig());

		step2 = new WizardConnectionPage();
		addPage(step2);

		step3 = new SubreportParameterPage();
		addPage(step3);

		// Setting up the expressions context. This is not really useful, since
		// the subreport has not been added to the report yet and it will be fallback to the default dataset.
		// FIXME: pass a proper ANode to the wizard to let the code to lookup for a more appropriate dataset.
		ExpressionContext ec = ModelUtils.getElementExpressionContext((JRDesignElement) subreport.getValue(), subreport);
		step0.setExpressionContext(ec);
		step2.setExpressionContext(ec);
		step3.setExpressionContext(ec);
	}

	/**
	 * Return the first page of the wizard, where an existing report can be selected or create a new one
	 * 
	 * @return a not null NewSubreportPage
	 */
	protected NewSubreportPage getSubreportPage() {
		return new NewSubreportPage();
	}

	/**
	 * The getNextPage implementations does nothing, since all the logic has been moved inside each page, specifically
	 * extended for this wizard
	 * 
	 * @see com.jaspersoft.studio.wizards.JSSWizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
	 *
	 * @param the
	 *          current page.
	 *
	 * @return the next page
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == step3) {
			try {
				step3.setJasperDesign(null);
				getContainer().run(true, true, new IRunnableWithProgress() {

					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						Object reportFileName = null;
						JRExpression subreportExp = getSubreport().getValue().getExpression();
						JasperReportsConfiguration jrConf = getConfig();
						if (subreportExp != null && !Misc.isNullOrEmpty(subreportExp.getText()))
							reportFileName = ExpressionUtil.cachedExpressionEvaluation(subreportExp, jrConf);

						// Report not found
						if (reportFileName != null) {
							if (reportFileName instanceof File)
								reportFileName = ((File) reportFileName).toURI().toString();
							else if (reportFileName instanceof String) {
								String location = (String) reportFileName;
								InputStream in = null;
								try {
									in = RepositoryUtil.getInstance(jrConf).getInputStreamFromLocation(location);
									readJR(jrConf, location, in);
								} catch (JRException e) {
									e.printStackTrace();
									if (location.endsWith(FileExtension.PointJASPER)) {
										location = StringUtils.replaceAllIns(location, FileExtension.JASPER + "$", FileExtension.JRXML);
										try {
											in = RepositoryUtil.getInstance(jrConf).getInputStreamFromLocation(location);
											readJR(jrConf, location, in);
										} catch (JRException e1) {
											e.printStackTrace();
										}
									}
								} finally {
									FileUtils.closeStream(in);
								}
							}
						}
					}

					protected void readJR(JasperReportsConfiguration jrConf, String location, InputStream in) throws JRException {
						JRReport r = null;
						if (location.endsWith(FileExtension.PointJRXML))
							r = JRXmlLoader.load(in);
						else if (location.endsWith(FileExtension.PointJASPER))
							r = (JRReport) JRLoader.loadObject(jrConf, in);
						else {
							try {
								r = JRXmlLoader.load(in);
							} catch (JRException ex) {
								r = (JRReport) JRLoader.loadObject(jrConf, in);
							}
						}
						step3.setJasperDesign(r);
					}

				});
			} catch (InvocationTargetException e) {
				UIUtils.showError(e.getCause());
			} catch (InterruptedException e) {
				UIUtils.showError(e.getCause());
			}
		}
		return super.getNextPage(page);

	}

	@Override
	public boolean performFinish() {
		for (IWizard w : getChildWizards()) {
			if (w instanceof ReportNewWizard) {
				JRSubreportParameter[] parameters = GenericJSSParameter.convertToSubreport(step3.getValue());
				((ReportNewWizard) w).getSettings().put(StaticWizardDataSourcePage.EXTRA_PARAMETERS, parameters);
			}
			w.performFinish();
		}
		return true;
	}

	/**
	 * Retutn the subreport object...
	 * 
	 * @return
	 */
	public MSubreport getSubreport() {

		JRSubreportParameter[] map = GenericJSSParameter.convertToSubreport(step3.getValue());

		if (map != null)
			subreport.setPropertyValue(JRDesignSubreport.PROPERTY_PARAMETERS, map);

		// Configure connection expression...
		JRDesignDatasetRun datasetRun = step2.getJRDesignDatasetRun();

		subreport.setPropertyValue(JRDesignSubreport.PROPERTY_PARAMETERS_MAP_EXPRESSION,
				datasetRun.getParametersMapExpression());
		subreport.setPropertyValue(JRDesignSubreport.PROPERTY_CONNECTION_EXPRESSION, datasetRun.getConnectionExpression());
		subreport.setPropertyValue(JRDesignSubreport.PROPERTY_DATASOURCE_EXPRESSION, datasetRun.getDataSourceExpression());

		// Create the subreport expression....
		if (step0.getSelectedOption() == NewSubreportPage.EXISTING_REPORT) {
			subreport.setPropertyValue(JRDesignSubreport.PROPERTY_EXPRESSION, step0.getSelectedSubreportExpression());
		} else if (step0.getSelectedOption() == NewSubreportPage.NEW_REPORT) {
			// In this case the new report has been created by using a report wizard
			// which stores the location of a file in the
			// wizard settings...
			IPath path = (IPath) getSettings().get(JSSWizard.FILE_PATH);
			String fname = (String) getSettings().get(JSSWizard.FILE_NAME);

			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IResource resource = root.findMember(path);
			IFile file = ((IContainer) resource).getFile(new Path(fname));

			IFile contextfile = (IFile) getConfig().get(FileUtils.KEY_FILE);

			String filepath = "";
			if (contextfile != null && file.getProject().equals(contextfile.getProject())) {
				filepath = file.getProjectRelativePath().toPortableString().replaceAll(file.getProject().getName() + "/", "");
			} else {
				filepath = file.getRawLocationURI().toASCIIString();
			}
			if (filepath.toLowerCase().endsWith(".jrxml")) {
				filepath = filepath.substring(0, filepath.lastIndexOf(".")) + ".jasper";
			}
			JRDesignExpression exp = new JRDesignExpression();
			exp.setText("\"" + filepath + "\""); //$NON-NLS-1$ $NON-NLS-1$

			subreport.setPropertyValue(JRDesignSubreport.PROPERTY_EXPRESSION, exp);
		}
		return subreport;
	}

	@Override
	public void setConfig(JasperReportsConfiguration config, boolean disposeConfig) {
		super.setConfig(config, disposeConfig);
		if (subreport != null)
			subreport.setJasperConfiguration(config);
	}

	@Override
	public boolean canFinish() {

		if (step0.getSelectedOption() == NewSubreportPage.NEW_REPORT) {
			return getContainer().getCurrentPage() == step3;
		}
		return super.canFinish();
	}

}
