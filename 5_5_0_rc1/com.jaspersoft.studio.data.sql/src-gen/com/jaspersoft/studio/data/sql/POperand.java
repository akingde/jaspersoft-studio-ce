/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>POperand</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.POperand#getPrm <em>Prm</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getPOperand()
 * @model
 * @generated
 */
public interface POperand extends EObject
{
  /**
   * Returns the value of the '<em><b>Prm</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Prm</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Prm</em>' attribute.
   * @see #setPrm(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getPOperand_Prm()
   * @model
   * @generated
   */
  String getPrm();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.POperand#getPrm <em>Prm</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Prm</em>' attribute.
   * @see #getPrm()
   * @generated
   */
  void setPrm(String value);

} // POperand
