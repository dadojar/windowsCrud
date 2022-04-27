package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="supplier")
public class Supplier {


	@Id
	@Column(name = "id", unique = true)
	private Integer id;
	
	@Column(name = "name", unique = true)
	private String name;
	
	@Column(name = "address", unique = true)
	private String address;
	
	@Column(name = "telephone", unique = true)
	private Integer telephone;

	public Supplier(Integer id, String name, String address, Integer telephone) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
	}
	
	public Supplier() {}


	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getTelephone() {
		return telephone;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

}
