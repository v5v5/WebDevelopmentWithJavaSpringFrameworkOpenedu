INSERT INTO tournament ("name", country)
VALUES
('Ball','Russia'),
('Foot','UK');

INSERT INTO team ("name", players)
VALUES
('First', 43),
('Second', 50);

INSERT INTO tournament_team (id_tournament, id_team)
VALUES
(1,1),
(1,2),
(2,1);

INSERT INTO match (id_owners, id_guests, score_owners, score_guests, id_tournament, match_date)
VALUES
(1,2,5,0,1,'1999-01-08'),
(2,1,0,5,1,'1999-01-09'),
(2,1,2,3,2,'2020-01-01');

INSERT INTO coach (id, id_team, surname, "name", age, experience)
VALUES
(1,1,'FirstCoach','Name',55,20),
(2,2,'SecondCoach','NameMore',64,30);

INSERT INTO news (id, "name", content)
VALUES
(1,'FirstNews', 'GoalGOALgoal'),
(2,'SecondNews', 'MissedMISS');

INSERT INTO team_news (id_news, id_team)
VALUES
(1,2),
(1,1),
(2,1);

INSERT INTO player (surname, "name", height, weight, age)
VALUES
('Ovechkin', 'Alex', 188, 107, 34),
('Plante', 'Jacques', 176, 105, 23),
('Yzerman', 'Steve', 186, 95, 28),
('Sawchuk', 'Terry', 184, 86, 29),
('Béliveau', 'Jean', 192, 105, 24),
('Maurice', 'Richard', 183, 110, 26),
('Mario', 'Lemieux', 179, 89, 27),
('Orr', 'Bobby', 184, 88, 28),
('Gretzky', 'Wayne', 183, 87, 35),
('Howe', 'Gordie', 188, 97, 37);

INSERT INTO player_team (id_player, id_team)
VALUES
(1,1),
(1,2),
(2,1),
(3,2);

INSERT INTO event ("name", content)
VALUES
('FirstEvent', 'GoalGOALgoal'),
('SecondEvent', 'MissedMISS');

INSERT INTO player_event (id_event, id_player)
VALUES
(1,2),
(2,2),
(1,3);

INSERT INTO tournament_result (id_team, id_tournament, place, points, wins,
                             loses, draw, goals_missed, goals, missed)
VALUES
(1,1,1,9,3,0,0,15,18,3),
(2,1,2,4,1,0,0,-15,3,18),
(2,2,2,4,1,0,0,-15,3,18);