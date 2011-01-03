/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptor.properties.dialog;

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.studio.messages.Messages;

public class PropertiesList {

	private static List<PropertyDTO> props;

	public static List<PropertyDTO> getJRProperties() {
		if (props != null)
			return props;
		props = new ArrayList<PropertyDTO>();
		for (int i = 0; i < 20; i++) {
			props.add(new PropertyDTO("net.sf.jasperreports.text.truncate.at.char", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.text.truncate.suffix", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.print.keep.full.text", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.text.measurer.factory", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.chart.theme", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.h1", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.h2", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.h3", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.table", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.tr", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.th", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.td", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.colspan", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.rowspan", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.id", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.character.encoding", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.graphics2d.min.job.size", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.frames.as.nested.tables", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props
					.add(new PropertyDTO("net.sf.jasperreports.export.html.remove.empty.space.between.rows", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.white.page.background", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.wrap.break.word", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.size.unit", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.using.images.to.align", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.force.svg.shapes", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.force.linebreak.policy", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.create.batch.mode.bookmarks", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.compressed", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.encrypted", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.128.bit.key", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.create.custom.palette", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.one.page.per.sheet", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.remove.empty.space.between.rows", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.remove.empty.space.between.columns", "Property", //$NON-NLS-1$ //$NON-NLS-2$
					"true")); //$NON-NLS-1$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.white.page.background", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.detect.cell.type", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.size.fix.enabled", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.ignore.graphics", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.collapse.row.span", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.ignore.cell.border", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.max.rows.per.sheet", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.xml.validation", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.csv.field.delimiter", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.csv.record.delimiter", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tagged", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.language", "Property", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		return props;
	}
}
