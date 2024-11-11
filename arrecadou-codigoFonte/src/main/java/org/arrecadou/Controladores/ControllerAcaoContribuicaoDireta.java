package org.arrecadou.Controladores;

import org.arrecadou.Model.AcaoContribuicaoDireta;
import org.arrecadou.Model.Coordenador;
import org.arrecadou.Services.ServiceAcaoContribuicaoDireta;
import org.arrecadou.Services.ServiceCoordenador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


@Controller
public class ControllerAcaoContribuicaoDireta {

    private final ServiceAcaoContribuicaoDireta serviceAcao;
    private final ServiceCoordenador serviceCoordenador;

    @Autowired
    public ControllerAcaoContribuicaoDireta(ServiceAcaoContribuicaoDireta serviceAcao, ServiceCoordenador serviceCoordenador) {
        this.serviceAcao = serviceAcao;
        this.serviceCoordenador = serviceCoordenador;
    }


    public void cadastrarAcaoContribuicaoDireta(List<Coordenador> coordenadores, LocalDateTime dataFim, LocalDateTime dataInicio, String objetivoAcao, String descricao, String nome){
        this.serviceAcao.cadastrarAcaoContribuicaoDireta(coordenadores, dataFim, dataInicio, objetivoAcao, descricao, nome);
    }

    public List<AcaoContribuicaoDireta> listarTodasAcoesContribuicaoDireta() {
        return this.serviceAcao.listarTodasAcoesContribuicaoDireta();
    }

    public void addDoacao(String telefoneDoador, String nomeDoador, boolean isAnonimo , double valor, AcaoContribuicaoDireta acao) {
        this.serviceAcao.addDoacao(telefoneDoador, nomeDoador, isAnonimo, valor, acao);
    }
}
