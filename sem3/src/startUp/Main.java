package sem3.src.startUp;

import sem3.src.controller.Controller;
import sem3.src.intergration.Inventory;
import sem3.src.view.View;

public class Main {
	public static void main(String[] args) {
		Inventory inv = new Inventory();
		Controller contr = new Controller(inv);

		View view = new View(contr);
		view.runFakeSale();
	}
}
