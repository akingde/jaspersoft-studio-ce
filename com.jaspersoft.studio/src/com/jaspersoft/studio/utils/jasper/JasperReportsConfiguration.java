package com.jaspersoft.studio.utils.jasper;

import net.sf.jasperreports.engine.util.FileResolver;

public class JasperReportsConfiguration {
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

}
