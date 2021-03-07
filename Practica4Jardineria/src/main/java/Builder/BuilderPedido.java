package Builder;

import errores.*;
import java.util.*;

import Dao.ClienteDao;
import Dao.PedidosDao;
import Model.Cliente;
import Model.Pedido;

public class BuilderPedido {
	public static Pedido build(int codigoPedido, Calendar  fechaPedido, Calendar  fechaEsperada, int codigoCliente)
			throws Exception {

		Calendar fechaActual = new GregorianCalendar();
		Calendar fechaMinimaEsperada = new GregorianCalendar();
		fechaMinimaEsperada = fechaPedido;
		
		
		if ((fechaPedido.get(Calendar.DAY_OF_MONTH) == fechaActual.get(Calendar.DAY_OF_MONTH))
				&&  (fechaPedido.get(Calendar.MONTH) == fechaActual.get(Calendar.MONTH))
				&& (fechaPedido.get(Calendar.YEAR) == fechaPedido.get(Calendar.YEAR))) 
		{	
			
		} else throw new Exception();
		
		fechaMinimaEsperada.add(Calendar.DAY_OF_MONTH, 3);
		
		if (fechaEsperada.before(fechaMinimaEsperada)) 
		{
			throw new Exception();
		}
		
		fechaMinimaEsperada.add(Calendar.DAY_OF_MONTH, -3);
		
		ClienteDao clientDAO = new ClienteDao();
		
		boolean clientExist = false;
		
		List <Cliente> clientesSaved = clientDAO.getAll();
		
		for (Cliente clienteSaved: clientesSaved) {
			if (codigoCliente == clienteSaved.getCodigoCliente()) {
				clientExist = true;
			}
		}
		
		if (clientExist == false) {
			throw new Exception();
		}
		
		return new Pedido(codigoPedido, 
					     fechaPedido,
					     fechaEsperada, 
					     codigoCliente);
		
	}
}