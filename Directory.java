//interface for Directories and methods to use
public interface Directory {
	//add new entry
	void newEntry(Entry newEntry);
	//delete using name 
	void deleteEntryName(String name);
	//delete using number
	void deleteEntryNumber(String number);
	//find number
	Entry findExt(String name);
	//change extension
	void changeNo(String name, String number);
	//print whole directory
	void printDir();
}
