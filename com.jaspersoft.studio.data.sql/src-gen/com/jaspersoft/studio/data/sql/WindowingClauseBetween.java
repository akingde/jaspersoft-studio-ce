/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Windowing Clause Between</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.WindowingClauseBetween#getWcoP <em>Wco P</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.WindowingClauseBetween#getWcoF <em>Wco F</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getWindowingClauseBetween()
 * @model
 * @generated
 */
public interface WindowingClauseBetween extends WindowingClause
{
  /**
   * Returns the value of the '<em><b>Wco P</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wco P</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Wco P</em>' containment reference.
   * @see #setWcoP(WindowingClauseOperandPreceding)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getWindowingClauseBetween_WcoP()
   * @model containment="true"
   * @generated
   */
  WindowingClauseOperandPreceding getWcoP();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.WindowingClauseBetween#getWcoP <em>Wco P</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Wco P</em>' containment reference.
   * @see #getWcoP()
   * @generated
   */
  void setWcoP(WindowingClauseOperandPreceding value);

  /**
   * Returns the value of the '<em><b>Wco F</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wco F</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Wco F</em>' containment reference.
   * @see #setWcoF(WindowingClauseOperandFollowing)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getWindowingClauseBetween_WcoF()
   * @model containment="true"
   * @generated
   */
  WindowingClauseOperandFollowing getWcoF();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.WindowingClauseBetween#getWcoF <em>Wco F</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Wco F</em>' containment reference.
   * @see #getWcoF()
   * @generated
   */
  void setWcoF(WindowingClauseOperandFollowing value);

} // WindowingClauseBetween
