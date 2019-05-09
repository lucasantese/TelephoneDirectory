import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class TelephoneDirectory {

	// This class i used to read in all the data saved in the directory text file
	// also used when i was testing the methods of each implementation of directory
	// and finally used when timing each directory
	public static void main(String[] args) {

		//StopWatch stop = new StopWatch();

		ArrayDirectory arr = new ArrayDirectory();
		ListDirectory list = new ListDirectory();
		
		//initialise and read into hash
		Entry[] en = readDirectory();
		HashDirectory hash = new HashDirectory(en);
		//read into array
		readDirectoryArr(arr);
		//read into list
		readDirectoryList(list);
				
		arr.printDir();
		list.printDir();
		hash.printDir();
		
		//used to populate the directory with 10000 entries 
		//so i can time an average time to insert and lookup
		String sources = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < 10000; i++) {
            String ran = generateString(new Random(), sources, 5);
            String s1 = ran.substring(0, 1).toUpperCase();
            String nameCap = s1 + ran.substring(1);
            Entry e = new Entry(nameCap, "N.A.", "12345");
            arr.newEntry(e);
		}
        
		
		/*
		Entry ent = new Entry("San", "L.D.", "12345");
		hash.newEntry(ent);
		hash.printDir();
		System.out.println(list.findExt("Santese").getNumber());
		System.out.println("\n");
		hash.changeNo("Lee", "12345");
		hash.printDir();
		System.out.println("\n");
		hash.deleteEntryName("Roger");
		hash.printDir();*/
		

		
		//this block of code was used to test both lookup and write of each 
		//implementation of the directory class
		/*for (int i = 0; i < 1000; i++) {
			stop.start();
			Entry en2 = new Entry("Mmmm", "L.D.", "12345");
			hash.newEntry(en2);
			hash.deleteEntryName("Mmmm");
			stop.stop();
			hash.newEntry(en2);
		}
		System.out.println("Hash insert: " + stop.getElapsedTime() / 1000);
		stop.reset();
		
		for (int i = 0; i < 1000; i++) {			
			stop.start();
			hash.findExt("Mmmm");
			stop.stop();
		}
		System.out.println("Hash lookup: " + stop.getElapsedTime() / 1000);
		stop.reset();
		*/
		/*for (int i = 0; i < 1000; i++) {

			stop.start();
			Entry en2 = new Entry("Mmmm", "L.D.", "12345");
			list.newEntry(en2);
			list.deleteEntryName("Mmmm");
			stop.stop();
			list.newEntry(en2);
		}
		System.out.println("List insert: " + stop.getElapsedTime() / 1000);
		stop.reset();
		
		for (int i = 0; i < 1000; i++) {
			stop.start();
			list.findExt("Mmmm");
			stop.stop();
		}
		System.out.println("List lookup: " + stop.getElapsedTime() / 1000);
		stop.reset();*/

		/*for (int i = 0; i < 1000; i++) {

			stop.start();
			Entry en2 = new Entry("Mmmm", "L.D.", "12345");
			arr.newEntry(en2);
			arr.deleteEntryName("Mmmm");
			stop.stop();
			arr.newEntry(en2);
		}
		System.out.println("Array insert: " + stop.getElapsedTime() / 1000);
		
		for (int i = 0; i < 1000; i++) {
			stop.start();
			arr.findExt("Mmmm");
			stop.stop();
		}
		System.out.println("Array lookup: " + stop.getElapsedTime() / 1000);
		stop.reset();*/

	}

	// method used to read all the entries from text file into directory
	public static Entry[] readDirectory() {
		// array used when using the HashDirectory implementation
		Entry[] en = new Entry[20];
		BufferedReader reader;

		try {
			// read oin the text file
			reader = new BufferedReader(new FileReader("C:\\Users\\lucal\\OneDrive\\Documents\\Directory.txt"));
			String line;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				// split each line into an array
				String[] words = line.toString().split("\t");
				// use array to create a new entry using constructor
				Entry e = new Entry(words[0], words[1], words[2]);
				// add to array instead if using HashDirectory
				en[i] = e;
				i++;

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		// return array for HashDirectory
		return en;

	}
	public static void readDirectoryList(ListDirectory list) {
		// array used when using the HashDirectory implementation
		BufferedReader reader;

		try {
			// read oin the text file
			reader = new BufferedReader(new FileReader("C:\\Users\\lucal\\OneDrive\\Documents\\Directory.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				// split each line into an array
				String[] words = line.toString().split("\t");
				// use array to create a new entry using constructor
				Entry e = new Entry(words[0], words[1], words[2]);
				//add to list
				list.newEntry(e);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		// return array for HashDirectory

	}
	public static void readDirectoryArr(ArrayDirectory arr) {
		// array used when using the HashDirectory implementation
		BufferedReader reader;

		try {
			// read oin the text file
			reader = new BufferedReader(new FileReader("C:\\Users\\lucal\\OneDrive\\Documents\\Directory.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				// split each line into an array
				String[] words = line.toString().split("\t");
				// use array to create a new entry using constructor
				Entry e = new Entry(words[0], words[1], words[2]);
				//add to list
				arr.newEntry(e);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		// return array for HashDirectory

	}
	 public static String generateString(Random random, String characters, int length) {
	        char[] text = new char[length];
	        for (int i = 0; i < length; i++) {
	            text[i] = characters.charAt(random.nextInt(characters.length()));
	        }
	        return new String(text);
	    }

}
