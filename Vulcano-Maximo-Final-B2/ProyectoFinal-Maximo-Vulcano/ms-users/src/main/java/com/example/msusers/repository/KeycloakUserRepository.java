package com.example.msusers.repository;

import com.example.msusers.model.User;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class KeycloakUserRepository implements IUserRepository{

    private final Keycloak keycloakClient;
    private FeignBillsRepository feignBillsRepository;
    
    @Value("${config-user.keycloak.realm}")
    private String realm;
    
    @Override
    public List<User> findAll() {
        return keycloakClient.realm(realm).users().list().stream().map(this::toUser).collect(Collectors.toList());
    }

    private User toUser(UserRepresentation userRepresentation) {
        return User.builder()
                .id(userRepresentation.getId())
                .userName(userRepresentation.getUsername())
                .build();
    }

    @Override
    public Optional<User> findById(String id) {
        UserRepresentation userRepresentation;
        try {
            userRepresentation = keycloakClient.realm(realm)
                    .users().get(id)
                    .toRepresentation();
        } catch (NotFoundException e) {
            return Optional.empty();
        }
        return Optional.of(toUser(userRepresentation));
    }

    private User fromRepresentation (UserRepresentation userRepresentation) {
        return new User(userRepresentation.getId(), userRepresentation.getUsername(),feignBillsRepository.getByUserId(userRepresentation.getId()).getBody());
    }

}
