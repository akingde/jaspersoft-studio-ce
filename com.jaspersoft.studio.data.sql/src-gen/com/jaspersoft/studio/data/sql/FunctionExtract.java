/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Extract</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.FunctionExtract#getV <em>V</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.FunctionExtract#getOperand <em>Operand</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getFunctionExtract()
 * @model
 * @generated
 */
public interface FunctionExtract extends EObject
{
  /**
   * Returns the value of the '<em><b>V</b></em>' attribute.
   * The literals are from the enumeration {@link com.jaspersoft.studio.data.sql.EXTRACT_VALUES}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>V</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>V</em>' attribute.
   * @see com.jaspersoft.studio.data.sql.EXTRACT_VALUES
   * @see #setV(EXTRACT_VALUES)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFunctionExtract_V()
   * @model
   * @generated
   */
  EXTRACT_VALUES getV();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FunctionExtract#getV <em>V</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>V</em>' attribute.
   * @see com.jaspersoft.studio.data.sql.EXTRACT_VALUES
   * @see #getV()
   * @generated
   */
  void setV(EXTRACT_VALUES value);

  /**
   * Returns the value of the '<em><b>Operand</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Operand</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Operand</em>' containment reference.
   * @see #setOperand(Operands)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFunctionExtract_Operand()
   * @model containment="true"
   * @generated
   */
  Operands getOperand();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FunctionExtract#getOperand <em>Operand</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Operand</em>' containment reference.
   * @see #getOperand()
   * @generated
   */
  void setOperand(Operands value);

} // FunctionExtract
