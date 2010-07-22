package com.jaspersoft.studio.property.descriptor.properties.dialog;

import java.util.ArrayList;
import java.util.List;

public class PropertiesList {

	private static List<PropertyDTO> props;

	public static List<PropertyDTO> getJRProperties() {
		if (props != null)
			return props;
		props = new ArrayList<PropertyDTO>();
		for (int i = 0; i < 20; i++) {
			props.add(new PropertyDTO("net.sf.jasperreports.text.truncate.at.char", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.text.truncate.suffix", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.print.keep.full.text", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.text.measurer.factory", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.chart.theme", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.h1", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.h2", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.h3", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.table", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.tr", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.th", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.td", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.colspan", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.rowspan", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.id", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.character.encoding", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.graphics2d.min.job.size", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.frames.as.nested.tables", "Property", "true"));
			props
					.add(new PropertyDTO("net.sf.jasperreports.export.html.remove.empty.space.between.rows", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.white.page.background", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.wrap.break.word", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.size.unit", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.html.using.images.to.align", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.force.svg.shapes", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.force.linebreak.policy", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.create.batch.mode.bookmarks", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.compressed", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.encrypted", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.128.bit.key", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.create.custom.palette", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.one.page.per.sheet", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.remove.empty.space.between.rows", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.remove.empty.space.between.columns", "Property",
					"true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.white.page.background", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.detect.cell.type", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.size.fix.enabled", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.ignore.graphics", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.collapse.row.span", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.ignore.cell.border", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.xls.max.rows.per.sheet", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.xml.validation", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.csv.field.delimiter", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.csv.record.delimiter", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tagged", "Property", "true"));
			props.add(new PropertyDTO("net.sf.jasperreports.export.pdf.tag.language", "Property", "true"));
		}
		return props;
	}
}
