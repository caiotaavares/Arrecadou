package org.arrecadou.View;

import org.arrecadou.Config.AppConfig;
import org.arrecadou.Config.JpaConfig;
import org.arrecadou.Controladores.ControllerAcaoContribuicaoDireta;
import org.arrecadou.Controladores.ControllerCoordenador;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.util.Objects;

@SuppressWarnings("ExtractMethodRecommender")
public class MainScreen extends JFrame {
    private AnnotationConfigApplicationContext context;
    private ControllerAcaoContribuicaoDireta controllerAcaoContribuicaoDireta;
    private ControllerCoordenador controllerCoordenador;

    public MainScreen() {
        initializeContext();
        initializeUI();
        setupMenu();
    }

    private void initializeContext() {
        context = new AnnotationConfigApplicationContext(AppConfig.class, JpaConfig.class);
        this.controllerAcaoContribuicaoDireta = context.getBean(ControllerAcaoContribuicaoDireta.class);
        this.controllerCoordenador = context.getBean(ControllerCoordenador.class);
    }

    private void initializeUI() {
        setTitle("Sistema de Gestão");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/img.png")));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setSize(backgroundImage.getIconWidth() + 50, backgroundImage.getIconHeight() + 50);
        setContentPane(backgroundLabel);
        setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        setLocationRelativeTo(null);

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


        JMenu menuAcaoEventoComida = new JMenu("Ação Produção Evento de Comida");
        JMenuItem itemCadastrarAcaoEvento = new JMenuItem("Cadastrar Ação");

        menuAcaoEventoComida.add(itemCadastrarAcaoEvento);
        menuBar.add(menuAcaoEventoComida);


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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainScreen ms = new MainScreen();
            ms.setVisible(true);
        });
    }
}
