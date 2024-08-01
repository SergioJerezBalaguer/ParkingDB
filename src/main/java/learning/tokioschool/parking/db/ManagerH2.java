package learning.tokioschool.parking.db;

import learning.tokioschool.parking.Coche;
import learning.tokioschool.parking.DatabaseSource;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ManagerH2 extends ManagerDbAbstract {
    private DataSource dataSource;
    public ManagerH2() {
        this.dataSource = DatabaseSource.getDataSource();
    }

    public Connection iniConexion() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public int update(String matricula, LocalDateTime horaSalida) {
        try (Connection connection = iniConexion();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setTimestamp(1, Timestamp.valueOf(horaSalida));
            statement.setString(2, matricula);
            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int insert(String matricula, Coche coche) {
        try (Connection connection = iniConexion();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, matricula);
            statement.setString(2, coche.getMarca());
            statement.setString(3, coche.getModelo());
            statement.setTimestamp(4, Timestamp.valueOf(coche.getHoraEntrada()));
            statement.setTimestamp(5, coche.getHoraSalida() != null ? Timestamp.valueOf(coche.getHoraSalida()) : null);
            int filas = statement.executeUpdate();

            if (filas > 0) {
                System.out.println("Coche registrado correctamente.");
            }
            return filas;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Coche search(String matricula) {
        try (Connection connection = iniConexion();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_MATRICULA)) {

            statement.setString(1, matricula);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String marca = resultSet.getString("Marca");
                    String modelo = resultSet.getString("Modelo");
                    LocalDateTime horaEntrada = resultSet.getTimestamp("HoraEntrada").toLocalDateTime();
                    Timestamp tsHoraSalida = resultSet.getTimestamp("HoraSalida");
                    LocalDateTime horaSalida = tsHoraSalida != null ? tsHoraSalida.toLocalDateTime() : null;
                    return new Coche(matricula, marca, modelo, horaEntrada, horaSalida);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Coche> searchAll() {
        Map<String, Coche> coches = new HashMap<>();
        try (Connection connection = iniConexion();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {

            while (resultSet.next()) {
                String matricula = resultSet.getString("Matricula");
                String marca = resultSet.getString("Marca");
                String modelo = resultSet.getString("Modelo");
                LocalDateTime horaEntrada = resultSet.getTimestamp("HoraEntrada").toLocalDateTime();
                Timestamp tsHoraSalida = resultSet.getTimestamp("HoraSalida");
                LocalDateTime horaSalida = tsHoraSalida != null ? tsHoraSalida.toLocalDateTime() : null;
                coches.put(matricula, new Coche(matricula, marca, modelo, horaEntrada, horaSalida));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coches;
    }

    @Override
    public Map<String, Coche> searchAllFilterHoraSalida() {
        Map<String, Coche> coches = new HashMap<>();
        try (Connection connection = iniConexion();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_WITHOUT_HORA_SALIDA)) {


            while (resultSet.next()) {
                String matricula = resultSet.getString("Matricula");
                String marca = resultSet.getString("Marca");
                String modelo = resultSet.getString("Modelo");
                LocalDateTime horaEntrada = resultSet.getTimestamp("HoraEntrada").toLocalDateTime();
                Timestamp tsHoraSalida = resultSet.getTimestamp("HoraSalida");
                LocalDateTime horaSalida = tsHoraSalida != null ? tsHoraSalida.toLocalDateTime() : null;
                coches.put(matricula, new Coche(matricula, marca, modelo, horaEntrada, horaSalida));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coches;
    }
}
