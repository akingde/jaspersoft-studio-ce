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
/*
 * Copyright (c) 2006-2009 Nicolas Richeton.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors :
 *    Nicolas Richeton (nicolas.richeton@gmail.com) - initial API and implementation
 *******************************************************************************/

package org.eclipse.nebula.animation.movement;
/*
 * Moves at a constant speed.
 * 
 * @author Nicolas Richeton
 */
public class LinearInOut extends AbstractMovement {

	double increment;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sharemedia.gui.viewers.impl.gl.IMovement#getValue(int)
	 */
	public double getValue(double step) {
		return min + increment * step;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sharemedia.gui.viewers.impl.gl.IMovement#init(float, float, int)
	 */
	public void init(double min, double max, int steps) {
		increment = (max - min) / steps;
		super.init(min, max, steps);
	}

}
