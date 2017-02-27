/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.directeditor;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.CellEditorActionHandler;

import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextElement;

import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.base.JRBaseStaticText;

public class TextEditManager extends DirectEditManager {
	private IActionBars actionBars;
	private MTextElement textElement;
	private CellEditorActionHandler actionHandler;
	private IAction copy, cut, paste, undo, redo, find, selectAll, delete;
	// private double cachedZoom = -1.0;
	private Font scaledFont;
	private ZoomListener zoomListener = new ZoomListener() {
		public void zoomChanged(double newZoom) {
			updateScaledFont(newZoom, textElement);
		}
	};

	public TextEditManager(GraphicalEditPart source, CellEditorLocator locator) {
		super(source, null, locator);
	}

	/** * @see org.eclipse.gef.tools.DirectEditManager#bringDown() */
	@Override
	protected void bringDown() {
		ZoomManager zoomMgr = (ZoomManager) getEditPart().getViewer().getProperty(ZoomManager.class.toString());
		if (zoomMgr != null)
			zoomMgr.removeZoomListener(zoomListener);
		if (actionHandler != null) {
			actionHandler.dispose();
			actionHandler = null;
		}
		if (actionBars != null) {
			restoreSavedActions(actionBars);
			actionBars.updateActionBars();
			actionBars = null;
		}
		if (getCellEditor() != null) super.bringDown();
		// dispose any scaled fonts that might have been created disposeScaledFont();
	}

	/**
	 * The cell editor is created inside a separate shell because of some ubuntu 
	 * refreshing problem when the widget is created directly above the figure.
	 * See http://jira.jaspersoft.com/browse/JSS-539 for more information
	 * 
	 */
	protected CellEditor createCellEditorOn(Composite composite) {
		return new TextCellEditor(composite, SWT.MULTI | SWT.WRAP){
			@Override
			protected Control createControl(Composite parent) {
				Shell shell = new Shell(parent.getShell(), SWT.NO_TRIM);
				final Text text = (Text)super.createControl(shell);
				//Set the canvas of the figure on the shell
				shell.setData(parent);
				//Create the layout for the shell to place the widget centered with 1 pixel of shell visible,
				//used to create the border
				shell.setLayout(new Layout() {
					
					@Override
					protected void layout(Composite composite, boolean flushCache) {
						Rectangle availableSize = composite.getClientArea();
						text.setBounds(1, 1, availableSize.width - 2, availableSize.height - 2);
					}
					
					@Override
					protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
						return text.computeSize(wHint, hHint);
					}
				});
				//Add the paint listener to the shell to define the border
				shell.addPaintListener(new PaintListener() {
					
					@Override
					public void paintControl(PaintEvent e) {
						Rectangle bounds = ((Shell)e.widget).getBounds();
						int w = bounds.width - 1;
						int h = bounds.height- 1;
						e.gc.setForeground(ColorConstants.orange);
						e.gc.drawRectangle(0, 0, w, h);
					}
				});
				
				text.setBackground(ColorConstants.white);
				return shell;
			}
		};
	}

	/**
	 * Asks the source edit part to show source feedback.
	 */
	public void showFeedback() {
		getEditPart().showSourceFeedback(getDirectEditRequest());
	}
	
	public void dispose() {
		disposeScaledFont();
	}

	private void disposeScaledFont() {
		if (scaledFont != null) {
			scaledFont.dispose();
			scaledFont = null;
		}
	}

	protected void initCellEditor() {
		// update text

		try {
			MStaticText model = (MStaticText) ((FigureEditPart) getEditPart()).getModel();
			getCellEditor().setValue(model.getPropertyValue(JRBaseStaticText.PROPERTY_TEXT));
			getCellEditor().getControl().setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

			// update font
			ZoomManager zoomMgr = (ZoomManager) getEditPart().getViewer().getProperty(ZoomManager.class.toString());
			if (zoomMgr != null) { // this will force the font to be set cachedZoom = -1.0;
				updateScaledFont(zoomMgr.getZoom(), model);
				zoomMgr.addZoomListener(zoomListener);
			} // else
				// getCellEditor().getControl().setFont(stickyNote.getFont());
			// Hook the cell editor's copy/paste actions to the actionBars so that they can
			// be invoked via keyboard
			// shortcuts.
			actionBars = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()
					.getEditorSite().getActionBars();
			saveCurrentActions(actionBars);
			actionHandler = new CellEditorActionHandler(actionBars);
			actionHandler.addCellEditor(getCellEditor());
			actionBars.updateActionBars();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void restoreSavedActions(IActionBars actionBars) {
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), copy);
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), paste);
		actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), delete);
		actionBars.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(), selectAll);
		actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cut);
		actionBars.setGlobalActionHandler(ActionFactory.FIND.getId(), find);
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undo);
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redo);
	}

	private void saveCurrentActions(IActionBars actionBars) {
		copy = actionBars.getGlobalActionHandler(ActionFactory.COPY.getId());
		paste = actionBars.getGlobalActionHandler(ActionFactory.PASTE.getId());
		delete = actionBars.getGlobalActionHandler(ActionFactory.DELETE.getId());
		selectAll = actionBars.getGlobalActionHandler(ActionFactory.SELECT_ALL.getId());
		cut = actionBars.getGlobalActionHandler(ActionFactory.CUT.getId());
		find = actionBars.getGlobalActionHandler(ActionFactory.FIND.getId());
		undo = actionBars.getGlobalActionHandler(ActionFactory.UNDO.getId());
		redo = actionBars.getGlobalActionHandler(ActionFactory.REDO.getId());
	}

	private void updateScaledFont(double zoom, MTextElement model) {
		// if (cachedZoom == zoom)
		// return;

		int fontSize = 1;
		Object fontSizeValue = model == null ? null : model.getPropertyActualValue(JRBaseFont.PROPERTY_FONT_SIZE);

		try {
			if (fontSizeValue != null) {
				if (fontSizeValue instanceof String) {
					fontSize = Integer.parseInt((String) fontSizeValue);
				} else if (fontSizeValue instanceof Number) {
					fontSize = ((Number) fontSizeValue).intValue();
				}

			}
		} catch (Exception ex) {

		}

		String fontName = model == null ? null : (String) model.getPropertyActualValue(JRBaseFont.PROPERTY_FONT_NAME);

		Shell shell = (Shell) getCellEditor().getControl();
		Text text = (Text) shell.getChildren()[0];
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Keep old iReport behavior:
				// Hitting ENTER will confirm the value
				if (e.keyCode == '\r' && (e.stateMask & SWT.SHIFT) == 0) {
					e.doit = false;
					commit();
				}
			}
		});
		Font font = getEditPart().getFigure().getFont();

		disposeScaledFont();

		try {
			FontData fd = font.getFontData()[0];
			fd.setHeight(Math.max(12, (int) (zoom * fontSize * 0.75))); // fd.getHeight()
			if (fontName != null && fontName.length() > 0) {
				fd.setName(fontName);
			}
			text.setFont(scaledFont = new Font(null, fd));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
