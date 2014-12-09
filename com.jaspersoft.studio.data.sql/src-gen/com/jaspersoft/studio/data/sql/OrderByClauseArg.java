/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Order By Clause Arg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.OrderByClauseArg#getCol <em>Col</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getOrderByClauseArg()
 * @model
 * @generated
 */
public interface OrderByClauseArg extends OrderByClauseArgs
{
  /**
   * Returns the value of the '<em><b>Col</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Col</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Col</em>' containment reference.
   * @see #setCol(AnalyticExprArg)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getOrderByClauseArg_Col()
   * @model containment="true"
   * @generated
   */
  AnalyticExprArg getCol();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.OrderByClauseArg#getCol <em>Col</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Col</em>' containment reference.
   * @see #getCol()
   * @generated
   */
  void setCol(AnalyticExprArg value);

} // OrderByClauseArg
