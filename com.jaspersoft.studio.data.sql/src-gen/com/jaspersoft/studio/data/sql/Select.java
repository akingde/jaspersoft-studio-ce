/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Select</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.Select#getSelect <em>Select</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Select#getCols <em>Cols</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect()
 * @model
 * @generated
 */
public interface Select extends Model
{
  /**
   * Returns the value of the '<em><b>Select</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Select</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Select</em>' attribute.
   * @see #setSelect(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect_Select()
   * @model
   * @generated
   */
  String getSelect();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Select#getSelect <em>Select</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Select</em>' attribute.
   * @see #getSelect()
   * @generated
   */
  void setSelect(String value);

  /**
   * Returns the value of the '<em><b>Cols</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cols</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cols</em>' containment reference.
   * @see #setCols(Columns)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect_Cols()
   * @model containment="true"
   * @generated
   */
  Columns getCols();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Select#getCols <em>Cols</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cols</em>' containment reference.
   * @see #getCols()
   * @generated
   */
  void setCols(Columns value);

} // Select
