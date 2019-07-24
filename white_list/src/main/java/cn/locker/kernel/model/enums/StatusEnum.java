package cn.locker.kernel.model.enums;

public enum StatusEnum
{
  ENABLE(Integer.valueOf(1), "启用"), 

  DISABLE(Integer.valueOf(2), "禁用");

  private Integer code;
  private String desc;

  private StatusEnum(Integer code, String desc) { this.code = code;
    this.desc = desc; }

  public static String getNameByCode(Integer code)
  {
    if (code == null) {
      return "";
    }
    for (StatusEnum enumItem : values()) {
      if (enumItem.getCode().equals(code)) {
        return enumItem.getDesc();
      }
    }
    return "";
  }

  public static StatusEnum toEnum(Integer code)
  {
    if (null == code) {
      return null;
    }
    for (StatusEnum e : values()) {
      if (e.getCode().equals(code)) {
        return e;
      }
    }
    return null;
  }

  public Integer getCode()
  {
    return this.code; } 
  public String getDesc() { return this.desc;
  }
}