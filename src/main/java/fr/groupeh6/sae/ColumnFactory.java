package fr.groupeh6.sae;



public class ColumnFactory {
	
	private static ColumnFactory instance;
	
	
	public static ColumnFactory getInstance() {
		if(instance == null) instance = new ColumnFactory();
		return instance;
	}

}
