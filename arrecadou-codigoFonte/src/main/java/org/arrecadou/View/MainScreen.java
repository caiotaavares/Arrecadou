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

        // Configurando a imagem de fundo
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/img.png")));
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        setContentPane(backgroundLabel);

        // Ajustar o tamanho da tela ao tamanho da imagem
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

        // Menu Ação
        menuAcao = new JMenu("Ação");
        menuBar.add(menuAcao);

        // Submenu cadastrar coordenador
        itemCoordenador = new JMenuItem("Cadastrar Coordenador");
        menuAcao.add(itemCoordenador);

        // Submenu cadastrar uma ação de produção de evento de comida
        itemEventoComida = new JMenuItem("Cadastrar uma Ação de Produção de Evento de Comida");
        menuAcao.add(itemEventoComida);

        // Submenu cadastrar uma ação de contribuição direta
        itemContribuicaoDireta = new JMenuItem("Cadastrar uma Ação de Contribuição Direta");
        menuAcao.add(itemContribuicaoDireta);

        setJMenuBar(menuBar);
    }

    private void closeContext() {
        if (context != null) {
            context.close();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainScreen ms = new MainScreen();
            ms.setVisible(true);
        });
    }
}
