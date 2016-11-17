/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.widgets.framework.ui.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.swt.widgets.NumericText;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.customizers.shape.Point;

/**
 * Page used to define the number of sides of a polygon shape
 * 
 * @author Orlandin Marco
 *
 */
public class SidesWizardPage extends JSSHelpWizardPage {
	
	private Integer sidesNumber;
	
	public SidesWizardPage() {
		super("polygonDefinitionPage");
		sidesNumber = 3;
		setTitle("Polygon sides");
		setMessage("Define a number equals or greater of 3 to be used as the number of sides of the polygon");
	}
	
	public SidesWizardPage(List<Point> points) {
		this();
		if (points != null){
			sidesNumber = points.size();
		}
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2,  false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		new Label(container, SWT.NONE).setText("Sides");
		final NumericText sides = new NumericText(container, SWT.BORDER, 0, 0);
		sides.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		sides.setValues(sidesNumber, 3, Integer.MAX_VALUE);
		sides.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				sidesNumber = sides.getValueAsInteger();
				getContainer().updateButtons();
			}
			
		});
		setControl(container);
	}

	@Override
	protected String getContextName() {
		return null;
	}

	public boolean isPageComplete() {
		return (sidesNumber != null && sidesNumber > 2);
	};
	
	/**
	 * Compute the points that compose the regular polygon with the specified number of 
	 * sides. To allow a better rounding the radius is fixed to 1000. All the points 
	 * returned are positive
	 * 
	 * @return the list of points that compose the polygon
	 */
	public List<Point> getPoints(){
		List<Point> result = new ArrayList<Point>();
		int radius = 1000;
		
		Integer minX = null;
		Integer minY = null;
		for(int i = 0; i < sidesNumber; i++){
			int x = (int)(radius * Math.cos(2*Math.PI*((float)i/(float)sidesNumber)));
			int y = (int)(radius * Math.sin(2*Math.PI*((float)i/(float)sidesNumber)));
			result.add(new Point(x, y));
			if (minX == null || minX > x){
				minX = x;
			}
			if (minY == null || minY > y){
				minY = y;
			}
		}
		
		//Make all the points positives
		int xOffset = minX < 0 ? Math.abs(minX) : 0;
		int yOffset = minY < 0 ? Math.abs(minY) : 0;
		for(Point point : result){
			point.setX(point.getX() + xOffset);
			point.setY(point.getY() + yOffset);
		}
		
		return result;
	}
}
