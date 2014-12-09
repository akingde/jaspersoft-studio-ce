/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Query Partition Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.QueryPartitionClause#getObc <em>Obc</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.QueryPartitionClause#getWinc <em>Winc</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.QueryPartitionClause#getArgs <em>Args</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getQueryPartitionClause()
 * @model
 * @generated
 */
public interface QueryPartitionClause extends AnalyticClause
{
  /**
   * Returns the value of the '<em><b>Obc</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Obc</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Obc</em>' containment reference.
   * @see #setObc(OrderByClause)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getQueryPartitionClause_Obc()
   * @model containment="true"
   * @generated
   */
  OrderByClause getObc();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.QueryPartitionClause#getObc <em>Obc</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Obc</em>' containment reference.
   * @see #getObc()
   * @generated
   */
  void setObc(OrderByClause value);

  /**
   * Returns the value of the '<em><b>Winc</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Winc</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Winc</em>' containment reference.
   * @see #setWinc(WindowingClause)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getQueryPartitionClause_Winc()
   * @model containment="true"
   * @generated
   */
  WindowingClause getWinc();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.QueryPartitionClause#getWinc <em>Winc</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Winc</em>' containment reference.
   * @see #getWinc()
   * @generated
   */
  void setWinc(WindowingClause value);

  /**
   * Returns the value of the '<em><b>Args</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Args</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Args</em>' containment reference.
   * @see #setArgs(AnalyticExprArgs)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getQueryPartitionClause_Args()
   * @model containment="true"
   * @generated
   */
  AnalyticExprArgs getArgs();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.QueryPartitionClause#getArgs <em>Args</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Args</em>' containment reference.
   * @see #getArgs()
   * @generated
   */
  void setArgs(AnalyticExprArgs value);

} // QueryPartitionClause
