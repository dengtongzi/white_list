package cn.locker.kernel.model.resource;

import java.io.Serializable;
import java.util.Date;

public class ResourceDefinition
  implements Serializable
{
  private String appCode;
  private String className;
  private String methodName;
  private String modularCode;
  private String modularName;
  private String code;
  private String name;
  private String url;
  private String httpMethod;
  private Boolean menuFlag;
  private Boolean requiredLogin;
  private Boolean requiredPermission;
  private Date createTime;
  private String ipAddress;

  public String getAppCode()
  {
    return this.appCode;
  }

  public String getClassName()
  {
    return this.className;
  }

  public String getMethodName()
  {
    return this.methodName;
  }

  public String getModularCode()
  {
    return this.modularCode;
  }

  public String getModularName()
  {
    return this.modularName;
  }

  public String getCode()
  {
    return this.code;
  }

  public String getName()
  {
    return this.name;
  }

  public String getUrl()
  {
    return this.url;
  }

  public String getHttpMethod()
  {
    return this.httpMethod;
  }

  public Boolean getMenuFlag()
  {
    return this.menuFlag;
  }

  public Boolean getRequiredLogin()
  {
    return this.requiredLogin;
  }

  public Boolean getRequiredPermission()
  {
    return this.requiredPermission;
  }

  public Date getCreateTime()
  {
    return this.createTime;
  }

  public String getIpAddress()
  {
    return this.ipAddress;
  }

  public void setAppCode(String appCode)
  {
    this.appCode = appCode; } 
  public void setClassName(String className) { this.className = className; } 
  public void setMethodName(String methodName) { this.methodName = methodName; } 
  public void setModularCode(String modularCode) { this.modularCode = modularCode; } 
  public void setModularName(String modularName) { this.modularName = modularName; } 
  public void setCode(String code) { this.code = code; } 
  public void setName(String name) { this.name = name; } 
  public void setUrl(String url) { this.url = url; } 
  public void setHttpMethod(String httpMethod) { this.httpMethod = httpMethod; } 
  public void setMenuFlag(Boolean menuFlag) { this.menuFlag = menuFlag; } 
  public void setRequiredLogin(Boolean requiredLogin) { this.requiredLogin = requiredLogin; } 
  public void setRequiredPermission(Boolean requiredPermission) { this.requiredPermission = requiredPermission; } 
  public void setCreateTime(Date createTime) { this.createTime = createTime; } 
  public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; } 
  public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ResourceDefinition)) return false; ResourceDefinition other = (ResourceDefinition)o; if (!other.canEqual(this)) return false; Object this$appCode = getAppCode(); Object other$appCode = other.getAppCode(); if (this$appCode == null ? other$appCode != null : !this$appCode.equals(other$appCode)) return false; Object this$className = getClassName(); Object other$className = other.getClassName(); if (this$className == null ? other$className != null : !this$className.equals(other$className)) return false; Object this$methodName = getMethodName(); Object other$methodName = other.getMethodName(); if (this$methodName == null ? other$methodName != null : !this$methodName.equals(other$methodName)) return false; Object this$modularCode = getModularCode(); Object other$modularCode = other.getModularCode(); if (this$modularCode == null ? other$modularCode != null : !this$modularCode.equals(other$modularCode)) return false; Object this$modularName = getModularName(); Object other$modularName = other.getModularName(); if (this$modularName == null ? other$modularName != null : !this$modularName.equals(other$modularName)) return false; Object this$code = getCode(); Object other$code = other.getCode(); if (this$code == null ? other$code != null : !this$code.equals(other$code)) return false; Object this$name = getName(); Object other$name = other.getName(); if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false; Object this$url = getUrl(); Object other$url = other.getUrl(); if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false; Object this$httpMethod = getHttpMethod(); Object other$httpMethod = other.getHttpMethod(); if (this$httpMethod == null ? other$httpMethod != null : !this$httpMethod.equals(other$httpMethod)) return false; Object this$menuFlag = getMenuFlag(); Object other$menuFlag = other.getMenuFlag(); if (this$menuFlag == null ? other$menuFlag != null : !this$menuFlag.equals(other$menuFlag)) return false; Object this$requiredLogin = getRequiredLogin(); Object other$requiredLogin = other.getRequiredLogin(); if (this$requiredLogin == null ? other$requiredLogin != null : !this$requiredLogin.equals(other$requiredLogin)) return false; Object this$requiredPermission = getRequiredPermission(); Object other$requiredPermission = other.getRequiredPermission(); if (this$requiredPermission == null ? other$requiredPermission != null : !this$requiredPermission.equals(other$requiredPermission)) return false; Object this$createTime = getCreateTime(); Object other$createTime = other.getCreateTime(); if (this$createTime == null ? other$createTime != null : !this$createTime.equals(other$createTime)) return false; Object this$ipAddress = getIpAddress(); Object other$ipAddress = other.getIpAddress(); return this$ipAddress == null ? other$ipAddress == null : this$ipAddress.equals(other$ipAddress); } 
  protected boolean canEqual(Object other) { return other instanceof ResourceDefinition; } 
  public int hashCode() { int PRIME = 59; int result = 1; Object $appCode = getAppCode(); result = result * 59 + ($appCode == null ? 43 : $appCode.hashCode()); Object $className = getClassName(); result = result * 59 + ($className == null ? 43 : $className.hashCode()); Object $methodName = getMethodName(); result = result * 59 + ($methodName == null ? 43 : $methodName.hashCode()); Object $modularCode = getModularCode(); result = result * 59 + ($modularCode == null ? 43 : $modularCode.hashCode()); Object $modularName = getModularName(); result = result * 59 + ($modularName == null ? 43 : $modularName.hashCode()); Object $code = getCode(); result = result * 59 + ($code == null ? 43 : $code.hashCode()); Object $name = getName(); result = result * 59 + ($name == null ? 43 : $name.hashCode()); Object $url = getUrl(); result = result * 59 + ($url == null ? 43 : $url.hashCode()); Object $httpMethod = getHttpMethod(); result = result * 59 + ($httpMethod == null ? 43 : $httpMethod.hashCode()); Object $menuFlag = getMenuFlag(); result = result * 59 + ($menuFlag == null ? 43 : $menuFlag.hashCode()); Object $requiredLogin = getRequiredLogin(); result = result * 59 + ($requiredLogin == null ? 43 : $requiredLogin.hashCode()); Object $requiredPermission = getRequiredPermission(); result = result * 59 + ($requiredPermission == null ? 43 : $requiredPermission.hashCode()); Object $createTime = getCreateTime(); result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode()); Object $ipAddress = getIpAddress(); return result * 59 + ($ipAddress == null ? 43 : $ipAddress.hashCode()); } 
  public String toString() { return "ResourceDefinition(appCode=" + getAppCode() + ", className=" + getClassName() + ", methodName=" + getMethodName() + ", modularCode=" + getModularCode() + ", modularName=" + getModularName() + ", code=" + getCode() + ", name=" + getName() + ", url=" + getUrl() + ", httpMethod=" + getHttpMethod() + ", menuFlag=" + getMenuFlag() + ", requiredLogin=" + getRequiredLogin() + ", requiredPermission=" + getRequiredPermission() + ", createTime=" + getCreateTime() + ", ipAddress=" + getIpAddress() + ")";
  }
}