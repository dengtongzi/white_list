package cn.locker.kernel.model.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidateUtil
{
  public static boolean isNotEmpty(Object o)
  {
    return !isEmpty(o);
  }

  public static boolean isEmpty(Object o)
  {
    if (o == null) {
      return true;
    }
    if ((o instanceof String)) {
      if (o.toString().trim().equals(""))
        return true;
    }
    else if ((o instanceof List)) {
      if (((List)o).size() == 0)
        return true;
    }
    else if ((o instanceof Map)) {
      if (((Map)o).size() == 0)
        return true;
    }
    else if ((o instanceof Set)) {
      if (((Set)o).size() == 0)
        return true;
    }
    else if ((o instanceof Object[])) {
      if (((Object[])(Object[])o).length == 0)
        return true;
    }
    else if ((o instanceof int[])) {
      if (((int[])(int[])o).length == 0)
        return true;
    }
    else if (((o instanceof long[])) && 
      (((long[])(long[])o).length == 0)) {
      return true;
    }

    return false;
  }

  public static boolean isOneEmpty(Object[] os)
  {
    for (Object o : os) {
      if (isEmpty(o)) {
        return true;
      }
    }
    return false;
  }

  public static boolean isAllEmpty(Object[] os)
  {
    for (Object o : os) {
      if (!isEmpty(o)) {
        return false;
      }
    }
    return true;
  }
}