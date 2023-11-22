package dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Vehiculo;

public class ControladorVehiculo {

	public static boolean vehiculosToJson(Connection conn, String ruta) {
		File fichero = new File(ruta);
		String sql = """
				SELECT matricula, modelo
				FROM vehiculos
				""";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
			ResultSet rs = conn.createStatement().executeQuery(sql);
			bw.write("[");
			while (rs.next()) {
				Vehiculo v = read(rs);
				if (v.toString() != null) {
					bw.write(
					"\n\t{\n" + 
					"\t\t\"matricula\": \"" + v.getMatricula() + "\",\n" +
					"\t\t\"modelo\": " + v.getModelo() + ",\n" +
					"\t}");
				}
			}
			bw.write("]");
			bw.close();
			return true;
		
		}catch(IOException e){
			System.out.println("Error con el fichero");
		}catch (SQLException e ) {
			System.out.println("Error con la sentencia sql");
		}
		return false;
	}

	private static Vehiculo read(ResultSet rs) {
		try {
			String matricula = rs.getString("matricula");
			String modelo = rs.getString("modelo");
			return new Vehiculo(matricula, modelo);
		} catch (SQLException e) {
		}
		return null;
	}
}
