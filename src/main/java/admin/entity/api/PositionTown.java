package admin.entity.api;

import java.math.BigInteger;

public class PositionTown {
	private Integer id;
	private BigInteger countyId;
	private BigInteger townId;
	private String townName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigInteger getCountyId() {
		return countyId;
	}
	public void setCountyId(BigInteger countyId) {
		this.countyId = countyId;
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
	

}
