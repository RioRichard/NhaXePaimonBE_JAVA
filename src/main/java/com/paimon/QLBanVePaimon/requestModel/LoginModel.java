package com.paimon.QLBanVePaimon.requestModel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginModel {
    
    @NotNull
    @Min(1)
    private String username;
    @NotNull
    @Min(1)
    private String pass;
    
}
