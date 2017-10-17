/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.report.util;

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.studio.property.metadata.PropertyMetadataRegistry;

import net.sf.jasperreports.annotations.properties.PropertyScope;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.properties.PropertyMetadata;
import net.sf.jasperreports.properties.StandardPropertyMetadata;

public class PHolderUtil {
	public static final String COM_JASPERSOFT_STUDIO_REPORT_DESCRIPTION = "com.jaspersoft.studio.report.description";
	public static final String COM_JASPERSOFT_STUDIO_UNIT = "com.jaspersoft.studio.unit.";

	public static String getUnit(JRPropertiesHolder pholder, String prop, String def) {
		return Misc.nvl(pholder.getPropertiesMap().getProperty(COM_JASPERSOFT_STUDIO_UNIT + prop), def);
	}

	public static boolean setProperty(boolean anychanges, JRPropertiesMap pmap, String prop, String unit,
			String defUnit) {
		String pname = COM_JASPERSOFT_STUDIO_UNIT + prop;
		String p = pmap.getProperty(pname);
		if (p != null && defUnit != null && defUnit.equals(unit)) {
			pmap.removeProperty(pname);
			return true;
		}
		if ((p != null && !p.equals(unit)) || (p == null && unit != null)) {
			pmap.setProperty(pname, unit);
			return true;
		}
		if (p != null && unit == null) {
			pmap.removeProperty(pname);
			return true;
		}
		return anychanges;
	}

	public static void initMetadata() {
		List<PropertyMetadata> pm = new ArrayList<PropertyMetadata>();

		StandardPropertyMetadata spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + "x");
		spm.setLabel("Unit for x");
		spm.setDescription("Unit for x");
		spm.setValueType(String.class.getName());
		List<PropertyScope> scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.ELEMENT);
		scopes.add(PropertyScope.FRAME);
		scopes.add(PropertyScope.BAND);
		scopes.add(PropertyScope.CROSSTAB_CELL);
		scopes.add(PropertyScope.TABLE_CELL);
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:units");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + "y");
		spm.setLabel("Unit for y");
		spm.setDescription("Unit for y");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.ELEMENT);
		scopes.add(PropertyScope.FRAME);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:units");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + "width");
		spm.setLabel("Unit for width");
		spm.setDescription("Unit for width");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.ELEMENT);
		scopes.add(PropertyScope.FRAME);
		scopes.add(PropertyScope.CROSSTAB_CELL);
		scopes.add(PropertyScope.TABLE_CELL);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:units");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + "height");
		spm.setLabel("Unit for height");
		spm.setDescription("Unit for height");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.ELEMENT);
		scopes.add(PropertyScope.FRAME);
		scopes.add(PropertyScope.BAND);
		scopes.add(PropertyScope.CROSSTAB_CELL);
		scopes.add(PropertyScope.TABLE_CELL);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:units");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + "pageHeight");
		spm.setLabel("Unit for page height");
		spm.setDescription("Unit for page height");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:units");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + "pageWidth");
		spm.setLabel("Unit for page width");
		spm.setDescription("Unit for page width");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:units");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + "topMargin");
		spm.setLabel("Unit for page top margin");
		spm.setDescription("Unit for page top margin");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:units");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + "bottomMargin");
		spm.setLabel("Unit for page bottom margin");
		spm.setDescription("Unit for page bottom margin");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:units");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + "leftMargin");
		spm.setLabel("Unit for page left margin");
		spm.setDescription("Unit for page left margin");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:units");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + "rightMargin");
		spm.setLabel("Unit for page right margin");
		spm.setDescription("Unit for page right margin");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:units");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + "columnWidth");
		spm.setLabel("Unit for page column width");
		spm.setDescription("Unit for page column width");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:units");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + "columnSpacing");
		spm.setLabel("Unit for page column spacing");
		spm.setDescription("Unit for page column spacing");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:units");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_REPORT_DESCRIPTION);
		spm.setLabel("Description");
		spm.setDescription("Report description.");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		pm.add(spm);

		PropertyMetadataRegistry.addMetadata(pm);
	}
}
