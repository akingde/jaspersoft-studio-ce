package com.jaspersoft.studio.editor.preview.jive;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import com.jaspersoft.studio.utils.UIUtils;

public class JettyUtil {
	
	private static final String METHOD_NAME = "getInstance";
	private static final String PROPERTY_NAME = "utilityClass";
	private static final String JIVE_JETTYUTIL_PROPERTIES_FILE = "com/jaspersoft/studio/editor/preview/jive/jetty.properties";
	private static JiveJettyUtilitiesProvider jettyUtil=null;
	
	public static JiveJettyUtilitiesProvider getJettyUtilInstance(){
		if(jettyUtil==null) {
			try {
				InputStream propFileStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(JIVE_JETTYUTIL_PROPERTIES_FILE);
				Properties props=new Properties();
				props.load(propFileStream);
				String className = (String) props.get(PROPERTY_NAME);
				Class<?> clazz=Class.forName(className);
				Method instanceMethod=clazz.getDeclaredMethod(METHOD_NAME);
				jettyUtil=(JiveJettyUtilitiesProvider) instanceMethod.invoke(null, new Object[0]);
				propFileStream.close();
			} catch (Exception e) {
				UIUtils.showError(e);
			}
		}
		return jettyUtil;
	}
	
}
