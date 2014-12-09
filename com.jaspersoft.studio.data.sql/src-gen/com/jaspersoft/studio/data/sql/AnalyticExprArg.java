/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Analytic Expr Arg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.AnalyticExprArg#getCe <em>Ce</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.AnalyticExprArg#getColAlias <em>Col Alias</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getAnalyticExprArg()
 * @model
 * @generated
 */
public interface AnalyticExprArg extends AnalyticExprArgs
{
  /**
   * Returns the value of the '<em><b>Ce</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ce</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ce</em>' containment reference.
   * @see #setCe(Operands)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getAnalyticExprArg_Ce()
   * @model containment="true"
   * @generated
   */
  Operands getCe();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.AnalyticExprArg#getCe <em>Ce</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ce</em>' containment reference.
   * @see #getCe()
   * @generated
   */
  void setCe(Operands value);

  /**
   * Returns the value of the '<em><b>Col Alias</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Col Alias</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Col Alias</em>' containment reference.
   * @see #setColAlias(DbObjectName)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getAnalyticExprArg_ColAlias()
   * @model containment="true"
   * @generated
   */
  DbObjectName getColAlias();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.AnalyticExprArg#getColAlias <em>Col Alias</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Col Alias</em>' containment reference.
   * @see #getColAlias()
   * @generated
   */
  void setColAlias(DbObjectName value);

} // AnalyticExprArg
