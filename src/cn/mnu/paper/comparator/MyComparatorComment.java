package cn.mnu.paper.comparator;

import java.util.Comparator;

import cn.mnu.paper.bean.CommentBean;
import cn.mnu.paper.bean.SortBean;

public class MyComparatorComment implements Comparator<CommentBean> {


	// 决定all的大小
	public int compare(CommentBean o1, CommentBean o2) {
		// TODO Auto-generated method stub
		return o2.getId() - o1.getId();
	}

	


}
