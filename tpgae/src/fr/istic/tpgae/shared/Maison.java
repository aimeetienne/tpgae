package fr.istic.tpgae.shared;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.datanucleus.api.jpa.annotations.Extension;

@Entity
public class Maison implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value="true") 
	private String id;
	
	private String adresse;
	private String ville;
	private String cp;
	
	@OneToMany
	private List<Personne> personnes;

	public void setPersonnes(List<Personne> personnes) {
		this.personnes=personnes;
	}
	public List<Personne> getPersonnes() {
		return personnes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}

}
