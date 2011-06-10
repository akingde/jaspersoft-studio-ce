package com.jaspersoft.studio.data.jrdsprovider;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.provider.DataSourceProviderDataAdapterService;
import net.sf.jasperreports.engine.JRDataSourceProvider;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.design.JRDesignField;

import com.jaspersoft.studio.data.fields.IFieldsProvider;

public class JRDSProviderFieldsProvider implements IFieldsProvider {

	private JRDataSourceProvider jrdsp;

	public boolean supportsGetFieldsOperation() {
		return jrdsp.supportsGetFieldsOperation();
	}

	public List<JRDesignField> getFields(DataAdapterService con,
			JRDataset reportDataset) throws JRException,
			UnsupportedOperationException {
		con.getParameters();
		jrdsp = ((DataSourceProviderDataAdapterService) con).getProvider();

		JRField[] aray = jrdsp.getFields(null);
		if (aray != null) {
			List<JRDesignField> fields = new ArrayList<JRDesignField>();
			for (JRField f : aray)
				fields.add((JRDesignField) f);
			return fields;
		}
		return new ArrayList<JRDesignField>();
	}

}
