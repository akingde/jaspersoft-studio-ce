/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.AnalyticExprArgs;
import com.jaspersoft.studio.data.sql.OrderByClause;
import com.jaspersoft.studio.data.sql.QueryPartitionClause;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.WindowingClause;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Query Partition Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.QueryPartitionClauseImpl#getObc <em>Obc</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.QueryPartitionClauseImpl#getWinc <em>Winc</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.QueryPartitionClauseImpl#getArgs <em>Args</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryPartitionClauseImpl extends AnalyticClauseImpl implements QueryPartitionClause
{
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
   * The cached value of the '{@link #getArgs() <em>Args</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArgs()
   * @generated
   * @ordered
   */
  protected AnalyticExprArgs args;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected QueryPartitionClauseImpl()
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
    return SqlPackage.Literals.QUERY_PARTITION_CLAUSE;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.QUERY_PARTITION_CLAUSE__OBC, oldObc, newObc);
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
        msgs = ((InternalEObject)obc).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.QUERY_PARTITION_CLAUSE__OBC, null, msgs);
      if (newObc != null)
        msgs = ((InternalEObject)newObc).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.QUERY_PARTITION_CLAUSE__OBC, null, msgs);
      msgs = basicSetObc(newObc, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.QUERY_PARTITION_CLAUSE__OBC, newObc, newObc));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.QUERY_PARTITION_CLAUSE__WINC, oldWinc, newWinc);
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
        msgs = ((InternalEObject)winc).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.QUERY_PARTITION_CLAUSE__WINC, null, msgs);
      if (newWinc != null)
        msgs = ((InternalEObject)newWinc).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.QUERY_PARTITION_CLAUSE__WINC, null, msgs);
      msgs = basicSetWinc(newWinc, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.QUERY_PARTITION_CLAUSE__WINC, newWinc, newWinc));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnalyticExprArgs getArgs()
  {
    return args;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetArgs(AnalyticExprArgs newArgs, NotificationChain msgs)
  {
    AnalyticExprArgs oldArgs = args;
    args = newArgs;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.QUERY_PARTITION_CLAUSE__ARGS, oldArgs, newArgs);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArgs(AnalyticExprArgs newArgs)
  {
    if (newArgs != args)
    {
      NotificationChain msgs = null;
      if (args != null)
        msgs = ((InternalEObject)args).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.QUERY_PARTITION_CLAUSE__ARGS, null, msgs);
      if (newArgs != null)
        msgs = ((InternalEObject)newArgs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.QUERY_PARTITION_CLAUSE__ARGS, null, msgs);
      msgs = basicSetArgs(newArgs, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.QUERY_PARTITION_CLAUSE__ARGS, newArgs, newArgs));
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
      case SqlPackage.QUERY_PARTITION_CLAUSE__OBC:
        return basicSetObc(null, msgs);
      case SqlPackage.QUERY_PARTITION_CLAUSE__WINC:
        return basicSetWinc(null, msgs);
      case SqlPackage.QUERY_PARTITION_CLAUSE__ARGS:
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
      case SqlPackage.QUERY_PARTITION_CLAUSE__OBC:
        return getObc();
      case SqlPackage.QUERY_PARTITION_CLAUSE__WINC:
        return getWinc();
      case SqlPackage.QUERY_PARTITION_CLAUSE__ARGS:
        return getArgs();
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
      case SqlPackage.QUERY_PARTITION_CLAUSE__OBC:
        setObc((OrderByClause)newValue);
        return;
      case SqlPackage.QUERY_PARTITION_CLAUSE__WINC:
        setWinc((WindowingClause)newValue);
        return;
      case SqlPackage.QUERY_PARTITION_CLAUSE__ARGS:
        setArgs((AnalyticExprArgs)newValue);
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
      case SqlPackage.QUERY_PARTITION_CLAUSE__OBC:
        setObc((OrderByClause)null);
        return;
      case SqlPackage.QUERY_PARTITION_CLAUSE__WINC:
        setWinc((WindowingClause)null);
        return;
      case SqlPackage.QUERY_PARTITION_CLAUSE__ARGS:
        setArgs((AnalyticExprArgs)null);
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
      case SqlPackage.QUERY_PARTITION_CLAUSE__OBC:
        return obc != null;
      case SqlPackage.QUERY_PARTITION_CLAUSE__WINC:
        return winc != null;
      case SqlPackage.QUERY_PARTITION_CLAUSE__ARGS:
        return args != null;
    }
    return super.eIsSet(featureID);
  }

} //QueryPartitionClauseImpl
