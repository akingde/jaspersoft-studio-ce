/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.jasper;

import java.awt.Graphics2D;

import net.sf.jasperreports.engine.JRBreak;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.convert.ConvertVisitor;
import net.sf.jasperreports.engine.convert.ReportConverter;
import net.sf.jasperreports.engine.export.AwtTextRenderer;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.draw.FrameDrawer;
import net.sf.jasperreports.engine.export.draw.Offset;
import net.sf.jasperreports.engine.export.draw.PrintDrawVisitor;
import net.sf.jasperreports.engine.export.draw.TextDrawer;
import net.sf.jasperreports.engine.util.JRStyledText;
import net.sf.jasperreports.engine.util.UniformElementVisitor;

public class JSSDrawVisitor extends UniformElementVisitor {

	protected ConvertVisitor convertVisitor;
	protected PrintDrawVisitor drawVisitor;

	/**
	 *
	 */
	public JSSDrawVisitor(ReportConverter reportConverter, Graphics2D grx) {
		this.convertVisitor = new JSSConvertVisitor(reportConverter);
		final JasperReportsContext jasperReportsContext = reportConverter.getJasperReportsContext();
		this.drawVisitor = new PrintDrawVisitor(jasperReportsContext) {
			@Override
			public void setTextRenderer(JRReport report) {
				AwtTextRenderer textRenderer = new AwtTextRenderer(jasperReportsContext, JRPropertiesUtil.getInstance(
						jasperReportsContext).getBooleanProperty(report, JRGraphics2DExporter.MINIMIZE_PRINTER_JOB_SIZE, true),
						JRPropertiesUtil.getInstance(jasperReportsContext).getBooleanProperty(report,
								JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT, false));

				setTextDrawer(new TextDrawer(jasperReportsContext, textRenderer));
				setFrameDrawer(new FrameDrawer(jasperReportsContext, null, textRenderer));
			}
		};
		setTextRenderer(reportConverter.getReport());
		setGraphics2D(grx);
		this.drawVisitor.setClip(true);
	}

	public void setClip(boolean clip) {
		this.drawVisitor.setClip(clip);
	}

	/**
	 *
	 */
	public void setGraphics2D(Graphics2D grx) {
		drawVisitor.setGraphics2D(grx);
	}

	/**
	 *
	 */
	public void setTextRenderer(JRReport report) {
		drawVisitor.setTextRenderer(report);
	}

	/**
	 *
	 */
	@Override
	public void visitBreak(JRBreak breakElement) {
		// FIXMEDRAW
	}

	@Override
	protected void visitElement(JRElement element) {
		JRPrintElement printElement = convertVisitor.getVisitPrintElement(element);
		try {
			printElement.accept(drawVisitor, elementOffset(element));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	protected Offset elementOffset(JRElement element) {
		return new Offset(-element.getX(), -element.getY());
	}

	/**
	 *
	 */
	public void visitElementGroup(JRElementGroup elementGroup) {
		// nothing to draw. elements are drawn individually.
	}

}
