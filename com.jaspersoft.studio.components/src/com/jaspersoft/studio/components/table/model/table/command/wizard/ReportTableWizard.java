package com.jaspersoft.studio.components.table.model.table.command.wizard;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.EditPart;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.components.engine.TableTemplateEngine;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;
import com.jaspersoft.studio.property.dataset.wizard.WizardDataSourcePage;
import com.jaspersoft.studio.property.dataset.wizard.WizardDatasetNewPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardFieldsGroupByPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardFieldsPage;
import com.jaspersoft.studio.templates.engine.DefaultTemplateEngine;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.CongratulationsWizardPage;
import com.jaspersoft.studio.wizards.JSSWizard;
import com.jaspersoft.studio.wizards.NewFileCreationWizard;
import com.jaspersoft.studio.wizards.ReportNewWizard;
import com.jaspersoft.studio.wizards.ReportTemplatesWizardPage;
import com.jaspersoft.studio.wizards.WizardUtils;
import com.jaspersoft.templates.ReportBundle;
import com.jaspersoft.templates.TemplateBundle;

public class ReportTableWizard extends JSSWizard implements IWorkbenchWizard, INewWizard {

	public static final String WIZARD_ID = "com.jaspersoft.studio.wizards.ReportNewWizard";

	private ReportTemplatesWizardPage step0;
	private NewFileCreationWizard step1;
	private WizardDataSourcePage step2;
	private WizardFieldsPage step3;
	private WizardFieldsGroupByPage step4;

	private TableWizardFieldsPage step3Table;
	private TableWizardLayoutPage step4Table;
	private CongratulationsWizardPage congratulationsStep;

	private boolean showCongratulationsStep = true;

	private ISelection selection;

	/**
	 * Constructor for ReportNewWizard.
	 */
	public ReportTableWizard() {
		super();
		setWindowTitle(Messages.ReportNewWizard_title);
		setNeedsProgressMonitor(true);

		// Attention! This operation should always be performed by
		// the wizard caller, since we are forcing here a new config.
		setConfig(new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null));
	}

	public ReportTableWizard(IWizard parentWizard, IWizardPage fallbackPage) {
		super(parentWizard, fallbackPage);
		setWindowTitle(Messages.ReportNewWizard_title);
		setNeedsProgressMonitor(true);
		showCongratulationsStep = false;
	}

	/**
	 * Adding the page to the wizard.
	 */

	@Override
	public void addPages() {
		step0 = new ReportTemplatesWizardPage();
		addPage(step0);

		step1 = new NewFileCreationWizard("newFilePage1", (IStructuredSelection) selection);//$NON-NLS-1$
		addPage(step1);

		step2 = new WizardDataSourcePage();
		addPage(step2);

		step3 = new WizardFieldsPage();
		addPage(step3);

		step4 = new WizardFieldsGroupByPage();
		addPage(step4);

		step3Table = new TableWizardFieldsPage();
		addPage(step3Table);

		step4Table = new TableWizardLayoutPage();
		addPage(step4Table);

		if (showCongratulationsStep) {
			congratulationsStep = new CongratulationsWizardPage();
			addPage(congratulationsStep);
		}

	}

	private JRDesignDataset createTableDataset(Map<String, Object> settings) {

		// Create a new dataset
		JRDesignDataset dataset = new JRDesignDataset(false);
		JRDesignQuery query = new JRDesignQuery();
		dataset.setQuery(query);

		// Get values from the settings...
		if (settings != null) {

			if (settings.containsKey(WizardDatasetNewPage.DATASET_NAME)) {
				dataset.setName((String) settings.get(WizardDatasetNewPage.DATASET_NAME));
			}

			// If the user specified to use an empty dataset, return the dataset as it
			// is...
			if (settings.containsKey(WizardDatasetNewPage.DATASET_EMPTY)) {
				Boolean b = (Boolean) settings.get(WizardDatasetNewPage.DATASET_EMPTY);
				if (b.booleanValue() == true) {
					return dataset;
				}
			}

			if (settings.containsKey(WizardDataSourcePage.DATASET_QUERY_LANGUAGE)) {
				query.setLanguage((String) settings.get(WizardDataSourcePage.DATASET_QUERY_LANGUAGE));
			}

			if (settings.containsKey(WizardDataSourcePage.DATASET_QUERY_TEXT)) {
				query.setText((String) settings.get(WizardDataSourcePage.DATASET_QUERY_TEXT));
			}

			// Check for fields...
			if (settings.containsKey(WizardDataSourcePage.DISCOVERED_FIELDS)) {
				List<JRDesignField> fields = (List<JRDesignField>) (settings.get(WizardDataSourcePage.DISCOVERED_FIELDS));
				for (JRDesignField f : fields) {
					try {
						dataset.addField(f);
					} catch (JRException ex) {
						// Let's ignore exceptions here, the worst case would be a
						// duplicated fields name not getting
						// in the dataset, situation that should be checked upfront, not
						// now.
						ex.printStackTrace();
					}
				}

			}
		}

		return dataset;
	}

	public JRDesignDataset getDataset() {
		JRDesignDataset ds = createTableDataset(getSettings());
		if (step2 != null && step2.getDataAdapter() != null) {
			// Save the information on the default data adapter
			// to propose selected for this specific dataset
			ds.getPropertiesMap().setProperty(DataQueryAdapters.DEFAULT_DATAADAPTER, step2.getDataAdapter().getName());
		}
		return ds;
	}

	/**
	 * This method drive the logic to just skip steps.
	 * 
	 * The getNextPage method is generally used to get stuff from a page and
	 * configure the next one creating more logic between pages. This logic has
	 * been moved elsewhere: the glue used in JSSWizard is acutally the settings
	 * map, which is passed along the way, since stored inside the wizard. A
	 * mechanism to load and store settings allow the pages to act in a consistent
	 * way without having to put any logic here, even if logic can still be added
	 * in case of special behaviours (just like it would be possible to extend the
	 * relevant pages).
	 * 
	 * An interesting example is the JSSWizardPage and JSSWizardRunnablePage which
	 * provide the base pages to JSS based wizard. In particular the
	 * JSSWizardRunnablePage allows to execute a process on next, which can be
	 * used for time consuming tasks (like read fields).
	 * 
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {

		if (page == step2) {
			if (!getSettings().containsKey(WizardDataSourcePage.DISCOVERED_FIELDS) || ((List<?>) getSettings().get(WizardDataSourcePage.DISCOVERED_FIELDS)).isEmpty()) {
				if (!showCongratulationsStep) {
					// ask for the next page by giving the last page available...
					return super.getNextPage(getPageList().get(getPageList().size() - 1));
				}
				return congratulationsStep;
			}
		}
		return super.getNextPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We
	 * will create an operation and run it using wizard as execution context.
	 */
	@Override
	public boolean performFinish() {

		final String containerName = step1.getContainerFullPath().toPortableString();
		final String fileName = step1.getFileName();

		try {
			getContainer().run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask(Messages.ReportNewWizard_2, IProgressMonitor.UNKNOWN);
					try {
						doFinish(containerName, fileName, monitor);
					} catch (Exception e) {
						UIUtils.showError(e);
					} finally {
						monitor.done();
					}
				}
			});
		} catch (InvocationTargetException e) {
			UIUtils.showError(e.getCause());
		} catch (InterruptedException e) {
			UIUtils.showError(e.getCause());
		}
		return super.performFinish();
	}

	/*
	 * private JRBand getDetail(JRBand[] bands) { for(JRBand band : bands){
	 * band.get } }
	 */

	/**
	 * The worker method. It will find the container, create the file if missing
	 * or just replace its contents, and open the editor on the newly created
	 * file.
	 */
	private void doFinish(String containerName, String fileName, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(Messages.ReportNewWizard_3 + fileName, 2);

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException(String.format(Messages.ReportNewWizard_4, containerName));
		}

		Map<String, Object> templateSettings = new HashMap<String, Object>();

		TemplateBundle templateBundle = step0.getTemplateBundle();

		JRDesignDataset dataset = WizardUtils.createDataset(true, getSettings());

		templateSettings.put(DefaultTemplateEngine.DATASET, dataset);
		if (getSettings().containsKey(WizardDataSourcePage.DATASET_FIELDS)) {
			templateSettings.put(DefaultTemplateEngine.FIELDS, getSettings().get(WizardDataSourcePage.DATASET_FIELDS));
		}

		if (getSettings().containsKey(WizardDataSourcePage.GROUP_FIELDS)) {
			templateSettings.put(DefaultTemplateEngine.GROUP_FIELDS, getSettings().get(WizardDataSourcePage.GROUP_FIELDS));
		}

		templateSettings.put(TableTemplateEngine.TABLE_FIELDS, step3Table.getSelectedFields());

		templateSettings.put(TableTemplateEngine.TABLE_STYLE, step4Table.getSelectedStyle());

		templateSettings.put(TableTemplateEngine.TABLE_SECTIONS, step4Table.getVisibileSections());

		TableTemplateEngine templateEngine = new TableTemplateEngine();
		ByteArrayInputStream stream = null;
		try {

			ReportBundle reportBundle = templateEngine.generateReportBundle(templateBundle, templateSettings);

			// Save the data adapter used...
			if (step2.getDataAdapter() != null) {
				reportBundle.getJasperDesign().setProperty(DataQueryAdapters.DEFAULT_DATAADAPTER, step2.getDataAdapter().getName());
			}

			// Store the report bundle on file system
			IContainer container = (IContainer) resource;
			reportFile = container.getFile(new Path(fileName));
			String repname = reportFile.getName();
			int lindx = repname.lastIndexOf(".");
			if (lindx > 0 && lindx < repname.length() - 1)
				repname = repname.substring(0, lindx);

			reportBundle.getJasperDesign().setName(repname);
			// Save the all the files...
			String contents = JRXmlWriterHelper.writeReport(getConfig(), reportBundle.getJasperDesign(), reportFile, false);

			stream = new ByteArrayInputStream(contents.getBytes());

			if (reportFile.exists()) {
				reportFile.setContents(stream, true, true, monitor);
			} else {
				reportFile.create(stream, true, monitor);
			}
			stream.close();
			saveReportBundleResources(monitor, reportBundle, container);

			monitor.setTaskName(Messages.ReportNewWizard_5);
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					try {
						IDE.openEditor(page, reportFile, true);
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			UIUtils.showError(e);
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private IFile reportFile;

	public IFile getReportFile() {
		return reportFile;
	}

	/**
	 * Store all the resources provided by the report bundle in the same folder as
	 * the new report.
	 * 
	 * @param monitor
	 * @param reportBundle
	 * @param container
	 */
	private void saveReportBundleResources(final IProgressMonitor monitor, ReportBundle reportBundle, IContainer container) {
		monitor.subTask(Messages.ReportNewWizard_6);

		List<String> resourceNames = reportBundle.getResourceNames();

		for (String resourceName : resourceNames) {
			IFile resourceFile = container.getFile(new Path(resourceName));
			InputStream is = null;
			try {
				if (!resourceFile.exists()) {
					is = reportBundle.getResource(resourceName);
					if (is != null) {
						resourceFile.create(is, true, monitor);
					}
				}
			} catch (Exception e) {
				UIUtils.showError(e);
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		monitor.done();
	}

	private void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR, "com.jaspersoft.studio", IStatus.OK, message, null); //$NON-NLS-1$
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if we can initialize
	 * from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		if (selection instanceof StructuredSelection) {
			if (selection.getFirstElement() instanceof IProject || selection.getFirstElement() instanceof IFile || selection.getFirstElement() instanceof IFolder) {
				this.selection = selection;
				return;
			}
			for (Object obj : selection.toList()) {
				if (obj instanceof EditPart) {
					IEditorInput ein = SelectionHelper.getActiveJRXMLEditor().getEditorInput();
					if (ein instanceof FileEditorInput) {
						this.selection = new TreeSelection(new TreePath(new Object[] { ((FileEditorInput) ein).getFile() }));
						return;
					}
				}
			}
			IProgressMonitor progressMonitor = new NullProgressMonitor();
			IProject[] prjs = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			for (IProject p : prjs) {
				try {
					if (p.isAccessible() && p.getNature(JavaCore.NATURE_ID) != null) {
						p.open(progressMonitor);
						this.selection = new TreeSelection(new TreePath(new Object[] { p.getFile(ReportNewWizard.NEW_REPORT_JRXML) }));
						return;
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
			for (IProject p : prjs) {
				try {
					if (p.isAccessible()) {
						p.open(progressMonitor);
						this.selection = new TreeSelection(new TreePath(new Object[] { p.getFile(ReportNewWizard.NEW_REPORT_JRXML) }));
						return;
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
		this.selection = selection;
	}

	/**
	 * We don't want to finish the wizard at "any" time. This methods allows to
	 * decide when we can and when we cannot finish...
	 * 
	 */
	@Override
	public boolean canFinish() {
		if (getContainer().getCurrentPage() == congratulationsStep || getContainer().getCurrentPage() == step4Table)
			return true;
		return false;
	}

}
