package cn.locker.kernel.model.exception;

public class RequestEmptyException extends ServiceException
{
  public RequestEmptyException()
  {
    super(Integer.valueOf(400), "请求数据不完整或格式错误！");
  }

  public RequestEmptyException(String errorMessage) {
    super(Integer.valueOf(400), errorMessage);
  }

  public synchronized Throwable fillInStackTrace()
  {
    return null;
  }

  public boolean equals(Object o)
  {
    if (o == this) return true; if (!(o instanceof RequestEmptyException)) return false; RequestEmptyException other = (RequestEmptyException)o; return other.canEqual(this); } 
  protected boolean canEqual(Object other) { return other instanceof RequestEmptyException; } 
  public int hashCode() { return 1; } 
  public String toString() { return "RequestEmptyException()";
  }
}