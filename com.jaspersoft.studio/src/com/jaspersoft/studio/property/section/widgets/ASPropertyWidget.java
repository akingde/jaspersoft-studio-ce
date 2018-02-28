/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.Map;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.properties.internal.IHighlightPropertyWidget;
import com.jaspersoft.studio.properties.view.validation.ValidationError;
import com.jaspersoft.studio.property.ResetValueCommand;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.combomenu.ComboButton;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;

import net.sf.jasperreports.eclipse.ui.WritableComboButton;

public abstract class ASPropertyWidget<T extends IPropertyDescriptor> implements IHighlightPropertyWidget {

	protected T pDescriptor;

	protected AbstractSection section;

	private CLabel label;

	/**
	 * On MacOS seems the contextual menu is not opened on combo, this lister will
	 * force it to open when a right click is found
	 */
	protected static MouseAdapter macComboMenuOpener = new MouseAdapter() {

		@Override
		public void mouseUp(MouseEvent e) {
			if (e.button == 3 && ((Control) e.widget).getMenu() != null) {
				Menu menu = ((Control) e.widget).getMenu();
				if (!menu.isDisposed() && !menu.isVisible()) {
					Point location = e.widget.getDisplay().getCursorLocation();
					menu.setLocation(location.x, location.y);
					menu.setVisible(true);
				}
			}
		}
	};

	public ASPropertyWidget(Composite parent, AbstractSection section, T pDescriptor) {
		this.pDescriptor = pDescriptor;
		this.section = section;
		createComponent(parent);
		if (getControl() != null)
			setupFocusControl(pDescriptor, getControl());
	}

	protected void setupFocusControl(IPropertyDescriptor pDescriptor, Control c) {
		if (c.isEnabled()) {
			c.removeFocusListener(focusListener);
			c.addFocusListener(focusListener);
			HelpSystem.bindToHelp(pDescriptor, c);
		}
		if (c instanceof Composite) {
			for (Control cc : ((Composite) c).getChildren())
				setupFocusControl(pDescriptor, cc);
		}
	}

	public void setReadOnly(boolean readonly) {
		if (getControl() != null)
			getControl().setEnabled(!readonly);
	}

	protected abstract void createComponent(Composite parent);

	public abstract void setData(APropertyNode pnode, Object value);

	protected void refresh() {
		setData(section.getElement(), section.getElement().getPropertyValue(getId()));
	}

	/**
	 * Set the data of the widget with a flag also indicating if the set value is
	 * inherited or not. If not reimplemented this is the same of setData with two
	 * parameters
	 * 
	 * @param pnode
	 *            the current node
	 * @param resolvedValue
	 *            the current value of the property used by the node, resolved by JR
	 * @param elementValue
	 *            the value of the property own by the elements, when this is
	 *            different from null probably it will be the same of the
	 *            resolvedValue, otherwise if this is null then the resolvedValue
	 *            was inherited
	 */
	public void setData(APropertyNode pnode, Object resolvedValue, Object elementValue) {
		setData(pnode, resolvedValue);
	}

	protected void createContextualMenu(final APropertyNode node) {
		Control control = getControl();
		createContextualMenu(node, control, pDescriptor.getId().toString());
	}

	/**
	 * Create a contextual menu for the current control. This contextual menu will
	 * contains the action to reset the value of a property if the property has
	 * default value inside the node. Also it will contain the action to set the
	 * value to null if the operation is allowed.
	 * 
	 * Since on mac the combo item doens't have a contextual menu it add a special
	 * listneer for them as workaround to the problem
	 * 
	 * @param node
	 *            node where the the command will be executed and from where the
	 *            default map is extracted
	 * @param control
	 *            control where the contextual menu will be set
	 * @param propertyID
	 *            id of the property to set
	 */
	protected void createContextualMenu(final APropertyNode node, final Control control, final String propertyID) {
		if (node != null && control != null && !control.isDisposed()) {

			// MacOS fix, the combo on MacOS doesn't have a contextual menu, so we need to
			// handle this listener manually
			boolean handleComboListener = Util.isMac() && control.getClass() == Combo.class;
			if (handleComboListener) {
				control.removeMouseListener(macComboMenuOpener);
			}

			boolean entryCreated = false;
			Map<String, DefaultValue> defaultMap = node.getDefaultsPropertiesMap();
			if (defaultMap != null) {
				DefaultValue defaultEntry = defaultMap.get(propertyID);
				if (defaultEntry != null && (defaultEntry.isNullable() || defaultEntry.hasDefault())) {
					Menu controlMenu = new Menu(control);

					// Create the reset entry if necessary
					if (defaultEntry.hasDefault()) {
						MenuItem resetItem = new MenuItem(controlMenu, SWT.NONE);
						entryCreated = true;
						resetItem.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								JSSCompoundCommand resetCommand = new JSSCompoundCommand(node);
								for(APropertyNode selectedNode : section.getElements()) {
									ResetValueCommand cmd = new ResetValueCommand();
									cmd.setPropertyId(propertyID);
									cmd.setTarget(selectedNode);
									resetCommand.add(cmd);
								}
								section.getEditDomain().getCommandStack().execute(resetCommand);
								focusControl(control);
							}
						});
						resetItem.setText(Messages.ASPropertyWidget_0);
					}

					// Create the null entry if necessary
					if (defaultEntry.isNullable()) {
						MenuItem nullItem = new MenuItem(controlMenu, SWT.NONE);
						entryCreated = true;
						nullItem.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								JSSCompoundCommand resetCommand = new JSSCompoundCommand(node);
								for(APropertyNode selectedNode : section.getElements()) {
									SetValueCommand cmd = new SetValueCommand();
									cmd.setPropertyId(propertyID);
									cmd.setTarget(selectedNode);
									cmd.setPropertyValue(null);
									resetCommand.add(cmd);
								}
								section.getEditDomain().getCommandStack().execute(resetCommand);
								focusControl(control);
							}
						});
						nullItem.setText(Messages.ASPropertyWidget_1);
					}

					// if the control already have a menu dispose it first, since it is a swt widget
					// it is not disposed automatically by the garbage collector
					if (control.getMenu() != null) {
						control.getMenu().dispose();
					}

					// set the new menu
					control.setMenu(controlMenu);
					if (handleComboListener) {
						control.addMouseListener(macComboMenuOpener);
					}
				}
			}
			if (!entryCreated) {
				// if no entry was created remove the contextual menu, but first dispose
				// the old one
				if (control.getMenu() != null) {
					control.getMenu().dispose();
				}
				control.setMenu(null);
			}
		}
	}

	/**
	 * Focus the passed control, this is typically called when a contextual reset is
	 * used, can be overridden by the widget that doesn't handle very well the set
	 * focus
	 * 
	 * @param control
	 *            the control to focus
	 */
	protected void focusControl(Control control) {
		if (control != null) {
			UIUtil.updateFocus(control);
		}
	}

	public String getId() {
		return pDescriptor.getId().toString();
	}

	public String getName() {
		return pDescriptor.getDisplayName();
	}

	public CLabel getLabel() {
		return label;
	}

	public void setLabel(CLabel label) {
		this.label = label;
	}

	protected FocusListener focusListener = new FocusListener() {

		@Override
		public void focusLost(FocusEvent e) {
			handleFocusLost();
		}

		@Override
		public void focusGained(FocusEvent e) {
			handleFocusGained();
		}
	};

	private IStatusLineManager getStatusLineManager() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();

		IWorkbenchPage page = win.getActivePage();

		IWorkbenchPart part = page.getActivePart();
		if (part == null)
			return null;
		IWorkbenchPartSite site = part.getSite();
		IActionBars actionBars = null;
		if (site instanceof IEditorSite)
			actionBars = ((IEditorSite) site).getActionBars();
		else if (site instanceof IViewSite)
			actionBars = ((IViewSite) site).getActionBars();
		if (actionBars == null)
			return null;
		return actionBars.getStatusLineManager();
	}

	protected void handleFocusGained() {
		IStatusLineManager statusLineManager = getStatusLineManager();
		if (statusLineManager != null)
			statusLineManager.setMessage(pDescriptor.getDescription());
	}

	protected void handleFocusLost() {
		IStatusLineManager statusLineManager = getStatusLineManager();
		if (statusLineManager != null)
			statusLineManager.setMessage(null);
	}

	public abstract Control getControl();

	/**
	 * Since a property widget can have many controls inside it, this method return
	 * the control to which a border will be added to highlight the widget
	 * 
	 * @return control to border
	 */
	public Control getControlToBorder() {
		return getControl();
	}

	/**
	 * According to the type of the control to highlight will be returned an object
	 * that offer the functionality to put a border on the widget or to set its
	 * background, to highlight it
	 * 
	 * @return An object that offer the functionality to highlight the widget
	 */
	public static IHighlightControl getControlHighlight(Control control) {
		if (control.getClass().equals(Spinner.class))
			return new BackgroundHighlight(control);
		if (control.getClass().equals(Text.class))
			return new BackgroundHighlight(control);
		if (control.getClass().equals(ToolBar.class))
			return new BackgroundHighlight(control);
		if (control.getClass().equals(Combo.class) && !((control.getStyle() & SWT.READ_ONLY) == SWT.READ_ONLY))
			return new BackgroundHighlight(control);
		if (control.getClass().equals(Button.class) && ((control.getStyle() & SWT.CHECK) == SWT.CHECK))
			return new BackgroundHighlight(control);
		if (control.getClass().equals(Button.class) && ((control.getStyle() & SWT.PUSH) == SWT.PUSH))
			return new BorderHightLight(control, Combo.class);
		if (control.getClass().equals(ComboButton.GraphicButton.class))
			return new BackgroundHighlight(control);
		if (control.getClass().equals(WritableComboButton.class))
			return new BackgroundHighlight(control);
		if (control instanceof DoubleControlComposite)
			return new DoubleControlHighlight((DoubleControlComposite) control);
		if (control instanceof Composite)
			return new BorderHightLight(control);
		if (control instanceof Button)
			return new BorderHightLight(control);
		return null;
	}

	/**
	 * highlight the widget by changing its background or by drawing a border around
	 * it for a fixed (depending from the widget) amount of time
	 * 
	 */
	@Override
	public void highLightWidget(final long ms) {
		// if there isn't a control defined where add the border then return
		final Control ctb = getControlToBorder();
		if (ctb == null)
			return;
		if (searchHighLight == null)
			searchHighLight = getControlHighlight(ctb);
		if (searchHighLight == null)
			return;
		// highlight the control
		searchHighLight.highLightControl();
		ctb.forceFocus();
		// Create a thread to remove the paint listener after specified time
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(ms);
					// It need two thread to avoid to freeze the UI during the sleep
					ctb.getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							searchHighLight.deHighLightControl();
							if (errorDeco != null)
								errorDeco.showHoverText(errorDeco.getDescriptionText());
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private static int defCharWidth = -1;

	public static int getCharWidth(Control c) {
		if (defCharWidth < 0)
			defCharWidth = UIUtil.getCharWidth(c);
		return defCharWidth;
	}

	/**
	 * Toggle the visibility of the property widget.
	 */
	public void toggleVisibility(boolean show) {
		// widget label
		if (getLabel().getLayoutData() instanceof GridData) {
			((GridData) getLabel().getLayoutData()).exclude = !show;
		}
		getLabel().setVisible(show);
		getLabel().setEnabled(show);
		// widget control
		if (getControl().getLayoutData() instanceof GridData) {
			((GridData) getControl().getLayoutData()).exclude = !show;
		}
		getControl().setVisible(show);
		getControl().setEnabled(show);
	}

	private IHighlightControl searchHighLight;
	private IHighlightControl errorHighLight;
	private ControlDecoration errorDeco;

	public void resetErrors() {
		if (errorDeco != null)
			errorDeco.hideHover();
		if (getControlToBorder() == null)
			return;
		if (errorHighLight == null)
			return;
		errorHighLight.deHighLightControl(null);
	}

	public void showErrors(String msg, boolean warning) {
		if (errorDeco == null) {
			Control c = getControlToBorder();
			if (c == null)
				c = getControl();
			errorDeco = new ControlDecoration(c, SWT.TOP | SWT.LEFT);
			errorDeco.setShowOnlyOnFocus(true);
		}
		errorDeco.showHoverText(msg);

		if (getControlToBorder() == null)
			return;
		if (errorHighLight == null) {
			errorHighLight = getControlHighlight(getControlToBorder());
			if (errorHighLight instanceof BackgroundHighlight)
				if (warning)
					((BackgroundHighlight) errorHighLight).setColor(ValidationError.WARN);
				else
					((BackgroundHighlight) errorHighLight).setColor(ValidationError.ERROR);
		}
		if (errorHighLight == null)
			return;
		errorHighLight.highLightControl();
	}
}
