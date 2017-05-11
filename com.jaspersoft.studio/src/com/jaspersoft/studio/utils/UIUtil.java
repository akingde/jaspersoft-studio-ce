/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.NullableSpinner;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.SWTImageEffects.Glow;

import net.sf.jasperreports.eclipse.util.Misc;

public class UIUtil {
	/** ID for the "Properties View" */
	public static final String PROPERTIES_VIEW_ID = "org.eclipse.ui.views.PropertySheet"; //$NON-NLS-1$

	/**
	 * Set the value of a spinner. For convenience this method takes an object as value, but if the obj is null, or if it
	 * is not an Integer the method does nothing. If the displayed value is the same as the one provided, nothing is done
	 * (preventing on windows the whole selection of the number).
	 * 
	 * @param spinner
	 * @param obj
	 */
	public static void setSpinnerSelection(Spinner spinner, Object obj) {
		if (obj == null)
			return;
		if (!(obj instanceof Integer))
			return;
		int num = ((Integer) obj).intValue();
		if (spinner.getSelection() != num) {
			spinner.setSelection(num);
		}
	}

	/**
	 * Set the value of a spinner. For convenience this method takes an object as value, but if the obj is null, or if it
	 * is not an Integer the method uses the defValue. If the displayed value is the same as the one provided, nothing is
	 * done (preventing on windows the whole selection of the number).
	 * 
	 * @param spinner
	 * @param obj
	 */
	public static void setSpinnerSelection(Spinner spinner, Object obj, int defValue) {
		int num = defValue;
		if (obj != null && obj instanceof Integer) {
			num = ((Integer) obj).intValue();
		}

		if (!spinner.isDisposed() && spinner.getSelection() != num) {
			spinner.setSelection(num);
		}
	}

	/**
	 * Set the value of a spinner. For convenience this method takes an object as value, but if the obj is null, or if it
	 * is not an Integer the method uses the defValue. If the displayed value is the same as the one provided, nothing is
	 * done (preventing on windows the whole selection of the number).
	 * 
	 * @param spinner
	 * @param obj
	 */
	public static void setSpinnerSelection(NullableSpinner spinner, Object obj, Number defValue) {
		Number num = defValue;
		if (obj != null && obj instanceof Number) {
			num = (Number) obj;
		}

		if (!spinner.isDisposed() && spinner.getValue() != num) {
			spinner.setValue(num);
		}
	}
	
	public static Label createLabel(Composite parent, String txt) {
		return createLabel(parent, txt, -1);
	}

	public static Label createLabel(Composite parent, String txt, int span) {
		Label lbl = new Label(parent, SWT.RIGHT);
		lbl.setText(txt);
		if (parent.getLayout() instanceof RowLayout) {
			RowData rd = new RowData();
			lbl.setLayoutData(rd);
		} else if (parent.getLayout() instanceof GridLayout) {
			GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_BEGINNING);
			gd.verticalIndent = 5;
			if (span > 0)
				gd.horizontalSpan = span;
			lbl.setLayoutData(gd);
		}
		return lbl;
	}

	public static Label createSeparator(Composite parent, int span) {
		Label lbl = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.WRAP);
		if (parent.getLayout() instanceof RowLayout) {
			RowData rd = new RowData();
			lbl.setLayoutData(rd);
		} else if (parent.getLayout() instanceof GridLayout) {
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = span;
			lbl.setLayoutData(gd);
		}
		return lbl;
	}

	public static Composite createSection(Composite parent, String text, boolean expanded, int cols) {
		Section ec = new Section(parent, Section.TREE_NODE);
		ec.setText(Misc.nvl(text));
		ec.setExpanded(expanded);
		ec.setFont(ResourceManager.getBoldFont(ec.getFont()));

		Label lbl = new Label(ec, SWT.SEPARATOR | SWT.HORIZONTAL);
		ec.setSeparatorControl(lbl);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		ec.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		ec.setLayoutData(gd);

		Composite c = new Composite(ec, SWT.WRAP);
		c.setLayout(new GridLayout(cols, false));
		ec.setClient(c);
		return c;
	}

	public static void setBold(Control control) {
		control.setFont(SWTResourceManager.getBoldFont(control.getFont()));
	}

	public static int getCharWidth(Drawable control) {
		GC gc = new GC(control);
		FontMetrics fm = gc.getFontMetrics();
		int w = fm.getAverageCharWidth();
		gc.dispose();
		return w;
	}

	public static int getCharHeight(Drawable control) {
		GC gc = new GC(control);
		FontMetrics fm = gc.getFontMetrics();
		int h = fm.getHeight();
		gc.dispose();
		return h;
	}

	public static void setFontHeight(Control control, int height) {
		String name = "";
		FontData[] fontData = control.getFont().getFontData();
		for (int i = 0; i < fontData.length; ++i) {
			name = fontData[i].getName();
		}
		control.setFont(SWTResourceManager.getFont(name, height, SWT.BOLD));
	}

	/**
	 * This method adds select-on-focus functionality to a {@link Text} component.
	 * 
	 * Specific behavior: - when the Text is already focused -> normal behavior - when the Text is not focused: -> focus
	 * by keyboard -> select all text -> focus by mouse click -> select all text unless user manually selects text
	 * 
	 * @param text
	 */
	public static void addSelectOnFocusToText(Text text) {
		// THIS METHOD WAS TAKEN FROM THE FOLLOWING TOPIC ON STACKOVERFLOW:
		// http://stackoverflow.com/questions/10038570/implementing-select-on-focus-behavior-for-an-eclipse-text-control
		// REFER ALSO TO THE FOLLOWING ECLIPSE BUG 46059
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=46059
		Listener listener = new Listener() {

			private boolean hasFocus = false;
			private boolean hadFocusOnMousedown = false;

			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.FocusIn: {
					Text t = (Text) e.widget;

					// Covers the case where the user focuses by keyboard.
					t.selectAll();

					// The case where the user focuses by mouse click is special because Eclipse,
					// for some reason, fires SWT.FocusIn before SWT.MouseDown, and on mouse down
					// it cancels the selection. So we set a variable to keep track of whether the
					// control is focused (can't rely on isFocusControl() because sometimes it's wrong),
					// and we make it asynchronous so it will get set AFTER SWT.MouseDown is fired.
					t.getDisplay().asyncExec(new Runnable() {
						public void run() {
							hasFocus = true;
						}
					});

					break;
				}
				case SWT.FocusOut: {
					hasFocus = false;
					((Text) e.widget).clearSelection();

					break;
				}
				case SWT.MouseDown: {
					// Set the variable which is used in SWT.MouseUp.
					hadFocusOnMousedown = hasFocus;

					break;
				}
				case SWT.MouseUp: {
					Text t = (Text) e.widget;
					if (t.getSelectionCount() == 0 && !hadFocusOnMousedown) {
						((Text) e.widget).selectAll();
					}

					break;
				}
				}
			}

		};

		text.addListener(SWT.FocusIn, listener);
		text.addListener(SWT.FocusOut, listener);
		text.addListener(SWT.MouseDown, listener);
		text.addListener(SWT.MouseUp, listener);
	}

	/**
	 * Setups the start of cell editing on a {@link TableViewer} when a {@link DoubleClickEvent} occurs.
	 * 
	 * @param tviewer
	 *          the table viewer
	 */
	public static void setViewerCellEditingOnDblClick(TableViewer tviewer) {
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(tviewer) {
			protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
				return event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION;
			}
		};

		TableViewerEditor.create(tviewer, actSupport, ColumnViewerEditor.DEFAULT);
	}
	
	/**
	 * Setups the start of cell editing on a {@link TreeViewer} when a {@link DoubleClickEvent} occurs.
	 * 
	 * @param tviewer
	 *          the tree viewer
	 */
	public static void setViewerCellEditingOnDblClick(TreeViewer tviewer) {
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(tviewer) {
			protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
				return event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION;
			}
		};

		TreeViewerEditor.create(tviewer, actSupport, ColumnViewerEditor.DEFAULT);
	}

	/**
	 * Creates an error decoration on the top left of the specified {@link Text} widget when the text is empty or null.
	 * 
	 * @param widget
	 *          the text widget to which attach the decorator
	 * @param marginWidth
	 *          margin between decoration and widget
	 * @param description
	 *          description message to show on hover
	 */
	public static void createErrorDecorationForEmptyText(final Text widget, int marginWidth, String description) {
		final ControlDecoration textDecoration = createErrorDecoration(widget, marginWidth, description);
		widget.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (widget.getText() == null || widget.getText().trim().isEmpty()) {
					textDecoration.show();
				} else {
					textDecoration.hide();
				}
			}
		});
	}

	/**
	 * Creates an error decoration on the top left of the specified {@link WTextExpression} widget when the expression
	 * text is empty or null.
	 * 
	 * @param widget
	 *          the expression widget to which attach the decorator
	 * @param marginWidth
	 *          margin between decoration and widget
	 * @param description
	 *          description message to show on hover
	 */
	public static void createErrorDecorationForBlankExpression(final WTextExpression widget, int marginWidth,
			String description) {
		final ControlDecoration textDecoration = createErrorDecoration(widget, marginWidth, description);
		widget.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				if (widget.getText() == null || widget.getText().trim().isEmpty()) {
					textDecoration.show();
				} else {
					textDecoration.hide();
				}
			}
		});
	}

	/*
	 * Create a decoration attached to the specified control.
	 */
	private static ControlDecoration createErrorDecoration(final Control control, int marginWidth, String description) {
		final ControlDecoration textDecoration = new ControlDecoration(control, SWT.LEFT | SWT.TOP);
		textDecoration.setDescriptionText(description);
		textDecoration.setMarginWidth(marginWidth);
		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
				.getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
		textDecoration.setImage(fieldDecoration.getImage());
		return textDecoration;
	}

	/**
	 * Checks if the PropertiesView has currently the focus.
	 * 
	 * @return <code>true</code> if the properties view has the focus, <code>false</code> otherwise
	 */
	public static boolean isPropertiesViewFocused() {
		try {
			String activePartViewID = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart()
					.getSite().getId();
			return PROPERTIES_VIEW_ID.equals(activePartViewID);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Customizes a {@link GalleryItem} in order to enrich with a "standard" image plus a "selected" image with a custom
	 * shadow.
	 * <p>
	 * 
	 * Cache maps are used for performance purposes.
	 * 
	 * @param item
	 *          the gallery item to modify
	 * @param pluginID
	 *          the ID of the plugin, where the image is located
	 * @param imagePath
	 *          the plugin-relative path of the image
	 * @param selectedImagesCache
	 *          a cache of selected images
	 * @param standardImagesCache
	 *          a cache of standard images
	 */
	public static void setGallyeryItemImageInfo(GalleryItem item, String pluginID, String imagePath,
			Map<String, Image> selectedImagesCache, Map<String, Image> standardImagesCache) {
		Image selectedImg = selectedImagesCache.get(imagePath);
		Image standardImg = standardImagesCache.get(imagePath);
		if (selectedImg == null || standardImg == null) {
			Image itemImage = ResourceManager.getPluginImage(pluginID, imagePath);
			// Add viewer required effects to the images shown...
			selectedImg = new Image(itemImage.getDevice(), SWTImageEffects.extendArea(itemImage.getImageData(), 20, null));
			standardImg = new Image(itemImage.getDevice(),
					Glow.glow(itemImage.getImageData(), ResourceManager.getColor(SWT.COLOR_GRAY), 20, 0, 255));
			// Cache images
			standardImagesCache.put(imagePath, standardImg);
			selectedImagesCache.put(imagePath, selectedImg);
		}
		item.setSelectedImage(selectedImg);
		item.setStandardImage(standardImg);
		item.setImage(standardImg);
	}

	/**
	 * @return <code>true</code> if it is an arrow key, <code>false</code> otherwise
	 */
	public static boolean isArrowKey(int keyCode) {
		return keyCode == SWT.ARROW_DOWN || keyCode == SWT.ARROW_LEFT || keyCode == SWT.ARROW_RIGHT
				|| keyCode == SWT.ARROW_UP;
	}

	/**
	 * Utility enumeration that maintains a collection of the know extensions for files that can be open with a direct
	 * double click from a file system navigator (i.e: in Windows).
	 */
	public static enum EditorExtension {
		JRXML(".jrxml"), JRCTX(".jrctx"), JRTX(".jrtx"), JASPER(".jasper"), JRPRINT(".jrprint"), JRPXML(".jrpxml"), JSSCE(
				".jssce");

		private String extension;

		private EditorExtension(String extension) {
			this.extension = extension;
		}

		public String getExtension() {
			return this.extension;
		}

		public static boolean isKnowExtension(String extension) {
			for (EditorExtension ex : values()) {
				if (ex.getExtension().equals(extension)) {
					return true;
				}
			}
			return false;
		}

		public static List<String> getKnowExtensions() {
			List<String> result = new ArrayList<String>();
			for (EditorExtension ex : values()) {
				result.add(ex.getExtension());
			}
			return result;
		}

		public static boolean hasValidExtension(String filepath) {
			for (String ext : getKnowExtensions()) {
				if (filepath.endsWith(ext)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Checks if we are currently on a Mac OS X platform and running inside an Eclipse 4.x based installation.
	 * 
	 * @return <code>true</code> if is Mac and we are in E4, <code>false</code> otherwise
	 */
	public static boolean isMacAndEclipse4() {
		return Util.isMac() && isEclipse4();
	}

	/**
	 * Checks if we are running into an Eclipse 4 based environment.
	 * 
	 * @return <code>true</code> if we are in E4, <code>false</code> otherwise
	 */
	public static boolean isEclipse4() {
		return Platform.getBundle("org.eclipse.e4.ui.workbench") != null; //$NON-NLS-1$
	}

	/**
	 * Returns the Eclipse version (i.e. 4.5.1) in the format <code>major.minor.micro</code>.
	 * 
	 * @see Version
	 * @return the Eclipse version number
	 */
	public static String getEclipseVersion() {
		Bundle platformBundle = Platform.getBundle("org.eclipse.platform"); //$NON-NLS-1$
		Bundle rcpBundle = Platform.getBundle("org.eclipse.rcp"); //$NON-NLS-1$
		Version version = null;
		if (platformBundle != null) {
			version = platformBundle.getVersion();
		}
		if (version == null && rcpBundle != null) {
			version = rcpBundle.getVersion();
		}
		if (version != null) {
			return version.getMajor() + "." + version.getMinor() + "." + version.getMicro();
		} else {
			// FIXME - Maybe it would be better to throw an exception?
			return "0.0.0"; //$NON-NLS-1$
		}
	}

	/**
	 * There is the bug in the custom JSS toolbar rendering with Eclipse Mars version. This was mostly observed in
	 * Windows. The method checks if it is the case of applying the trick to handle the toolbar rendering correctly.
	 * 
	 * @see Bugzilla #44189
	 * @return <code>true</code> if the toolbar
	 */
	public static boolean shouldTrickToolbar() {
		return isEclipse4();
	}
	
	/**
	 * Force a control to loose the focus and another one to gain it (if it can).
	 * If the control is already focused it does nothing.
	 * 
	 * @param toFocus the control to focus, must be not null
	 */
	public static void updateFocus(Control toFocus) {
		Control focusedControl = toFocus.getDisplay().getFocusControl();
		if (focusedControl != toFocus){
			boolean isFocusedEnabled = focusedControl != null && focusedControl.isEnabled();
			if (isFocusedEnabled){
				//force the lost of focus by disabling and enabling the control
				focusedControl.setEnabled(false);
				focusedControl.setEnabled(true);
			}
			toFocus.setFocus();
		}
	}
}
