/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operand Function Arguments</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.OperandFunctionArguments#getFname <em>Fname</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.OperandFunctionArguments#getArg <em>Arg</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getOperandFunctionArguments()
 * @model
 * @generated
 */
public interface OperandFunctionArguments extends OperandFunction
{
  /**
   * Returns the value of the '<em><b>Fname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fname</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fname</em>' attribute.
   * @see #setFname(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getOperandFunctionArguments_Fname()
   * @model
   * @generated
   */
  String getFname();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.OperandFunctionArguments#getFname <em>Fname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fname</em>' attribute.
   * @see #getFname()
   * @generated
   */
  void setFname(String value);

  /**
   * Returns the value of the '<em><b>Arg</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arg</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arg</em>' containment reference.
   * @see #setArg(OperandFunctionArgs)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getOperandFunctionArguments_Arg()
   * @model containment="true"
   * @generated
   */
  OperandFunctionArgs getArg();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.OperandFunctionArguments#getArg <em>Arg</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Arg</em>' containment reference.
   * @see #getArg()
   * @generated
   */
  void setArg(OperandFunctionArgs value);

} // OperandFunctionArguments
