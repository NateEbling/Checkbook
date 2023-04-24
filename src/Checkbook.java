/* Program created by Nate Ebling
 * 
 * Log:
 * 
 * Version 1.0 on 04/05/19 -- Completed the program
 * Version 1.1 on 04/05/19 -- Switched button locations for "clear" and "exit" (UI)
 * Version 1.2 on 04/10/19 -- Added transaction description as a parameter (UI); changed directory methods (main)
 * Version 1.3 on 04/10/19 -- Added final variables for directory (UI & main); removed directory methods (main)
 * Version 1.4 on 04/10/19 -- Changed grid lines to black (UI)
 * 
 */

import javax.swing.JOptionPane;
import java.io.*;
import java.text.DateFormat;
import java.text.*;
import java.util.Date;xf



public class Checkbook {
	
	private final static String bDir = "/Users/nathanebling/Documents/Checkbook/balance.txt";
	private final static String cbDir = "/Users/nathanebling/Documents/Checkbook/checkbook.txt";
	
	public static void main(String[] args) throws IOException {

		readFile();


		//		System.out.println(balance); // Test

		getDate();

		//		System.out.println(date); // Test 

		UI.createUI();


	}

	public static double readFile() throws IOException {

//		Scanner in = new Scanner(System.in);
		
		File file = new File(bDir);
		
		BufferedReader br = new BufferedReader(new FileReader(file));

		String currentLine;

		String lastLine = "";

		while((currentLine = br.readLine()) != null) {

			lastLine = currentLine;

		}
		
		br.close();
		

		double balance = Double.parseDouble(lastLine);

		return balance; 
		

	}

	public static String getDate() throws IOException {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();

		String temp = dateFormat.format(date);

		return temp;

	}

	public static double deposit() {
		
		String temp = (JOptionPane.showInputDialog(null, "Please enter the amount", "Deposit", JOptionPane.QUESTION_MESSAGE));

		if(!temp.contains(".")) {
			temp = temp + ".00";
		}
		double deposit = Double.parseDouble(temp);

		//		System.out.println(deposit); // Test

		return deposit;
		
	
	}

	public static double withdrawal() {

		String temp = (JOptionPane.showInputDialog(null, "Please enter the amount", "Withdrawal", JOptionPane.QUESTION_MESSAGE));

		if(!temp.contains(".")) {
			temp = temp + ".00";
		}

		double withdrawal = Double.parseDouble(temp);

		return withdrawal;

	}

	public static double getBalance() throws IOException {
		
		File file = new File(bDir);
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String currentLine;

		String lastLine = "";

		while((currentLine = br.readLine()) != null) {

			lastLine = currentLine;

		}
		
		br.close();

		double balance = Double.parseDouble(lastLine);

		return balance;

	}

	public static void writeFile(double transaction, String sign) throws IOException {

		double deposit = 0;
		double withdrawal = 0;

		double balance = getBalance();

//		System.out.println(balance); // Test

		if(sign == "+") {

			deposit = transaction;
			
			balance = balance + deposit;

		}

		if(sign == "-") {

			withdrawal = transaction;
			
			balance = balance - withdrawal;

		}

//		System.out.println(balance); // Test
		
		PrintWriter out = new PrintWriter(new FileWriter(bDir, true));
		out.printf("\n" + "%.2f", balance);
		
		out.close();
		
		String date = getDate();
		
		String description = (JOptionPane.showInputDialog(null, "Please enter the description", "Description", JOptionPane.QUESTION_MESSAGE));
	
		PrintWriter out2 = new PrintWriter(new FileWriter(cbDir, true));
		out2.printf("\n" + date + ";" + description + ";" + sign + "%.2f" + ";" + "%.2f", transaction, balance);

		out2.close();
		

	}
	
	public static void clearRegister() throws FileNotFoundException, IOException {
		
		PrintWriter pw = new PrintWriter(cbDir);
		pw.close();
		
		PrintWriter pw2 = new PrintWriter(bDir);
		pw2.close();
		
		String balance = (JOptionPane.showInputDialog(null, "Please enter the new balace", "New Register", JOptionPane.QUESTION_MESSAGE));
		
		double bal = Double.parseDouble(balance);
		
		PrintWriter out = new PrintWriter(new FileWriter(bDir, true));
		out.printf("%.2f", bal);
		
		out.close();
		
		
		String date = getDate();
		
		PrintWriter out2 = new PrintWriter(new FileWriter(cbDir, true));
		out2.printf(date + ";" + "+" + "0.00" + ";" + "%.2f", bal);

		out2.close();
		

		
	}

}
