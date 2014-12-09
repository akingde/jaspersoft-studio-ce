/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Op Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.OpFunction#getFname <em>Fname</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.OpFunction#getArgs <em>Args</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.OpFunction#getFan <em>Fan</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getOpFunction()
 * @model
 * @generated
 */
public interface OpFunction extends EObject
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
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getOpFunction_Fname()
   * @model
   * @generated
   */
  String getFname();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.OpFunction#getFname <em>Fname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fname</em>' attribute.
   * @see #getFname()
   * @generated
   */
  void setFname(String value);

  /**
   * Returns the value of the '<em><b>Args</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Args</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Args</em>' containment reference.
   * @see #setArgs(OpFunctionArg)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getOpFunction_Args()
   * @model containment="true"
   * @generated
   */
  OpFunctionArg getArgs();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.OpFunction#getArgs <em>Args</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Args</em>' containment reference.
   * @see #getArgs()
   * @generated
   */
  void setArgs(OpFunctionArg value);

  /**
   * Returns the value of the '<em><b>Fan</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fan</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fan</em>' containment reference.
   * @see #setFan(FunctionAnalytical)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getOpFunction_Fan()
   * @model containment="true"
   * @generated
   */
  FunctionAnalytical getFan();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.OpFunction#getFan <em>Fan</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fan</em>' containment reference.
   * @see #getFan()
   * @generated
   */
  void setFan(FunctionAnalytical value);

} // OpFunction
