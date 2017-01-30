package com.jaspersoft.studio.properties.internal;

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.fieldassist.IControlContentAdapter2;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

import net.sf.jasperreports.eclipse.ui.WritableComboButton;

/**
 * An {@link IControlContentAdapter} for SWT Text controls. This is a
 * convenience class for easily creating a {@link ContentProposalAdapter} for
 * text fields.
 *
 * @since 3.2
 */
public class ComboButtonContentAdapter implements IControlContentAdapter,
		IControlContentAdapter2 {

	@Override
	public String getControlContents(Control control) {
		return ((WritableComboButton) control).getText();
	}

	@Override
	public void setControlContents(Control control, String text,
			int cursorPosition) {
		((WritableComboButton) control).setText(text);
		((WritableComboButton) control).setSelection(cursorPosition, cursorPosition);
	}

	@Override
	public void insertControlContents(Control control, String text,
			int cursorPosition) {
		Point selection = ((WritableComboButton) control).getSelection();
		((WritableComboButton) control).insert(text);
		// Insert will leave the cursor at the end of the inserted text. If this
		// is not what we wanted, reset the selection.
		if (cursorPosition < text.length()) {
			((WritableComboButton) control).setSelection(selection.x + cursorPosition,
					selection.x + cursorPosition);
		}
	}

	@Override
	public int getCursorPosition(Control control) {
		return ((WritableComboButton) control).getSelection().y;
	}

	@Override
	public Rectangle getInsertionBounds(Control control) {
		WritableComboButton text = (WritableComboButton) control;
		Point caretOrigin = text.getSelection();
		// We fudge the y pixels due to problems with getCaretLocation
		// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=52520
		return new Rectangle(caretOrigin.x + text.getClientArea().x,
				caretOrigin.y + text.getClientArea().y + 3, 1, text.getLineHeight());
	}

	@Override
	public void setCursorPosition(Control control, int position) {
		((WritableComboButton) control).setSelection(new Point(position, position));
	}

	/**
	 * @see org.eclipse.jface.fieldassist.IControlContentAdapter2#getSelection(org.eclipse.swt.widgets.Control)
	 *
	 * @since 3.4
	 */
	@Override
	public Point getSelection(Control control) {
		return ((WritableComboButton) control).getSelection();
	}

	/**
	 * @see org.eclipse.jface.fieldassist.IControlContentAdapter2#setSelection(org.eclipse.swt.widgets.Control,
	 *      org.eclipse.swt.graphics.Point)
	 *
	 * @since 3.4
	 */
	@Override
	public void setSelection(Control control, Point range) {
		((WritableComboButton) control).setSelection(range);
	}
}
