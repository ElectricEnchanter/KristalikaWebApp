package com.kristalika.app.models;
import jakarta.persistence.*;

@Entity
@Table(name = "Appointment")
public class Appointment {

	public Appointment() {
	}

	public Appointment(Long masterId, String date, String time) {
		this.masterId = masterId;
		this.date = date;
		this.time = time;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long masterId;
	private String date;
	private String time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}