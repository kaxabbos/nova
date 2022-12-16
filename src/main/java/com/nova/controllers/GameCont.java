package com.nova.controllers;

import com.nova.models.GameAll;
import com.nova.models.GameComments;
import com.nova.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class GameCont extends Global {

    @GetMapping("/game/{id}")
    public String game(@PathVariable(value = "id") Long id, Model model) {
        GameAll gameAll = new GameAll(repoGames.findById(id).orElseThrow(), repoGameDescription.findByGameid(id));

        long cart = 1, buy = 1;
        Users userFromDB = checkUser();

        if (userFromDB.getCart() != null) {
            long[] carts = userFromDB.getCart();
            for (long c : carts) {
                if (c == id) {
                    model.addAttribute("cart", cart);
                    break;
                }
            }
        }

        if (userFromDB.getBuy() != null) {
            long[] buys = userFromDB.getBuy();
            for (long b : buys)
                if (b == id) {
                    model.addAttribute("buy", buy);
                    break;
                }
        }

        List<GameComments> comments = repoComments.findAllByGameid(gameAll.getId());

        Collections.reverse(comments);

        model.addAttribute("games", gameAll);
        model.addAttribute("comments", comments);
        model.addAttribute("role", checkUserRole());
        return "game";
    }

    @PostMapping("/game/{id}/comment_add")
    public String comment_add(@PathVariable(value = "id") Long id, @RequestParam String date, @RequestParam String[] comment) {
        StringBuilder com = new StringBuilder();
        for (String s : comment) com.append(s);

        GameComments c = new GameComments(id, checkUser().getUsername(), date, com.toString());

        repoComments.save(c);

        return "redirect:/game/{id}";
    }
}
