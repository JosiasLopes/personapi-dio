package one.digitalinovation.personApi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageRequestDto {

    private String message;
}