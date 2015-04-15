/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.PivotInClause;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.SubQueryOperand;
import com.jaspersoft.studio.data.sql.UnpivotInClauseArgs;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pivot In Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.PivotInClauseImpl#getSq <em>Sq</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.PivotInClauseImpl#getArgs <em>Args</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.PivotInClauseImpl#getPinany <em>Pinany</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PivotInClauseImpl extends MinimalEObjectImpl.Container implements PivotInClause
{
  /**
   * The cached value of the '{@link #getSq() <em>Sq</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSq()
   * @generated
   * @ordered
   */
  protected SubQueryOperand sq;

  /**
   * The cached value of the '{@link #getArgs() <em>Args</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArgs()
   * @generated
   * @ordered
   */
  protected UnpivotInClauseArgs args;

  /**
   * The default value of the '{@link #getPinany() <em>Pinany</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPinany()
   * @generated
   * @ordered
   */
  protected static final String PINANY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPinany() <em>Pinany</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPinany()
   * @generated
   * @ordered
   */
  protected String pinany = PINANY_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PivotInClauseImpl()
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
    return SqlPackage.Literals.PIVOT_IN_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SubQueryOperand getSq()
  {
    return sq;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSq(SubQueryOperand newSq, NotificationChain msgs)
  {
    SubQueryOperand oldSq = sq;
    sq = newSq;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.PIVOT_IN_CLAUSE__SQ, oldSq, newSq);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSq(SubQueryOperand newSq)
  {
    if (newSq != sq)
    {
      NotificationChain msgs = null;
      if (sq != null)
        msgs = ((InternalEObject)sq).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.PIVOT_IN_CLAUSE__SQ, null, msgs);
      if (newSq != null)
        msgs = ((InternalEObject)newSq).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.PIVOT_IN_CLAUSE__SQ, null, msgs);
      msgs = basicSetSq(newSq, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.PIVOT_IN_CLAUSE__SQ, newSq, newSq));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnpivotInClauseArgs getArgs()
  {
    return args;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetArgs(UnpivotInClauseArgs newArgs, NotificationChain msgs)
  {
    UnpivotInClauseArgs oldArgs = args;
    args = newArgs;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.PIVOT_IN_CLAUSE__ARGS, oldArgs, newArgs);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArgs(UnpivotInClauseArgs newArgs)
  {
    if (newArgs != args)
    {
      NotificationChain msgs = null;
      if (args != null)
        msgs = ((InternalEObject)args).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.PIVOT_IN_CLAUSE__ARGS, null, msgs);
      if (newArgs != null)
        msgs = ((InternalEObject)newArgs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.PIVOT_IN_CLAUSE__ARGS, null, msgs);
      msgs = basicSetArgs(newArgs, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.PIVOT_IN_CLAUSE__ARGS, newArgs, newArgs));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPinany()
  {
    return pinany;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPinany(String newPinany)
  {
    String oldPinany = pinany;
    pinany = newPinany;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.PIVOT_IN_CLAUSE__PINANY, oldPinany, pinany));
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
      case SqlPackage.PIVOT_IN_CLAUSE__SQ:
        return basicSetSq(null, msgs);
      case SqlPackage.PIVOT_IN_CLAUSE__ARGS:
        return basicSetArgs(null, msgs);
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
      case SqlPackage.PIVOT_IN_CLAUSE__SQ:
        return getSq();
      case SqlPackage.PIVOT_IN_CLAUSE__ARGS:
        return getArgs();
      case SqlPackage.PIVOT_IN_CLAUSE__PINANY:
        return getPinany();
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
      case SqlPackage.PIVOT_IN_CLAUSE__SQ:
        setSq((SubQueryOperand)newValue);
        return;
      case SqlPackage.PIVOT_IN_CLAUSE__ARGS:
        setArgs((UnpivotInClauseArgs)newValue);
        return;
      case SqlPackage.PIVOT_IN_CLAUSE__PINANY:
        setPinany((String)newValue);
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
      case SqlPackage.PIVOT_IN_CLAUSE__SQ:
        setSq((SubQueryOperand)null);
        return;
      case SqlPackage.PIVOT_IN_CLAUSE__ARGS:
        setArgs((UnpivotInClauseArgs)null);
        return;
      case SqlPackage.PIVOT_IN_CLAUSE__PINANY:
        setPinany(PINANY_EDEFAULT);
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
      case SqlPackage.PIVOT_IN_CLAUSE__SQ:
        return sq != null;
      case SqlPackage.PIVOT_IN_CLAUSE__ARGS:
        return args != null;
      case SqlPackage.PIVOT_IN_CLAUSE__PINANY:
        return PINANY_EDEFAULT == null ? pinany != null : !PINANY_EDEFAULT.equals(pinany);
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
    result.append(" (pinany: ");
    result.append(pinany);
    result.append(')');
    return result.toString();
  }

} //PivotInClauseImpl
