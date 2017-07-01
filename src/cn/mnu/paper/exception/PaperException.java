package cn.mnu.paper.exception;

public class PaperException extends Exception {
	
	// 定义一个无参数的构造器
	public PaperException()
	{
	}
	// 定义一个带message参数的构造参数
	public PaperException(String message)
	{
		super(message);
	}
}
