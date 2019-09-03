package domain;

public class Linkman {

	public Linkman() {
		super();
	}

	public Linkman(Long lkm_id, String lkm_name, Customer customer, String lkm_gender, String lkm_phone,
			String lkm_mobile, String lkm_email, String lkm_qq, String lkm_position, String lkm_memo, Integer version) {
		super();
		this.lkm_id = lkm_id;
		this.lkm_name = lkm_name;
		this.customer = customer;
		this.lkm_gender = lkm_gender;
		this.lkm_phone = lkm_phone;
		this.lkm_mobile = lkm_mobile;
		this.lkm_email = lkm_email;
		this.lkm_qq = lkm_qq;
		this.lkm_position = lkm_position;
		this.lkm_memo = lkm_memo;
		this.version = version;
	}

	public Linkman(Long lkm_id, String lkm_name) {
		super();
		this.lkm_id = lkm_id;
		this.lkm_name = lkm_name;
	}

//	Field         Type          Null    Key     Default  Extra           
//	------------  ------------  ------  ------  -------  ----------------
//	lkm_id        bigint(32)    NO      PRI     (NULL)   auto_increment  
//	lkm_name      varchar(16)   YES             (NULL)  

//	lkm_cust_id   bigint(32)    NO      MUL     (NULL)        外键

//	lkm_gender    char(1)       YES             (NULL)                   
//	lkm_phone     varchar(16)   YES             (NULL)                   
//	lkm_mobile    varchar(16)   YES             (NULL)      

//	lkm_email     varchar(64)   YES             (NULL)                   
//	lkm_qq        varchar(16)   YES             (NULL)                   
//	lkm_position  varchar(16)   YES             (NULL)                   
//	lkm_memo      varchar(512)  YES             (NULL)                   

	private Long lkm_id;
	private String lkm_name;

	private Customer customer = new Customer();

	private String lkm_gender;
	private String lkm_phone;
	private String lkm_mobile;

	private String lkm_email;
	private String lkm_qq;
	private String lkm_position;

	private String lkm_memo;
	private Integer version;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getLkm_id() {
		return lkm_id;
	}

	public void setLkm_id(Long lkm_id) {
		this.lkm_id = lkm_id;
	}

	public String getLkm_name() {
		return lkm_name;
	}

	public void setLkm_name(String lkm_name) {
		this.lkm_name = lkm_name;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getLkm_gender() {
		return lkm_gender;
	}

	public void setLkm_gender(String lkm_gender) {
		this.lkm_gender = lkm_gender;
	}

	public String getLkm_phone() {
		return lkm_phone;
	}

	public void setLkm_phone(String lkm_phone) {
		this.lkm_phone = lkm_phone;
	}

	public String getLkm_mobile() {
		return lkm_mobile;
	}

	public void setLkm_mobile(String lkm_mobile) {
		this.lkm_mobile = lkm_mobile;
	}

	public String getLkm_email() {
		return lkm_email;
	}

	public void setLkm_email(String lkm_email) {
		this.lkm_email = lkm_email;
	}

	public String getLkm_qq() {
		return lkm_qq;
	}

	public void setLkm_qq(String lkm_qq) {
		this.lkm_qq = lkm_qq;
	}

	public String getLkm_position() {
		return lkm_position;
	}

	public void setLkm_position(String lkm_position) {
		this.lkm_position = lkm_position;
	}

	public String getLkm_memo() {
		return lkm_memo;
	}

	public void setLkm_memo(String lkm_memo) {
		this.lkm_memo = lkm_memo;
	}

	@Override
	public String toString() {
		return "Linkman [lkm_id=" + lkm_id + ", lkm_name=" + lkm_name + ", lkm_gender=" + lkm_gender + ", lkm_phone="
				+ lkm_phone + ", lkm_mobile=" + lkm_mobile + ", lkm_email=" + lkm_email + ", lkm_qq=" + lkm_qq
				+ ", lkm_position=" + lkm_position + ", lkm_memo=" + lkm_memo + ", version=" + version + "]";
	}

}
