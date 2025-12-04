package com.example.actividades_app.config;

import com.example.actividades_app.model.Rol;
import com.example.actividades_app.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{
    private final RolRepository rolRepository;

    public DataLoader(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (rolRepository.count() == 0) {
            Rol admin = new Rol();
            admin.setName("ADMIN");
            rolRepository.save(admin);

            Rol user = new Rol();
            user.setName("USER");
            rolRepository.save(user);
            
        }
    }
}
