package in.regres.model;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserToken {
    private Long id;
    private String token;
}
