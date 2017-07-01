package cn.mnu.paper.action;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.mnu.paper.bean.SortBean;
import cn.mnu.paper.domain.ArticleType;
import cn.mnu.paper.domain.Department;
import cn.mnu.paper.domain.Msuser;
import cn.mnu.paper.action.base.BaseArticleAction;

public class ArticleSortAction extends BaseArticleAction {
	private List<SortBean> sblist;
	private String sortMain;
	private String contain;
	private Date date1;
	private Date date2;
	private String sort;
	private int allArticle;
	private String data;
	
	private int depart1;
	private int depart2;
	private List<Department> dl1;
	private List<Department> dl2;
	private String department;
	
	private InputStream excelStream;
	
	
	
	public String execute() throws Exception {
		// 初始化部门
		department = null;
		dl1 = departmentmgr.findDepartmentByType(1);
		dl2 = departmentmgr.findDepartmentByType(2);
		Department de = new Department();
		de.setId(-1);
		de.setName("所有内部部门");
		dl1.add(0,de);
		de.setName("所有外部部门");
		dl2.add(0,de);
		
		if (sortMain != null && sortMain.trim().equals("none")) {
			allArticle = 0;
			
			return SUCCESS;
		}
		if (date1 != null && date2 != null && 
				date1.after(date2)) {
			allArticle = 0;
			data = "日期错误！请重试！";
			return SUCCESS;
		}
		sblist = new ArrayList<SortBean>();
		
		SortBean sb;
		if (sortMain != null) {
			
			if (sortMain.trim().equals("depart")) {
				setDepart1(0);
				setDepart2(0);
				//allArticle = articlemgr.findAllArticleBySortSum(sortMain, sort, contain, date1, date2);
				allArticle = 0;
				data = "部门投稿情况";
				List<Department> dl = departmentmgr.findAllDepartment();
				Department d;
				
				for (int i = 0;i < dl.size(); i++) {
					d = dl.get(i);
					if (sort.trim().equals("in") && d.getType() != 1) {
						continue;
					}
					if (sort.trim().equals("out") && d.getType() != 2) {
						continue;
					}
					sb = new SortBean();
					
					int ds = articlemgr.findArticleBySortSum("depart", d.getId(), contain, date1, date2);
					sb.setName(d.getName());
					
					sb.setAll(ds);
					sblist.add(sb);
					allArticle += ds;
				}
				if (allArticle == 0) {
					data = "没有结果，或数量为0！请重试！";
					return SUCCESS;
				}
			} else if (sortMain.trim().equals("msuser")) {
				allArticle = 0;
				// 设置部门
			
				data = "投稿用户投稿情况";
				List<Msuser> ml = msusermgr.findAllMsuser();
				Msuser m;
				for (int i = 0;i < ml.size(); i++) {
					m = ml.get(i);
					if (department == null || department.trim().equals("")) {
						department = m.getDepartment().getName();
					}
					if (sort.trim().equals("in")) {
						if (m.getType().trim().equals("内部用户")) {
							if (depart1 != -1 && m.getDepartment().getId() != depart1) {
								
								continue;
							}
						} else {
							continue;
						}
						
					}
					if (sort.trim().equals("out")) {
						if (m.getType().trim().equals("外部用户")) {
							if (depart2 != -1 && m.getDepartment().getId() != depart2) {
								continue;
							}
						} else {
							continue;
						}
					}
					sb = new SortBean();
					int ds = articlemgr.findArticleBySortSum("msuser", m.getId(), contain, date1, date2);
					sb.setName(m.getName());
					sb.setAll(ds);
					sblist.add(sb);
					allArticle += ds;
				}
				if (allArticle == 0) {
					data = "没有结果，或数量为0！请重试！";
					return SUCCESS;
				}
			} else if (sortMain.trim().equals("type")) {
				allArticle = 0;
				
				data = "稿件类型分布情况";
				List<ArticleType> atl = articleTypemgr.findAllArticleType();
				ArticleType at;
				for (int i = 0;i < atl.size(); i++) {
					at = atl.get(i);
					sb = new SortBean();
					int ds = articlemgr.findArticleBySortSum("type", at.getId(), contain,  date1, date2);
					sb.setName(at.getName());
					sb.setAll(ds);
					sblist.add(sb);
					allArticle += ds;
				}
				if (allArticle == 0) {
					data = "没有结果，或数量为0！请重试！";
					return SUCCESS;
				}
			} 
		}
		return SUCCESS;
	}
	
	public String exportSort() throws Exception {
		if (execute().equals(SUCCESS)){
			String other = "";
			if (department != null && !department.trim().equals("")) {
				other = "，" + department;
			}
			excelStream = articlemgr.findInputStreamArticles(getSblist(), getAllArticle(), 
					getSortMain(), getSort(), getContain(), getDate1(), getDate2(), other);
			return SUCCESS;
		} else {
			return "failure";
		}
	}
	
	public List<SortBean> getSblist() {
		return sblist;
	}
	public void setSblist(List<SortBean> sblist) {
		this.sblist = sblist;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getAllArticle() {
		return allArticle;
	}

	public void setAllArticle(int allArticle) {
		this.allArticle = allArticle;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSortMain() {
		return sortMain;
	}

	public void setSortMain(String sortMain) {
		this.sortMain = sortMain;
	}

	public String getContain() {
		return contain;
	}

	public void setContain(String contain) {
		this.contain = contain;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public int getDepart1() {
		return depart1;
	}

	public void setDepart1(int depart1) {
		this.depart1 = depart1;
	}

	public int getDepart2() {
		return depart2;
	}

	public void setDepart2(int depart2) {
		this.depart2 = depart2;
	}

	public List<Department> getDl1() {
		return dl1;
	}

	public void setDl1(List<Department> dl1) {
		this.dl1 = dl1;
	}

	public List<Department> getDl2() {
		return dl2;
	}

	public void setDl2(List<Department> dl2) {
		this.dl2 = dl2;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}



	
	
}
