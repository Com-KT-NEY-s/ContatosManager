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
    private final JLabel fileLabel; // Label para mostrar o nome do arquivo carregado/salvo
    private final ContactManager contactManager;
    private final FileManager fileManager;

    public MainFrame() {
        contactManager = new ContactManager();
        fileManager = new FileManager(contactManager, this);  // Passa a referência do MainFrame para FileManager

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

        // Filter Panel setup
        JPanel filterPanel = new JPanel();
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
        addButton.addActionListener(e -> contactManager.showAddEditDialog(frame, tableModel, null));
        editButton.addActionListener(e -> {
            int selectedRow = contactTable.getSelectedRow();
            if (selectedRow != -1) {
                Contact contact = contactManager.getContactFromTable(selectedRow, tableModel);
                contactManager.showAddEditDialog(frame, tableModel, contact);
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
        frame.add(filterPanel, BorderLayout.SOUTH);
        frame.add(filePanel, BorderLayout.PAGE_END);  // Adiciona o painel com o label do arquivo na parte inferior

        frame.setVisible(true);
    }

    // Método para atualizar o label do arquivo carregado
    public void updateFileLabel(String fileName) {
        fileLabel.setText("Arquivo atual: " + fileName);
    }
}
