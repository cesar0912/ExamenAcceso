package view;

import java.sql.Connection;

import dao.BD;
import dao.ControladorVehiculo;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = BD.getConnection();
		if(ControladorVehiculo.vehiculosToJson(conn, "vehiculo.json")) {
			System.out.println("creado correctamente");
		}else {
			System.out.println("no se pudo crear");
		}
	}

}
