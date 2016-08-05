package com.jaspersoft.studio.data;

import java.io.IOException;

import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.w3c.tools.codec.Base64Decoder;
import org.w3c.tools.codec.Base64Encoder;
import org.w3c.tools.codec.Base64FormatException;

import com.jaspersoft.studio.prm.ParameterSet;
import com.jaspersoft.studio.prm.ParameterSetProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignParameter;

public class HttpBuiltInParameterProvider {
	public static final String PARAMETERSET_HTTP = "Http Data Adapters Built In Parameters";

	public static void init() {
		ScopedPreferenceStore pstore = JasperReportsConfiguration.getDefaultInstance().getPrefStore();
		ParameterSet pset = ParameterSetProvider.getParameterSet(PARAMETERSET_HTTP, pstore);
		if (pset == null) {
			pset = new ParameterSet();
			pset.setName(PARAMETERSET_HTTP);
			pset.setBuiltIn(true);

			JRDesignParameter p = new JRDesignParameter();
			p.setName("HTTP_DATA_URL");
			p.setDescription("URL To get file from");
			p.setValueClassName("java.lang.String");
			p.setDefaultValueExpression(new JRDesignExpression("\"http://someurl.com\""));
			p.setForPrompting(false);

			pset.getParameters().add(p);

			p = new JRDesignParameter();
			p.setName("HTTP_DATA_USERNAME");
			p.setDescription("User name");
			p.setValueClassName("java.lang.String");
			p.setDefaultValueExpression(new JRDesignExpression("\"user\""));
			p.setForPrompting(false);

			pset.getParameters().add(p);

			p = new JRDesignParameter();
			p.setName("HTTP_DATA_PASSWORD");
			p.setDescription("Password");
			p.setValueClassName("java.lang.String");
			p.setDefaultValueExpression(new JRDesignExpression("\"pass\""));
			p.setForPrompting(false);

			pset.getParameters().add(p);

			p = new JRDesignParameter();
			p.setName("HTTP_DATA_URL_PARAMETER_");
			p.setDescription("URL Parameter prefix");
			p.setValueClassName("java.lang.String");
			p.setDefaultValueExpression(new JRDesignExpression("\"prm\""));
			p.setForPrompting(false);

			pset.getParameters().add(p);

			p = new JRDesignParameter();
			p.setName("HTTP_DATA_POST_PARAMETER_");
			p.setDescription("POST request parameter prefix");
			p.setValueClassName("java.lang.String");
			p.setDefaultValueExpression(new JRDesignExpression("\"prm\""));
			p.setForPrompting(false);

			pset.getParameters().add(p);

			p = new JRDesignParameter();
			p.setName("HTTP_DATA_HEADER_PARAMETER_");
			p.setDescription("Http Header parameter prefix");
			p.setValueClassName("java.lang.String");
			p.setDefaultValueExpression(new JRDesignExpression("\"value\""));
			p.setForPrompting(false);

			pset.getParameters().add(p);

			ParameterSetProvider.storeParameterSet(pset, pstore);
		}
		try {
			String str = pstore.getString(ParameterSet.PARAMETER_SETS);
			if (str != null) {
				try {
					str = new Base64Decoder(str).processString();
				} catch (Base64FormatException e) {
					e.printStackTrace();
					return;
				}
				String[] sets = str.split("\n");
				for (String key : sets) {
					if (key.equals(PARAMETERSET_HTTP))
						return;
				}
			}
			str = PARAMETERSET_HTTP + (str == null ? "" : "\n" + str);
			pstore.setValue(ParameterSet.PARAMETER_SETS, new Base64Encoder(str).processString());
		} finally {
			try {
				pstore.save();
			} catch (IOException e) {
				Activator.getDefault().logError(e);
			}
		}
	}
}
