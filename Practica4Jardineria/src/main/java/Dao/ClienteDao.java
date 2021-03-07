package Dao;

import java.util.*;
import Model.*;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements DAO<Cliente> {
	Datos db = new Datos();

	public String insert(Object obj) {
		Cliente client = (Cliente) obj;
		Connection connection;
		PreparedStatement pst;
		String sql = "INSERT INTO cliente VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String answer = "";

		try {
			Class.forName(db.getDriver());
			connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());

			pst = connection.prepareStatement(sql);

			pst.setInt(1, client.getCodigoCliente());
			pst.setString(2, client.getNombreCliente());
			pst.setString(3, client.getNombreContacto());
			pst.setString(4, client.getApellidoContacto());
			pst.setString(5, client.getTelefono());
			pst.setString(6, client.getFax());
			pst.setString(7, client.getLineaDireccion1());
			pst.setString(8, client.getLineaDireccion2());
			pst.setString(9, client.getCiudad());
			pst.setString(10, client.getRegion());
			pst.setString(11, client.getPais());
			pst.setString(12, client.getCodigoPostal());
			pst.setInt(13, client.getCodigoEmpleadoRepVentas());
			pst.setInt(14, client.getLimiteCredito());

			int fields = pst.executeUpdate();
			answer = "Se insertaron " + fields + " filas nuevas.";
			connection.close();

		} catch (ClassNotFoundException | SQLException e) {
		}

		return answer;

	}

	@Override
	public String delete(Object obj) {
		Cliente client = (Cliente) obj;
		Connection connection;
		PreparedStatement pst;
		String sql = "DELETE FROM cliente WHERE codigoCliente=?";
		String answer = "";

		try {
			Class.forName(db.getDriver());
			connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());

			pst = connection.prepareStatement(sql);


			pst.setInt(1, client.getCodigoCliente());

			int fields = pst.executeUpdate();

			answer = "Se eliminaron " + fields + " elementos.";

			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
		}

		return answer;

	}

	@Override
	public String update(Object obj) {
		Cliente client = (Cliente) obj;
		Connection connection;
		PreparedStatement pst;
		String sql = "UPDATE cliente SET nombreCliente=?, nombreContacto=?,  "
				+ "apellidoContacto=?, telefono=?, fax=?, lineaDireccion1=?, lineaDireccion2=?,"
				+ "ciudad=?, region=?, pais=?, codigoPostal=?, codigoEmpleadoRepVentas=?, "
				+ "limiteCredito=? WHERE codigoCliente=?";
		String answer = "";

		try {
			Class.forName(db.getDriver());
			connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());

			pst = connection.prepareStatement(sql);

			pst.setString(1, client.getNombreCliente());
			pst.setString(2, client.getNombreContacto());
			pst.setString(3, client.getApellidoContacto());
			pst.setString(4, client.getTelefono());
			pst.setString(5, client.getFax());
			pst.setString(6, client.getLineaDireccion1());
			pst.setString(7, client.getLineaDireccion2());
			pst.setString(8, client.getCiudad());
			pst.setString(9, client.getRegion());
			pst.setString(10, client.getPais());
			pst.setString(11, client.getCodigoPostal());
			pst.setInt(12, client.getCodigoEmpleadoRepVentas());
			pst.setInt(13, client.getLimiteCredito());
			pst.setInt(14, client.getCodigoCliente());

			int fields = pst.executeUpdate();

			answer = "Se modificaron " + fields + " elementos.";

			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
		}

		return answer;
	}

	@Override
	public List<Cliente> read() {
		List<Cliente> data = new ArrayList<>();
		Connection connection;
		PreparedStatement pst;
		ResultSet resultSet;

		String sql = "SELECT * FROM client";

		try {
			Class.forName(db.getDriver());
			connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());

			pst = connection.prepareStatement(sql);
			resultSet = pst.executeQuery();

			while (resultSet.next()) {
				data.add(new Cliente(resultSet.getInt("codigoCliente"), resultSet.getString("nombreCliente"),
						resultSet.getString("nombreContacto"), resultSet.getString("apellidoContacto"),
						resultSet.getString("telefono"), resultSet.getString("fax"),
						resultSet.getString("lineaDireccion1"), resultSet.getString("lineaDireccion2"),
						resultSet.getString("ciudad"), resultSet.getString("region"), resultSet.getString("pais"),
						resultSet.getString("codigoPostal"), resultSet.getInt("codigoEmpleadoRepVentas"),
						resultSet.getInt("limiteCredito"), resultSet.getString("DNI"), resultSet.getString("mail"),
						resultSet.getString("password")));
			}

			connection.close();

		} catch (ClassNotFoundException | SQLException e) {
		}

		return data;

	}

	@Override
	public List<Cliente> filter(String field, String searchCriteria) {
		List<Cliente> data = new ArrayList<>();
		Connection connection;
		PreparedStatement pst;
		ResultSet resultSet;

		String sql = "SELECT * FROM cliente WHERE field LIKE '%searchCriteria%'";

		try {
			Class.forName(db.getDriver());
			connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());

			pst = connection.prepareStatement(sql);
			resultSet = pst.executeQuery();

			while (resultSet.next()) {
				data.add(new Cliente(resultSet.getInt("codigoCliente"), resultSet.getString("nombreCliente"),
						resultSet.getString("nombreContacto"), resultSet.getString("apellidoContacto"),
						resultSet.getString("telefono"), resultSet.getString("fax"),
						resultSet.getString("lineaDireccion1"), resultSet.getString("lineaDireccion2"),
						resultSet.getString("ciudad"), resultSet.getString("region"), resultSet.getString("pais"),
						resultSet.getString("codigoPostal"), resultSet.getInt("codigoEmpleadoRepVentas"),
						resultSet.getInt("limiteCredito"), resultSet.getString("DNI"), resultSet.getString("mail"),
						resultSet.getString("password")));
			}

			connection.close();

		} catch (ClassNotFoundException | SQLException e) {
		}

		return data;

	}

	public Cliente getClient(int codigo) {
		List<Cliente> data = new ArrayList<>();
		for (Cliente client : data) {
			if (client.getCodigoCliente() == codigo) {
				return client;
			}
		}
		return null;
	}

	public List<Cliente> getAll() {
		List<Cliente> data = new ArrayList<>();
		return data;
	}

	@Override
	public Cliente get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Cliente t) {
		// TODO Auto-generated method stub
		
	}


}