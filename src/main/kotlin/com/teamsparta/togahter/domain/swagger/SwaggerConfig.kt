package com.teamsparta.togahter.domain.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .addSecurityItem(
                SecurityRequirement().addList("Bearer Authentication")
            )
            .components(
                Components().addSecuritySchemes(
                    "Bearer Authentication",
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("Bearer")
                        .bearerFormat("JWT")
                        .`in`(SecurityScheme.In.HEADER)
                        .name("Authorization")
                )
            )
            .info(
                Info()
                    .title("ToGather API")
                    .description("To Gather = 모으기\n" +
                            "Together = 함께\n" +
                            "발음이 같은 두 단어의 의미를 모두 내포한 함께 할 사람을 모으는 서비스를 기획해보았습니다.\n" +
                            "스터디, 운동, 게임 등을 할 때 같이 할 사람이 없거나 인원이 모자란 경우, 팀을 만들고 생각이 맞는 팀원을 쉽게 모집할 수 있도록 서비스를 제공하는 것을 목표로 합니다.")
                    .version("1.0.0")
            )
    }
}