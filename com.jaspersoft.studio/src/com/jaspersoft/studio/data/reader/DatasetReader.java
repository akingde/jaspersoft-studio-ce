/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.reader;

import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.adapter.DataAdapterParameterContributorFactory;
import com.jaspersoft.studio.editor.preview.view.control.ReportController;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.DataAdapterServiceUtil;
import net.sf.jasperreports.data.cache.ColumnDataCacheHandler;
import net.sf.jasperreports.data.cache.DataCacheHandler;
import net.sf.jasperreports.eclipse.builder.JasperReportCompiler;
import net.sf.jasperreports.eclipse.builder.Markers;
import net.sf.jasperreports.eclipse.builder.jdt.JRErrorHandler;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRScriptlet;
import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.ParameterContributorContext;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.JRFiller;
import net.sf.jasperreports.engine.fill.ReportFiller;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * Reader class for generic dataset.
 * <p>
 * Example of usage: data preview in dataset dialog.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class DatasetReader {

	// Data adapter descriptor
	private DataAdapterDescriptor dataAdapterDesc;
	// List of columns that will be used for data preview
	private List<String> columns;
	// Allows to get parameters, fields, query (language and text)
	// and other properties
	private JRDesignDataset designDataset;
	// Max records num
	private int maxRecords = -1;
	// Listeners for dataset reading
	// They will receive event notification for example by a scriptlet
	private List<DatasetReaderListener> listeners;
	// Status flag that lets you know if a report is running
	private boolean running;

	public DatasetReader() {
		listeners = new ArrayList<>();
	}

	public static JasperDesign getJasperDesign(JasperReportsConfiguration jConfig) throws IOException, JRException {
		String reportLocation = JaspersoftStudioPlugin.getInstance()
				.getFileLocation(DataPreviewScriptlet.PREVIEW_REPORT_PATH);
		try (FileInputStream is = new FileInputStream(reportLocation);) {
			return JRXmlLoader.load(jConfig, is);
		}
	}

	public static void setupDataset(JasperDesign dataJD, JRDesignDataset designDataset,
			JasperReportsConfiguration jConfig, List<String> columns) throws JRException {
		// 2. Set query information
		JRDesignQuery query = new JRDesignQuery();
		if (designDataset.getQuery() != null) {
			query.setLanguage(designDataset.getQuery().getLanguage());
			query.setText(designDataset.getQuery().getText());
		}
		dataJD.setQuery(query);
		// and the report language to the actual report one
		dataJD.setLanguage(jConfig.getJasperDesign().getLanguage());
		dataJD.setFilterExpression(designDataset.getFilterExpression());
		// 3. Replace properties map
		ModelUtils.replacePropertiesMap(jConfig.getJasperDesign().getPropertiesMap(), dataJD.getPropertiesMap());

		// 4. Set "standard" parameters
		List<JRParameter> parametersList = designDataset.getParametersList();
		for (JRParameter param : parametersList) {
			if (param.isSystemDefined())
				continue;
			if (dataJD.getParametersMap().containsKey(param.getName())) {
				dataJD.removeParameter(param.getName());
			}
			dataJD.addParameter(param);
		}

		// 5. Add the fields
		if (!columns.isEmpty()) {
			// Clear "dirty" fields
			dataJD.getFieldsList().clear();
			dataJD.getFieldsMap().clear();
			//
			JRField[] fields = designDataset.getFields();
			for (JRField f : fields) {
				dataJD.addField(f);
			}
		}
		// 5.a Add the variables
		// Clear "dirty" variables
		dataJD.getVariablesList().clear();
		dataJD.getVariablesMap().clear();

		// 5.b add groups
		dataJD.getGroupsList().clear();
		dataJD.getMainDesignDataset().getGroupsMap().clear();
		for (JRGroup sf : designDataset.getGroupsList()) {
			JRDesignGroup gr = new JRDesignGroup();
			gr.setName(sf.getName());
			dataJD.addGroup(gr);
		}

		// 5.c add the variables, ignore duplicates
		JRVariable[] variables = designDataset.getVariables();
		for (JRVariable f : variables)
			try {
				JRDesignVariable v = new JRDesignVariable();
				v.setName(f.getName());
				v.setSystemDefined(f.isSystemDefined());
				v.setValueClassName(f.getValueClassName());
				v.setCalculation(f.getCalculationValue());
				v.setExpression(f.getExpression());
				v.setInitialValueExpression(f.getInitialValueExpression());

				v.setIncrementerFactoryClassName(f.getIncrementerFactoryClassName());
				v.setIncrementType(f.getIncrementTypeValue());
				if (f.getIncrementGroup() != null)
					v.setIncrementGroup(dataJD.getGroupsMap().get(f.getIncrementGroup().getName()));

				v.setResetType(f.getResetTypeValue());
				if (f.getResetGroup() != null)
					v.setResetGroup(dataJD.getGroupsMap().get(f.getResetGroup().getName()));

				dataJD.addVariable(v);
			} catch (JRException e) {
				// it's possible we'll have some duplicates from groups
				if (!e.getMessageKey().equals(JRDesignDataset.EXCEPTION_MESSAGE_KEY_DUPLICATE_VARIABLE))
					e.printStackTrace();
			}

		// 6. add sort fields
		dataJD.getSortFieldsList().clear();
		dataJD.getMainDesignDataset().getSortFieldsMap().clear();
		for (JRSortField sf : designDataset.getSortFieldsList())
			dataJD.addSortField(sf);

		// 6.b add scriptlets
		dataJD.getScriptletsList().clear();
		dataJD.getMainDesignDataset().getScriptletsMap().clear();
		for (JRScriptlet sf : designDataset.getScriptletsList())
			dataJD.addScriptlet(sf);
	}

	public static JasperReport compile(JasperReportsConfiguration jConfig, JasperDesign dataJD,
			IProgressMonitor monitor) throws CoreException, JRException {
		JasperReport jrobj = null;
		IFile f = (IFile) jConfig.get(FileUtils.KEY_FILE);
		if (f != null) {
			Markers.deleteMarkers(f);
			JasperReportCompiler compiler = new JasperReportCompiler();
			compiler.setErrorHandler(new JRErrorHandler(f));
			compiler.setProject(f.getProject());
			jrobj = compiler.compileReport(jConfig, dataJD, monitor);
			if (jrobj == null) {
				IMarker[] markers = f.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
				if (!Misc.isNullOrEmpty(markers)) {
					Set<String> set = new LinkedHashSet<>();
					for (IMarker m : markers)
						set.add(m.getAttribute(IMarker.MESSAGE).toString());
					StringBuilder str = new StringBuilder("");
					for (String s : set)
						str.append(s + "\n");
					UIUtils.showError(new Exception(str.toString()));
				}
				return null;
			}
		} else
			jrobj = JasperCompileManager.getInstance(jConfig).compile(dataJD);
		return jrobj;
	}

	public static Map<String, Object> prepareParameters(JasperReportsConfiguration jConfig, int maxRecords) {
		Map<String, Object> hm = new HashMap<>();
		if (jConfig.getJRParameters() != null) {
			// add also prompting parameters that may have previously
			// set during a preview phase. This way we can get a more
			// likely "default" value.
			Map<String, Object> existing = jConfig.getJRParameters();
			if (existing != null)
				hm.putAll(existing);
		}
		hm = ReportController.resetParameters(hm, jConfig);
		if (maxRecords > 0) {
			hm.put(JRDesignParameter.REPORT_MAX_COUNT, maxRecords);
		} else {
			hm.remove(JRDesignParameter.REPORT_MAX_COUNT);
		}
		return hm;
	}

	private ReportFiller rf;

	public JasperPrint fillReport(JasperReportsConfiguration jConfig, JRDesignDataset designDataset,
			DataAdapterDescriptor dataAdapterDesc, JasperReport jrobj, Map<String, Object> hm) throws JRException {
		DataAdapterService das = null;
		try {
			if (dataAdapterDesc != null) {
				hm.put(DataAdapterParameterContributorFactory.PARAMETER_DATA_ADAPTER, dataAdapterDesc.getDataAdapter());
				ReportContext rc = (ReportContext) hm.get(JRParameter.REPORT_CONTEXT);
				if (rc != null) {
					Object obj = rc.getParameterValue(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER);
					if (obj == null || (obj instanceof ColumnDataCacheHandler
							&& !((ColumnDataCacheHandler) obj).isSnapshotPopulated())) {
						das = DataAdapterServiceUtil
								.getInstance(new ParameterContributorContext(jConfig, designDataset, hm))
								.getService(dataAdapterDesc.getDataAdapter());
						das.contributeParameters(hm);
					}
				}
			}
			ModelUtils.replacePropertiesMap(designDataset.getPropertiesMap(),
					jrobj.getMainDataset().getPropertiesMap());

			JaspersoftStudioPlugin.getExtensionManager().onRun(jConfig, jrobj, hm);

			// 9. Fill the report

			rf = JRFiller.createReportFiller(jConfig, jrobj);

			return rf.fill(hm);
		} finally {
			if (das != null)
				das.dispose();
		}
	}

	/**
	 * Executes the task for the dataset reading task.
	 * <p>
	 * The following sequential steps are performed:
	 * <ol>
	 * <li>loading the jasper design for the custom data preview report;</li>
	 * <li>setting the query information and report language</li>
	 * <li>replacing properties map</li>
	 * <li>adding the standard parameters and custom ones</li>
	 * <li>adding the fields</li>
	 * <li>compiling the report obtaining a jasper report object</li>
	 * <li>setting the parameters (including data adapter contributed ones)
	 * map</li>
	 * <li>filling the report</li>
	 * </ol>
	 * 
	 * @param jConfig the configuration instance
	 */
	public void start(JasperReportsConfiguration jConfig, IProgressMonitor monitor) {
		// Temporary replace the class loader to get the "report" one.
		// This is necessary for example to load JDBC drivers or additional
		// classes that are in the classpath of the JasperReports project
		// containing the report.
		ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(jConfig.getClassLoader());
		ReportContext rc = null;
		Map<String, Object> hm = null;
		try {
			running = true;
			// 1. Load JD from custom data preview report
			JasperDesign dataJD = getJasperDesign(jConfig);

			setupDataset(dataJD, designDataset, jConfig, columns);
			// and add the custom ones
			JRDesignParameter pColumns = new JRDesignParameter();
			pColumns.setName(DataPreviewScriptlet.PARAM_COLUMNS);
			pColumns.setValueClass(List.class);
			dataJD.addParameter(pColumns);
			JRDesignParameter pListeners = new JRDesignParameter();
			pListeners.setName(DataPreviewScriptlet.PARAM_LISTENERS);
			pListeners.setValueClass(List.class);
			dataJD.addParameter(pListeners);

			// 6. Compile report
			JasperReport jrobj = compile(jConfig, dataJD, monitor);
			if (jrobj == null)
				return;

			// 7. Prepare parameters
			hm = prepareParameters(jConfig, maxRecords);
			if (recalcParameters) {
				ExpressionUtil.initBuiltInParameters(jConfig, jrobj);
				recalcParameters = false;
			}
			hm.put(DataPreviewScriptlet.PARAM_COLUMNS, columns);
			hm.put(DataPreviewScriptlet.PARAM_LISTENERS, listeners);
			rc = (ReportContext) hm.get(JRParameter.REPORT_CONTEXT);
			if (rc != null)
				hm.remove(JRParameter.REPORT_CONTEXT);

			// 8. Contribute parameters from the data adapter
			fillReport(jConfig, designDataset, dataAdapterDesc, jrobj, hm);
		} catch (DataPreviewInterruptedException e) {
			e.printStackTrace();
			// DO NOTHING
			// This exception should occur only when
			// a stop on the reading has been invoked.
		} catch (Exception e) {
			UIUtils.showError(e);
		} finally {
			if (hm != null && rc != null)
				hm.put(JRParameter.REPORT_CONTEXT, rc);
			running = false;
			for (DatasetReaderListener l : listeners) {
				l.finished();
			}
			Thread.currentThread().setContextClassLoader(originalClassLoader);
		}
	}

	/**
	 * Ends the dataset reading task.
	 */
	public void stop() {
		if (running) {
			if (rf != null)
				try {
					rf.cancelFill();
				} catch (JRException e) {
					e.printStackTrace();
				}
			for (DatasetReaderListener l : listeners) {
				// Invalidating the listener will cause the running scriptlet
				// to launch a JRScriptletException, that will abort the running
				// report.
				l.invalidate();
			}
		}
	}

	/* Getters and setters */

	public DataAdapterDescriptor getDataAdapterDescriptor() {
		return dataAdapterDesc;
	}

	public void setDataAdapterDescriptor(DataAdapterDescriptor dataAdapterDesc) {
		this.dataAdapterDesc = dataAdapterDesc;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public JRDesignDataset getDesignDataset() {
		return designDataset;
	}

	private boolean recalcParameters = false;

	public void setDesignDataset(JRDesignDataset designDataset) {
		this.designDataset = designDataset;

		designDataset.getEventSupport().addPropertyChangeListener(evt -> {
			if (evt.getPropertyName().equals(JRDesignDataset.PROPERTY_PARAMETERS)) {
				recalcParameters = true;
				listenParameters();
			}
		});

		listenParameters();
	}

	private void listenParameters() {
		for (JRParameter p : designDataset.getParameters()) {
			((JRDesignParameter) p).getEventSupport().removePropertyChangeListener(dveListener);
			((JRDesignParameter) p).getEventSupport().addPropertyChangeListener(dveListener);
		}
	}

	private PropertyChangeListener dveListener = evt -> {
		if (evt.getPropertyName().equals(JRDesignParameter.PROPERTY_DEFAULT_VALUE_EXPRESSION))
			recalcParameters = true;
	};

	public int getMaxRecords() {
		return maxRecords;
	}

	public void setMaxRecords(int maxRecords) {
		this.maxRecords = maxRecords;
	}

	/* Listener methods */

	/**
	 * Adds a new {@link DatasetReaderListener} to the list of listeners that
	 * will be notified when a read event on the dataset occurs.
	 * 
	 * @param listener the listener to add
	 */
	public void addDatasetReaderListener(DatasetReaderListener listener) {
		listeners.add(listener);
	}

	/**
	 * Removes the specified {@link DatasetReaderListener} from the list of
	 * listeners that will be notified when a read event on the dataset occurs.
	 * 
	 * @param listener the listener to remove
	 */
	public void removeDatasetReaderListener(DatasetReaderListener listener) {
		listeners.remove(listener);
	}

	/**
	 * @return <code>true</code> if the dataset reader is running,
	 * <code>false</code> otherwise
	 */
	public boolean isRunning() {
		return this.running;
	}

}
