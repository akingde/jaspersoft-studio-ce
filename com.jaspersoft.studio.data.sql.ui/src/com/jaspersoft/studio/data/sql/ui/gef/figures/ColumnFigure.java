package com.jaspersoft.studio.data.sql.ui.gef.figures;

import org.eclipse.draw2d.ButtonModel;
import org.eclipse.draw2d.ChangeEvent;
import org.eclipse.draw2d.ChangeListener;
import org.eclipse.draw2d.CheckBox;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Label;

public class ColumnFigure extends Figure {
	private CheckBox checkbox;

	/**
	 * Constructs a CheckBox with the passed text in its label.
	 * 
	 * @param text
	 *          The label text
	 * @since 2.0
	 */
	public ColumnFigure(String text) {
		setLayoutManager(new FlowLayout(true));
		checkbox = new CheckBox();
		checkbox.addChangeListener(new ChangeListener() {

			@Override
			public void handleStateChanged(ChangeEvent event) {
				if (event.getPropertyName().equals(ButtonModel.SELECTED_PROPERTY))
					handleSelectionChanged();
			}
		});
		add(checkbox);
		add(new Label(text));
	}

	/**
	 * Adjusts CheckBox's icon depending on selection status.
	 * 
	 * @since 2.0
	 */
	protected void handleSelectionChanged() {

	}

	public boolean isSelected() {
		return checkbox.isSelected();
	}

	public void setSelected(boolean sel) {
		checkbox.setSelected(sel);
	}
}