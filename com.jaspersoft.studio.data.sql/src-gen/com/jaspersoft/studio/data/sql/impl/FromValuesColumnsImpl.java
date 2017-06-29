/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.FromValuesColumnNames;
import com.jaspersoft.studio.data.sql.FromValuesColumns;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>From Values Columns</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.FromValuesColumnsImpl#getFvCols <em>Fv Cols</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FromValuesColumnsImpl extends MinimalEObjectImpl.Container implements FromValuesColumns
{
  /**
   * The cached value of the '{@link #getFvCols() <em>Fv Cols</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFvCols()
   * @generated
   * @ordered
   */
  protected FromValuesColumnNames fvCols;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FromValuesColumnsImpl()
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
    return SqlPackage.Literals.FROM_VALUES_COLUMNS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FromValuesColumnNames getFvCols()
  {
    return fvCols;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFvCols(FromValuesColumnNames newFvCols, NotificationChain msgs)
  {
    FromValuesColumnNames oldFvCols = fvCols;
    fvCols = newFvCols;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.FROM_VALUES_COLUMNS__FV_COLS, oldFvCols, newFvCols);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFvCols(FromValuesColumnNames newFvCols)
  {
    if (newFvCols != fvCols)
    {
      NotificationChain msgs = null;
      if (fvCols != null)
        msgs = ((InternalEObject)fvCols).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FROM_VALUES_COLUMNS__FV_COLS, null, msgs);
      if (newFvCols != null)
        msgs = ((InternalEObject)newFvCols).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FROM_VALUES_COLUMNS__FV_COLS, null, msgs);
      msgs = basicSetFvCols(newFvCols, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FROM_VALUES_COLUMNS__FV_COLS, newFvCols, newFvCols));
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
      case SqlPackage.FROM_VALUES_COLUMNS__FV_COLS:
        return basicSetFvCols(null, msgs);
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
      case SqlPackage.FROM_VALUES_COLUMNS__FV_COLS:
        return getFvCols();
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
      case SqlPackage.FROM_VALUES_COLUMNS__FV_COLS:
        setFvCols((FromValuesColumnNames)newValue);
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
      case SqlPackage.FROM_VALUES_COLUMNS__FV_COLS:
        setFvCols((FromValuesColumnNames)null);
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
      case SqlPackage.FROM_VALUES_COLUMNS__FV_COLS:
        return fvCols != null;
    }
    return super.eIsSet(featureID);
  }

} //FromValuesColumnsImpl
