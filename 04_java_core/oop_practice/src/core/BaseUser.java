package core;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseUser {
    private final UUID id;
    private LocalDateTime created_at;

    public BaseUser(){
        this.id = UUID.randomUUID();
        this.created_at = LocalDateTime.now();
        
    }


    // Getters
    public UUID getId(){
        return this.id;
    }

    public LocalDateTime getCreatedAt(){
        return this.created_at;
    }
}
