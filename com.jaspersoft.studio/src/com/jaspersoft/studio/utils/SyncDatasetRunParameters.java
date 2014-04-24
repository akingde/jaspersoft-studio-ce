package com.jaspersoft.studio.utils;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

public class SyncDatasetRunParameters {
	public static void sync(JasperDesign jd) {
		String mlang = jd.getMainDataset().getQuery().getLanguage();
		for (JRDataset subds : jd.getDatasetsList())
			if (subds.getQuery() != null && mlang.equals(subds.getQuery().getLanguage())) {
				// find query executer, look if there are built-in parameters
				// if yes
				// find all datasetrun that point to subdataset

			}
	}
}
