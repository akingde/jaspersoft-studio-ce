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
 * Copyright (c) 2006-2009 Nicolas Richeton. All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors : Nicolas Richeton (nicolas.richeton@gmail.com) - initial API and implementation
 * *****************************************************************************
 */

package org.eclipse.nebula.widgets.gallery;

import org.eclipse.nebula.animation.effects.AbstractEffect;
import org.eclipse.nebula.animation.movement.IMovement;

/*
 * <p> Animation used internally on collapse / expand events. Should not be used directly. </p> <p> NOTE: THIS WIDGET
 * AND ITS API ARE STILL UNDER DEVELOPMENT. </p>
 * 
 * @see AnimationRunner#runEffect(org.eclipse.nebula.animation.effects.IEffect)
 * 
 * @author Nicolas Richeton (nicolas.richeton@gmail.com)
 */
public class GalleryGroupResizeEffect extends AbstractEffect {

	int src, dest, diff;
	GalleryItem item = null;

	/**
	 * Set up a new resize effect on a gallery item.
	 * 
	 * 
	 * @param item
	 * @param src
	 * @param dest
	 * @param lengthMilli
	 * @param movement
	 * @param onStop
	 * @param onCancel
	 */
	public GalleryGroupResizeEffect(GalleryItem item, int src, int dest, long lengthMilli, IMovement movement,
			Runnable onStop, Runnable onCancel) {
		super(lengthMilli, movement, onStop, onCancel);

		this.src = src;
		this.dest = dest;
		this.diff = dest - src;

		easingFunction.init(0, 1, (int) lengthMilli);

		this.item = item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sharemedia.ui.sat.AbstractEffect#applyEffect(long)
	 */
	public void applyEffect(final long currentTime) {
		if (!item.isDisposed()) {
			double value = src + diff * easingFunction.getValue((int) currentTime);

			item.setData(DefaultGalleryGroupRenderer.DATA_ANIMATION, new Double(value));

			item.getParent().updateStructuralValues(null, false);
			item.getParent().updateScrollBarsProperties();
			item.getParent().redraw();

		}
	}
}
