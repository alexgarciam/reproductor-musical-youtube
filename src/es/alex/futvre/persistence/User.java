package es.alex.futvre.persistence;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
/*
@Entity
@Table(name = "USERS", uniqueConstraints = @UniqueConstraint(columnNames = { "USERNAME" }))
*/
public class User {

	@Id
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ENABLED")
	private boolean enabled;

	@OneToMany
	@JoinColumn(name = "username")
	private Collection<Authority> authorities;

	@OneToMany
	@JoinColumn(name = "username")
	private Collection<Favorito> favoritos;

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Lista> listas;

	public Collection<Lista> getListas() {
		return listas;
	}

	public void setListas(Collection<Lista> listas) {
		this.listas = listas;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	public Collection<Favorito> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(Collection<Favorito> favoritos) {
		this.favoritos = favoritos;
	}
	
}