package cn.mnu.paper.comparator;

import java.util.Comparator;

import cn.mnu.paper.bean.SortBean;

public class MyComparator implements Comparator<SortBean> {


	// 决定all的大小
	public int compare(SortBean o1, SortBean o2) {
		// TODO Auto-generated method stub
		return o2.getAll() - o1.getAll();
	}


}
