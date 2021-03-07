package Builder;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import errores.*;
import Dao.ClienteDao;
import Model.*;
import Model.Cliente.DocumentType;

public class BuilderCliente {

	public static Cliente buildClient(int codigoCliente, String nombreCliente, String apellidoContacto, String telefono,
			DocumentType documentType, String DNI, String email, String password) throws Exception {

		ClienteDao clienteDao = new ClienteDao();
		List<Cliente> clienteList = clienteDao.getAll();

		for (Cliente allClient : clienteList) {
			if (codigoCliente == allClient.getCodigoCliente()) {
				throw new MismoCodigo();
			} else if (allClient.getApellidoContacto().equalsIgnoreCase(apellidoContacto)
					&& allClient.getNombreCliente().equalsIgnoreCase(nombreCliente)
					|| allClient.getTelefono().equals(telefono)) {
				throw new Exception();
			}
		}

		if (DNI.length() != 9) {
			throw new DNImayor();
		}

		if (documentType == DocumentType.DNI) {
			char letra = DNI.toUpperCase().charAt(8);
			if (!(letra > 64 && letra < 91)) {
				throw new UltimaLetra();
			} else {
				for (int x = 0; x < 8; x++) {
					letra = DNI.charAt(x);
					if (!(letra > 47 && letra < 58)) {
						throw new OchoNumerosUnaLetra();
					}
				}
			}

		} else if (documentType == DocumentType.NIE) {
			char letraLast = DNI.toUpperCase().charAt(8);
			char letraFirst = DNI.toUpperCase().charAt(0);
			if ((!(letraLast > 64 && letraLast < 91)) || (!(letraFirst > 64 && letraFirst < 91))) {
				throw new NIEPrimeraLetra();
			} else {
				for (int y = 1; y < 8; y++) {
					letraFirst = DNI.charAt(y);
					if (!(letraFirst > 47 && letraFirst < 58)) {
						throw new NIEUltimaLetra();
					}
				}
			}

		}

		Pattern patternEmail = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matherEmail = patternEmail.matcher(email);

		if (matherEmail.find() == false) {
			throw new EmailNoExiste();
		}

		return new Cliente(codigoCliente, nombreCliente, apellidoContacto, telefono, documentType, DNI, email,
				password);

	}

}