/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expr Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.ExprGroup#getIsnot <em>Isnot</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.ExprGroup#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getExprGroup()
 * @model
 * @generated
 */
public interface ExprGroup extends EObject
{
  /**
   * Returns the value of the '<em><b>Isnot</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Isnot</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Isnot</em>' attribute.
   * @see #setIsnot(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getExprGroup_Isnot()
   * @model
   * @generated
   */
  String getIsnot();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ExprGroup#getIsnot <em>Isnot</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Isnot</em>' attribute.
   * @see #getIsnot()
   * @generated
   */
  void setIsnot(String value);

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(OrExpr)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getExprGroup_Expr()
   * @model containment="true"
   * @generated
   */
  OrExpr getExpr();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ExprGroup#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(OrExpr value);

} // ExprGroup
