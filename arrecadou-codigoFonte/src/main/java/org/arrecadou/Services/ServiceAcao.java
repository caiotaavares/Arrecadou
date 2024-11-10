package org.arrecadou.Services;

import org.arrecadou.Model.Coordenador;
import org.arrecadou.Repositories.AcaoContribuicaoDiretaRepository;
import org.arrecadou.Repositories.CoordenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAcao {

    private final AcaoContribuicaoDiretaRepository acaoContribuicaoDiretaRepository;
    private final CoordenadorRepository coordenadorRepository;

    @Autowired
    public ServiceAcao(AcaoContribuicaoDiretaRepository acaoContribuicaoDiretaRepository, CoordenadorRepository coordenadorRepository) {
        this.acaoContribuicaoDiretaRepository = acaoContribuicaoDiretaRepository;
        this.coordenadorRepository = coordenadorRepository;
    }

    public void cadastrarCoordenador(String nome, String cpf, String telefone) {
        this.coordenadorRepository.save(new Coordenador(nome, cpf, telefone));
    }
}
