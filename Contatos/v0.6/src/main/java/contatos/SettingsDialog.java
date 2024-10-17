package contatos;

import javax.swing.*;
import java.awt.*;

public class SettingsDialog extends JDialog {
    private final MainFrame mainFrame;

    public SettingsDialog(JFrame parent, MainFrame mainFrame, boolean isDarkMode) {
        super(parent, "Configurações", true);
        this.mainFrame = mainFrame;

        setLayout(new GridLayout(3, 1, 10, 10));
        setSize(300, 200);
        setLocationRelativeTo(parent);

        // Checkbox para modo escuro
        JCheckBox darkModeCheckbox = new JCheckBox("Ativar Modo Escuro");
        darkModeCheckbox.setSelected(isDarkMode);  // Definir se está no modo escuro ou não
        darkModeCheckbox.addActionListener(e -> {
            boolean selected = darkModeCheckbox.isSelected();
            mainFrame.toggleDarkMode(selected);  // Alternar entre os modos claro e escuro
            updateDialogColors(selected);        // Atualiza a cor do próprio diálogo
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

        // Aplica o tema escuro ou claro na janela de configurações
        updateDialogColors(isDarkMode);

        setVisible(true);
    }

    // Exibe uma janela de diálogo com detalhes da aplicação
    private void showDetails() {
        JOptionPane.showMessageDialog(this,
                "Aplicação de Gerenciamento de Contatos\nVersão: 1.0\nDesenvolvido por: Seu Nome",
                "Detalhes",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Atualiza as cores da janela de configurações com base no tema
    private void updateDialogColors(boolean isDarkMode) {
        Color backgroundColor = isDarkMode ? Color.DARK_GRAY : Color.LIGHT_GRAY;
        Color textColor = isDarkMode ? Color.WHITE : Color.BLACK;
        getContentPane().setBackground(backgroundColor);

        // Percorre e atualiza todos os componentes filhos
        for (Component component : getContentPane().getComponents()) {
            component.setBackground(backgroundColor);
            component.setForeground(textColor);
        }
    }
}
