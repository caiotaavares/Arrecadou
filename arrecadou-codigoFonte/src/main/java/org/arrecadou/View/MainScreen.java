package org.arrecadou.View;

import org.arrecadou.Config.AppConfig;
import org.arrecadou.Config.JpaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.util.Objects;

public class MainScreen extends JFrame {
    private JMenu menuAcao;
    private JMenuItem itemCoordenador, itemEventoComida, itemContribuicaoDireta;
    private AnnotationConfigApplicationContext context;
    private JLabel backgroundLabel;

    public MainScreen() {
        initializeContext();
        initializeUI();
        setupMenu();
    }

    private void initializeContext() {
        context = new AnnotationConfigApplicationContext(AppConfig.class, JpaConfig.class);
    }

    private void initializeUI() {
        setTitle("Sistema de Gestão");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/img.png")));
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setSize(backgroundImage.getIconWidth()+50, backgroundImage.getIconHeight()+50);
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

        menuAcao = new JMenu("Ação");
        menuBar.add(menuAcao);

        itemCoordenador = new JMenuItem("Cadastrar Coordenador");
        itemCoordenador.addActionListener(actionEvent -> openCadastroCoordenadorView());
        menuAcao.add(itemCoordenador);

        itemEventoComida = new JMenuItem("Cadastrar uma Ação de Produção de Evento de Comida");
        menuAcao.add(itemEventoComida);

        itemContribuicaoDireta = new JMenuItem("Cadastrar uma Ação de Contribuição Direta");
        menuAcao.add(itemContribuicaoDireta);

        setJMenuBar(menuBar);
    }

    private void closeContext() {
        if (context != null) {
            context.close();
        }
    }

    private void openCadastroCoordenadorView() {
        SwingUtilities.invokeLater(() -> {
            CadastroCoordenadorView screen = new CadastroCoordenadorView(context);
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