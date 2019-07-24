package cn.locker.kernel.model.auth.context;

import cn.locker.kernel.model.auth.AbstractLoginUser;

public class LoginUserHolder
{
  private static final ThreadLocal<Boolean> OPEN_UP_FLAG = new ThreadLocal();
  private static final ThreadLocal<AbstractLoginUser> LONGIN_USER_HOLDER = new ThreadLocal();

  public static void init()
  {
    OPEN_UP_FLAG.set(Boolean.valueOf(true));
  }

  public static void set(AbstractLoginUser abstractLoginUser)
  {
    Boolean openUpFlag = (Boolean)OPEN_UP_FLAG.get();
    if ((openUpFlag == null) || (openUpFlag.equals(Boolean.valueOf(false)))) {
      return;
    }
    LONGIN_USER_HOLDER.set(abstractLoginUser);
  }

  public static AbstractLoginUser get()
  {
    Boolean openUpFlag = (Boolean)OPEN_UP_FLAG.get();
    if ((openUpFlag == null) || (openUpFlag.equals(Boolean.valueOf(false)))) {
      return null;
    }
    return (AbstractLoginUser)LONGIN_USER_HOLDER.get();
  }

  public static void remove()
  {
    OPEN_UP_FLAG.remove();
    LONGIN_USER_HOLDER.remove();
  }
}