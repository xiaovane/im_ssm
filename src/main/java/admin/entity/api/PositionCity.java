package admin.entity.api;

import java.math.BigInteger;

public class PositionCity {

	private Integer id;
	private Integer provinceId;
	private  BigInteger cityId;
	private String cityName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public BigInteger getCityId() {
		return cityId;
	}
	public void setCityId(BigInteger cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
