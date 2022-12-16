package com.nova.controllers;

import com.nova.models.GameComments;
import com.nova.models.Games;
import com.nova.models.Users;
import com.nova.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;

public class Global {

    @Autowired
    RepoUsers repoUsers;

    @Autowired
    RepoGames repoGames;

    @Autowired
    RepoGameComments repoComments;

    @Autowired
    RepoGameDescription repoGameDescription;

    @Autowired
    RepoGameIncome repoGameIncome;

    @Value("${upload.path_img}")
    String uploadPathImg;

    protected String DateNow() {
        return LocalDateTime.now().toString().substring(0, 10);
    }
    String checkUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            if (userDetail != null) {
                Users userFromDB = repoUsers.findByUsername(userDetail.getUsername());
                return String.valueOf(userFromDB.getRole());
            }
            return "NOT";
        }
        return "NOT";
    }

    Users checkUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            if (userDetail != null) {
                return repoUsers.findByUsername(userDetail.getUsername());
            }
        }
        return null;
    }

    void totalGameDelete(long id) {
        List<Users> users = repoUsers.findAll();

        for (Users user : users) {
            if (user.getCart() != null) for (long carts : user.getCart())
                if (id == carts) {
                    if (user.getCart().length == 1) user.setCart(null);
                    else {
                        long[] cart = new long[user.getCart().length - 1];
                        int i = 0;
                        for (long c : user.getCart()) {
                            if (id == c) continue;
                            cart[i] = c;
                            i++;
                        }
                        user.setCart(cart);
                    }
                }
            if (user.getBuy() != null) for (long carts : user.getBuy())
                if (id == carts) {
                    if (user.getBuy().length == 1) user.setBuy(null);
                    else {
                        long[] cart = new long[user.getBuy().length - 1];
                        int i = 0;
                        for (long c : user.getBuy()) {
                            if (id == c) continue;
                            cart[i] = c;
                            i++;
                        }
                        user.setBuy(cart);
                    }
                }
        }

        repoUsers.saveAll(users);

        repoGames.deleteById(id);
        repoGameIncome.deleteById(id);
        repoGameDescription.deleteById(id);

        List<GameComments> comments = repoComments.findAllByGameid(id);

        for (GameComments c : comments) {
            repoComments.deleteById(c.getId());
        }
    }

    void deleteNull() {
        List<Games> gamesList = repoGames.findAll();
        for (Games g : gamesList) {
            if (g.getYear() == 0 && g.getPoster() == null) {
                repoGames.deleteById(g.getId());
            }
        }
    }

}
