package one.digitalinovation.personApi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageRequestDto {

    private String message;

    public MessageRequestDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
