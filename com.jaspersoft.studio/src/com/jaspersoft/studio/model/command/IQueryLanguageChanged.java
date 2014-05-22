package com.jaspersoft.studio.model.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;

public interface IQueryLanguageChanged {
	public void syncDataset(JRDesignDataset ds, String oldLang, String newLang) throws JRException;
}
