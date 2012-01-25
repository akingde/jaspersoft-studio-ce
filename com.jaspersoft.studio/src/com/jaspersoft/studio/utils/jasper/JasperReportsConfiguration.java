package com.jaspersoft.studio.utils.jasper;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.FileResolver;

public class JasperReportsConfiguration {
	private Map<String, Object> params = new HashMap<String, Object>();

	public void put(String key, Object value) {
		params.put(key, value);
	}

	public Object get(String key) {
		return params.get(key);
	}

	private ClassLoader classLoader;
	private FileResolver fileResolver;

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public FileResolver getFileResolver() {
		return fileResolver;
	}

	public void setFileResolver(FileResolver fileResolver) {
		this.fileResolver = fileResolver;
	}

	public static final String KEY_JASPERDESIGN = "JasperDesign";

	public JasperDesign getJasperDesign() {
		return (JasperDesign) get(KEY_JASPERDESIGN);
	}

	public void setJasperDesign(JasperDesign jd) {
		put(KEY_JASPERDESIGN, jd);
	}
}
