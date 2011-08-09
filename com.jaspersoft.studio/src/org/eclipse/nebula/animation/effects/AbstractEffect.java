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

package org.eclipse.nebula.animation.effects;

import org.eclipse.nebula.animation.movement.IMovement;
/*
 * Abstract implementation for an effect.
 * 
 * @author Nicolas Richeton
 * 
 */
public abstract class AbstractEffect implements IEffect {

	protected boolean done = false;
	protected IMovement easingFunction;
	protected long length = 0;
	protected Runnable runnableOnCancel = null;
	protected Runnable runnableOnStop = null;

	/**
	 * Create a new effect.
	 * 
	 * @param lengthMilli
	 * @param movement
	 */
	public AbstractEffect(long lengthMilli, IMovement movement) {
		this(lengthMilli, movement, null, null);
	}

	/**
	 * Create a new effect, with listeners for stop and cancel events.
	 * 
	 * @param lengthMilli
	 * @param movement
	 * @param onStop
	 * @param onCancel
	 */
	public AbstractEffect(long lengthMilli, IMovement movement,
			Runnable onStop, Runnable onCancel) {
		this.length = lengthMilli;
		easingFunction = movement;
		this.runnableOnCancel = onCancel;
		this.runnableOnStop = onStop;
	}

	/**
	 * Apply this effect.
	 * 
	 * @param currentTime
	 */
	public abstract void applyEffect(final long currentTime);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.nebula.animation.effects.IEffect#cancel()
	 */
	public void cancel() {
		done = true;
		doCancel();
	}

	/**
	 * Run the onCancel runnable if any.
	 */
	protected void doCancel() {
		if (runnableOnCancel != null)
			runnableOnCancel.run();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.nebula.animation.effects.IEffect#doEffect()
	 */
	public void doEffect() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.nebula.animation.effects.IEffect#doEffect(long)
	 */
	public void doEffect(long time) {
		long currentTime = time;
		if (currentTime > length) {
			currentTime = length;
		}
		applyEffect(currentTime);
		processEnd(currentTime);
	}

	/**
	 * Run the onStop runnable if any.
	 */
	protected void doStop() {
		if (runnableOnStop != null)
			runnableOnStop.run();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.nebula.animation.effects.IEffect#getLength()
	 */
	public long getLength() {
		return length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.nebula.animation.effects.IEffect#isDone()
	 */
	public boolean isDone() {
		return done;
	}

	/**
	 * Check if the effect has ended. In that case, start the onStop runnable.
	 */
	public void processEnd(long time) {
		if (done)
			return;

		if (time == length) {
			done = true;
			doStop();
		}
	}
}
