package ru.tigran.cardcollector.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SecretSessionKey {

    @Id
    @Column private String SecretKey;

    public String getSecretKey() {
        return SecretKey;
    }

    public SecretSessionKey setSecretKey(String secretKey) {
        SecretKey = secretKey;
        return this;
    }

    public SecretSessionKey(String secretKey) {
        SecretKey = secretKey;
    }

    public SecretSessionKey() {
    }
}
