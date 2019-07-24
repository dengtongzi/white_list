package cn.locker.kernel.model.tree;

import java.util.List;

public abstract interface Tree
{
  public abstract String getNodeId();

  public abstract String getNodeParentId();

  public abstract void setChildrenNodes(List paramList);
}