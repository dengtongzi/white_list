package cn.locker.kernel.model.node;

import java.util.List;

import cn.locker.kernel.model.tree.Tree;

public class TreeNode
  implements Tree
{
  public static final String ROOT_NODE_ID = "-1";
  public static final String ROOT_NODE_NAME = "根结点";
  private String id;
  private String pId;
  private String name;
  private Boolean open;
  private Boolean checked = Boolean.valueOf(false);
  private String isMenu;
  private List<TreeNode> children;

  public String getNodeId()
  {
    return this.id.toString();
  }

  public String getNodeParentId()
  {
    return this.pId.toString();
  }

  public void setChildrenNodes(List childrenNodes)
  {
    this.children = childrenNodes;
  }

  public static TreeNode createRoot()
  {
    TreeNode root = new TreeNode();
    root.setChecked(Boolean.valueOf(false));
    root.setId("-1");
    root.setName("根结点");
    root.setOpen(Boolean.valueOf(true));
    root.setPId(null);
    return root;
  }

  public String getId()
  {
    return this.id;
  }

  public String getPId()
  {
    return this.pId;
  }

  public String getName()
  {
    return this.name;
  }

  public Boolean getOpen()
  {
    return this.open;
  }

  public Boolean getChecked()
  {
    return this.checked;
  }

  public String getIsMenu()
  {
    return this.isMenu;
  }

  public List<TreeNode> getChildren()
  {
    return this.children;
  }

  public void setId(String id)
  {
    this.id = id; } 
  public void setPId(String pId) { this.pId = pId; } 
  public void setName(String name) { this.name = name; } 
  public void setOpen(Boolean open) { this.open = open; } 
  public void setChecked(Boolean checked) { this.checked = checked; } 
  public void setIsMenu(String isMenu) { this.isMenu = isMenu; } 
  public void setChildren(List<TreeNode> children) { this.children = children; } 
  public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof TreeNode)) return false; TreeNode other = (TreeNode)o; if (!other.canEqual(this)) return false; Object this$id = getId(); Object other$id = other.getId(); if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false; Object this$pId = getPId(); Object other$pId = other.getPId(); if (this$pId == null ? other$pId != null : !this$pId.equals(other$pId)) return false; Object this$name = getName(); Object other$name = other.getName(); if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false; Object this$open = getOpen(); Object other$open = other.getOpen(); if (this$open == null ? other$open != null : !this$open.equals(other$open)) return false; Object this$checked = getChecked(); Object other$checked = other.getChecked(); if (this$checked == null ? other$checked != null : !this$checked.equals(other$checked)) return false; Object this$isMenu = getIsMenu(); Object other$isMenu = other.getIsMenu(); if (this$isMenu == null ? other$isMenu != null : !this$isMenu.equals(other$isMenu)) return false; Object this$children = getChildren(); Object other$children = other.getChildren(); return this$children == null ? other$children == null : this$children.equals(other$children); } 
  protected boolean canEqual(Object other) { return other instanceof TreeNode; } 
  public int hashCode() { int PRIME = 59; int result = 1; Object $id = getId(); result = result * 59 + ($id == null ? 43 : $id.hashCode()); Object $pId = getPId(); result = result * 59 + ($pId == null ? 43 : $pId.hashCode()); Object $name = getName(); result = result * 59 + ($name == null ? 43 : $name.hashCode()); Object $open = getOpen(); result = result * 59 + ($open == null ? 43 : $open.hashCode()); Object $checked = getChecked(); result = result * 59 + ($checked == null ? 43 : $checked.hashCode()); Object $isMenu = getIsMenu(); result = result * 59 + ($isMenu == null ? 43 : $isMenu.hashCode()); Object $children = getChildren(); return result * 59 + ($children == null ? 43 : $children.hashCode()); } 
  public String toString() { return "TreeNode(id=" + getId() + ", pId=" + getPId() + ", name=" + getName() + ", open=" + getOpen() + ", checked=" + getChecked() + ", isMenu=" + getIsMenu() + ", children=" + getChildren() + ")";
  }
}