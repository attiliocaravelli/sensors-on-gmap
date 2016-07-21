package com.demo.webapplication.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "sensors")
public class Sensor extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = -5527689248002296042L;

	@Column(name = "name", nullable = false, length = 45)
	@Size(min = 2, max = 45, message = "{sensor.name.size}")
	private String name;
	
	@Column(name = "description", nullable = false, length = 45)
	@Size(min = 2, max = 45, message = "{description.size}")
	private String description;

	@ManyToOne
	@JsonManagedReference
	private Location location;
	
	@OneToMany(mappedBy="sensor")
	@JsonManagedReference
	private List<Reading> readings;
	 
	@OneToMany(mappedBy="sensor")
	@JsonManagedReference
	private List<Alert> alerts;
	 
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Override
    public int hashCode() {
        return ((name == null) ? 0 : name.hashCode());
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Sensor))
            return false;
        Sensor other = (Sensor) obj;
        return name.equals(other.name);
    }
 
    @Override
    public String toString() {
        return "Sensor [name=" + name + ", description=" + description + "]";
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Reading> getReadings() {
		return readings;
	}

	public List<Alert> getAlerts() {
		return alerts;
	}

	public void setReadings(List<Reading> readings) {
		this.readings = readings;
	}

	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}
}
