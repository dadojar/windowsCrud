package models;

public class Supplier {



	private Integer id;
	
	private String name;
	
	private String address;
	
	private Integer telephone;

	public Supplier(Integer id, String name, String address, Integer telephone) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
	}


	
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
