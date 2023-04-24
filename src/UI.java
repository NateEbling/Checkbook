import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;


public class UI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static String bDir = "/Users/nathanebling/Documents/Checkbook/balance.txt";
	private final static String cbDir = "/Users/nathanebling/Documents/Checkbook/checkbook.txt";
	
	public static void createUI() throws IOException {


		JFrame frame = new JFrame("Register");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



		String[] columns = {"Date", "Description", "Transaction", "Balance"};



		String[][] data = {{""}, {""}};

		data = setData(data);		


		JTable register = new JTable(data, columns) {

			/* Comment the following code to make the register editable */
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
			};

		};

		frame.getContentPane().add(new JScrollPane(register), "Center");

		register.setGridColor(Color.black);

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 3, 0, 0));


		JButton deposit = new JButton("Deposit");
		JButton withdrawal = new JButton("Withdrawal");
		JButton exit = new JButton("Exit");
		JButton clear = new JButton("Clear");

		buttons.add(deposit);
		buttons.add(withdrawal);
		buttons.add(clear);
		buttons.add(exit);

		deposit.addActionListener(new Actionlistener() {
			public void actionPerformed(ActionEvent d) {

				double deposit = Checkbook.deposit();

				//				System.out.println(deposit); // Test

				try {

					Checkbook.writeFile(deposit, "+");

					//					frame.dispose();

					//					update(register);

					frame.dispose();

					createUI();



				} catch (IOException e) {

					e.printStackTrace();

				}

			}
		});

		withdrawal.addActionListener(new Actionlistener() {
			public void actionPerformed(ActionEvent w) {

				double withdrawal = Checkbook.withdrawal();

				//				System.out.println(withdrawal); // Test

				try {

					Checkbook.writeFile(withdrawal, "-");

					//					frame.dispose();

					//					update(register);

					frame.dispose();

					createUI();


				} catch (IOException e) {

					e.printStackTrace();

				}

			}
		});

		exit.addActionListener(new Actionlistener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();

			}
		});

		clear.addActionListener(new Actionlistener() {
			public void actionPerformed(ActionEvent g) {

				int test = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Clear register", 0);


				if(test == 1) {

					return;

				}

				if(test == 0) {

					try {

						Checkbook.clearRegister();

						frame.dispose();

						createUI();

					} catch (IOException e) {

						e.printStackTrace();

					}


				}

			}
		});


		frame.add(buttons, BorderLayout.NORTH);

		frame.setVisible(true);
		frame.setSize(700,800);
		frame.setResizable(true);

		frame.setLocationRelativeTo(null);


	}

	public static String[][] setData(String[][] data) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(cbDir));

		int counter = 0;

		while(br.readLine() != null) {
			counter++;
		}

		br.close();

		BufferedReader br2 = new BufferedReader(new FileReader(cbDir));

		int row = counter;
		int col = 4;

		String[][] array = new String[row][col];

		for(int i = 0; i < row; i++) {
			String[] temp = br2.readLine().split(";");
			int k = 0;
			for(int j = 0; j < col; j++) {
				array[i][j] = temp[k];
				k++;
			}
		}

		br2.close();

		return array;

	}

}
