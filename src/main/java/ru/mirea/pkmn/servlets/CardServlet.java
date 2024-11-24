package ru.mirea.pkmn.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mirea.pkmn.entities.Card;
import ru.mirea.pkmn.repository.CardRepo;
import ru.mirea.pkmn.repository.StudentRepo;
import ru.mirea.pkmn.services.CardService;

import java.io.IOException;

@WebServlet(name = "CardServlet", urlPatterns = "/cards/*", loadOnStartup = 1)
public class CardServlet extends HttpServlet {

    private ObjectMapper objectMapper;

    private EntityManager em;

    private CardService cardService;

    @Override
    public void init() throws ServletException {
        cardService = (CardService) getServletContext().getAttribute("cardService");
        em = (EntityManager) getServletContext().getAttribute("entityManager");
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Card card = cardService.findCardByRequest(request);
        if (card == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        objectMapper.writeValue(response.getWriter(), card);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Card card = objectMapper.readValue(request.getInputStream(), Card.class);
        try {
            cardService.saveCard(card, null);
            objectMapper.writeValue(response.getWriter(), card);
        } catch (Exception e) {
            response.sendError(e.hashCode(), e.getMessage());
        }
    }
}