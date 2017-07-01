package cn.mnu.paper.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.mnu.paper.action.base.BasePaperAction;
import cn.mnu.paper.domain.Paper;

public class MspaperPaperAction extends BasePaperAction {
	private List<Paper> papers;
	private Paper paperfirst;
	private Integer id;
	private String backPaper;
	
	public String getTop() throws Exception {
		
		
		//System.out.println("id" + id);
		
		papers = papermgr.findAllPaperOrderByCbtime();
		if (papers.size() > 0) {
			paperfirst = papers.get(0);
		}
		
		return SUCCESS;
	}

	public String toIndex() throws Exception {
		paperfirst = papermgr.findLastPaper();
		return SUCCESS;
	}
	
	public String prevPaper() throws Exception {
		backPaper = papermgr.findPrevPaper(id);
		if (!backPaper.trim().equals("0")) {
			return SUCCESS;
		} else {
			return "failure";
		}
	}
	
	public String nextPaper() throws Exception {
		backPaper = papermgr.findNextPaper(id);
		if (!backPaper.trim().equals("0")) {
			return SUCCESS;
		} else {
			return "failure";
		}
	}
	
	public List<Paper> getPapers() {
		return papers;
	}

	public void setPapers(List<Paper> papers) {
		this.papers = papers;
	}

	public Paper getPaperfirst() {
		return paperfirst;
	}

	public void setPaperfirst(Paper paperfirst) {
		this.paperfirst = paperfirst;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBackPaper() {
		return backPaper;
	}

	public void setBackPaper(String backPaper) {
		this.backPaper = backPaper;
	}

	
}
