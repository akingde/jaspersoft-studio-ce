/**
 */
package com.jaspersoft.studio.data.sql;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>EXTRACT VALUES</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getEXTRACT_VALUES()
 * @model
 * @generated
 */
public enum EXTRACT_VALUES implements Enumerator
{
  /**
   * The '<em><b>Ms</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MS_VALUE
   * @generated
   * @ordered
   */
  MS(0, "ms", "MICROSECOND"),

  /**
   * The '<em><b>S</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #S_VALUE
   * @generated
   * @ordered
   */
  S(1, "s", "SECOND"),

  /**
   * The '<em><b>M</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #M_VALUE
   * @generated
   * @ordered
   */
  M(2, "m", "MINUTE"),

  /**
   * The '<em><b>H</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #H_VALUE
   * @generated
   * @ordered
   */
  H(3, "h", "HOUR"),

  /**
   * The '<em><b>Day</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #DAY_VALUE
   * @generated
   * @ordered
   */
  DAY(4, "day", "DAY"),

  /**
   * The '<em><b>Week</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #WEEK_VALUE
   * @generated
   * @ordered
   */
  WEEK(5, "week", "WEEK"),

  /**
   * The '<em><b>Month</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MONTH_VALUE
   * @generated
   * @ordered
   */
  MONTH(6, "month", "MONTH"),

  /**
   * The '<em><b>Quart</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #QUART_VALUE
   * @generated
   * @ordered
   */
  QUART(7, "quart", "QUARTER"),

  /**
   * The '<em><b>Year</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #YEAR_VALUE
   * @generated
   * @ordered
   */
  YEAR(8, "year", "YEAR"),

  /**
   * The '<em><b>Micros</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MICROS_VALUE
   * @generated
   * @ordered
   */
  MICROS(9, "micros", "SECOND_MICROSECOND"),

  /**
   * The '<em><b>Min Micro</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MIN_MICRO_VALUE
   * @generated
   * @ordered
   */
  MIN_MICRO(10, "minMicro", "MINUTE_MICROSECOND"),

  /**
   * The '<em><b>Min Sec</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MIN_SEC_VALUE
   * @generated
   * @ordered
   */
  MIN_SEC(11, "minSec", "MINUTE_SECOND"),

  /**
   * The '<em><b>Hms</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #HMS_VALUE
   * @generated
   * @ordered
   */
  HMS(12, "hms", "HOUR_MICROSECOND"),

  /**
   * The '<em><b>Hs</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #HS_VALUE
   * @generated
   * @ordered
   */
  HS(13, "hs", "HOUR_SECOND"),

  /**
   * The '<em><b>Hmin</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #HMIN_VALUE
   * @generated
   * @ordered
   */
  HMIN(14, "hmin", "HOUR_MINUTE"),

  /**
   * The '<em><b>Dms</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #DMS_VALUE
   * @generated
   * @ordered
   */
  DMS(15, "dms", "DAY_MICROSECOND"),

  /**
   * The '<em><b>Ds</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #DS_VALUE
   * @generated
   * @ordered
   */
  DS(16, "ds", "DAY_SECOND"),

  /**
   * The '<em><b>Daymin</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #DAYMIN_VALUE
   * @generated
   * @ordered
   */
  DAYMIN(17, "daymin", "DAY_MINUTE"),

  /**
   * The '<em><b>Dayh</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #DAYH_VALUE
   * @generated
   * @ordered
   */
  DAYH(18, "dayh", "DAY_HOUR"),

  /**
   * The '<em><b>Year Month</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #YEAR_MONTH_VALUE
   * @generated
   * @ordered
   */
  YEAR_MONTH(19, "yearMonth", "YEAR_MONTH");

  /**
   * The '<em><b>Ms</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Ms</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MS
   * @model name="ms" literal="MICROSECOND"
   * @generated
   * @ordered
   */
  public static final int MS_VALUE = 0;

  /**
   * The '<em><b>S</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>S</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #S
   * @model name="s" literal="SECOND"
   * @generated
   * @ordered
   */
  public static final int S_VALUE = 1;

  /**
   * The '<em><b>M</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>M</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #M
   * @model name="m" literal="MINUTE"
   * @generated
   * @ordered
   */
  public static final int M_VALUE = 2;

  /**
   * The '<em><b>H</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>H</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #H
   * @model name="h" literal="HOUR"
   * @generated
   * @ordered
   */
  public static final int H_VALUE = 3;

  /**
   * The '<em><b>Day</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Day</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #DAY
   * @model name="day" literal="DAY"
   * @generated
   * @ordered
   */
  public static final int DAY_VALUE = 4;

  /**
   * The '<em><b>Week</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Week</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #WEEK
   * @model name="week" literal="WEEK"
   * @generated
   * @ordered
   */
  public static final int WEEK_VALUE = 5;

  /**
   * The '<em><b>Month</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Month</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MONTH
   * @model name="month" literal="MONTH"
   * @generated
   * @ordered
   */
  public static final int MONTH_VALUE = 6;

  /**
   * The '<em><b>Quart</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Quart</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #QUART
   * @model name="quart" literal="QUARTER"
   * @generated
   * @ordered
   */
  public static final int QUART_VALUE = 7;

  /**
   * The '<em><b>Year</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Year</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #YEAR
   * @model name="year" literal="YEAR"
   * @generated
   * @ordered
   */
  public static final int YEAR_VALUE = 8;

  /**
   * The '<em><b>Micros</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Micros</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MICROS
   * @model name="micros" literal="SECOND_MICROSECOND"
   * @generated
   * @ordered
   */
  public static final int MICROS_VALUE = 9;

  /**
   * The '<em><b>Min Micro</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Min Micro</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MIN_MICRO
   * @model name="minMicro" literal="MINUTE_MICROSECOND"
   * @generated
   * @ordered
   */
  public static final int MIN_MICRO_VALUE = 10;

  /**
   * The '<em><b>Min Sec</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Min Sec</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MIN_SEC
   * @model name="minSec" literal="MINUTE_SECOND"
   * @generated
   * @ordered
   */
  public static final int MIN_SEC_VALUE = 11;

  /**
   * The '<em><b>Hms</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Hms</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #HMS
   * @model name="hms" literal="HOUR_MICROSECOND"
   * @generated
   * @ordered
   */
  public static final int HMS_VALUE = 12;

  /**
   * The '<em><b>Hs</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Hs</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #HS
   * @model name="hs" literal="HOUR_SECOND"
   * @generated
   * @ordered
   */
  public static final int HS_VALUE = 13;

  /**
   * The '<em><b>Hmin</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Hmin</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #HMIN
   * @model name="hmin" literal="HOUR_MINUTE"
   * @generated
   * @ordered
   */
  public static final int HMIN_VALUE = 14;

  /**
   * The '<em><b>Dms</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Dms</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #DMS
   * @model name="dms" literal="DAY_MICROSECOND"
   * @generated
   * @ordered
   */
  public static final int DMS_VALUE = 15;

  /**
   * The '<em><b>Ds</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Ds</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #DS
   * @model name="ds" literal="DAY_SECOND"
   * @generated
   * @ordered
   */
  public static final int DS_VALUE = 16;

  /**
   * The '<em><b>Daymin</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Daymin</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #DAYMIN
   * @model name="daymin" literal="DAY_MINUTE"
   * @generated
   * @ordered
   */
  public static final int DAYMIN_VALUE = 17;

  /**
   * The '<em><b>Dayh</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Dayh</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #DAYH
   * @model name="dayh" literal="DAY_HOUR"
   * @generated
   * @ordered
   */
  public static final int DAYH_VALUE = 18;

  /**
   * The '<em><b>Year Month</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Year Month</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #YEAR_MONTH
   * @model name="yearMonth" literal="YEAR_MONTH"
   * @generated
   * @ordered
   */
  public static final int YEAR_MONTH_VALUE = 19;

  /**
   * An array of all the '<em><b>EXTRACT VALUES</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final EXTRACT_VALUES[] VALUES_ARRAY =
    new EXTRACT_VALUES[]
    {
      MS,
      S,
      M,
      H,
      DAY,
      WEEK,
      MONTH,
      QUART,
      YEAR,
      MICROS,
      MIN_MICRO,
      MIN_SEC,
      HMS,
      HS,
      HMIN,
      DMS,
      DS,
      DAYMIN,
      DAYH,
      YEAR_MONTH,
    };

  /**
   * A public read-only list of all the '<em><b>EXTRACT VALUES</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<EXTRACT_VALUES> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>EXTRACT VALUES</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static EXTRACT_VALUES get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      EXTRACT_VALUES result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>EXTRACT VALUES</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static EXTRACT_VALUES getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      EXTRACT_VALUES result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>EXTRACT VALUES</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static EXTRACT_VALUES get(int value)
  {
    switch (value)
    {
      case MS_VALUE: return MS;
      case S_VALUE: return S;
      case M_VALUE: return M;
      case H_VALUE: return H;
      case DAY_VALUE: return DAY;
      case WEEK_VALUE: return WEEK;
      case MONTH_VALUE: return MONTH;
      case QUART_VALUE: return QUART;
      case YEAR_VALUE: return YEAR;
      case MICROS_VALUE: return MICROS;
      case MIN_MICRO_VALUE: return MIN_MICRO;
      case MIN_SEC_VALUE: return MIN_SEC;
      case HMS_VALUE: return HMS;
      case HS_VALUE: return HS;
      case HMIN_VALUE: return HMIN;
      case DMS_VALUE: return DMS;
      case DS_VALUE: return DS;
      case DAYMIN_VALUE: return DAYMIN;
      case DAYH_VALUE: return DAYH;
      case YEAR_MONTH_VALUE: return YEAR_MONTH;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EXTRACT_VALUES(int value, String name, String literal)
  {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }
  
} //EXTRACT_VALUES
