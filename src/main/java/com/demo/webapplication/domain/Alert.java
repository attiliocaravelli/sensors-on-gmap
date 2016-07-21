package com.demo.webapplication.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "alerts")
public class Alert extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = -5527566248002296042L;

	@Column(name = "timestamp", nullable = false)
	@NotNull(message = "{timestamp.notnull}")
	@Type(type="date")
	private Date timestamp;
	
	@Column(name = "description", length = 45)
	@Size(min = 2, max = 45, message = "{description.size}")
	private String description;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name ="sensor")
	@JsonBackReference
	private Sensor sensor;
	
	public Date getTimestamp() {
		return timestamp;
	}

	public String getDescription() {
		return description;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
 
    @Override
    public String toString() {
        return "Alert [Date of the event=" + timestamp + 
        	           ", description=" + description +  "]";
    }
}
