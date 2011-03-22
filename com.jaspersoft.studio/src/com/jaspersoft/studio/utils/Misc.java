package com.jaspersoft.studio.utils;

public class Misc {
	
	
	/**
	 * Return def if object is null, otherwise it return obj.toString().
	 * 
	 * @param obj
	 * @param def
	 * @return
	 */
	public static String nvl(Object obj, String def)
  {
      if (obj == null) return def;
      else return obj.toString();
  }
	
}
