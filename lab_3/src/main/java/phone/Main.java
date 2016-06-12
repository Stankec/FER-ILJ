package phone;

import phone.AppClassContext;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AppClassImpl printImpl = new AppClassImpl();
		AppClassContext context = new AppClassContext(printImpl);
		
		context.zoviBrojN();
	/*	context.pass();
		
		context.pass();
		
		context.coin();
		context.coin();*/
	}

}
