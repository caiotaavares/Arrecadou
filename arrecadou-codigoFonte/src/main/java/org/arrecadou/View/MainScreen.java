package org.arrecadou.View;

import org.arrecadou.Config.AppConfig;
import org.arrecadou.Config.JpaConfig;
import org.arrecadou.Controladores.ControllerAcaoContribuicaoDireta;
import org.arrecadou.Controladores.ControllerAcaoProducao;
import org.arrecadou.Controladores.ControllerCoordenador;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

@SuppressWarnings("ExtractMethodRecommender")
public class MainScreen extends JFrame {
    private AnnotationConfigApplicationContext context;
    private ControllerAcaoContribuicaoDireta controllerAcaoContribuicaoDireta;
    private ControllerCoordenador controllerCoordenador;
    private ControllerAcaoProducao controllerAcaoProducao;

    public MainScreen() {
        initializeContext();
        initializeUI();
        setupMenu();
    }

    private void initializeContext() {
        context = new AnnotationConfigApplicationContext(AppConfig.class, JpaConfig.class);
        this.controllerAcaoContribuicaoDireta = context.getBean(ControllerAcaoContribuicaoDireta.class);
        this.controllerCoordenador = context.getBean(ControllerCoordenador.class);
        this.controllerAcaoProducao = context.getBean(ControllerAcaoProducao.class);
    }

    private void initializeUI() {
        setTitle("Arrecadou");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Carregar a imagem de fundo
        ImageIcon originalImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/img.png")));
        Image backgroundImage = originalImage.getImage();

        // Configurar o painel com Graphics2D para melhorar a qualidade
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        setContentPane(panel);
        setSize(originalImage.getIconWidth(), originalImage.getIconHeight());
        setLocationRelativeTo(null);

        // Fechar o contexto Spring ao encerrar a janela
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                closeContext();
            }
        });
    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();

        // Menu Coordenador
        JMenu menuCoordenador = new JMenu("Coordenador");
        JMenuItem itemCadastrarCoordenador = new JMenuItem("Cadastrar Coordenador");
        itemCadastrarCoordenador.addActionListener(actionEvent -> openCadastroCoordenadorView());
        menuCoordenador.add(itemCadastrarCoordenador);
        menuBar.add(menuCoordenador);

        // Menu Ação Produção Evento de Comida
        JMenu menuAcaoEventoComida = new JMenu("Ação Produção Evento de Comida");
        JMenuItem itemCadastrarAcaoEvento = new JMenuItem("Cadastrar Ação");
        menuAcaoEventoComida.add(itemCadastrarAcaoEvento);
        menuBar.add(menuAcaoEventoComida);
        itemCadastrarAcaoEvento.addActionListener(actionEvent -> openCadastroAcaoDeProducaoView());

        // Menu Ação Contribuição Direta
        JMenu menuAcaoContribuicaoDireta = new JMenu("Ação Contribuição Direta");
        JMenuItem itemCadastrarAcaoContribuicao = new JMenuItem("Cadastrar Ação");
        itemCadastrarAcaoContribuicao.addActionListener(actionEvent -> openCadastroAcaoContribuicaoDiretaView());
        menuAcaoContribuicaoDireta.add(itemCadastrarAcaoContribuicao);

        JMenuItem itemCadastrarDoacao = new JMenuItem("Cadastrar Doação");
        itemCadastrarDoacao.addActionListener(actionEvent -> openCadastroDoacaoParaAcaoContribuicaoDiretaView());
        menuAcaoContribuicaoDireta.add(itemCadastrarDoacao);

        menuBar.add(menuAcaoContribuicaoDireta);

        setJMenuBar(menuBar);
    }

    private void closeContext() {
        if (context != null) {
            context.close();
        }
    }

    private void openCadastroAcaoContribuicaoDiretaView() {
        SwingUtilities.invokeLater(() -> {
            CadastroAcaoContribuicaoDiretaView screen = new CadastroAcaoContribuicaoDiretaView(controllerAcaoContribuicaoDireta, controllerCoordenador);
            screen.setVisible(true);
        });
    }

    private void openCadastroCoordenadorView() {
        SwingUtilities.invokeLater(() -> {
            CadastroCoordenadorView screen = new CadastroCoordenadorView(controllerCoordenador);
            screen.setVisible(true);
        });
    }

    private void openCadastroDoacaoParaAcaoContribuicaoDiretaView() {
        SwingUtilities.invokeLater(() -> {
            CadastrarDoacaoParaAcaoContribuicaoDiretaView screen = new CadastrarDoacaoParaAcaoContribuicaoDiretaView(controllerAcaoContribuicaoDireta);
            screen.setVisible(true);
        });
    }

    private void openCadastroAcaoDeProducaoView() {
        SwingUtilities.invokeLater(() -> {
            CadastrarAcaoDeProducaoView screen = new CadastrarAcaoDeProducaoView(controllerCoordenador, controllerAcaoProducao);
            screen.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainScreen ms = new MainScreen();
            ms.setVisible(true);
        });
    }
}
