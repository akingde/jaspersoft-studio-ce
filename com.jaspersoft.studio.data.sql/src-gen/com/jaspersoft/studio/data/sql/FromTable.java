/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>From Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.FromTable#getTable <em>Table</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.FromTable#getJoin <em>Join</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.FromTable#getOnTable <em>On Table</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.FromTable#getJoinExpr <em>Join Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getFromTable()
 * @model
 * @generated
 */
public interface FromTable extends OrTable
{
  /**
   * Returns the value of the '<em><b>Table</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Table</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Table</em>' containment reference.
   * @see #setTable(TableOrAlias)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFromTable_Table()
   * @model containment="true"
   * @generated
   */
  TableOrAlias getTable();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FromTable#getTable <em>Table</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Table</em>' containment reference.
   * @see #getTable()
   * @generated
   */
  void setTable(TableOrAlias value);

  /**
   * Returns the value of the '<em><b>Join</b></em>' attribute.
   * The literals are from the enumeration {@link com.jaspersoft.studio.data.sql.JoinType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Join</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Join</em>' attribute.
   * @see com.jaspersoft.studio.data.sql.JoinType
   * @see #setJoin(JoinType)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFromTable_Join()
   * @model
   * @generated
   */
  JoinType getJoin();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FromTable#getJoin <em>Join</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Join</em>' attribute.
   * @see com.jaspersoft.studio.data.sql.JoinType
   * @see #getJoin()
   * @generated
   */
  void setJoin(JoinType value);

  /**
   * Returns the value of the '<em><b>On Table</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>On Table</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>On Table</em>' containment reference.
   * @see #setOnTable(TableOrAlias)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFromTable_OnTable()
   * @model containment="true"
   * @generated
   */
  TableOrAlias getOnTable();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FromTable#getOnTable <em>On Table</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>On Table</em>' containment reference.
   * @see #getOnTable()
   * @generated
   */
  void setOnTable(TableOrAlias value);

  /**
   * Returns the value of the '<em><b>Join Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Join Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Join Expr</em>' containment reference.
   * @see #setJoinExpr(OrExpr)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFromTable_JoinExpr()
   * @model containment="true"
   * @generated
   */
  OrExpr getJoinExpr();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FromTable#getJoinExpr <em>Join Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Join Expr</em>' containment reference.
   * @see #getJoinExpr()
   * @generated
   */
  void setJoinExpr(OrExpr value);

} // FromTable
