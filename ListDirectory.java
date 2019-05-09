import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ListDirectory implements Directory {

	LinkedList<Entry> entryList = new LinkedList<Entry>();
	ListIterator<Entry> iterator = entryList.listIterator();

	@Override
	public void newEntry(Entry newEntry) {

		// Very convoluted way of adding new value to list
		// only way i could come up with
		// may change insert efficiency results
		// checks if empty
		if (entryList.size() == 0) {
			entryList.add(newEntry);
		}
		// checks
		else if (entryList.get(0).getSurname().compareTo(newEntry.getSurname()) >= 0) {
			entryList.add(0, newEntry);
		} else {
			int i = 0;
			while (entryList.get(i).getSurname().compareTo(newEntry.getSurname()) < 0) {
				i++;
			}
			entryList.add(i, newEntry);
		}

	}

	@Override

	public void deleteEntryName(String name) {
		//use try catch to catch out of bounds if name is not in directory
		try {
			ListIterator<Entry> iterator = entryList.listIterator();
			boolean notFound = true;
			while (notFound) {
				// iterates through until the name if found in list
				if (iterator.next().getSurname().equals(name)) {
					// removes the entry
					iterator.remove();
					// stops loop
					notFound = false;
				}

			}
		} catch (NoSuchElementException e) {
			System.out.print("Caught NoSuchElementException\n");
		}

	}

	@Override
	public void deleteEntryNumber(String number) {
		//use try catch to catch out of bounds if name is not in directory
		try {
			ListIterator<Entry> iterator = entryList.listIterator();
			boolean notFound = true;
			while (notFound) {
				// same ideas as name but finds the number asked for and removes entry
				if (iterator.next().getNumber().equals(number)) {
					iterator.remove();
					notFound = false;
				}

			}
		} catch (NoSuchElementException e) {
			System.out.print("Caught NoSuchElementException\n");
		}

	}

	@Override
	public Entry findExt(String name) {
		//use try catch to catch out of bounds if name is not in directory
		try {
			ListIterator<Entry> iterator = entryList.listIterator();
			boolean notFound = true;
			while (notFound) {
				// finds the right name and the returns the extension for siad name
				if (iterator.next().getSurname().equals(name)) {
					Entry en = iterator.previous();
					return en;
				}
			}
			return null;
		} catch (NoSuchElementException e) {
			System.out.print("Caught NoSuchElementException\n");
		}
		return null;
	}

	@Override
	public void changeNo(String name, String number) {
		//use try catch to catch out of bounds if name is not in directory
		try {
			ListIterator<Entry> iterator = entryList.listIterator();
			boolean notFound = true;
			while (notFound) {
				// iterates through list and then renames extension to the one specified
				if (iterator.next().getSurname().equals(name)) {
					iterator.previous().setNumber(number);
					notFound = false;
				}

			}

		} catch (NoSuchElementException e) {
			System.out.print("Caught NoSuchElementException\n");
		}
	}

	@Override
	public void printDir() {
		// iterates through and prints every member of the directory
		ListIterator<Entry> iterator = entryList.listIterator();
		System.out.println("Surname" + "\t\t" + "Initials" + "\t" + "Number");
		int i = 0;
		while (iterator.hasNext()) {
			System.out.println(entryList.get(i).getSurname() + "\t\t" + entryList.get(i).getInitials() + "\t\t"
					+ entryList.get(i).getNumber());
			i++;
		}

	}

}
