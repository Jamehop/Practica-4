package Dao;

import java.util.*;
import java.sql.*;
import Model.*;

public class PedidosDao implements DAO<Pedido> {
	Datos db = new Datos();

	@Override
	public String insert(Object obj) {
		Pedido pedido = (Pedido) obj;
		Connection connection;
		PreparedStatement pst;
		String sql = "INSERT INTO pedido VALUES(?,?,?,?,?,?,?)";
		String answer = "";

		try {
			Class.forName(db.getDriver());
			connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());
			pst = connection.prepareStatement(sql);

			pst.setInt(1, pedido.getCodigoPedido());
			/*pst.setDate(2, pedido.getFechaPedido());
			pst.setDate(3, (Calendar) pedido.getFechaEsperada());
			pst.setDate(4, (Calendar) pedido.getFechaEntrega());*/
			pst.setString(5, pedido.getEstado());
			pst.setString(6, pedido.getComentarios());
			pst.setInt(7, pedido.getCodigoCliente());

			int fields = pst.executeUpdate(); 
			answer = "Se insertaron " + fields + " filas nuevas.";
			connection.close(); 
		} catch (ClassNotFoundException | SQLException e) {
		}

		return answer;

	}

	@Override
	public String delete(Object obj) {
		Pedido pedido = (Pedido) obj;
		Connection connection;
		PreparedStatement pst;
		String sql = "DELETE FROM pedido WHERE codigoPedido=?";
		String answer = "";

		try {
			Class.forName(db.getDriver());
			connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());

			pst = connection.prepareStatement(sql);

			pst.setInt(1, pedido.getCodigoPedido());

			int fields = pst.executeUpdate();
			answer = "Se eliminaron " + fields + " elementos.";
			connection.close(); 

		} catch (ClassNotFoundException | SQLException e) {
		}
		return answer;

	}

	@Override
	public String update(Object obj) {
		Pedido pedido = (Pedido) obj;
		Connection connection;
		PreparedStatement pst;
		String sql = "UPDATE pedido SET fechaPedido=?, fechaEsperada=?,"
				+ " fechaEntrega=?, estado=?, comentarios=?, codigoCliente=? WHERE codigoPedido=?";
		String answer = "";

		try {
			Class.forName(db.getDriver());
			connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());
			pst = connection.prepareStatement(sql);

			/*pst.setDate(1, (Date) pedido.getFechaPedido());
			pst.setDate(2, (Date) pedido.getFechaEsperada());
			pst.setDate(3, (Date) pedido.getFechaEntrega());*/
			pst.setString(4, pedido.getEstado());
			pst.setString(5, pedido.getComentarios());
			pst.setInt(6, pedido.getCodigoCliente());
			pst.setInt(7, pedido.getCodigoPedido());

			int fields = pst.executeUpdate(); 

			answer = "Se modificaron " + fields + " elementos.";

			connection.close(); 
		} catch (ClassNotFoundException | SQLException e) {
		}
		return answer;
	}

	@Override
	public List<Pedido> read() {
		List<Pedido> data = new ArrayList<>();
		Connection connection;
		PreparedStatement pst;
		ResultSet resultSet;

		String sql = "SELECT * FROM pedido";

		try {
			Class.forName(db.getDriver());
			connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());

			pst = connection.prepareStatement(sql);
			resultSet = pst.executeQuery();


			/*while (resultSet.next()) {
				data.add(new pedido(resultSet.getInt("codigoPedido"), resultSet.getDate("fechaPedido"),
						resultSet.getDate("fechaEsperada"), resultSet.getDate("fechaEntrega"),
						resultSet.getString("estado"), resultSet.getString("comentarios"),
						resultSet.getInt("codigoCliente")));
			}*/

			connection.close();

		} catch (ClassNotFoundException | SQLException e) {
		}

		return data;

	}

	@Override
	public List<Pedido> filter(String field, String searchCriteria) {
		List<Pedido> data = new ArrayList<>();
		Connection connection;
		PreparedStatement pst;
		ResultSet resultSet;

		String sql = "SELECT * FROM pedido WHERE field LIKE '%searchCriteria%'";

		try {
			Class.forName(db.getDriver());
			connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());

			pst = connection.prepareStatement(sql);
			resultSet = pst.executeQuery();

			/*while (resultSet.next()) {
				data.add(new pedido(resultSet.getInt("codigoPedido"), resultSet.getDate("fechaPedido"),
						resultSet.getDate("fechaEsperada"), resultSet.getDate("fechaEntrega"),
						resultSet.getString("estado"), resultSet.getString("comentarios"),
						resultSet.getInt("codigoCliente")));
			}*/

			connection.close();

		} catch (ClassNotFoundException | SQLException e) {
		}

		return data;
	}

	public Pedido getPedido(int codigoPedido) {
		List<Pedido> data = new ArrayList<>();
		for (Pedido pedido : data) {
			if (pedido.getCodigoPedido() == codigoPedido) {
				return pedido;
			}
		}

		return null;
	}

	@Override
	public Pedido get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Pedido t) {
		// TODO Auto-generated method stub
		
	}

}