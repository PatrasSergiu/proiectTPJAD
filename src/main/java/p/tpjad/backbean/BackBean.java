package p.tpjad.backbean;

import javax.annotation.PostConstruct;

import p.tpjad.config.scope.ViewScoped;

import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class BackBean {

	private String email;
	private String password;

	@PostConstruct
	public void doAfterConstruction() {
		System.out.println("doAfterConstruction");
	}

	public void buttonAction() {
		System.out.println(email);
		System.out.println(password);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		this.email = value;
	}

	public String getPassword(){
		return  password;
	}
	public void setPassword(String value){
		this.password=value;
	}
}
