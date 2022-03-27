package pe.com.cma.apiaccess.service.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class ClientDTO {
    private Long clientId;
    private String name;
    private String phone;
    private String email;
}
