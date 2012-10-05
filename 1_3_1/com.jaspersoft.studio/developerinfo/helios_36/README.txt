===============================================================================
This text file is used to track all compatibility problems found in supporting
the Eclipse version 3.6.
Once the support for this platform will be dropped it will be possible to 
revert the compatibility code in order to use the latest API available from 3.7.
=============================================================================== 

1. JavaJRExpressionSwitch should extend the class org.eclipse.emf.ecore.util.Switch.
This class was introduced in EMF 2.7. Solution is to copy the code and create a 
Switch class in the plugin.

2. TableCellResizableEditPolicy missing constructor for PrecisionRectangle class.
Solution was to change to compatible constructor. Line 140 and 294.

3. CrosstabCellResizableEditPolicy missing constructor for PrecisionRectangle class.
Solution was to change to a compatible constructor. Line 114.

4. ColorStyledText can not use SWT.SPACE constant. Solution was to replace with the
' ' character. Line 235.

5. ElementResizableEditPolicy missing methods. Some methods were added as they are in 3.7.
Plus an @Override annotation was removed because the method was not still declared in 3.6.

6. Methods for manipulation of the size and dimension
	CellResizeHandleLocator2: line 94
	CreateElementCommand: line 277
	DeleteElementCommand: line 97,104
	LayoutSection: line 103,111
	SetConstraintCommand: line 145
	SetPageConstraintCommand: line 121
	PageLayoutEditPolicy: line 179,188
	BandEditPart: line 338
	FrameFigureEditPart: 149

7. Introduced the package com.jaspersoft.studio.utils.compatibility inside the plugin
com.jaspersoft.studio. See references to classes inside it.