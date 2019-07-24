package cn.locker.kernel.model.exception;

public abstract class ApiServiceException extends Exception
{
  private Integer code;
  private String errorMessage;

  public ApiServiceException(AbstractBaseExceptionEnum exception)
  {
    super(exception.getMessage());
    this.code = exception.getCode();
    this.errorMessage = exception.getMessage();
  }

  public abstract String getExceptionClassName();

  public Integer getCode()
  {
    return this.code;
  }

  public String getErrorMessage()
  {
    return this.errorMessage;
  }

  public void setCode(Integer code)
  {
    this.code = code; } 
  public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage;
  }
}