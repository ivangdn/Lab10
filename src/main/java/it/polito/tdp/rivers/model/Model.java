package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;
	private List<Flow> flows;
	private Simulator sim;
	
	public Model() {
		this.dao = new RiversDAO();
		this.sim = new Simulator();
	}
	
	public List<River> getAllRivers() {
		return this.dao.getAllRivers();
	}
	
	public List<Flow> getFlows(River river) {
		if(flows==null)
			this.flows =  this.dao.getFlows(river);
		
		return flows;
	}
	
	public LocalDate getStartDate(River river) {
		return getFlows(river).get(0).getDay();
	}
	
	public LocalDate getEndDate(River river) {
		return getFlows(river).get(flows.size()-1).getDay();
	}
	
	public int getNumMisurazioni(River river) {
		return getFlows(river).size();
	}
	
	public double getFMed(River river) {
		double fMed = 0;
		for(Flow f : getFlows(river)) {
			fMed += f.getFlow();
		}
		fMed = fMed / flows.size();
		return fMed;
	}
	
	public SimulationResult simula(double k, River river) {
		this.sim.init(k, river);
		this.sim.run();
		return this.sim.getSimulationResult();
	}

}
