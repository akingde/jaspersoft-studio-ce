/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Analytic Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.AnalyticClause#getAbc <em>Abc</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.AnalyticClause#getObc <em>Obc</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.AnalyticClause#getWinc <em>Winc</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getAnalyticClause()
 * @model
 * @generated
 */
public interface AnalyticClause extends EObject
{
  /**
   * Returns the value of the '<em><b>Abc</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Abc</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Abc</em>' containment reference.
   * @see #setAbc(QueryPartitionClause)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getAnalyticClause_Abc()
   * @model containment="true"
   * @generated
   */
  QueryPartitionClause getAbc();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.AnalyticClause#getAbc <em>Abc</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Abc</em>' containment reference.
   * @see #getAbc()
   * @generated
   */
  void setAbc(QueryPartitionClause value);

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
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getAnalyticClause_Obc()
   * @model containment="true"
   * @generated
   */
  OrderByClause getObc();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.AnalyticClause#getObc <em>Obc</em>}' containment reference.
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
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getAnalyticClause_Winc()
   * @model containment="true"
   * @generated
   */
  WindowingClause getWinc();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.AnalyticClause#getWinc <em>Winc</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Winc</em>' containment reference.
   * @see #getWinc()
   * @generated
   */
  void setWinc(WindowingClause value);

} // AnalyticClause
