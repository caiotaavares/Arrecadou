package org.arrecadou.Controladores;

import org.arrecadou.Model.*;
import org.arrecadou.Services.ServiceAcaoProducao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ControllerAcaoProducao {

    private final ServiceAcaoProducao serviceAcaoProducao;

    @Autowired
    public ControllerAcaoProducao(ServiceAcaoProducao serviceAcaoProducao) {
        this.serviceAcaoProducao = serviceAcaoProducao;
    }

    public ItemEsperado cadastrarItemEsperado(String nome, int quantidadeEmKg, double valorKg){
        return this.serviceAcaoProducao.cadastrarItemEsperado(nome, quantidadeEmKg, valorKg);
    }

    public void cadastrarAcaoProducao(
            List<Coordenador> coordenadores,
            LocalDateTime dataFim,
            LocalDateTime dataInicio,
            String objetivo,
            String descricao,
            String nome,
            List<Colaborador> colaboradores,
            List<ItemEsperado> itensEsperados
    ){
        this.serviceAcaoProducao.cadastrarAcaoProducao(
                coordenadores, dataFim, dataInicio, objetivo, descricao, nome, colaboradores, itensEsperados
        );
    }

    public List<AcaoProducao> listarTodasAcaesProducao(){
        return this.serviceAcaoProducao.listarTodasAcoesProducao();
    }

    public Colaborador cadastrarColaborador(String nomeColaborador, String email, String telefone) {
        return this.serviceAcaoProducao.cadastrarColaborador(nomeColaborador, email, telefone);
    }
}
