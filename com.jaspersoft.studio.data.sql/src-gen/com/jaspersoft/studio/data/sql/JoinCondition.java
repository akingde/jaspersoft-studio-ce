/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Join Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.JoinCondition#getUseCols <em>Use Cols</em>}</li>
 * </ul>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getJoinCondition()
 * @model
 * @generated
 */
public interface JoinCondition extends EObject
{
  /**
   * Returns the value of the '<em><b>Use Cols</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Use Cols</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Use Cols</em>' containment reference.
   * @see #setUseCols(UsingCols)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getJoinCondition_UseCols()
   * @model containment="true"
   * @generated
   */
  UsingCols getUseCols();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.JoinCondition#getUseCols <em>Use Cols</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Use Cols</em>' containment reference.
   * @see #getUseCols()
   * @generated
   */
  void setUseCols(UsingCols value);

} // JoinCondition
