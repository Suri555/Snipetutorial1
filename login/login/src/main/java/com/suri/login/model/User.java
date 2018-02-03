package com.suri.login.model;

import java.security.Permission;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="USERS")

public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@Size(min=4, message="username must be atleast 4 char !!!  ")
	@Column(unique=true)
	private String username;
	
	@NotNull
	@NotEmpty(message="password should be enter")
	@Size(min=4, message="password must be atleast 4 char")
	private String password;
	
	@NotNull
	@NotEmpty(message="firstname should be enter")
	private String firstname;
	
	@NotNull
	@NotEmpty(message="lastname should be enter")
	private String lastname;
	
	@NotNull
	@NotEmpty(message="profile should be enter")
	private String profile;
	
	@NotNull
	@NotEmpty(message="message should be enter")
	private String message;
	
	private boolean enabled;
	private boolean tokenExpired;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "user_permissions",
	 		joinColumns = @JoinColumn(name = "role_id", referencedColumnName="id"),
	 		inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName="id"))
	
	private Set<Role> roles;
	
	public User() {}
	public User(String username, String password, String firstname, String lastname, String profile, String message, String tokenExpired)
	{
		this.username=username;
		this.password=password;
		this.firstname=firstname;
		this.lastname=lastname;
		this.profile=profile;
		this.message=message;
		this.tokenExpired=false;
		
	}
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isTokenExpired() {
		return tokenExpired;
	}

	public void setTokenExpired(boolean tokenExpired) {
		this.tokenExpired = tokenExpired;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public String toString() {
		return "User [Id=" + Id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", profile=" + profile + ", message=" + message + ", enabled=" + enabled + ", tokenExpired="
				+ tokenExpired + ", roles=" + roles + "]";
	}
	
	@Transient
	public Set<Permission> getPermissions()
	{
		Set<Permission> perms= new HashSet <Permission>() ;
		for(Role role:roles)
		{
			perms.addAll(getPermissions());	//dont have to use User
		}
		return perms; 
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 	Set<GrantedAuthority> authorities =new HashSet<GrantedAuthority>();
		 	authorities.addAll(getRoles());
		 	authorities.addAll((Collection<? extends GrantedAuthority>) getPermissions());	//here assAll(getPermission);
	return authorities;
	}

	@Override
	public String getPassword() {
		return getPassword();
	}

	@Override
	public String getUsername() {
		return getUsername(); 	}

	@Override
	public boolean isAccountNonExpired() {
		return true;	}

	@Override
	public boolean isAccountNonLocked() {	
		return true;	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;	}

	@Override
	public boolean isEnabled() {
		return true;	}

}
