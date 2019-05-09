
public class Entry {
	
	//create variables used for an entry
	private String surname;
	private String initials;
	private String number;
	
	Entry next;
	
	public Entry(String entry)
	{
		//Constructor used for GUI when entry entered as a sentence
		String[] parts = entry.split(" ");
		
		surname = parts[0];
		initials = parts[1];
		number = parts[2];
		
	}
	
	//Constructor used when in code have seperate variables fore each part of entry 
	public Entry(String name, String initiials, String number)
	{
		this.surname = name;
		this.initials = initiials;
		this.number = number;
		
	}
	//set surname of entry
	public void setSurname(String name) {
		this.surname = name;
	}
	//retrieve name of entry
	public String getSurname() {
		return this.surname;
	}
	//set initials of the entry
	public void setInitials(String initials) {
		this.initials = initials;
	}
	//retrieve initials of entry
	public String getInitials() {
		return this.initials;
	}
	//set number of entry
	public void setNumber(String num) {
		this.number = num;
	}
	//retrieve number of entry
	public String getNumber() {
		return this.number;
	}

}
