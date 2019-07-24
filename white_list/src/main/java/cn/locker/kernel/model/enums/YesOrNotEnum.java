package cn.locker.kernel.model.enums;

public enum YesOrNotEnum
{
  Y(Boolean.valueOf(true), "是", Integer.valueOf(1)), 

  N(Boolean.valueOf(false), "否", Integer.valueOf(0));

  private Boolean flag;
  private String desc;
  private Integer code;

  private YesOrNotEnum(Boolean flag, String desc, Integer code) { this.flag = flag;
    this.desc = desc;
    this.code = code; }

  public static String valueOf(Integer status)
  {
    if (status == null) {
      return "";
    }
    for (YesOrNotEnum s : values()) {
      if (s.getCode().equals(status)) {
        return s.getDesc();
      }
    }
    return "";
  }

  public Boolean getFlag()
  {
    return this.flag; } 
  public String getDesc() { return this.desc; } 
  public Integer getCode() { return this.code;
  }
}