package cn.mnu.paper.comparator;

import java.util.Comparator;

import cn.mnu.paper.bean.CommentBean;
import cn.mnu.paper.bean.SortBean;

public class MyComparatorComment implements Comparator<CommentBean> {


	// ����all�Ĵ�С
	public int compare(CommentBean o1, CommentBean o2) {
		// TODO Auto-generated method stub
		return o2.getId() - o1.getId();
	}

	


}
