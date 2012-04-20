package com.jaspersoft.studio.editor.preview.stats;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class RecordCountScriptlet extends JRDefaultScriptlet {
	private int counter = 0;

	public int getCounter() {
		return counter;
	}

	@Override
	public void afterDetailEval() throws JRScriptletException {
		counter++;
		super.afterDetailEval();
	}
}
