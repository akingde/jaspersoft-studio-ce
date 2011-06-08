package com.jaspersoft.studio.data.fields;

import java.util.List;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

public interface IFieldsProvider {

	public boolean supportsGetFieldsOperation();

	/**
	 * Returns the fields that are available from a query of a specific language The provider can use the passed in report
	 * to extract some additional configuration information such as report properties. The IReportConnection object can be
	 * used to execute the query.
	 * 
	 * @param con
	 *          the DataAdapter
	 * @param the
	 *          JRDataset that will be filled using the data source created by this provider. The passed in report can be
	 *          null. That means that no compiled report is available yet.
	 * @param parameters
	 *          map containing the interpreted default value of each parameter
	 * @throws UnsupportedOperationException
	 *           is the method is not supported
	 * @throws JRException
	 *           if an error occurs.
	 */
	public List<JRDesignField> getFields(DataAdapterService con, JRDataset reportDataset) throws JRException,
			UnsupportedOperationException;

}
