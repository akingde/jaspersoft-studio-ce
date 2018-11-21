/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.subreport.returnvalue.dialog;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRSubreport;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlDigesterFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.repo.RepositoryContext;
import net.sf.jasperreports.repo.RepositoryUtil;
import net.sf.jasperreports.repo.SimpleRepositoryContext;
import net.sf.jasperreports.repo.SimpleRepositoryResourceContext;

import org.eclipse.core.resources.IFile;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.JReportsDTO;
import com.jaspersoft.studio.property.descriptor.returnvalue.RVPropertyPage;
import com.jaspersoft.studio.property.descriptor.returnvalue.ReturnValueContainer;

/**
 * Return values configuration page for a subreport element element
 * 
 * @author Orlandin Marco
 *
 */
public class SubreportRVPropertyPage extends RVPropertyPage {

	/**
	 * The dto of the subreport. The dto is essentially a container
	 * for the return values and for the subreport itself
	 */
	private JReportsDTO dto;
	
	protected SubreportRVPropertyPage() {
		super("subreportproperties", null);
		setTitle(Messages.RVPropertyPage_subreport_return_values);
		setDescription(Messages.RVPropertyPage_description);
	}
	
	/**
	 * Return the actual dto with the updated return values taken
	 * from the dialog
	 * 
	 * @return a dto with the last valid return values
	 */
	public JReportsDTO getDto() {
		dto.setValue(ReturnValueContainer.convertToSubreport(getValue()));
		return dto;
	}
	
	/**
	 * Set the current dto and initialize the table showing the return values
	 * with the one read from the dto
	 * 
	 * @param dto a not null dto
	 */
	public void setDto(JReportsDTO dto) {
		this.dto = dto;
		design = dto.getjConfig().getJasperDesign();
		values = ReturnValueContainer.convertFromSubreportReturn(dto.getValue());
		toVariables = null;
	}
	
	/**
	 * Return the list of jrvariables defined for the subreport, they are
	 * readed from the main dataset
	 */
	@Override
	public JRVariable[] getDatasetVariables() {
		List<JRVariable> vlist = dto.getjConfig().getJasperDesign().getVariablesList();
		return vlist.toArray(new JRVariable[vlist.size()]);
	}
	
	/**
	 * Get an array of all the variables that can be used as to variable. These are variables defined for the element on
	 * its dataset that aren't already used as to variable. The context of the variable is the neares dataset in the 
	 * hierarchy 
	 * 
	 * @return a not null array of variable names that can be used as to variables
	 */
	@Override
	protected String[] getToVariablesNames() {
		if (toVariables == null) {
			List<String> res = new ArrayList<String>();
			HashSet<String> usedVariables = getAlreadyUsedToVariables();
			JRVariable[] vlist = null;
			if (dto.getDataset() != null && dto.getDataset().getVariables() != null) {
				vlist = dto.getDataset().getVariables();
			} else {
				vlist = getMainDatasetVariables();
			}
			for (JRVariable o : vlist) {
				JRDesignVariable jdVar = (JRDesignVariable) o;
				if (!jdVar.isSystemDefined() && !usedVariables.contains(jdVar.getName()))
					res.add(jdVar.getName());
			}
			return res.toArray(new String[res.size()]);
		}
		return toVariables;
	}


	private void getSubreport() {
		JRSubreport sr = (JRSubreport) dto.getSubreport();
		if (sr.getExpression() != null) {
			String path = sr.getExpression().getText();
			path = path.replace("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$

			JRBaseReport jd = getFromJRXML(path.replaceAll("\\.jasper$", ".jrxml"));
			if (jd == null)
				jd = getFromJasper(path);
		}
	}

	private JRBaseReport getFromJasper(String path) {
		InputStream in = null;
		JRBaseReport jd = null;
		try {
			IFile file = (IFile) dto.getjConfig().get(FileUtils.KEY_FILE);
			String parentPath = file.getParent().getLocation().toFile().getAbsolutePath();
			SimpleRepositoryResourceContext context = SimpleRepositoryResourceContext.of(parentPath);
			RepositoryContext repoContext = SimpleRepositoryContext.of(dto.getjConfig(), context);
			in = RepositoryUtil.getInstance(repoContext).getInputStreamFromLocation(path);
			if (in != null) {
				Object obj = JRLoader.loadObject(in);
				if (obj instanceof JasperReport)
					jd = (JasperReport) obj;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			FileUtils.closeStream(in);
		}
		return jd;
	}
	

	private JRBaseReport getFromJRXML(String path) {
		InputStream in = null;
		JRBaseReport jd = null;
		try {
			IFile file = (IFile) dto.getjConfig().get(FileUtils.KEY_FILE);
			String parentPath = file.getParent().getLocation().toFile().getAbsolutePath();
			SimpleRepositoryResourceContext context = SimpleRepositoryResourceContext.of(parentPath);
			RepositoryContext repoContext = SimpleRepositoryContext.of(dto.getjConfig(), context);
			in = RepositoryUtil.getInstance(repoContext).getInputStreamFromLocation(path);
			if (in != null) {
				InputSource is = new InputSource(new InputStreamReader(in, FileUtils.UTF8_ENCODING));
				jd = new JRXmlLoader(dto.getjConfig(), JRXmlDigesterFactory.createDigester(dto.getjConfig())).loadXML(is);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			FileUtils.closeStream(in);
		}
		return jd;
	}
	
	@Override
	public void setValue(List<ReturnValueContainer> value) {
		super.setValue(value);
		getSubreport();
	}
	
	@Override
	protected void fillTable() {
		super.fillTable();
		getSubreport();
	}
}
