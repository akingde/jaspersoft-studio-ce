/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.properties.dialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PropertiesList {

	private static List<PropertyDTO> props;
	private static String[] names;
	private static String[] sortedProperties;

	public static String[] getPropertiesNames() {
		if (names != null)
			return names;
		getJRProperties();
		String[] res = new String[props.size()];
		for (int i = 0; i < props.size(); i++)
			res[i] = props.get(i).getName();
		names = res;
		return res;
	}

	public static String[] getSortedProperitesNames() {
		if (sortedProperties != null)
			return sortedProperties;
		getJRProperties();
		List<String> res = new ArrayList<>();
		HashSet<String> alreadyAdded = new HashSet<>();
		for (int i = 0; i < props.size(); i++) {
			String actualProp = props.get(i).getName();
			if (!alreadyAdded.contains(actualProp)) {
				alreadyAdded.add(actualProp);
				res.add(actualProp);
			}
		}
		sortedProperties = res.toArray(new String[res.size()]);
		return sortedProperties;
	}

	public static PropertyDTO getDTO(String name) {
		// Called to initialize the properties list
		getJRProperties();
		for (PropertyDTO dto : props)
			if (dto.getName().equalsIgnoreCase(name))
				return dto;
		return null;
	}

	public static List<PropertyDTO> getJRProperties() {
		if (props != null)
			return props;
		props = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			props.add(new PropertyDTO("net.sf.jasperreports.text.truncate.at.char", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.text.truncate.suffix", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.print.keep.full.text", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.text.measurer.factory", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.chart.theme", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.h1", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.h2", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.h3", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.table", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.tr", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.th", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.td", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.colspan", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.rowspan", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.id", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.character.encoding", "UTF-8")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.graphics2d.min.job.size", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.frames.as.nested.tables", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.remove.empty.space.between.rows", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.white.page.background", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.wrap.break.word", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.size.unit", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.using.images.to.align", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.force.svg.shapes", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.force.linebreak.policy", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.create.batch.mode.bookmarks", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.compressed", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.encrypted", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.128.bit.key", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.create.custom.palette", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.one.page.per.sheet", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.remove.empty.space.between.rows", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.remove.empty.space.between.columns", "true"));//$NON-NLS-2$ //$NON-NLS-1$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.white.page.background", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.detect.cell.type", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.font.size.fix.enabled", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.ignore.graphics", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.collapse.row.span", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.ignore.cell.border", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.max.rows.per.sheet", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xml.validation", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.csv.field.delimiter", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.csv.record.delimiter", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tagged", "true")); //$NON-NLS-1$ //$NON-NLS-2$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.language", "true")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return props;
	}
}
