import java.util.Arrays;

class ArrayDirectory implements Directory {

	Entry[] direct;

	@Override
	public void newEntry(Entry newEntry) {

		// If empty just add to array
		if (direct == null) {
			direct = new Entry[] { newEntry };
			return;
		}

		// create temp array with 1 larger size that directory array
		Entry[] temp = new Entry[direct.length + 1];
		// set index after finding surname with binary search
		int index = binarySearchInsert(newEntry.getSurname());
		// copy the array
		temp = Arrays.copyOf(direct, direct.length + 1);
		// create new copy of array
		System.arraycopy(direct, index, temp, index + 1, direct.length - index);

		// set directory to new temp
		direct = temp;
		// add new entry to correct spot in array
		direct[index] = newEntry;

	}

	private int binarySearchInsert(String name) {
		//use try catch to catch out of bounds if name is not in directory
		try {
			int low = 0;
			int high = direct.length - 1;

			while (low <= high) {
				int mid = (high + low) / 2;

				int comp = direct[mid].getSurname().compareTo(name);

				// Check if present at mid
				if (comp == 0)
					return mid;

				// If greater, ignore left half
				if (comp < 0)
					low = mid + 1;

				// If smaller, ignore right half
				else
					high = mid - 1;
			}

			// if we reach here, then element was
			// not present
			return low;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("Caught ArrayIndexOutOfBoundsException\n");
		}
		return 0;
	}

	// same as other binary seach but just used to find instead of insert
	private int binarySearch(String name) {
		//use try catch to catch out of bounds if name is not in directory
		try {
			int low = 0;
			int high = direct.length - 1;
			// use loop to split array
			while (low <= high) {
				int mid = (high + low) / 2;

				int comp = direct[mid].getSurname().compareTo(name);
				// check name is the one looking for
				if (comp == 0)
					return mid;
				// carry on binary search if not found
				if (comp < 0)
					low = mid + 1;
				else
					high = mid - 1;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("Caught ArrayIndexOutOfBoundsException\n");
		}

		return -1;

	}

	// index++;
	@Override
	// used to find an entry based of a name and the dekete that entry
	public void deleteEntryName(String name) {
		//use try catch to catch out of bounds if name is not in directory
		try {
			if (direct == null) {
				System.out.println("Array Empty");
				return;
			}

			Entry[] temp = new Entry[direct.length - 1];

			int index = binarySearch(name);

			for (int i = 0, j = 0; i < direct.length; i++) {
				if (i == index)
					continue;
				else
					temp[j++] = direct[i];
			}

			direct = temp;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("Caught ArrayIndexOutOfBoundsException\n");
		}
	}

	@Override
	public void deleteEntryNumber(String number) {
		//use try catch to catch out of bounds if name is not in directory
		try {
			// search for the entry will matching number and deletes said entry
			boolean found = false;

			Entry[] temp = new Entry[direct.length - 1];
			// loop through until found = true
			for (int i = 0, j = 0; i < direct.length; i++) {
				if (direct[i].getNumber().equals(number) && !found) {
					found = true;
					continue;
				} else {
					temp[j++] = direct[i];
				}
			}

			if (found)
				direct = temp;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("Caught ArrayIndexOutOfBoundsException\n");
		}
	}

	@Override
	public Entry findExt(String name) {
		//use try catch to catch out of bounds if name is not in directory
		try {
			// call binary search to find name the return entry
			int index = binarySearch(name);

			if (index < 0) {
				System.out.println("Entry not in Directory");
				return null;
			}

			return direct[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("Caught ArrayIndexOutOfBoundsException\n");
		}
		return null;
	}

	@Override
	public void changeNo(String name, String number) {
		//use try catch to catch out of bounds if name is not in directory
		try {
			// binary search to find entry then change extension
			int index = binarySearch(name);

			if (index < 0) {
				System.out.println("Entry not in Directory");
				return;
			}

			direct[index].setNumber(number);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("Caught ArrayIndexOutOfBoundsException\n");
		}

	}

	@Override
	public void printDir() {
		System.out.println("Surname" + "\t\t" + "Initials" + "\t" + "Number");
		// iterates through every entry and prints it
		for (int i = 0; i < (direct.length); i++) {
			if (direct[i] == null) {
				break;
			} else {
				System.out.println(
						direct[i].getSurname() + "\t\t" + direct[i].getInitials() + "\t\t" + direct[i].getNumber());
			}
		}
	}

	// used to print directory to GUI when print button is pressed
	public String printGUI(int i) {
		return (direct[i].getSurname() + "\t" + direct[i].getInitials() + "\t" + direct[i].getNumber()).toString();
	}

	public int arrayLength() {
		return direct.length;
	}

}
