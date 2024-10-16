package contatos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class FileManager {
    private File loadedFile;
    private final ContactManager contactManager;
    private final MainFrame mainFrame;  // Referência para a MainFrame

    public FileManager(ContactManager contactManager, MainFrame mainFrame) {
        this.contactManager = contactManager;
        this.mainFrame = mainFrame;
    }

    public void saveContactsToFile() {
        File fileToSave = loadedFile;

        if (fileToSave == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar contatos em um arquivo");

            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                fileToSave = fileChooser.getSelectedFile();
                loadedFile = fileToSave;
                mainFrame.updateFileLabel(loadedFile.getName());  // Atualiza o label com o nome do arquivo
            } else {
                JOptionPane.showMessageDialog(null, "Operação cancelada.");
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
            for (Contact contact : contactManager.getAllContacts()) {
                writer.write("Nome: " + contact.getNome());
                writer.newLine();
                writer.write("Telefone: " + contact.getTelefone());
                writer.newLine();
                writer.write("E-mail: " + contact.getEmail());
                writer.newLine();
                writer.write("Endereço: " + contact.getEndereco());
                writer.newLine();
                writer.write("Categoria: " + contact.getCategoria());
                writer.newLine();
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Contatos salvos com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os contatos: " + e.getMessage());
        }
    }

    public void loadContactsFromFile(DefaultTableModel tableModel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione um arquivo (.txt)");

        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            loadedFile = fileChooser.getSelectedFile();
            tableModel.setRowCount(0);  // Limpa a tabela
            mainFrame.updateFileLabel(loadedFile.getName());  // Atualiza o label com o nome do arquivo carregado

            try (BufferedReader reader = new BufferedReader(new FileReader(loadedFile))) {
                String line;
                String[] contactData = new String[5];
                int index = 0;

                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        if (index > 0) {
                            tableModel.addRow(contactData);
                            contactData = new String[5];
                            index = 0;
                        }
                    } else {
                        String[] parts = line.split(": ");
                        if (parts.length == 2) {
                            switch (parts[0]) {
                                case "Nome" -> contactData[0] = parts[1];
                                case "Telefone" -> contactData[1] = parts[1];
                                case "E-mail" -> contactData[2] = parts[1];
                                case "Endereço" -> contactData[3] = parts[1];
                                case "Categoria" -> contactData[4] = parts[1];
                            }
                            index++;
                        }
                    }
                }
                if (index > 0) {
                    tableModel.addRow(contactData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
