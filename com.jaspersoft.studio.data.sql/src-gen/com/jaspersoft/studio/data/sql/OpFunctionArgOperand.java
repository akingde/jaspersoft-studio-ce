/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Op Function Arg Operand</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.OpFunctionArgOperand#getOp <em>Op</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getOpFunctionArgOperand()
 * @model
 * @generated
 */
public interface OpFunctionArgOperand extends OpFunctionArg
{
  /**
   * Returns the value of the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' containment reference.
   * @see #setOp(OpFunctionArgAgregate)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getOpFunctionArgOperand_Op()
   * @model containment="true"
   * @generated
   */
  OpFunctionArgAgregate getOp();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.OpFunctionArgOperand#getOp <em>Op</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' containment reference.
   * @see #getOp()
   * @generated
   */
  void setOp(OpFunctionArgAgregate value);

} // OpFunctionArgOperand
