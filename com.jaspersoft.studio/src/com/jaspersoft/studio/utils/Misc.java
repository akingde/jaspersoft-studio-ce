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

	/**
   * Don't use it. It does not work well in general,
   * it just does the job for the purposes it has been
   * created for.
   * The function replaces with \n \r \t \\ sequences
   * newlines, tabs etc...
   * @param str
   * @return
   */
	public static String addSlashesString(String str) {
		if (str == null) return str;

    String newStr = "";
    for (int i=0; i<str.length(); ++i)
    {
        char c = str.charAt(i);
        switch (c)
        {
            case '\n': newStr +="\\n"; break;
            case '\r': newStr +="\\r"; break;
            case '\t': newStr +="\\t"; break;
            case '\\': newStr +="\\\\"; break;
            default: newStr += c;
        }
    }
    return newStr;
	}

	/**
   * Don't use it. It does not work well in general,
   * it just does the job for the purposes it has been
   * created for.
   * The function parse \n \r \t \\ characters
   * replacing the sequences with real newline, tabs etc...
   * @param str
   * @return
   */
	public static String removeSlashesString(String str) {
		
		if (str == null) return str;

    String newStr = "";
    for (int i=0; i<str.length(); ++i)
    {
        char c = str.charAt(i);
        if (c == '\\' && str.length() > i+1)
        {
            i++;
            char c2 = str.charAt(i);
            switch (c2)
            {
                case 'n': newStr +="\n"; break;
                case 'r': newStr +="\r"; break;
                case 't': newStr +="\t"; break;
                case '\\': newStr +="\\"; break;
                default:
                {
                    newStr += c;
                    newStr += c2;
                }
            }
        }
        else
        {
            newStr += c;
        }
    }

    return newStr;
	}
}
