package Login;

public class Account {
	private String username, password;
	Account(){
		username="";
		password="";
	}
	Account(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	String getUsername() {
		return username;
	}
	
	String getPassword() {
		return password;
	}
	
	String hashedPassword() {
		return Hashing.getHash(password);
	}
}
