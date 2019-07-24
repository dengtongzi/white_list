package cn.locker.kernel.model.auth;

import java.util.Set;

public abstract interface AbstractLoginUser
{
  public abstract <T> T getUserUniqueId();

  public abstract <T> T getAppId();

  public abstract <T> Set<T> getRoleIds();

  public abstract <T> Set<T> getRoleCodes();

  public abstract <T> Set<T> getResourceUrls();
}