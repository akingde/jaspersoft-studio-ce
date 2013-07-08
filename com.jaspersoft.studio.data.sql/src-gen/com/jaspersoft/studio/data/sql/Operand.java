/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operand</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.Operand#getScalar <em>Scalar</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getOperand()
 * @model
 * @generated
 */
public interface Operand extends EObject
{
  /**
   * Returns the value of the '<em><b>Scalar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scalar</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scalar</em>' attribute.
   * @see #setScalar(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getOperand_Scalar()
   * @model
   * @generated
   */
  String getScalar();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Operand#getScalar <em>Scalar</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Scalar</em>' attribute.
   * @see #getScalar()
   * @generated
   */
  void setScalar(String value);

} // Operand
