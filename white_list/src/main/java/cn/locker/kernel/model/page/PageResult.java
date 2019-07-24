package cn.locker.kernel.model.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;
import java.util.List;

public class PageResult<T>
  implements Serializable
{
  private static final long serialVersionUID = -4071521319254024213L;
  private Integer page = Integer.valueOf(1);
  private Integer pageSize = Integer.valueOf(20);
  private Integer totalPage = Integer.valueOf(0);
  private Long totalRows = Long.valueOf(0L);
  private List<T> rows;

  public PageResult()
  {
  }

  public PageResult(Page<T> page)
  {
    setRows(page.getRecords());
    setTotalRows(Long.valueOf(page.getTotal()));
    setPage(Integer.valueOf((int)page.getCurrent()));
    setPageSize(Integer.valueOf((int)page.getSize()));
  }

  public Integer getPage()
  {
    return this.page; } 
  public Integer getPageSize() { return this.pageSize; } 
  public Integer getTotalPage() { return this.totalPage; } 
  public Long getTotalRows() { return this.totalRows; } 
  public List<T> getRows() { return this.rows;
  }

  public void setPage(Integer page)
  {
    this.page = page; } 
  public void setPageSize(Integer pageSize) { this.pageSize = pageSize; } 
  public void setTotalPage(Integer totalPage) { this.totalPage = totalPage; } 
  public void setTotalRows(Long totalRows) { this.totalRows = totalRows; } 
  public void setRows(List<T> rows) { this.rows = rows; } 
  public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof PageResult)) return false; PageResult other = (PageResult)o; if (!other.canEqual(this)) return false; Object this$page = getPage(); Object other$page = other.getPage(); if (this$page == null ? other$page != null : !this$page.equals(other$page)) return false; Object this$pageSize = getPageSize(); Object other$pageSize = other.getPageSize(); if (this$pageSize == null ? other$pageSize != null : !this$pageSize.equals(other$pageSize)) return false; Object this$totalPage = getTotalPage(); Object other$totalPage = other.getTotalPage(); if (this$totalPage == null ? other$totalPage != null : !this$totalPage.equals(other$totalPage)) return false; Object this$totalRows = getTotalRows(); Object other$totalRows = other.getTotalRows(); if (this$totalRows == null ? other$totalRows != null : !this$totalRows.equals(other$totalRows)) return false; Object this$rows = getRows(); Object other$rows = other.getRows(); return this$rows == null ? other$rows == null : this$rows.equals(other$rows); } 
  protected boolean canEqual(Object other) { return other instanceof PageResult; } 
  public int hashCode() { int PRIME = 59; int result = 1; Object $page = getPage(); result = result * 59 + ($page == null ? 43 : $page.hashCode()); Object $pageSize = getPageSize(); result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode()); Object $totalPage = getTotalPage(); result = result * 59 + ($totalPage == null ? 43 : $totalPage.hashCode()); Object $totalRows = getTotalRows(); result = result * 59 + ($totalRows == null ? 43 : $totalRows.hashCode()); Object $rows = getRows(); return result * 59 + ($rows == null ? 43 : $rows.hashCode()); } 
  public String toString() { return "PageResult(page=" + getPage() + ", pageSize=" + getPageSize() + ", totalPage=" + getTotalPage() + ", totalRows=" + getTotalRows() + ", rows=" + getRows() + ")";
  }
}