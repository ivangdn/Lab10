package it.polito.tdp.rivers.db;

public class TestRiversDAO {

	public static void main(String[] args) {
		RiversDAO dao = new RiversDAO();
		System.out.println(dao.getAllRivers());
		
		System.out.println(dao.getFlows(dao.getAllRivers().get(1)).get(0));
		System.out.println(dao.getFlows(dao.getAllRivers().get(1)).size());
	}

}
