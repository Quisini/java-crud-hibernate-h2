package cch;

import cch.model.*;
import cch.dao.*;

public class Main {

    public static void main(String[] args) {
        Cliente cliente = new Cliente("Gustavo", "12345678901", "gustavo@example.com", "1234567890");
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.salvar(cliente);
    }
}
