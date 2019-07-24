package cn.locker.kernel.model.api.base;

import cn.locker.kernel.model.validator.BaseValidatingParam;

public abstract class AbstractBaseRequest
  implements BaseValidatingParam
{
  private String requestNo;
  private String spanId;

  public String getRequestNo()
  {
    return this.requestNo;
  }

  public String getSpanId()
  {
    return this.spanId;
  }

  public void setRequestNo(String requestNo)
  {
    this.requestNo = requestNo; } 
  public void setSpanId(String spanId) { this.spanId = spanId;
  }
}