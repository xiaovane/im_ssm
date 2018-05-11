package admin.entity.api;

import java.math.BigInteger;

public class PositionCounty {
	private  Integer id;
	private  BigInteger cityId;
	private  BigInteger countyId;
	private  String countyName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigInteger getCityId() {
		return cityId;
	}
	public void setCityId(BigInteger cityId) {
		this.cityId = cityId;
	}
	public BigInteger getCountyId() {
		return countyId;
	}
	public void setCountyId(BigInteger countyId) {
		this.countyId = countyId;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	

}
