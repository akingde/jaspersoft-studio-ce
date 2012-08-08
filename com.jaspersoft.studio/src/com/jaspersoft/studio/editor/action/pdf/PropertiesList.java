/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.pdf;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Static class used to register a PDF properties, needed to have a common and easy
 * expandable list of properties. Knowing them it is important because when a new property 
 * is set for a model, all other pdf properties must be deleted (a model can have only one 
 * pdf property).
 * @author Orlandin Marco
 *
 */
public class PropertiesList {
	
	/**
	 * Contain the list of registered properties
	 */
	private static HashSet<String> propertyList = new HashSet<String>();
 
 /**
  * Register a new property
  * @param newItem id of the new property
  */
	public static void AddItem(String newItem){
		propertyList.add(newItem);
		
	}
	 
	
	/**
	 * Return an iterator to a property of the list
	 * @return Iterator to a property, use HasNex()t to know it there are
	 * an element to read and Next() to read it
	 */
	public static Iterator<String> GetIterator(){
		return propertyList.iterator();
	}
	
	/**
	 * Size of the list
	 * @return number of registered elements
	 */
	public static int Size(){
		return propertyList.size();
	}
	
	
 
}
