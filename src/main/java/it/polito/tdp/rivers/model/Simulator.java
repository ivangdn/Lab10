package it.polito.tdp.rivers.model;

import java.util.PriorityQueue;

public class Simulator {
	
	//coda degli eventi
	private PriorityQueue<Flow> queue;
	
	//parametri di simulazione
	private double Q; //capienza tot (m^3)
	private double fOutMin;
	private double fOut;
	private double probabilita = 0.05; //prob di avere flusso richiesto in uscita più elevato
	
	//output della simulazione
	private SimulationResult result;
	
	//stato del mondo simulato
	private double C; //quantità acqua presente nel bacino
	
	private double secToDay(double n) {
		return n*24*60*60;
	}
	
	public void init(double k, River river) {
		Model m = new Model();
		this.queue = new PriorityQueue<Flow>();
		this.result = new SimulationResult();
		this.Q = k * 30 * secToDay(m.getFMed(river));
		this.C = Q / 2;
		this.fOutMin = 0.8 * secToDay(m.getFMed(river));
		this.queue.addAll(m.getFlows(river));
	}
	
	public void run() {
		while(!queue.isEmpty()) {
			Flow f = queue.poll();
			processaEvento(f);
		}
	}

	private void processaEvento(Flow f) {
		//probabilità di avere flusso richiesto in uscita 10x
		if(Math.random() < this.probabilita) {
			this.fOut = 10 * this.fOutMin;
		} else {
			this.fOut = this.fOutMin;
		}
		
		if(secToDay(f.getFlow()) > fOut) {
			 this.C += (secToDay(f.getFlow()) - fOut);
		}
		
//		this.C += secToDay(f.getFlow());
		
		if(this.C > this.Q) {
			//tracimazione
			this.fOut += (C - Q);
		}
		
		if(this.C < this.fOut) {
			//non si può garantire l'erogazione minima
			this.result.incrementaGiorni();
			C = 0;
		} else {
			//esce la quantità giornaliera
			C -= fOut;
		}
		
		this.result.calcolaCMed(C);
		
	}
	
	public SimulationResult getSimulationResult() {
		return this.result;
	}
	
}
