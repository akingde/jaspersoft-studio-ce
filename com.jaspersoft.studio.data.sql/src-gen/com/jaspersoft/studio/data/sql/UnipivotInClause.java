/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unipivot In Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.UnipivotInClause#getOp <em>Op</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.UnipivotInClause#getArgs <em>Args</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getUnipivotInClause()
 * @model
 * @generated
 */
public interface UnipivotInClause extends UnpivotInClause
{
  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute.
   * @see #setOp(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getUnipivotInClause_Op()
   * @model
   * @generated
   */
  String getOp();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.UnipivotInClause#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see #getOp()
   * @generated
   */
  void setOp(String value);

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
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getUnipivotInClause_Args()
   * @model containment="true"
   * @generated
   */
  UnpivotInClauseArgs getArgs();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.UnipivotInClause#getArgs <em>Args</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Args</em>' containment reference.
   * @see #getArgs()
   * @generated
   */
  void setArgs(UnpivotInClauseArgs value);

} // UnipivotInClause
