/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.WindowingClauseBetween;
import com.jaspersoft.studio.data.sql.WindowingClauseOperandFollowing;
import com.jaspersoft.studio.data.sql.WindowingClauseOperandPreceding;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Windowing Clause Between</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.WindowingClauseBetweenImpl#getWcoP <em>Wco P</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.WindowingClauseBetweenImpl#getWcoF <em>Wco F</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WindowingClauseBetweenImpl extends WindowingClauseImpl implements WindowingClauseBetween
{
  /**
   * The cached value of the '{@link #getWcoP() <em>Wco P</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWcoP()
   * @generated
   * @ordered
   */
  protected WindowingClauseOperandPreceding wcoP;

  /**
   * The cached value of the '{@link #getWcoF() <em>Wco F</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWcoF()
   * @generated
   * @ordered
   */
  protected WindowingClauseOperandFollowing wcoF;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected WindowingClauseBetweenImpl()
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
    return SqlPackage.Literals.WINDOWING_CLAUSE_BETWEEN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WindowingClauseOperandPreceding getWcoP()
  {
    return wcoP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWcoP(WindowingClauseOperandPreceding newWcoP, NotificationChain msgs)
  {
    WindowingClauseOperandPreceding oldWcoP = wcoP;
    wcoP = newWcoP;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_P, oldWcoP, newWcoP);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWcoP(WindowingClauseOperandPreceding newWcoP)
  {
    if (newWcoP != wcoP)
    {
      NotificationChain msgs = null;
      if (wcoP != null)
        msgs = ((InternalEObject)wcoP).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_P, null, msgs);
      if (newWcoP != null)
        msgs = ((InternalEObject)newWcoP).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_P, null, msgs);
      msgs = basicSetWcoP(newWcoP, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_P, newWcoP, newWcoP));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WindowingClauseOperandFollowing getWcoF()
  {
    return wcoF;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWcoF(WindowingClauseOperandFollowing newWcoF, NotificationChain msgs)
  {
    WindowingClauseOperandFollowing oldWcoF = wcoF;
    wcoF = newWcoF;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_F, oldWcoF, newWcoF);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWcoF(WindowingClauseOperandFollowing newWcoF)
  {
    if (newWcoF != wcoF)
    {
      NotificationChain msgs = null;
      if (wcoF != null)
        msgs = ((InternalEObject)wcoF).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_F, null, msgs);
      if (newWcoF != null)
        msgs = ((InternalEObject)newWcoF).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_F, null, msgs);
      msgs = basicSetWcoF(newWcoF, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_F, newWcoF, newWcoF));
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
      case SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_P:
        return basicSetWcoP(null, msgs);
      case SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_F:
        return basicSetWcoF(null, msgs);
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
      case SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_P:
        return getWcoP();
      case SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_F:
        return getWcoF();
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
      case SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_P:
        setWcoP((WindowingClauseOperandPreceding)newValue);
        return;
      case SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_F:
        setWcoF((WindowingClauseOperandFollowing)newValue);
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
      case SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_P:
        setWcoP((WindowingClauseOperandPreceding)null);
        return;
      case SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_F:
        setWcoF((WindowingClauseOperandFollowing)null);
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
      case SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_P:
        return wcoP != null;
      case SqlPackage.WINDOWING_CLAUSE_BETWEEN__WCO_F:
        return wcoF != null;
    }
    return super.eIsSet(featureID);
  }

} //WindowingClauseBetweenImpl
