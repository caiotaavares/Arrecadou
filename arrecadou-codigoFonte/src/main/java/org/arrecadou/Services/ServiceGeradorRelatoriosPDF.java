package org.arrecadou.Services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.arrecadou.Model.*;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class ServiceGeradorRelatoriosPDF {

    public void gerarRelatorioPDF(Acao acao, String caminhoRelatorio) {
        caminhoRelatorio = caminhoRelatorio.concat("\\" + acao.getNome()+".pdf");
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                gerarDadosGerais(contentStream, acao);
                if (acao instanceof AcaoContribuicaoDireta) {
                    gerarRelatorioPDFacaoContribDireta(contentStream, (AcaoContribuicaoDireta) acao);
                } else{
                    gerarRelatorioPDFacaoProducao(contentStream, (AcaoProducao) acao);
                }
            }
            document.save(caminhoRelatorio);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar o relatório PDF: " + e.getMessage(), e);
        }
    }

    private void gerarDadosGerais(PDPageContentStream contentStream, Acao acao) throws Exception {
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(50, 750);
        contentStream.showText("Relatório da Ação");
        contentStream.newLineAtOffset(0, -30);
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.showText("Nome: " + acao.getNome());
        contentStream.newLine();
        contentStream.showText("Descrição: " + acao.getDescricao());
        contentStream.newLine();
        contentStream.showText("Data de Início: " + acao.getDataInicio());
        contentStream.newLine();
        contentStream.showText("Data de Fim: " + acao.getDataFim());
        contentStream.newLine();
        contentStream.showText("Objetivo: " + acao.getObjetivoAcao());
        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("Coordenadores:");

        for (Coordenador c : acao.getCoordenadores()) {
            contentStream.newLine();
            contentStream.showText("Nome: " + c.getNome()+ " - " + "Telefone: " + c.getTelefone());
        }
    }

    private void gerarRelatorioPDFacaoContribDireta(PDPageContentStream contentStream, AcaoContribuicaoDireta acao) throws IOException {
        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("Valor monetário arrecadado: R$ " + acao.calcularValorTotalArrecadado());
        contentStream.newLineAtOffset(0, -30);
        adicionarDoadores(contentStream, acao);
        contentStream.endText();
    }

    private void gerarRelatorioPDFacaoProducao(PDPageContentStream contentStream, AcaoProducao acao) throws IOException {
        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("Colaboradores: ");

        for (Colaborador colaborador : acao.getColaboradores()) {
            contentStream.showText("Nome: " + colaborador.getNome() + " - " + "Telefone: " + colaborador.getTelefone());
            contentStream.newLine();
        }

        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("Valor gasto pra comprar o que faltou dos itens: " + acao.calculaValorAserGastoComRestanteItensFaltantes());
        contentStream.newLineAtOffset(0, -30);
        adicionarDoadores(contentStream, acao);
        contentStream.endText();
    }

    private void adicionarDoadores(PDPageContentStream contentStream, Acao acao) throws IOException {
        contentStream.showText("Doações: ");
        contentStream.newLine();
        List<? extends Doacao> doacoes = acao instanceof AcaoContribuicaoDireta
                ? ((AcaoContribuicaoDireta) acao).getDoacoesDinheiro() : ((AcaoProducao) acao).getDoacoes();

        for (Doacao doacao : doacoes) {
           if (doacao.isAnonimo()) {
               contentStream.showText("Doador Anonimo doou ");
           }else{
               contentStream.showText(doacao.getNomeDoador() + " doou ");
           }

           if(doacao instanceof DoacaoDinheiro) contentStream.showText("R$ " + ((DoacaoDinheiro) doacao).getValor());
           else{
               contentStream.showText(((DoacaoItem) doacao).getQuantidadeEmKg() + " KG de " + ((DoacaoItem) doacao).getNome());
           }
           contentStream.newLine();
        }
    }
}