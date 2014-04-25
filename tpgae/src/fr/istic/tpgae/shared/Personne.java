package fr.istic.tpgae.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.datanucleus.api.jpa.annotations.Extension;


@Entity
public class Personne implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nom;
	private String prenom;
	private String mail;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Maison> maisons = new ArrayList<Maison>();
	
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value="true") 
	private String id;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	@Override
	public String toString() {
		return prenom+" "+nom+" "+mail;
	}
	public List<Maison> getMaison() {
		return maisons;
	}
	public void setMaison(List<Maison> maisons) {	
		this.maisons.addAll(maisons);
	}

}
