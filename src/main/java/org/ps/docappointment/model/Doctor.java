package org.ps.docappointment.model;

public class Doctor {
	private int docId;
	private String name;
	private String specialist;
	private String venue;
	private String timing;
	private int fee;
	
	public Doctor() {}
	
	public Doctor(int docId, String name, String specialist, String venue, String timing, int fee) {
		super();
		this.docId = docId;
		this.name = name;
		this.specialist = specialist;
		this.venue = venue;
		this.timing = timing;
		this.fee = fee;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialist() {
		return specialist;
	}
	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing = timing;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	@Override
	public String toString() {
		return "Doctor [docId=" + docId + ", name=" + name + ", specialist=" + specialist + ", venue=" + venue
				+ ", timing=" + timing + ", fee=" + fee + "]";
	}
	
	

}
