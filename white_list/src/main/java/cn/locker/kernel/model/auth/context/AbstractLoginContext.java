package cn.locker.kernel.model.auth.context;

import cn.locker.kernel.model.auth.AbstractLoginUser;

public abstract interface AbstractLoginContext
{
  public abstract String getCurrentUserToken();

  public abstract <T extends AbstractLoginUser> T getLoginUser();
}