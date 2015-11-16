/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.ColumnFull;
import com.jaspersoft.studio.data.sql.GroupByColumnFull;
import com.jaspersoft.studio.data.sql.OpFunction;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Group By Column Full</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.GroupByColumnFullImpl#getColGrBy <em>Col Gr By</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.GroupByColumnFullImpl#getGbFunction <em>Gb Function</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.GroupByColumnFullImpl#getGrByInt <em>Gr By Int</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupByColumnFullImpl extends OrGroupByColumnImpl implements GroupByColumnFull
{
  /**
   * The cached value of the '{@link #getColGrBy() <em>Col Gr By</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColGrBy()
   * @generated
   * @ordered
   */
  protected ColumnFull colGrBy;

  /**
   * The cached value of the '{@link #getGbFunction() <em>Gb Function</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGbFunction()
   * @generated
   * @ordered
   */
  protected OpFunction gbFunction;

  /**
   * The default value of the '{@link #getGrByInt() <em>Gr By Int</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGrByInt()
   * @generated
   * @ordered
   */
  protected static final Integer GR_BY_INT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getGrByInt() <em>Gr By Int</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGrByInt()
   * @generated
   * @ordered
   */
  protected Integer grByInt = GR_BY_INT_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GroupByColumnFullImpl()
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
    return SqlPackage.Literals.GROUP_BY_COLUMN_FULL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ColumnFull getColGrBy()
  {
    return colGrBy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetColGrBy(ColumnFull newColGrBy, NotificationChain msgs)
  {
    ColumnFull oldColGrBy = colGrBy;
    colGrBy = newColGrBy;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.GROUP_BY_COLUMN_FULL__COL_GR_BY, oldColGrBy, newColGrBy);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setColGrBy(ColumnFull newColGrBy)
  {
    if (newColGrBy != colGrBy)
    {
      NotificationChain msgs = null;
      if (colGrBy != null)
        msgs = ((InternalEObject)colGrBy).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.GROUP_BY_COLUMN_FULL__COL_GR_BY, null, msgs);
      if (newColGrBy != null)
        msgs = ((InternalEObject)newColGrBy).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.GROUP_BY_COLUMN_FULL__COL_GR_BY, null, msgs);
      msgs = basicSetColGrBy(newColGrBy, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.GROUP_BY_COLUMN_FULL__COL_GR_BY, newColGrBy, newColGrBy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OpFunction getGbFunction()
  {
    return gbFunction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGbFunction(OpFunction newGbFunction, NotificationChain msgs)
  {
    OpFunction oldGbFunction = gbFunction;
    gbFunction = newGbFunction;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.GROUP_BY_COLUMN_FULL__GB_FUNCTION, oldGbFunction, newGbFunction);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGbFunction(OpFunction newGbFunction)
  {
    if (newGbFunction != gbFunction)
    {
      NotificationChain msgs = null;
      if (gbFunction != null)
        msgs = ((InternalEObject)gbFunction).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.GROUP_BY_COLUMN_FULL__GB_FUNCTION, null, msgs);
      if (newGbFunction != null)
        msgs = ((InternalEObject)newGbFunction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.GROUP_BY_COLUMN_FULL__GB_FUNCTION, null, msgs);
      msgs = basicSetGbFunction(newGbFunction, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.GROUP_BY_COLUMN_FULL__GB_FUNCTION, newGbFunction, newGbFunction));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer getGrByInt()
  {
    return grByInt;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGrByInt(Integer newGrByInt)
  {
    Integer oldGrByInt = grByInt;
    grByInt = newGrByInt;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.GROUP_BY_COLUMN_FULL__GR_BY_INT, oldGrByInt, grByInt));
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
      case SqlPackage.GROUP_BY_COLUMN_FULL__COL_GR_BY:
        return basicSetColGrBy(null, msgs);
      case SqlPackage.GROUP_BY_COLUMN_FULL__GB_FUNCTION:
        return basicSetGbFunction(null, msgs);
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
      case SqlPackage.GROUP_BY_COLUMN_FULL__COL_GR_BY:
        return getColGrBy();
      case SqlPackage.GROUP_BY_COLUMN_FULL__GB_FUNCTION:
        return getGbFunction();
      case SqlPackage.GROUP_BY_COLUMN_FULL__GR_BY_INT:
        return getGrByInt();
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
      case SqlPackage.GROUP_BY_COLUMN_FULL__COL_GR_BY:
        setColGrBy((ColumnFull)newValue);
        return;
      case SqlPackage.GROUP_BY_COLUMN_FULL__GB_FUNCTION:
        setGbFunction((OpFunction)newValue);
        return;
      case SqlPackage.GROUP_BY_COLUMN_FULL__GR_BY_INT:
        setGrByInt((Integer)newValue);
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
      case SqlPackage.GROUP_BY_COLUMN_FULL__COL_GR_BY:
        setColGrBy((ColumnFull)null);
        return;
      case SqlPackage.GROUP_BY_COLUMN_FULL__GB_FUNCTION:
        setGbFunction((OpFunction)null);
        return;
      case SqlPackage.GROUP_BY_COLUMN_FULL__GR_BY_INT:
        setGrByInt(GR_BY_INT_EDEFAULT);
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
      case SqlPackage.GROUP_BY_COLUMN_FULL__COL_GR_BY:
        return colGrBy != null;
      case SqlPackage.GROUP_BY_COLUMN_FULL__GB_FUNCTION:
        return gbFunction != null;
      case SqlPackage.GROUP_BY_COLUMN_FULL__GR_BY_INT:
        return GR_BY_INT_EDEFAULT == null ? grByInt != null : !GR_BY_INT_EDEFAULT.equals(grByInt);
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
    result.append(" (grByInt: ");
    result.append(grByInt);
    result.append(')');
    return result.toString();
  }

} //GroupByColumnFullImpl
