package com.bs.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class MapController {

    @GetMapping("/map")
    public static String map(Model model) throws IOException {
        URL url = new URL("https://zaixianke.com/yq/all");
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        String text = br.readLine();
        model.addAttribute("detail", text);
        return "map";
    }
//    public static void main(String args[]) throws IOException {
//        System.out.println(map());
//    }
}
