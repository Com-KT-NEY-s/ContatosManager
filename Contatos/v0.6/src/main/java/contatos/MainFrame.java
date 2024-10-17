package contatos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame {
    private final JFrame frame;
    private final DefaultTableModel tableModel;
    private final JTable contactTable;
    private final JComboBox<String> categoryFilter;
    private final JTextField searchField;
    private final JLabel fileLabel;
    private final JPanel filterPanel;  // Agora é uma variável de instância
    private final ContactManager contactManager;
    private final FileManager fileManager;
    private boolean isDarkMode = false;  // Flag para controlar o modo escuro

    public MainFrame() {
        contactManager = new ContactManager();
        fileManager = new FileManager(contactManager, this);

        frame = new JFrame("Contatos");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Table setup
        String[] columnNames = {"Nome", "Telefone", "E-mail", "Endereço", "Categoria"};
        tableModel = new DefaultTableModel(columnNames, 0);
        contactTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(contactTable);

        // Tool Bar setup
        JToolBar toolBar = new JToolBar();
        JButton addButton = new JButton("Adicionar");
        JButton editButton = new JButton("Editar");
        JButton excButton = new JButton("Excluir");
        JButton saveButton = new JButton("Salvar");
        JButton loadButton = new JButton("Carregar");
        toolBar.add(addButton);
        toolBar.add(editButton);
        toolBar.add(excButton);
        toolBar.add(saveButton);
        toolBar.add(loadButton);

        // Menu setup (Configurações)
        JMenuBar menuBar = new JMenuBar();
        JMenu settingsMenu = new JMenu("Configurações");
        JMenuItem settingsItem = new JMenuItem("Abrir Configurações");
        settingsItem.addActionListener(e -> openSettingsDialog());
        settingsMenu.add(settingsItem);
        menuBar.add(settingsMenu);
        frame.setJMenuBar(menuBar);

        // Filter Panel setup
        filterPanel = new JPanel();  // Agora foi movido para uma variável de instância
        JLabel filterLabel = new JLabel("Filtrar por Categoria:");
        categoryFilter = new JComboBox<>(new String[]{"Todos", "Amigo", "Trabalho", "Família"});
        JLabel searchLabel = new JLabel("Buscar:");
        searchField = new JTextField(15);
        filterPanel.add(filterLabel);
        filterPanel.add(categoryFilter);
        filterPanel.add(searchLabel);
        filterPanel.add(searchField);

        // File label para exibir o arquivo carregado
        fileLabel = new JLabel("Nenhum arquivo carregado.");  // Mensagem padrão
        JPanel filePanel = new JPanel();
        filePanel.add(fileLabel);  // Adiciona o label ao painel

        // Button Actions
        addButton.addActionListener(e -> contactManager.showAddEditDialog(frame, tableModel, null, isDarkMode));
        editButton.addActionListener(e -> {
            int selectedRow = contactTable.getSelectedRow();
            if (selectedRow != -1) {
                Contact contact = contactManager.getContactFromTable(selectedRow, tableModel);
                contactManager.showAddEditDialog(frame, tableModel, contact, isDarkMode);  // Passa o tema para o diálogo
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um contato para editar.");
            }
        });
        excButton.addActionListener(e -> {
            int selectedRow = contactTable.getSelectedRow();
            if (selectedRow != -1) {
                contactManager.deleteContact(selectedRow, tableModel);
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um contato para excluir.");
            }
        });
        saveButton.addActionListener(e -> fileManager.saveContactsToFile());
        loadButton.addActionListener(e -> fileManager.loadContactsFromFile(tableModel));

        // Frame Layout
        frame.setLayout(new BorderLayout());
        frame.add(toolBar, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(filterPanel, BorderLayout.SOUTH);  // Agora o filterPanel pode ser acessado
        frame.add(filePanel, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }

    // Método para abrir a janela de configurações
    private void openSettingsDialog() {
        new SettingsDialog(frame, this, isDarkMode);  // Passa a referência do MainFrame para o SettingsDialog
    }

    // Método para atualizar o label do arquivo carregado
    public void updateFileLabel(String fileName) {
        fileLabel.setText("Arquivo atual: " + fileName);
    }

    // Método para alternar entre modo claro e escuro em todas as janelas
    public void toggleDarkMode(boolean enableDarkMode) {
        isDarkMode = enableDarkMode;  // Atualiza a flag
        Color backgroundColor = isDarkMode ? Color.DARK_GRAY : Color.LIGHT_GRAY;
        Color textColor = isDarkMode ? Color.WHITE : Color.BLACK;

        frame.getContentPane().setBackground(backgroundColor);

        // Atualiza a cor da tabela
        contactTable.setBackground(backgroundColor);
        contactTable.setForeground(textColor);
        contactTable.getTableHeader().setBackground(backgroundColor);
        contactTable.getTableHeader().setForeground(textColor);

        // Atualiza os componentes da barra de ferramentas
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JToolBar) {
                for (Component toolBarComp : ((JToolBar) comp).getComponents()) {
                    toolBarComp.setBackground(backgroundColor);
                    toolBarComp.setForeground(textColor);
                }
            }
        }

        // Atualiza os botões
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JButton) {
                comp.setBackground(backgroundColor);
                comp.setForeground(textColor);
            }
        }

        // Atualiza o painel de filtro
        filterPanel.setBackground(backgroundColor);  // Agora o filterPanel pode ser acessado
        categoryFilter.setBackground(backgroundColor);
        categoryFilter.setForeground(textColor);
        searchField.setBackground(backgroundColor);
        searchField.setForeground(textColor);

        // Atualiza o label do arquivo
        fileLabel.setForeground(textColor);
    }
}
