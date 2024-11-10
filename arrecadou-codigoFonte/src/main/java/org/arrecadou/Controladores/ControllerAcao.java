package org.arrecadou.Controladores;

import org.arrecadou.Services.ServiceAcao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ControllerAcao {

    private final ServiceAcao serviceAcao;

    @Autowired
    public ControllerAcao(ServiceAcao serviceAcao) {
        this.serviceAcao = serviceAcao;
    }

    public void cadastrarCoordenador(String nome, String cpf, String telefone) {
        this.serviceAcao.cadastrarCoordenador(nome, cpf, telefone);
    }

}
