/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.barcode;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.components.barbecue.BarbecueComponent;
import net.sf.jasperreports.components.barcode4j.CodabarComponent;
import net.sf.jasperreports.components.barcode4j.Code128Component;
import net.sf.jasperreports.components.barcode4j.Code39Component;
import net.sf.jasperreports.components.barcode4j.DataMatrixComponent;
import net.sf.jasperreports.components.barcode4j.EAN128Component;
import net.sf.jasperreports.components.barcode4j.EAN13Component;
import net.sf.jasperreports.components.barcode4j.EAN8Component;
import net.sf.jasperreports.components.barcode4j.Interleaved2Of5Component;
import net.sf.jasperreports.components.barcode4j.PDF417Component;
import net.sf.jasperreports.components.barcode4j.POSTNETComponent;
import net.sf.jasperreports.components.barcode4j.RoyalMailCustomerComponent;
import net.sf.jasperreports.components.barcode4j.UPCAComponent;
import net.sf.jasperreports.components.barcode4j.UPCEComponent;
import net.sf.jasperreports.components.barcode4j.USPSIntelligentMailComponent;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.IComponentFactory;
import com.jaspersoft.studio.barcode.command.CreateBarcodeCommand;
import com.jaspersoft.studio.barcode.model.MBarcode;
import com.jaspersoft.studio.barcode.model.MBarcodeBarbecue;
import com.jaspersoft.studio.barcode.model.barcode4j.MBarcode4j;
import com.jaspersoft.studio.barcode.model.barcode4j.MCodabar;
import com.jaspersoft.studio.barcode.model.barcode4j.MCode128;
import com.jaspersoft.studio.barcode.model.barcode4j.MCode39;
import com.jaspersoft.studio.barcode.model.barcode4j.MDataMatrix;
import com.jaspersoft.studio.barcode.model.barcode4j.MEAN128;
import com.jaspersoft.studio.barcode.model.barcode4j.MEAN13;
import com.jaspersoft.studio.barcode.model.barcode4j.MEAN8;
import com.jaspersoft.studio.barcode.model.barcode4j.MInterleaved2Of5;
import com.jaspersoft.studio.barcode.model.barcode4j.MPDF417;
import com.jaspersoft.studio.barcode.model.barcode4j.MPOSTNET;
import com.jaspersoft.studio.barcode.model.barcode4j.MRoyalMail;
import com.jaspersoft.studio.barcode.model.barcode4j.MUPCA;
import com.jaspersoft.studio.barcode.model.barcode4j.MUPCE;
import com.jaspersoft.studio.barcode.model.barcode4j.MUSPSIntelligent;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;

public class BarcodeComponentFactory implements IComponentFactory {

	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignComponentElement) {
			if (((JRDesignComponentElement) jrObject).getComponent() instanceof BarbecueComponent)
				return new MBarcodeBarbecue(parent, (JRDesignComponentElement) jrObject, newIndex);
			else if (((JRDesignComponentElement) jrObject).getComponent() instanceof CodabarComponent)
				return new MCodabar(parent, (JRDesignComponentElement) jrObject, newIndex);
			else if (((JRDesignComponentElement) jrObject).getComponent() instanceof Code128Component)
				return new MCode128(parent, (JRDesignComponentElement) jrObject, newIndex);
			else if (((JRDesignComponentElement) jrObject).getComponent() instanceof Code39Component)
				return new MCode39(parent, (JRDesignComponentElement) jrObject, newIndex);
			else if (((JRDesignComponentElement) jrObject).getComponent() instanceof DataMatrixComponent)
				return new MDataMatrix(parent, (JRDesignComponentElement) jrObject, newIndex);
			else if (((JRDesignComponentElement) jrObject).getComponent() instanceof EAN128Component)
				return new MEAN128(parent, (JRDesignComponentElement) jrObject, newIndex);
			else if (((JRDesignComponentElement) jrObject).getComponent() instanceof EAN13Component)
				return new MEAN13(parent, (JRDesignComponentElement) jrObject, newIndex);
			else if (((JRDesignComponentElement) jrObject).getComponent() instanceof EAN8Component)
				return new MEAN8(parent, (JRDesignComponentElement) jrObject, newIndex);
			else if (((JRDesignComponentElement) jrObject).getComponent() instanceof Interleaved2Of5Component)
				return new MInterleaved2Of5(parent, (JRDesignComponentElement) jrObject, newIndex);
			else if (((JRDesignComponentElement) jrObject).getComponent() instanceof PDF417Component)
				return new MPDF417(parent, (JRDesignComponentElement) jrObject, newIndex);
			else if (((JRDesignComponentElement) jrObject).getComponent() instanceof POSTNETComponent)
				return new MPOSTNET(parent, (JRDesignComponentElement) jrObject, newIndex);
			else if (((JRDesignComponentElement) jrObject).getComponent() instanceof RoyalMailCustomerComponent)
				return new MRoyalMail(parent, (JRDesignComponentElement) jrObject, newIndex);
			else if (((JRDesignComponentElement) jrObject).getComponent() instanceof UPCAComponent)
				return new MUPCA(parent, (JRDesignComponentElement) jrObject, newIndex);
			else if (((JRDesignComponentElement) jrObject).getComponent() instanceof UPCEComponent)
				return new MUPCE(parent, (JRDesignComponentElement) jrObject, newIndex);
			else if (((JRDesignComponentElement) jrObject).getComponent() instanceof USPSIntelligentMailComponent)
				return new MUSPSIntelligent(parent, (JRDesignComponentElement) jrObject, newIndex);
		}
		return null;
	}

	public IFigure createFigure(ANode node) {
		if (node instanceof MBarcodeBarbecue || node instanceof MBarcode4j)
			return new ComponentFigure();
		return null;
	}

	public List<?> getChildren4Element(Object jrObject) {

		return null;
	}

	public List<Class<?>> getPaletteEntries() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(MBarcodeBarbecue.class);
		return list;
	}

	public Command getCreateCommand(ANode parent, ANode child, Point location, int newIndex) {
		if (child instanceof MBarcode) {
			if (parent instanceof MElementGroup)
				return new CreateBarcodeCommand((MElementGroup) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MBand)
				return new CreateBarcodeCommand((MBand) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MFrame)
				return new CreateBarcodeCommand((MFrame) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MReport)
				return new CreateBarcodeCommand(parent, (MGraphicElement) child, location, newIndex);

			if (parent instanceof IGroupElement) {
				return new CreateBarcodeCommand(parent, (MGraphicElement) child, location, newIndex);
			}
		}
		return null;
	}

	public Command getDeleteCommand(ANode parent, ANode child) {
		return null;
	}

	public Command getReorderCommand(ANode parent, ANode child, int newIndex) {
		return null;
	}

	public List<Action> getActions(WorkbenchPart part) {
		return null;
	}

	public List<String> getActionsID() {
		return null;
	}

	public EditPart createEditPart(EditPart context, Object model) {
		return null;
	}

	public Command getOrphanCommand(ANode parent, ANode child) {
		// TODO Auto-generated method stub
		return null;
	}

}
