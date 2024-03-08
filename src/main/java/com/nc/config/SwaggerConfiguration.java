package com.nc.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Naveen Chandra",
                        email = "nc.upadhyay20@gmail.com",
                        url = "https://www.linkedin.com/in/ncupadhyay/"
                ),
                title = "Grab food- Singapore Api Documentation.",
                description = "Develop the scraper to scrape Grab Food Delivery using spring boot and java.",
                version = "1.0"

        ),
        servers = {
                @Server(
                        url = "http://localhost:8080/"
                        , description = "dev env"
                )
        }
)
public class SwaggerConfiguration {


}
