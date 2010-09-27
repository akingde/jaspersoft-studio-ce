package com.jaspersoft.studio.editor;

import java.io.InputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.xml.JRPrintXmlLoader;

public class JRPxmlEditor extends JRPrintEditor {

	@Override
	protected JasperPrint loadJRObject(InputStream in) throws JRException {
		return (JasperPrint) JRPrintXmlLoader.load(in);
	}
}
