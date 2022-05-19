package sem4.src.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import sem4.src.model.Sale;

public class TotalRevenueFileOutput implements Observer<Sale> {
	private int totalRevenue;
	private static final String LOG_FILE_NAME = "TotalRevenueFileOutput.txt";
	private static final TotalRevenueFileOutput INSTANCE = new TotalRevenueFileOutput();
	private PrintWriter log_File;

	public static TotalRevenueFileOutput getTotalRevenueFileOutput() {
		return INSTANCE;
	}

	public TotalRevenueFileOutput() {
		this.totalRevenue = 0;
		try {
			log_File = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
		} catch (IOException e) {
			System.out.println("Could not print to TotalRevenueFileOutput.txt");
			e.printStackTrace();
		}
	}

	@Override
	public void notice(Sale object) {
		totalRevenue += object.getRunningTotal() + object.getRunningVAT();

	}

	public void printRevenueToFile() {
		StringBuilder logMsgBuilder = new StringBuilder();
		logMsgBuilder.append(createTime());
		logMsgBuilder.append(", Total Revenue from all sales: " + this.totalRevenue + " SEK");
		log_File.println(logMsgBuilder);
		log_File.println("\n");
		log_File.flush();
	}

	private String createTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		return now.format(formatter);
	}
}
