/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pivot In Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.PivotInClause#getSq <em>Sq</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.PivotInClause#getArgs <em>Args</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.PivotInClause#getPinany <em>Pinany</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getPivotInClause()
 * @model
 * @generated
 */
public interface PivotInClause extends EObject
{
  /**
   * Returns the value of the '<em><b>Sq</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sq</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sq</em>' containment reference.
   * @see #setSq(SubQueryOperand)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getPivotInClause_Sq()
   * @model containment="true"
   * @generated
   */
  SubQueryOperand getSq();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.PivotInClause#getSq <em>Sq</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sq</em>' containment reference.
   * @see #getSq()
   * @generated
   */
  void setSq(SubQueryOperand value);

  /**
   * Returns the value of the '<em><b>Args</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Args</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Args</em>' containment reference.
   * @see #setArgs(UnpivotInClauseArgs)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getPivotInClause_Args()
   * @model containment="true"
   * @generated
   */
  UnpivotInClauseArgs getArgs();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.PivotInClause#getArgs <em>Args</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Args</em>' containment reference.
   * @see #getArgs()
   * @generated
   */
  void setArgs(UnpivotInClauseArgs value);

  /**
   * Returns the value of the '<em><b>Pinany</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pinany</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pinany</em>' attribute.
   * @see #setPinany(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getPivotInClause_Pinany()
   * @model
   * @generated
   */
  String getPinany();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.PivotInClause#getPinany <em>Pinany</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pinany</em>' attribute.
   * @see #getPinany()
   * @generated
   */
  void setPinany(String value);

} // PivotInClause
