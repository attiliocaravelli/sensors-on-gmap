package com.demo.webapplication.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "locations")
public class Location extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = -5527680378002296042L;

	@Column(name = "latitude", nullable = false)
	@NotNull(message = "{locations.latitude.notnull}")
	private BigDecimal latitude;

	@Column(name = "longitude", nullable = false)
	@NotNull(message = "{locations.longitude.notnull}")
	private BigDecimal longitude;
	
	@Column(name = "city", unique=true, nullable = false)
	@Size(min = 2, max = 45, message = "{locations.city.size}")
	private String city;
	
	public BigDecimal getLatitude() {
		return latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public String getCity() {
		return city;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
    public int hashCode() {
        return ((city == null) ? 0 : city.hashCode());
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Location))
            return false;
        Location other = (Location) obj;
        return city.equals(other.city);
    }
 
    @Override
    public String toString() {
        return "Location [city=" + city + 
        	           ", latitude=" + latitude + 
        	           ", longitude=" + longitude +  "]";
    }
}
