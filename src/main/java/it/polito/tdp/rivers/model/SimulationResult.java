package it.polito.tdp.rivers.model;

public class SimulationResult {
	
	private int nGiorni; //num giorni in cui non si è potuta garantire l'erogazione min
	private double cMed; //occupazione media del bacino nel corso della simulazione
	private int count;
	
	public SimulationResult() {
		this.nGiorni = 0;
		this.cMed = 0;
		this.count = 0;
	}

	public int getnGiorni() {
		return nGiorni;
	}

	public void incrementaGiorni() {
		this.nGiorni++;
	}

	public double getcMed() {
		return cMed;
	}
	
	public void calcolaCMed(double n) {
		this.count++;
		this.cMed += n;
		this.cMed = cMed / count;
	}

}
