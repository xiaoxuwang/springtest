package com.formBean;



/**
 * Author:xiaoxu.wang
 * Date:2010-8-31
 * Des:ss
 */


public class Menu {
	
		private int nodeid;
	    private String id;
	    private String text; //�ڵ�����
	    private String leaf;//ʱ���������ӽڵ�
	    private String cls;
	    private int parentid; //���ڵ�
	    private String href; //�ڵ�·��
	   
	    

		public String getHref() {
			return href;
		}
		public void setHref(String href) {
			this.href = href;
		}
		public int getNodeid() {
			return nodeid;
		}
		public void setNodeid(int nodeid) {
			this.nodeid = nodeid;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getLeaf() {
			return leaf;
		}
		public void setLeaf(String leaf) {
			this.leaf = leaf;
		}
		public int getParentid() {
			return parentid;
		}
		public void setParentid(int parentid) {
			this.parentid = parentid;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		
		public String getCls() {
			return cls;
		}
		public void setCls(String cls) {
			this.cls = cls;
		}
}
	
	



