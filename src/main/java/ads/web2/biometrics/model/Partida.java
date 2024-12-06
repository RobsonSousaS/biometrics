package ads.web2.biometrics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String local;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public Partida(Long id, String local, LocalDateTime inicio, LocalDateTime fim) {
        this.id = id;
        this.local = local;
        this.inicio = inicio;
        this.fim = fim;
    }

    public Partida() {
    }

    public Long getId() {
        return id;
    }

    public String getLocal() {
        return local;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public long duracaoMinutos() {
        Duration duration = Duration.between(inicio, fim);
        return duration.toMinutes();
    }
}
