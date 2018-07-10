package presentation.managed_beans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import businesslogic.UserManager;
import transferobjects.User;


@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	UserManager userManager;
	private User user;

	
	public UserBean() {
		userManager = new UserManager(); 
		user = new User();
	}
	
	
	public String saveUser() {
		
		//user = new User(user.getId(),user.getFirstname(),user.getLastname(),user.geteMail(), user.getPassword());
		userManager.addUser(user);
		return "registration_finish.xhtml";
		
	}
	
	
	public void resetRegistrationForm() {
		
	}
	
	
	//Getter und Setter
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	


}
