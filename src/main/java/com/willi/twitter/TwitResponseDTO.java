package com.willi.twitter;

import java.time.LocalDate;

public class TwitResponseDTO {

    public String content;
    public LocalDate creationDate;

    public TwitResponseDTO(String content, LocalDate creationDate) {
        this.content = content;
        this.creationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
