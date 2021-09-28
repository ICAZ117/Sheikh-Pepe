package Dependencies;

public class Submission {

	private String timestamp;
	private String firstName;
	private String lastName;
	private int age;
	private String Gender;
	private Boolean seenErtugrul;
	private Boolean seenPayitaht;
	private int progress;

	public Submission() {
		this.timestamp = "";
		this.firstName = "";
		this.lastName = "";
		this.age = 0;
		this.Gender = "";
		this.seenErtugrul = false;
		this.seenPayitaht = false;
		this.progress = 0;
	}

	public Submission(String timestamp, String firstName, String lastInitial, int age, String Gender, Boolean seenErtugrul, Boolean seenPayitaht, int progress) {
		this.timestamp = timestamp;
		this.firstName = firstName;
		this.lastName = lastInitial;
		this.age = age;
		this.Gender = Gender;
		this.seenErtugrul = seenErtugrul;
		this.seenPayitaht = seenPayitaht;
		this.progress = progress;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String Gender) {
		this.Gender = Gender;
	}

	public Boolean getSeenErtugrul() {
		return seenErtugrul;
	}

	public void setSeenErtugrul(Boolean seenErtugrul) {
		this.seenErtugrul = seenErtugrul;
	}

	public Boolean getSeenPayitaht() {
		return seenPayitaht;
	}

	public void setSeenPayitaht(Boolean seenPayitaht) {
		this.seenPayitaht = seenPayitaht;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public void incrementProgress() {
		this.progress++;
	}

	public void decrementProgress() {
		this.progress--;
	}

}
