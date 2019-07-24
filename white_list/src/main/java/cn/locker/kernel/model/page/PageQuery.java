package cn.locker.kernel.model.page;

public class PageQuery
{
  private Integer pageSize;
  private Integer pageNo;
  private String sort;
  private String orderByField;

  public PageQuery()
  {
  }

  public PageQuery(Integer pageSize, Integer pageNo, String sort, String orderByField)
  {
    this.pageSize = pageSize;
    this.pageNo = pageNo;
    this.sort = sort;
    this.orderByField = orderByField;
  }

  public Integer getPageSize()
  {
    return this.pageSize;
  }

  public Integer getPageNo()
  {
    return this.pageNo;
  }

  public String getSort()
  {
    return this.sort;
  }

  public String getOrderByField()
  {
    return this.orderByField;
  }

  public void setPageSize(Integer pageSize)
  {
    this.pageSize = pageSize; } 
  public void setPageNo(Integer pageNo) { this.pageNo = pageNo; } 
  public void setSort(String sort) { this.sort = sort; } 
  public void setOrderByField(String orderByField) { this.orderByField = orderByField; } 
  public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof PageQuery)) return false; PageQuery other = (PageQuery)o; if (!other.canEqual(this)) return false; Object this$pageSize = getPageSize(); Object other$pageSize = other.getPageSize(); if (this$pageSize == null ? other$pageSize != null : !this$pageSize.equals(other$pageSize)) return false; Object this$pageNo = getPageNo(); Object other$pageNo = other.getPageNo(); if (this$pageNo == null ? other$pageNo != null : !this$pageNo.equals(other$pageNo)) return false; Object this$sort = getSort(); Object other$sort = other.getSort(); if (this$sort == null ? other$sort != null : !this$sort.equals(other$sort)) return false; Object this$orderByField = getOrderByField(); Object other$orderByField = other.getOrderByField(); return this$orderByField == null ? other$orderByField == null : this$orderByField.equals(other$orderByField); } 
  protected boolean canEqual(Object other) { return other instanceof PageQuery; } 
  public int hashCode() { int PRIME = 59; int result = 1; Object $pageSize = getPageSize(); result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode()); Object $pageNo = getPageNo(); result = result * 59 + ($pageNo == null ? 43 : $pageNo.hashCode()); Object $sort = getSort(); result = result * 59 + ($sort == null ? 43 : $sort.hashCode()); Object $orderByField = getOrderByField(); return result * 59 + ($orderByField == null ? 43 : $orderByField.hashCode()); } 
  public String toString() { return "PageQuery(pageSize=" + getPageSize() + ", pageNo=" + getPageNo() + ", sort=" + getSort() + ", orderByField=" + getOrderByField() + ")";
  }
}