package com.example.hockey.core.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepo extends JpaRepository<Match, Long> {

    @Query(value = "SELECT m.id, m.id_guests, m.id_owners, m.id_tournament, match_date, te.command_name, ta.command_name,\n" +
            "score_owners, score_guests FROM match m\n" +
            "JOIN team te ON (m.id_owners = te.id)\n" +
            "JOIN player_team pt ON (te.id = pt.id_team)\n" +
            "JOIN player p ON (pt.id_player = p.id)\n" +
            "JOIN team ta ON (m.id_guests = ta.id)\n" +
            "JOIN player_team pl ON (ta.id = pl.id_team)\n" +
            "JOIN player py ON (pl.id_player = py.id)\n" +
            "WHERE (id_tournament = (SELECT id_tournament FROM match WHERE match_date = (SELECT max(match_date) FROM match)))\n" +
            "AND ((py.surname_player = :playerSurname AND py.name_player = :playerName) \n" +
            "OR (p.surname_player = :playerSurname AND p.name_player = :playerName))\n" +
            "GROUP BY m.id, m.id_guests, m.id_owners, m.id_tournament, score_owners, score_guests, match_date, te.command_name, ta.command_name", nativeQuery = true)
    List<Match> getLastMatch(@Param("playerSurname") String playerSurname, @Param("playerName") String playerName);
}
