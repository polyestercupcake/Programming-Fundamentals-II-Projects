package model;

public class Database {
	public String getAnswer(String sql) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		return "The answer is Pi/2";
	}
}
