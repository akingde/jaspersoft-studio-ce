/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.report.util;

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.metadata.PropertyMetadataRegistry;

import net.sf.jasperreports.annotations.properties.PropertyScope;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.properties.PropertyMetadata;
import net.sf.jasperreports.properties.StandardPropertyMetadata;

public class PHolderUtil {
	private static final String COM_JASPERSOFT_STUDIO_DESIGNER_UNITS = "com.jaspersoft.studio.designer:units"; //$NON-NLS-1$

	private PHolderUtil() {
	}

	public static final String COM_JASPERSOFT_STUDIO_REPORT_DESCRIPTION = "com.jaspersoft.studio.report.description"; //$NON-NLS-1$
	public static final String COM_JASPERSOFT_STUDIO_UNIT = "com.jaspersoft.studio.unit."; //$NON-NLS-1$

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
		if (p != null) {
			pmap.removeProperty(pname);
			return true;
		}
		return anychanges;
	}

	public static void initMetadata() {
		List<PropertyMetadata> pm = new ArrayList<>();

		StandardPropertyMetadata spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + "x"); //$NON-NLS-1$
		spm.setLabel(Messages.PHolderUtil_4);
		spm.setDescription(Messages.PHolderUtil_4);
		spm.setValueType(String.class.getName());
		List<PropertyScope> scopes = new ArrayList<>();
		scopes.add(PropertyScope.ELEMENT);
		scopes.add(PropertyScope.FRAME);
		scopes.add(PropertyScope.BAND);
		scopes.add(PropertyScope.CROSSTAB_CELL);
		scopes.add(PropertyScope.TABLE_CELL);
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory(COM_JASPERSOFT_STUDIO_DESIGNER_UNITS);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + "y"); //$NON-NLS-1$
		spm.setLabel(Messages.PHolderUtil_7);
		spm.setDescription(Messages.PHolderUtil_7);
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<>();
		scopes.add(PropertyScope.ELEMENT);
		scopes.add(PropertyScope.FRAME);
		spm.setScopes(scopes);
		spm.setCategory(COM_JASPERSOFT_STUDIO_DESIGNER_UNITS);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + Messages.PHolderUtil_9);
		spm.setLabel(Messages.PHolderUtil_10);
		spm.setDescription(Messages.PHolderUtil_10);
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<>();
		scopes.add(PropertyScope.ELEMENT);
		scopes.add(PropertyScope.FRAME);
		scopes.add(PropertyScope.CROSSTAB_CELL);
		scopes.add(PropertyScope.TABLE_CELL);
		spm.setScopes(scopes);
		spm.setCategory(COM_JASPERSOFT_STUDIO_DESIGNER_UNITS);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + Messages.PHolderUtil_12);
		spm.setLabel(Messages.PHolderUtil_13);
		spm.setDescription(Messages.PHolderUtil_13);
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<>();
		scopes.add(PropertyScope.ELEMENT);
		scopes.add(PropertyScope.FRAME);
		scopes.add(PropertyScope.BAND);
		scopes.add(PropertyScope.CROSSTAB_CELL);
		scopes.add(PropertyScope.TABLE_CELL);
		spm.setScopes(scopes);
		spm.setCategory(COM_JASPERSOFT_STUDIO_DESIGNER_UNITS);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + Messages.PHolderUtil_15);
		spm.setLabel(Messages.PHolderUtil_16);
		spm.setDescription(Messages.PHolderUtil_16);
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory(COM_JASPERSOFT_STUDIO_DESIGNER_UNITS);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + Messages.PHolderUtil_18);
		spm.setLabel(Messages.PHolderUtil_19);
		spm.setDescription(Messages.PHolderUtil_19);
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory(COM_JASPERSOFT_STUDIO_DESIGNER_UNITS);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + Messages.PHolderUtil_21);
		spm.setLabel(Messages.PHolderUtil_22);
		spm.setDescription(Messages.PHolderUtil_22);
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory(COM_JASPERSOFT_STUDIO_DESIGNER_UNITS);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + Messages.PHolderUtil_24);
		spm.setLabel(Messages.PHolderUtil_25);
		spm.setDescription(Messages.PHolderUtil_25);
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory(COM_JASPERSOFT_STUDIO_DESIGNER_UNITS);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + Messages.PHolderUtil_27);
		spm.setLabel(Messages.PHolderUtil_28);
		spm.setDescription(Messages.PHolderUtil_28);
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory(COM_JASPERSOFT_STUDIO_DESIGNER_UNITS);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + Messages.PHolderUtil_30);
		spm.setLabel(Messages.PHolderUtil_31);
		spm.setDescription(Messages.PHolderUtil_31);
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory(COM_JASPERSOFT_STUDIO_DESIGNER_UNITS);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + Messages.PHolderUtil_33);
		spm.setLabel(Messages.PHolderUtil_34);
		spm.setDescription(Messages.PHolderUtil_34);
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory(COM_JASPERSOFT_STUDIO_DESIGNER_UNITS);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_UNIT + Messages.PHolderUtil_36);
		spm.setLabel(Messages.PHolderUtil_37);
		spm.setDescription(Messages.PHolderUtil_37);
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory(COM_JASPERSOFT_STUDIO_DESIGNER_UNITS);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_REPORT_DESCRIPTION);
		spm.setLabel(Messages.PHolderUtil_39);
		spm.setDescription(Messages.PHolderUtil_40);
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(MGraphicElement.PROPERTY_ELEMENT_NAME); //$NON-NLS-1$
		spm.setLabel(Messages.PHolderUtil_1);
		spm.setDescription("Element label that will be used in outline and other views");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<>();
		scopes.add(PropertyScope.ELEMENT);
		spm.setScopes(scopes);
		pm.add(spm);

		PropertyMetadataRegistry.addMetadata(pm);
	}
}
