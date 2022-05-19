package sem4.src.startUp;

import sem4.src.controller.Controller;
import sem4.src.intergration.Inventory;
import sem4.src.view.View;

public class Main {
	public static void main(String[] args) {
		Inventory inv = new Inventory();
		Controller contr = new Controller(inv);

		View view = new View(contr);
		view.runFakeSale();
	}
}
