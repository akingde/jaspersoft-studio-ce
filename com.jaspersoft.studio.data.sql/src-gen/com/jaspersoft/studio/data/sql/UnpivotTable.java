/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unpivot Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.UnpivotTable#getPcols <em>Pcols</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.UnpivotTable#getPfor <em>Pfor</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.UnpivotTable#getInop <em>Inop</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getUnpivotTable()
 * @model
 * @generated
 */
public interface UnpivotTable extends EObject
{
  /**
   * Returns the value of the '<em><b>Pcols</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pcols</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pcols</em>' containment reference.
   * @see #setPcols(PivotColumns)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getUnpivotTable_Pcols()
   * @model containment="true"
   * @generated
   */
  PivotColumns getPcols();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.UnpivotTable#getPcols <em>Pcols</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pcols</em>' containment reference.
   * @see #getPcols()
   * @generated
   */
  void setPcols(PivotColumns value);

  /**
   * Returns the value of the '<em><b>Pfor</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pfor</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pfor</em>' containment reference.
   * @see #setPfor(PivotForClause)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getUnpivotTable_Pfor()
   * @model containment="true"
   * @generated
   */
  PivotForClause getPfor();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.UnpivotTable#getPfor <em>Pfor</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pfor</em>' containment reference.
   * @see #getPfor()
   * @generated
   */
  void setPfor(PivotForClause value);

  /**
   * Returns the value of the '<em><b>Inop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Inop</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Inop</em>' containment reference.
   * @see #setInop(UnpivotInClause)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getUnpivotTable_Inop()
   * @model containment="true"
   * @generated
   */
  UnpivotInClause getInop();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.UnpivotTable#getInop <em>Inop</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Inop</em>' containment reference.
   * @see #getInop()
   * @generated
   */
  void setInop(UnpivotInClause value);

} // UnpivotTable
