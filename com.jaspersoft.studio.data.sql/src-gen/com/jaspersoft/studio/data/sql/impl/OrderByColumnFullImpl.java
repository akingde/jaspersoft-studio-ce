/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.ColumnFull;
import com.jaspersoft.studio.data.sql.OrderByColumnFull;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Order By Column Full</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.OrderByColumnFullImpl#getColOrder <em>Col Order</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrderByColumnFullImpl extends OrderByColumnsImpl implements OrderByColumnFull
{
  /**
   * The cached value of the '{@link #getColOrder() <em>Col Order</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColOrder()
   * @generated
   * @ordered
   */
  protected ColumnFull colOrder;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected OrderByColumnFullImpl()
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
    return SqlPackage.Literals.ORDER_BY_COLUMN_FULL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ColumnFull getColOrder()
  {
    return colOrder;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetColOrder(ColumnFull newColOrder, NotificationChain msgs)
  {
    ColumnFull oldColOrder = colOrder;
    colOrder = newColOrder;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.ORDER_BY_COLUMN_FULL__COL_ORDER, oldColOrder, newColOrder);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setColOrder(ColumnFull newColOrder)
  {
    if (newColOrder != colOrder)
    {
      NotificationChain msgs = null;
      if (colOrder != null)
        msgs = ((InternalEObject)colOrder).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.ORDER_BY_COLUMN_FULL__COL_ORDER, null, msgs);
      if (newColOrder != null)
        msgs = ((InternalEObject)newColOrder).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.ORDER_BY_COLUMN_FULL__COL_ORDER, null, msgs);
      msgs = basicSetColOrder(newColOrder, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.ORDER_BY_COLUMN_FULL__COL_ORDER, newColOrder, newColOrder));
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
      case SqlPackage.ORDER_BY_COLUMN_FULL__COL_ORDER:
        return basicSetColOrder(null, msgs);
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
      case SqlPackage.ORDER_BY_COLUMN_FULL__COL_ORDER:
        return getColOrder();
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
      case SqlPackage.ORDER_BY_COLUMN_FULL__COL_ORDER:
        setColOrder((ColumnFull)newValue);
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
      case SqlPackage.ORDER_BY_COLUMN_FULL__COL_ORDER:
        setColOrder((ColumnFull)null);
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
      case SqlPackage.ORDER_BY_COLUMN_FULL__COL_ORDER:
        return colOrder != null;
    }
    return super.eIsSet(featureID);
  }

} //OrderByColumnFullImpl
