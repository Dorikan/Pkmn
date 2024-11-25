package ru.mirea.pkmn.servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.mirea.pkmn.repository.CardRepoImpl;
import ru.mirea.pkmn.repository.StudentRepoImpl;
import ru.mirea.pkmn.services.CardService;
import ru.mirea.pkmn.services.CardServiceImpl;

@WebListener
public class ConfigServlet implements ServletContextListener {

    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    private CardService cardService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Init config servlet");
        entityManagerFactory = Persistence.createEntityManagerFactory("pkmn");
        entityManager = entityManagerFactory.createEntityManager();
        cardService = new CardServiceImpl(new CardRepoImpl(entityManager), new StudentRepoImpl(entityManager));
        sce.getServletContext().setAttribute("entityManager", entityManager);
        sce.getServletContext().setAttribute("cardService", cardService);
    }
}