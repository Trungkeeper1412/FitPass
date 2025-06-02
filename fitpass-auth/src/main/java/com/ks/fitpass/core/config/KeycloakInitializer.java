package com.ks.fitpass.core.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.*;

@Configuration
@Profile("dev")
public class KeycloakInitializer {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.admin.username}")
    private String username;

    @Value("${keycloak.admin.password}")
    private String password;

    private static final String DEFAULT_PASSWORD = "password123";

    @Bean
    public CommandLineRunner initKeycloak(Keycloak keycloakAdmin) {
        return args -> {
            // Check if realm exists
            List<RealmRepresentation> realms = keycloakAdmin.realms().findAll();
            boolean realmExists = realms.stream().anyMatch(r -> r.getRealm().equals(realm));

            if (!realmExists) {
                // Create realm
                RealmRepresentation realmRep = new RealmRepresentation();
                realmRep.setRealm(realm);
                realmRep.setEnabled(true);
                realmRep.setRegistrationAllowed(true);

                // Create client
                ClientRepresentation client = new ClientRepresentation();
                client.setClientId(clientId);
                client.setPublicClient(true);
                client.setRedirectUris(Collections.singletonList("*"));
                client.setWebOrigins(Collections.singletonList("*"));
                client.setDirectAccessGrantsEnabled(true);
                realmRep.setClients(Collections.singletonList(client));

                // Define roles
                List<RoleRepresentation> realmRoles = Arrays.asList(
                        createRole("USER"),
                        createRole("ADMIN"),
                        createRole("EMPLOYEE"),
                        createRole("GYM_OWNER"),
                        createRole("BRAND_OWNER")
                );
                
                // Create and set RolesRepresentation
                RolesRepresentation rolesRepresentation = new RolesRepresentation();
                rolesRepresentation.setRealm(realmRoles);
                realmRep.setRoles(rolesRepresentation);

                // Create the realm
                keycloakAdmin.realms().create(realmRep);
                
                // Create example users
                createExampleUsers(keycloakAdmin);
            }
        };
    }

    private void createExampleUsers(Keycloak keycloakAdmin) {
        // Get the realm resource
        RealmResource realmResource = keycloakAdmin.realm(realm);
        UsersResource usersResource = realmResource.users();

        // Create admin user
        createUser(usersResource, realmResource, "admin", "admin@example.com", "Admin", "User", "ADMIN,USER");
        
        // Create gym owner user
        createUser(usersResource, realmResource, "gymowner", "gymowner@example.com", "Gym", "Owner", "GYM_OWNER,USER");
        
        // Create employee user
        createUser(usersResource, realmResource, "employee", "employee@example.com", "Gym", "Staff", "EMPLOYEE,USER");
        
        // Create regular user
        createUser(usersResource, realmResource, "user", "user@example.com", "Regular", "User", "USER");
    }

    private void createUser(UsersResource usersResource, RealmResource realmResource, 
                          String username, String email, String firstName, String lastName, String roles) {
        // Create user
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailVerified(true);

        // Create password credential
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(DEFAULT_PASSWORD);
        credential.setTemporary(false);
        user.setCredentials(Collections.singletonList(credential));

        // Create user
        usersResource.create(user);

        // Get the created user to assign roles
        UserRepresentation createdUser = usersResource.searchByEmail(email, true).get(0);
        
        // Assign roles
        Arrays.stream(roles.split(",")).forEach(role -> {
            RoleRepresentation roleRep = realmResource.roles().get(role).toRepresentation();
            usersResource.get(createdUser.getId()).roles().realmLevel().add(Collections.singletonList(roleRep));
        });
    }

    private RoleRepresentation createRole(String name) {
        RoleRepresentation role = new RoleRepresentation();
        role.setName(name);
        return role;
    }

    @Bean
    public Keycloak keycloakAdmin() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakServerUrl)
                .realm("master")
                .clientId("admin-cli")
                .username(username)
                .password(password)
                .build();
    }
}