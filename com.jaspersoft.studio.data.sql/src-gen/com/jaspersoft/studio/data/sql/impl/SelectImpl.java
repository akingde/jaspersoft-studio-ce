/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.Columns;
import com.jaspersoft.studio.data.sql.Select;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Select</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getSelect <em>Select</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getCols <em>Cols</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SelectImpl extends ModelImpl implements Select
{
  /**
   * The default value of the '{@link #getSelect() <em>Select</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelect()
   * @generated
   * @ordered
   */
  protected static final String SELECT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSelect() <em>Select</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelect()
   * @generated
   * @ordered
   */
  protected String select = SELECT_EDEFAULT;

  /**
   * The cached value of the '{@link #getCols() <em>Cols</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCols()
   * @generated
   * @ordered
   */
  protected Columns cols;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SelectImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return SqlPackage.Literals.SELECT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSelect()
  {
    return select;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSelect(String newSelect)
  {
    String oldSelect = select;
    select = newSelect;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__SELECT, oldSelect, select));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Columns getCols()
  {
    return cols;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCols(Columns newCols, NotificationChain msgs)
  {
    Columns oldCols = cols;
    cols = newCols;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__COLS, oldCols, newCols);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCols(Columns newCols)
  {
    if (newCols != cols)
    {
      NotificationChain msgs = null;
      if (cols != null)
        msgs = ((InternalEObject)cols).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__COLS, null, msgs);
      if (newCols != null)
        msgs = ((InternalEObject)newCols).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__COLS, null, msgs);
      msgs = basicSetCols(newCols, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__COLS, newCols, newCols));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case SqlPackage.SELECT__COLS:
        return basicSetCols(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case SqlPackage.SELECT__SELECT:
        return getSelect();
      case SqlPackage.SELECT__COLS:
        return getCols();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SqlPackage.SELECT__SELECT:
        setSelect((String)newValue);
        return;
      case SqlPackage.SELECT__COLS:
        setCols((Columns)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case SqlPackage.SELECT__SELECT:
        setSelect(SELECT_EDEFAULT);
        return;
      case SqlPackage.SELECT__COLS:
        setCols((Columns)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case SqlPackage.SELECT__SELECT:
        return SELECT_EDEFAULT == null ? select != null : !SELECT_EDEFAULT.equals(select);
      case SqlPackage.SELECT__COLS:
        return cols != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (select: ");
    result.append(select);
    result.append(')');
    return result.toString();
  }

} //SelectImpl
