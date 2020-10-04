package com.sapient.customer.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sapient.customer.beans.Customer;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String id;

	private String username;
	
	private Customer customer;

	private String email;

	private boolean status;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String id, String username, String email, String password, boolean status,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.status = status;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(Customer customer) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
		return new UserDetailsImpl(customer.getId(), customer.getEmail(), // assigning user name to email
				customer.getEmail(), customer.getPassword(), customer.getStatus(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}