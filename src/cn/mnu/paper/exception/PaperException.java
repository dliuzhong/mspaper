package cn.mnu.paper.exception;

public class PaperException extends Exception {
	
	// ����һ���޲����Ĺ�����
	public PaperException()
	{
	}
	// ����һ����message�����Ĺ������
	public PaperException(String message)
	{
		super(message);
	}
}
