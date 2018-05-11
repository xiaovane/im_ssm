package admin.entity.api;

import java.math.BigInteger;

public class Position {

	private Integer id;
	private Integer provinceId;
	private String provinceName;
	private BigInteger cityId;
	private String cityName;
	private String countyId;
	private String countyName;
	private BigInteger townId;
	private String townName;
	private String xiaoQuId;
	private String xiaoQuName;
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
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
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
	public String getCountyId() {
		return countyId;
	}
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public BigInteger getTownId() {
		return townId;
	}
	public void setTownId(BigInteger townId) {
		this.townId = townId;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public String getXiaoQuId() {
		return xiaoQuId;
	}
	public void setXiaoQuId(String xiaoQuId) {
		this.xiaoQuId = xiaoQuId;
	}
	public String getXiaoQuName() {
		return xiaoQuName;
	}
	public void setXiaoQuName(String xiaoQuName) {
		this.xiaoQuName = xiaoQuName;
	}

}
