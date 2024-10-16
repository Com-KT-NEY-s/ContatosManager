package contatos;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

public class ContactManager {
    private final ArrayList<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    public void showAddEditDialog(JFrame parent, DefaultTableModel tableModel, Contact contact) {
        JDialog dialog = new JDialog(parent, contact == null ? "Adicionar Contato" : "Editar Contato", true);
        dialog.setLayout(new GridLayout(0, 2, 10, 10));

        JTextField nomeField = new JTextField(contact != null ? contact.getNome() : "");
        
        // Criar um campo de telefone formatado (JFormattedTextField)
        JFormattedTextField telefoneField = null;
        try {
            MaskFormatter phoneFormatter = new MaskFormatter("(##) #####-####");  // Definindo a máscara
            phoneFormatter.setPlaceholderCharacter('_');
            telefoneField = new JFormattedTextField(phoneFormatter);
            if (contact != null) {
                telefoneField.setText(contact.getTelefone());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JTextField emailField = new JTextField(contact != null ? contact.getEmail() : "");
        JTextField enderecoField = new JTextField(contact != null ? contact.getEndereco() : "");
        JTextField categoriaField = new JTextField(contact != null ? contact.getCategoria() : "");

        dialog.add(new JLabel("Nome:"));
        dialog.add(nomeField);
        dialog.add(new JLabel("Telefone:"));
        dialog.add(telefoneField);  // Adiciona o JFormattedTextField no lugar do JTextField
        dialog.add(new JLabel("E-mail:"));
        dialog.add(emailField);
        dialog.add(new JLabel("Endereço:"));
        dialog.add(enderecoField);
        dialog.add(new JLabel("Categoria:"));
        dialog.add(categoriaField);

        JButton okButton = new JButton("OK");
        JFormattedTextField finalTelefoneField = telefoneField;
        okButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String telefone = finalTelefoneField.getText();  // Obtém o valor formatado
            String email = emailField.getText();
            String endereco = enderecoField.getText();
            String categoria = categoriaField.getText();

            if (!Validator.isValidPhone(telefone)) {
                JOptionPane.showMessageDialog(dialog, "Telefone inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!Validator.isValidEmail(email)) {
                JOptionPane.showMessageDialog(dialog, "E-mail inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Contact newContact = new Contact(nome, telefone, email, endereco, categoria);
            if (contact == null) {
                addContact(newContact, tableModel);
            } else {
                editContact(contact, newContact, tableModel);
            }
            dialog.dispose();
        });

        dialog.add(new JLabel());
        dialog.add(okButton);
        dialog.setSize(300, 300);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    public void addContact(Contact contact, DefaultTableModel tableModel) {
        contacts.add(contact);
        tableModel.addRow(new String[]{contact.getNome(), contact.getTelefone(), contact.getEmail(), contact.getEndereco(), contact.getCategoria()});
    }

    public void editContact(Contact oldContact, Contact newContact, DefaultTableModel tableModel) {
        oldContact.setNome(newContact.getNome());
        oldContact.setTelefone(newContact.getTelefone());
        oldContact.setEmail(newContact.getEmail());
        oldContact.setEndereco(newContact.getEndereco());
        oldContact.setCategoria(newContact.getCategoria());

        // Atualiza a linha na tabela
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(oldContact.getNome())) {
                tableModel.setValueAt(newContact.getNome(), i, 0);
                tableModel.setValueAt(newContact.getTelefone(), i, 1);
                tableModel.setValueAt(newContact.getEmail(), i, 2);
                tableModel.setValueAt(newContact.getEndereco(), i, 3);
                tableModel.setValueAt(newContact.getCategoria(), i, 4);
            }
        }
    }

    public void deleteContact(int rowIndex, DefaultTableModel tableModel) {
        contacts.remove(rowIndex);
        tableModel.removeRow(rowIndex);
    }

    public Contact getContactFromTable(int rowIndex, DefaultTableModel tableModel) {
        String nome = (String) tableModel.getValueAt(rowIndex, 0);
        String telefone = (String) tableModel.getValueAt(rowIndex, 1);
        String email = (String) tableModel.getValueAt(rowIndex, 2);
        String endereco = (String) tableModel.getValueAt(rowIndex, 3);
        String categoria = (String) tableModel.getValueAt(rowIndex, 4);
        return new Contact(nome, telefone, email, endereco, categoria);
    }

    // Retorna todos os contatos
    public ArrayList<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }
}
