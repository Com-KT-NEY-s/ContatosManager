package contatos;

import javax.swing.*;
import java.awt.*;

public class SettingsDialog extends JDialog {
    private final MainFrame mainFrame;

    public SettingsDialog(JFrame parent, MainFrame mainFrame) {
        super(parent, "Configurações", true);
        this.mainFrame = mainFrame;

        setLayout(new GridLayout(3, 1, 10, 10));
        setSize(300, 200);
        setLocationRelativeTo(parent);

        // Checkbox para modo escuro
        JCheckBox darkModeCheckbox = new JCheckBox("Ativar Modo Escuro");
        darkModeCheckbox.setSelected(mainFrame.isDarkMode);  // Definir se está no modo escuro ou não
        darkModeCheckbox.addActionListener(e -> {
            boolean selected = darkModeCheckbox.isSelected();
            mainFrame.toggleDarkMode(selected);  // Alternar entre os modos claro e escuro
        });

        // Botão para exibir detalhes
        JButton detailsButton = new JButton("Exibir Detalhes");
        detailsButton.addActionListener(e -> showDetails());

        // Botão para fechar a janela de configurações
        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(e -> dispose());

        add(darkModeCheckbox);
        add(detailsButton);
        add(closeButton);

        setVisible(true);
    }

    // Exibe uma janela de diálogo com detalhes da aplicação
    private void showDetails() {
        JOptionPane.showMessageDialog(this,
                "Aplicação de Gerenciamento de Contatos\nVersão: 0.3\nDesenvolvido por: Com KT NEY tor's",
                "Detalhes",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
