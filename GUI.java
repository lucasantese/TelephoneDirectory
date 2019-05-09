import java.awt.event.*;
import javax.swing.*;


public class GUI {

	public static void main(String[] args) {

		ArrayDirectory arr = new ArrayDirectory();

		TelephoneDirectory.readDirectoryArr(arr);
		
		//Creates the window the directory GUI will appear in
		JFrame f = new JFrame("Directory");
		
		//Set the labels at the top of the window explaining the program
		JLabel direct = new JLabel("Phone Extension Directory");
		JLabel label1 = new JLabel("Enter in the form when adding new Entry:");
		JLabel label2 = new JLabel("Surname I.N. 12345");
		direct.setBounds(25, 20, 200, 20);
		label1.setBounds(350, 10, 200, 20);
		label2.setBounds(350, 30, 200, 20);

		//set the textbox for instructions on what the buttons will do
		final JTextArea instruction = new JTextArea();
		instruction.setText("Instructions: \nPrint = Print all Directory entries \n"
				+ "Add New = Adds entry to directory \n" + "Extension = Enter name to find number \n"
				+ "Change Extension = Enter name and new number \n" + "Remove Name = Enter name to remove entry \n"
				+ "Remove Number = Enter extention to remove entry");
		instruction.setBounds(350, 250, 290, 120);

		//set the text are used to output the directory and extensions
		final JTextArea tf = new JTextArea();
		tf.setBounds(25, 60, 300, 310);

		//setting the text area use to look up, change and delete entries 
		final JTextField enter = new JTextField();
		enter.setBounds(350, 60, 290, 50);
		enter.setText("Enter Here");

		//Here is where i set up the names and positioning for all my buttons
		JButton print = new JButton("Print List");
		print.setBounds(350, 120, 90, 30);
		JButton add = new JButton("Add new");
		add.setBounds(450, 120, 90, 30);
		JButton getNum = new JButton("Extension");
		getNum.setBounds(550, 120, 90, 30);
		JButton changeNo = new JButton("Change Extension");
		changeNo.setBounds(350, 160, 140, 30);
		JButton deleteName = new JButton("Remove Name");
		deleteName.setBounds(500, 160, 140, 30);
		JButton deleteNum = new JButton("Remove Number");
		deleteNum.setBounds(350, 200, 140, 30);
		JButton exit = new JButton("Exit");
		exit.setBounds(500, 200, 140, 30);

	     

		//action to be taken when the print directory button is pressed
		print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf.setText("Surname\tInitials\tNumber");
				for (int i = 0; i < arr.arrayLength(); i++) {
					//prints each entry line by line into text box
					String x = arr.printGUI(i);
					tf.append("\n");
					tf.append(x);
				}
			}

		});
		
		//when add button is pressed it will take entry in entry textbox and enter it into the directory
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] split = enter.getText().split(" ");
				//basic error checking, was not able to work out how to check format completely
				//so i only check the length of the string to match the format needed
				if (split.length == 3 && split[2].length() == 5) {
					if (split[1].length() == 4 && split[2].length() == 5) {
						Entry en = new Entry(enter.getText());
						arr.newEntry(en);
						enter.setText("");
					}
				} 
				else {
					enter.setText("Please enter in the correct format");
				}
			}
		});
		
		//this button will retrieve the extension number for the name presented
		getNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = enter.getText();
				tf.setText(arr.findExt(name).getNumber());
				enter.setText("");

			}
		});
		
		//Change the extension number for the name provided
		changeNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] entry = enter.getText().split(" ");
				arr.changeNo(entry[0], entry[1]);
				enter.setText("");
			}
		});
		
		//Deletes entry using name provided
		deleteName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arr.deleteEntryName(enter.getText());
				enter.setText("");
			}
		});

		//Deletes entry using number provided
		deleteNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arr.deleteEntryNumber(enter.getText());
				enter.setText("");
			}
		});
		
		//close window when user is finished
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        System.exit(0);
			}
		});
		
		// adding all the labels, textboxes and buttons to the window
		f.add(direct);
		f.add(label1);
		f.add(label2);

		f.add(print);
		f.add(add);
		f.add(changeNo);
		f.add(getNum);
		f.add(deleteName);
		f.add(deleteNum);
		f.add(exit);

		f.add(instruction);
		f.add(tf);
		f.add(enter);

		//set size and layout of the window
		f.setSize(700, 450);
		f.setLayout(null);
		f.setVisible(true);
	}
}
