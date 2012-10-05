/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * This interface should be implemented by those classes that 
 * are supposed to provide information to deal with data preview.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public interface IDataPreviewInfoProvider {

	/**
	 * @return the jasper reports configuration
	 */
	JasperReportsConfiguration getJasperReportsConfig();
	
	/**
	 * @return the data adapter currently set
	 */
	DataAdapterDescriptor getDataAdapterDescriptor();

	/**
	 * @return the design dataset
	 */
	JRDesignDataset getDesignDataset();
	
	/**
	 * @return the list of fields currently selected for preview
	 */
	List<JRDesignField> getFieldsForPreview();
	
}
