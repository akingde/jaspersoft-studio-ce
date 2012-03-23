/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.rulers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.rulers.RulerChangeListener;
import org.eclipse.gef.rulers.RulerProvider;

import com.jaspersoft.studio.editor.gef.rulers.command.CreateGuideCommand;
import com.jaspersoft.studio.editor.gef.rulers.command.DeleteGuideCommand;
import com.jaspersoft.studio.editor.gef.rulers.command.MoveGuideCommand;
/*
 * The Class ReportRulerProvider.
 * 
 * @author Chicu Veaceslav
 */
public class ReportRulerProvider extends RulerProvider {

	/** The ruler. */
	private ReportRuler ruler;

	/** The ruler listener. */
	private PropertyChangeListener rulerListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(ReportRuler.PROPERTY_CHILDREN)) {
				ReportRulerGuide guide = (ReportRulerGuide) evt.getNewValue();
				if (getGuides().contains(guide)) {
					guide.addPropertyChangeListener(guideListener);
				} else {
					guide.removePropertyChangeListener(guideListener);
				}
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i)).notifyGuideReparented(guide);
				}
			} else {
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i)).notifyUnitsChanged(ruler.getUnit());
				}
			}
		}
	};

	/** The guide listener. */
	private PropertyChangeListener guideListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(ReportRulerGuide.PROPERTY_CHILDREN)) {
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i)).notifyPartAttachmentChanged(evt.getNewValue(), evt.getSource());
				}
			} else {
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i)).notifyGuideMoved(evt.getSource());
				}
			}
		}
	};

	/**
	 * Instantiates a new report ruler provider.
	 * 
	 * @param ruler
	 *          the ruler
	 */
	public ReportRulerProvider(ReportRuler ruler) {
		this.ruler = ruler;
		this.ruler.addPropertyChangeListener(rulerListener);
		List<ReportRulerGuide> guides = getGuides();
		for (int i = 0; i < guides.size(); i++) {
			(guides.get(i)).addPropertyChangeListener(guideListener);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.rulers.RulerProvider#getAttachedModelObjects(java.lang.Object)
	 */
	public List<Object> getAttachedModelObjects(Object guide) {
		return new ArrayList<Object>(((ReportRulerGuide) guide).getParts());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.rulers.RulerProvider#getCreateGuideCommand(int)
	 */
	public Command getCreateGuideCommand(int position) {
		return new CreateGuideCommand(ruler, position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.rulers.RulerProvider#getDeleteGuideCommand(java.lang.Object)
	 */
	public Command getDeleteGuideCommand(Object guide) {
		return new DeleteGuideCommand((ReportRulerGuide) guide, ruler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.rulers.RulerProvider#getMoveGuideCommand(java.lang.Object, int)
	 */
	public Command getMoveGuideCommand(Object guide, int pDelta) {
		return new MoveGuideCommand((ReportRulerGuide) guide, pDelta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.rulers.RulerProvider#getGuidePositions()
	 */
	public int[] getGuidePositions() {
		List<ReportRulerGuide> guides = getGuides();
		int[] result = new int[guides.size()];
		for (int i = 0; i < guides.size(); i++) {
			result[i] = (guides.get(i)).getPosition();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.rulers.RulerProvider#getRuler()
	 */
	public Object getRuler() {
		return ruler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.rulers.RulerProvider#getUnit()
	 */
	public int getUnit() {
		return ruler.getUnit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.rulers.RulerProvider#setUnit(int)
	 */
	public void setUnit(int newUnit) {
		ruler.setUnit(newUnit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.rulers.RulerProvider#getGuidePosition(java.lang.Object)
	 */
	public int getGuidePosition(Object guide) {
		return ((ReportRulerGuide) guide).getPosition();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.rulers.RulerProvider#getGuides()
	 */
	public List<ReportRulerGuide> getGuides() {
		return ruler.getGuides();
	}

}
