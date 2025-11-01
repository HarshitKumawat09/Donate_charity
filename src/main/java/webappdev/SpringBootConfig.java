package webappdev;

import org.aspectj.weaver.ast.Or;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import webappdev.history.Date;
import webappdev.history.DateRepository;
import webappdev.login.User;
import webappdev.login.UserRepository;
import webappdev.organizations.Organizations;
import webappdev.organizations.OrganizationsRepository;
import webappdev.preferences.Preferences;
import webappdev.preferences.PreferencesRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Configuration
public class SpringBootConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, PreferencesRepository preferencesRepository, OrganizationsRepository organizationsRepository, DateRepository dateRepository) {
        return args -> {

            // USERS
            User tommy = new User(
                    "tommy",
                    "Trojan",
                    "tommyt@gmail.com"

            );

            userRepository.saveAll (
                    List.of(tommy)
            );

            Preferences p1 = new Preferences(
                "animals",
                    1L
            );

            Preferences p2 = new Preferences(
                    "education",
                    1L
            );

            Preferences p3 = new Preferences(
                    "poverty",
                    1L
            );

            Preferences p4 = new Preferences(
                    "research",
                    1L
            );

            preferencesRepository.saveAll(
                    List.of(p1, p2, p3, p4)
            );

            Organizations org1 = new Organizations("Lil Bub's Big Fund", "https://www.goodjobbub.org/", "image1.jpg", "lilbubsbigfund", 1L);
            Organizations org2 = new Organizations("Malala Fund", "http://example.com/org2", "image2.jpg", "org2-slug", 1L);
            Organizations org3 = new Organizations("Innocence Project", "http://example.com/org3", "image3.jpg", "org3-slug", 1L);

            organizationsRepository.saveAll(
                    List.of(org1, org2, org3)
            );

            Date d1 = new Date(
                    "Lil Bub's Big Fund",
                    new java.sql.Date(System.currentTimeMillis()),
                    100,
                    1L
            );

            Date d2 = new Date(
                    "Lil Bub's Big Fund",
                    new java.sql.Date(System.currentTimeMillis()),
                    50,
                    1L
            );

            Date d3 = new Date(
                    "Lil Bub's Big Fund",
                    new java.sql.Date(System.currentTimeMillis()),
                    50,
                    1L
            );

            Date d4 = new Date(
                    "idk",
                    new java.sql.Date(System.currentTimeMillis()),
                    25,
                    1L
            );

            Date d5 = new Date(
                    "usc",
                    new java.sql.Date(System.currentTimeMillis()),
                    75,
                    1L
            );

            dateRepository.saveAll(
                    List.of(d1, d2, d3, d4, d5)
            );

        };
    }
}
