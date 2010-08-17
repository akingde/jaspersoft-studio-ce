/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.report;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.parts.JasperDesignEditPartFactory;
import com.jaspersoft.studio.editor.gef.parts.MainDesignerRootEditPart;
import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;
import com.jaspersoft.studio.editor.gef.rulers.ReportRulerProvider;
import com.jaspersoft.studio.editor.gef.rulers.component.JDRulerComposite;
import com.jaspersoft.studio.editor.palette.JDPaletteFactory;
import com.jaspersoft.studio.preferences.PreferenceConstants;

/**
 * The Class ReportEditor.
 * 
 * @author Chicu Veaceslav
 */
public class ReportEditor extends AbstractVisualEditor {

	/** The Constant ID. */
	public static final String ID = ReportEditor.class.getName();

	/**
	 * Instantiates a new report editor.
	 */
	public ReportEditor() {
		super();
	}

	/** The ruler comp. */
	private JDRulerComposite rulerComp;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.editor.java2d.J2DGraphicalEditorWithFlyoutPalette#createGraphicalViewer(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	protected void createGraphicalViewer(Composite parent) {
		rulerComp = new JDRulerComposite(parent, SWT.NONE);
		super.createGraphicalViewer(rulerComp);
		rulerComp.setGraphicalViewer((ScrollingGraphicalViewer) getGraphicalViewer());
	}

	// FIXME: something wrong, I should not do that, order in initialisation is
	// wrong
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getGraphicalControl()
	 */
	@Override
	protected Control getGraphicalControl() {
		if (rulerComp != null)
			return rulerComp;
		return super.getGraphicalControl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#configureGraphicalViewer()
	 */
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().getControl().setBackground(ColorConstants.button);

		GraphicalViewer graphicalViewer = getGraphicalViewer();
		MainDesignerRootEditPart rootEditPart = new MainDesignerRootEditPart();

		graphicalViewer.setRootEditPart(rootEditPart);
		// set EditPartFactory
		graphicalViewer.setEditPartFactory(new JasperDesignEditPartFactory());

		// set rulers providers
		RulerProvider provider = new ReportRulerProvider(new ReportRuler(true, RulerProvider.UNIT_PIXELS));
		graphicalViewer.setProperty(RulerProvider.PROPERTY_HORIZONTAL_RULER, provider);

		provider = new ReportRulerProvider(new ReportRuler(false, RulerProvider.UNIT_PIXELS));
		graphicalViewer.setProperty(RulerProvider.PROPERTY_VERTICAL_RULER, provider);

		Boolean isRulerVisible = JaspersoftStudioPlugin.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.P_PAGE_RULERGRID_SHOWRULER);
		
		graphicalViewer.setProperty(RulerProvider.PROPERTY_RULER_VISIBILITY, isRulerVisible);

		createAdditionalActions();
		graphicalViewer.setKeyHandler(new GraphicalViewerKeyHandler(graphicalViewer));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPaletteRoot()
	 */
	@Override
	protected PaletteRoot getPaletteRoot() {
		return JDPaletteFactory.createPalette();
	}

}
