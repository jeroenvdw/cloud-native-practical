package com.ezgroceries.shoppinglist.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testPrincipal")
public class PrincipalController {
    @GetMapping
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }
}
