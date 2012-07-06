package com.jaspersoft.studio.editor.layout;

import net.sf.jasperreports.engine.design.JRDesignElement;

public class LayoutManager {
	public static ILayout getLayout(JRDesignElement element) {
		ILayout layout = new HorizontalRowLayout();
		String prop = element.getPropertiesMap().getProperty(ILayout.KEY);
		if (prop != null) {
			try {
				layout = (ILayout) Class.forName(prop).newInstance();
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			} catch (ClassNotFoundException e) {
			}
		}
		return layout;
	}
}
