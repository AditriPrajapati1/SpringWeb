package io.herald.SpringWeb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Controller --> HTTP request handler like get, post, etc..
@Controller

public class TotalController {

    @GetMapping("/")
    public String firstPage()
    {
        return "index"; //returns index.html page

    }

}
