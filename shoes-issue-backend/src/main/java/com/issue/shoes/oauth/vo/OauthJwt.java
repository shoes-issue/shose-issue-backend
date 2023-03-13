package com.issue.shoes.oauth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OauthJwt {

    private String userJwtIdx;
    private String subject;
    private String userId;
    private String refreshToken;

}
