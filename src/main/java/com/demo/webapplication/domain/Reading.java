package com.demo.webapplication.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "readings")
public class Reading extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = -5527577248002296042L;
	 
	@Column(name = "timestamp", nullable = false)
	@NotNull(message = "{timestamp.notnull}")
	@Type(type="date")
	private Date timestamp;
	
	@Column(name = "temperature", nullable = false)
	@NotNull(message = "{reading.temperature.notnull}")
	private BigDecimal temperature;
	
	@Column(name = "unit", length = 1)
	@Size(min = 1, max = 1, message = "{reading.unit.size}")
	private String unit;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name ="sensor")
	@JsonBackReference
	private Sensor sensor;
	
	public Date getTimestamp() {
		return timestamp;
	}

	public BigDecimal getTemperature() {
		return temperature;
	}

	public String getUnit() {
		return unit;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	@Override
    public String toString() {
        return "Aler [Date of the event=" + timestamp + 
        	           ", temperature=" + temperature +  
        	           ", unit=" + unit + "]";
    }
}
