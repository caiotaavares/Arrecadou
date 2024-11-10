package org.arrecadou.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class AcaoProducao extends Acao{

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Doacao> doacoes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Colaborador> colaboradores;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemEsperado> itensEsperados;


    public AcaoProducao(Entidade entidade, List<Coordenador> coordenadores, LocalDateTime dataFim, LocalDateTime dataInicio, String objetivoAcao, String descricao, String nome, List<Colaborador> colaboradores, List<ItemEsperado> itensEsperados) {
        super(entidade, coordenadores, dataFim, dataInicio, objetivoAcao, descricao, nome);
        this.colaboradores = colaboradores;
        this.itensEsperados = itensEsperados;
        this.doacoes = new ArrayList<>();


    }

    public AcaoProducao() {

    }

    public List<Doacao> getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(List<Doacao> doacoes) {
        this.doacoes = doacoes;
    }

    @Override
    public String toString() {
        return "AcaoProducao{" +
                "doacoes=" + doacoes +
                ", colaboradores=" + colaboradores +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AcaoProducao that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getDoacoes(), that.getDoacoes()) && Objects.equals(getColaboradores(), that.getColaboradores());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDoacoes(), getColaboradores());
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public List<ItemEsperado> getItensEsperados() {
        return itensEsperados;
    }

    public void setItensEsperados(List<ItemEsperado> itensEsperados) {
        this.itensEsperados = itensEsperados;
    }
}
