package cn.locker.kernel.model.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.locker.kernel.model.auth.AbstractLoginUser;

@RequestMapping({"/api/authService"})
public abstract interface AuthService
{
  @RequestMapping(value={"/login"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public abstract String login(@RequestParam("account") String paramString1, @RequestParam("password") String paramString2);

  @RequestMapping(value={"/checkToken"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public abstract boolean checkToken(@RequestParam("token") String paramString);

  @RequestMapping(value={"/logout"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public abstract void logout(@RequestParam("token") String paramString);

  @RequestMapping(value={"/getLoginUserByToken"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public abstract AbstractLoginUser getLoginUserByToken(@RequestParam("token") String paramString);
}