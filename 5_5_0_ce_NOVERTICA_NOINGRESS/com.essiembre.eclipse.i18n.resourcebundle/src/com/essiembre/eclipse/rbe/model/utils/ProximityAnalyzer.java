/*
 * Copyright (C) 2003, 2004  Pascal Essiembre, Essiembre Consultant Inc.
 * 
 * This file is part of Essiembre ResourceBundle Editor.
 * 
 * Essiembre ResourceBundle Editor is free software; you can redistribute it 
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * Essiembre ResourceBundle Editor is distributed in the hope that it will be 
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with Essiembre ResourceBundle Editor; if not, write to the 
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330, 
 * Boston, MA  02111-1307  USA
 */
package com.essiembre.eclipse.rbe.model.utils;

/**
 * Analyse the proximity of two objects (i.e., how similar they are) and return
 * a proximity level between zero and one.  The higher the return value is, 
 * the closer the two objects are to each other.  "One" does not need to mean 
 * "identical", but it has to be the closest match and analyser can 
 * potentially acheive.
 * @author Pascal Essiembre
 * @version $Author: essiembre $ $Revision: 1.2 $ $Date: 2005/07/30 22:10:55 $
 */
public interface ProximityAnalyzer {
    /**
     * Analyses two objects and return the proximity level.
     * @param obj1 first object to analyse
     * @param obj2 second object to analyse
     * @return proximity level
     */
    double analyse(Object obj1, Object obj2);
}
