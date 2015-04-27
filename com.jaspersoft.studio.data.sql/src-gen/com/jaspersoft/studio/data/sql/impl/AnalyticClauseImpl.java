/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.AnalyticClause;
import com.jaspersoft.studio.data.sql.OrderByClause;
import com.jaspersoft.studio.data.sql.QueryPartitionClause;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.WindowingClause;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Analytic Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.AnalyticClauseImpl#getAbc <em>Abc</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.AnalyticClauseImpl#getObc <em>Obc</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.AnalyticClauseImpl#getWinc <em>Winc</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnalyticClauseImpl extends MinimalEObjectImpl.Container implements AnalyticClause
{
  /**
   * The cached value of the '{@link #getAbc() <em>Abc</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAbc()
   * @generated
   * @ordered
   */
  protected QueryPartitionClause abc;

  /**
   * The cached value of the '{@link #getObc() <em>Obc</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getObc()
   * @generated
   * @ordered
   */
  protected OrderByClause obc;

  /**
   * The cached value of the '{@link #getWinc() <em>Winc</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWinc()
   * @generated
   * @ordered
   */
  protected WindowingClause winc;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AnalyticClauseImpl()
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
    return SqlPackage.Literals.ANALYTIC_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public QueryPartitionClause getAbc()
  {
    return abc;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAbc(QueryPartitionClause newAbc, NotificationChain msgs)
  {
    QueryPartitionClause oldAbc = abc;
    abc = newAbc;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.ANALYTIC_CLAUSE__ABC, oldAbc, newAbc);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAbc(QueryPartitionClause newAbc)
  {
    if (newAbc != abc)
    {
      NotificationChain msgs = null;
      if (abc != null)
        msgs = ((InternalEObject)abc).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.ANALYTIC_CLAUSE__ABC, null, msgs);
      if (newAbc != null)
        msgs = ((InternalEObject)newAbc).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.ANALYTIC_CLAUSE__ABC, null, msgs);
      msgs = basicSetAbc(newAbc, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.ANALYTIC_CLAUSE__ABC, newAbc, newAbc));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrderByClause getObc()
  {
    return obc;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetObc(OrderByClause newObc, NotificationChain msgs)
  {
    OrderByClause oldObc = obc;
    obc = newObc;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.ANALYTIC_CLAUSE__OBC, oldObc, newObc);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setObc(OrderByClause newObc)
  {
    if (newObc != obc)
    {
      NotificationChain msgs = null;
      if (obc != null)
        msgs = ((InternalEObject)obc).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.ANALYTIC_CLAUSE__OBC, null, msgs);
      if (newObc != null)
        msgs = ((InternalEObject)newObc).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.ANALYTIC_CLAUSE__OBC, null, msgs);
      msgs = basicSetObc(newObc, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.ANALYTIC_CLAUSE__OBC, newObc, newObc));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WindowingClause getWinc()
  {
    return winc;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWinc(WindowingClause newWinc, NotificationChain msgs)
  {
    WindowingClause oldWinc = winc;
    winc = newWinc;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.ANALYTIC_CLAUSE__WINC, oldWinc, newWinc);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWinc(WindowingClause newWinc)
  {
    if (newWinc != winc)
    {
      NotificationChain msgs = null;
      if (winc != null)
        msgs = ((InternalEObject)winc).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.ANALYTIC_CLAUSE__WINC, null, msgs);
      if (newWinc != null)
        msgs = ((InternalEObject)newWinc).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.ANALYTIC_CLAUSE__WINC, null, msgs);
      msgs = basicSetWinc(newWinc, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.ANALYTIC_CLAUSE__WINC, newWinc, newWinc));
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
      case SqlPackage.ANALYTIC_CLAUSE__ABC:
        return basicSetAbc(null, msgs);
      case SqlPackage.ANALYTIC_CLAUSE__OBC:
        return basicSetObc(null, msgs);
      case SqlPackage.ANALYTIC_CLAUSE__WINC:
        return basicSetWinc(null, msgs);
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
      case SqlPackage.ANALYTIC_CLAUSE__ABC:
        return getAbc();
      case SqlPackage.ANALYTIC_CLAUSE__OBC:
        return getObc();
      case SqlPackage.ANALYTIC_CLAUSE__WINC:
        return getWinc();
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
      case SqlPackage.ANALYTIC_CLAUSE__ABC:
        setAbc((QueryPartitionClause)newValue);
        return;
      case SqlPackage.ANALYTIC_CLAUSE__OBC:
        setObc((OrderByClause)newValue);
        return;
      case SqlPackage.ANALYTIC_CLAUSE__WINC:
        setWinc((WindowingClause)newValue);
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
      case SqlPackage.ANALYTIC_CLAUSE__ABC:
        setAbc((QueryPartitionClause)null);
        return;
      case SqlPackage.ANALYTIC_CLAUSE__OBC:
        setObc((OrderByClause)null);
        return;
      case SqlPackage.ANALYTIC_CLAUSE__WINC:
        setWinc((WindowingClause)null);
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
      case SqlPackage.ANALYTIC_CLAUSE__ABC:
        return abc != null;
      case SqlPackage.ANALYTIC_CLAUSE__OBC:
        return obc != null;
      case SqlPackage.ANALYTIC_CLAUSE__WINC:
        return winc != null;
    }
    return super.eIsSet(featureID);
  }

} //AnalyticClauseImpl
