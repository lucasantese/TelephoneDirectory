import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class HashDirectory implements Directory {

	// Create an array of linked lists, one for each letter of the alphabet
	@SuppressWarnings("unchecked")
	private LinkedList<Entry>[] entries = new LinkedList[26];

	public HashDirectory(Entry[] entries) {

		// initialising array by adding a linked list to every spot
		for (int i = 0; i < this.entries.length; i++) {
			this.entries[i] = (new LinkedList<Entry>());
		}
		int alphNum;

		// adds each entry to specified array depending on the
		// first letter of the entries surname
		for (Entry temp : entries) {

			if (temp != null) {
				alphNum = getAlphaNum(temp.getSurname().charAt(0));
				this.entries[alphNum].add(temp);
			}

		}
	}

	@Override
	public void newEntry(Entry newEntry) {

		// find the right list to ass entry
		int x = getAlphaNum(newEntry.getSurname().charAt(0));
		// add entry to list
		entries[x].add(newEntry);

	}

	private int getAlphaNum(char charAt) {

		int position = (int) charAt - 65;

		return position;
	}

	// binary search method for linked list
	private Entry binarySearch(String name) {
		int alphNum = getAlphaNum(name.charAt(0));
		LinkedList<Entry> en = new LinkedList<Entry>(entries[alphNum]);

		int low = 0;
		int high = entries[alphNum].size() - 1;

		while (low <= high) {
			int mid = (high + low) / 2;

			int comp = en.iterator().next().getSurname().compareTo(name);

			if (comp == 0)
				return en.get(mid);

			if (comp < 0)
				low = mid + 1;
			else
				high = mid - 1;
		}
		//return null if not in directory
		return null;
	}

	@Override
	public void deleteEntryName(String name) {
		//delete entry based on name 
		//use try catch to catch out of bounds if name is not in directory
		try {
			Entry del = binarySearch(name);

			int x = getAlphaNum(name.charAt(0));
			entries[x].remove(del);
		} catch (NoSuchElementException e) {
			System.out.print("Caught NoSuchElementException\n");
		}

	}

	@Override
	public void deleteEntryNumber(String number) {
		//delete entry based on extension number
		//use try catch to catch out of bounds if name is not in directory
		try {
			for (int i = 0; i < entries.length; i++) {
				ListIterator<Entry> iterator = entries[i].listIterator();
				boolean notFound = true;
				while (notFound && entries[i] != null) {
					if (iterator.next().getNumber().equals(number)) {
						iterator.remove();
						notFound = false;
					}
				}
			}
		} catch (NoSuchElementException e) {
			System.out.print("Caught NoSuchElementException\n");
		}

	}

	@Override
	public Entry findExt(String name) {
		//find extension based on name
		//use try catch to catch out of bounds if name is not in directory
		try {
			Entry find = binarySearch(name);
			return find;
		} catch (NoSuchElementException e) {
			System.out.print("Caught NoSuchElementException\n");
			return null;
		}
	}

	@Override
	public void changeNo(String name, String number) {
		//Change the number of an entry based on name
		//use try catch to catch out of bounds if name is not in directory
		try {
			Entry change = binarySearch(name);
			change.setNumber(number);
		} catch (NoSuchElementException e) {
			System.out.print("Caught NoSuchElementException\n");
		}
	}

	@Override
	//loop through the whole directory and print each entry 
	//after printing the titles for each category
	public void printDir() {
		System.out.println("Surname" + "\t\t" + "Initials" + "\t" + "Number");
		for (int j = 0; j < (entries.length); j++) {
			for (int i = 0; i < (entries[j].size()); i++) {
				if (entries[j] == null) {
					break;
				} else {
					System.out.println(entries[j].listIterator().next().getSurname() + "\t\t"
							+ entries[j].listIterator().next().getInitials() + "\t\t"
							+ entries[j].listIterator().next().getNumber());
				}
			}
		}
	}

}
