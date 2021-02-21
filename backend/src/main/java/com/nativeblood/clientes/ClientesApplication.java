package com.nativeblood.clientes;

import com.nativeblood.clientes.model.entity.Cliente;
import com.nativeblood.clientes.model.repository.ClienteRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientesApplication {

//    @Bean
//    public CommandLineRunner run(@Autowired ClienteRepository repository) {
//        return args -> {
//            Cliente cliente = Cliente.builder()
//                    .cpf_cliente("01750483203")
//                    .nome_cliente("Ítalo Felippe")
//                    .build();
//
//            repository.save(cliente);
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(ClientesApplication.class, args);
    }
}
