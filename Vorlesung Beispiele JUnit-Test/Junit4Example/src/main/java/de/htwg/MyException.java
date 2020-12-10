package de.htwg;

public class MyException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5964931715021247414L;
	
	private String msg;
	
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public MyException( String msg) {
		 super(msg);
	        this.msg = msg;
    }
	
	

}
