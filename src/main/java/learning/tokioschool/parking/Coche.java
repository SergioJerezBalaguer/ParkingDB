package learning.tokioschool.parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Coche {
    private String matricula;
    private String marca;
    private String modelo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private final static float PRECIO_MINUTO = 0.15f;

    public Coche(final String matricula, final String marca, final String modelo, final LocalDateTime horaEntrada,
                 final LocalDateTime horaSalida) {
        super();
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public float cantidadAPagar() {
        long minutos = ChronoUnit.MINUTES.between(horaEntrada, horaSalida);
        return minutos * PRECIO_MINUTO;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(final String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(final String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(final String modelo) {
        this.modelo = modelo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(final LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(final LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Coche [matricula=");
        builder.append(matricula);
        builder.append(", marca=");
        builder.append(marca);
        builder.append(", modelo=");
        builder.append(modelo);
        builder.append(", horaEntrada=");
        builder.append(horaEntrada);
        builder.append(", horaSalida=");
        builder.append(horaSalida);
        builder.append("]");
        return builder.toString();
    }
}
