package learning.tokioschool.parking;

import learning.tokioschool.parking.db.ManagerH2;

import java.time.LocalDateTime;
import java.util.Map;

public class Parking {

	private final ManagerH2 managerDb;

	public Parking() {
		managerDb = new ManagerH2();
		try {
			managerDb.crearTabla();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean existeCoche(final String matricula) {
		return managerDb.search(matricula) != null;
	}

	public Coche getCoche(final String matricula) {
		return managerDb.search(matricula);
	}

	public void putCoche(final String matricula, final Coche coche) {
		managerDb.insert(matricula, coche);
		System.out.println("Coche insertado en la base de datos.");
	}

	public void registrarSalida(final String matricula) {
		Coche coche = managerDb.search(matricula);
		if (coche != null) {
			coche.setHoraSalida(LocalDateTime.now());
			managerDb.update(matricula, coche.getHoraSalida());
		}
	}

	public void imprimirCochesSistema() {
		try {
			Map<String, Coche> coches = managerDb.searchAll();
			coches.forEach((k, v) -> {
				System.out.println("Matricula: " + k + " Datos del " + v);
			});
		} catch (Exception ex) {
			System.out.println("Error al imprimir coches en el sistema");
		}
	}

	public void imprimirCochesParking() {
		try {
			Map<String, Coche> coches = managerDb.searchAllFilterHoraSalida();
			coches.forEach((k, v) -> {
				System.out.println("Matricula: " + k + " Datos del " + v);
			});
		} catch (Exception ex) {
			System.out.println("Error al imprimir coches en el parking");
		}
	}

	public void cantidadAPagar(final String matricula) {
		if (matricula != null) {
			Coche coche = managerDb.search(matricula);
			if (coche != null && coche.getHoraSalida() != null) {
				System.out.println("Cantidad a pagar " + coche.cantidadAPagar());
			} else {
				System.out.println("El coche no tiene registrada una hora de salida.");
			}
		}
	}
}
