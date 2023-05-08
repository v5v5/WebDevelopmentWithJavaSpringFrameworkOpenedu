CREATE SEQUENCE tournament_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE tournament (
  id int8 NOT NULL DEFAULT nextval('tournament_id_seq'),
  country VARCHAR (255),
  name VARCHAR (255),
  PRIMARY KEY (id));


CREATE SEQUENCE team_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE team (
  id int8 NOT NULL DEFAULT nextval('team_id_seq'),
  name VARCHAR(255),
  players int4,
  PRIMARY KEY(id));


CREATE TABLE tournament_team (
  id_tournament int8 NOT NULL ,
  id_team int8 NOT NULL ,
  PRIMARY KEY (id_team, id_tournament));


CREATE SEQUENCE match_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE match (
  id int8 NOT NULL DEFAULT nextval('match_id_seq'),
  match_date TIMESTAMP,
  score_guests int4,
  score_owners int4,
  id_guests int8 NOT NULL,
  id_owners int8 NOT NULL,
  id_tournament int8 NOT NULL,
  PRIMARY KEY (id));


CREATE SEQUENCE coach_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE coach (
  id int8 NOT NULL DEFAULT nextval('coach_id_seq'),
  age int4,
  experience int4,
  name VARCHAR(255),
  surname VARCHAR(255),
  id_team int8,
  PRIMARY KEY (id));


CREATE SEQUENCE news_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE news (
  id int8 NOT NULL DEFAULT nextval('news_id_seq'),
  content TEXT,
  name VARCHAR(255),
  PRIMARY KEY (id));


CREATE TABLE team_news (
  id_news int8 NOT NULL,
  id_team int8 NOT NULL,
  PRIMARY KEY (id_news, id_team));


CREATE SEQUENCE player_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE player (
  id int8 NOT NULL DEFAULT nextval('player_id_seq'),
  age int4,
  height int4,
  name VARCHAR(255),
  surname VARCHAR(255),
  weight int4,
  PRIMARY KEY (id));


CREATE TABLE player_team (
  id_player int8 NOT NULL,
  id_team int8 NOT NULL,
  PRIMARY KEY (id_player, id_team));


CREATE SEQUENCE event_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE event (
  id int8 NOT NULL DEFAULT nextval('event_id_seq'),
  content TEXT,
  name VARCHAR(255),
  PRIMARY KEY (id));


CREATE TABLE player_event (
  id_event int8 NOT NULL,
  id_player int8 NOT NULL,
  PRIMARY KEY (id_event, id_player));


CREATE SEQUENCE tournament_result_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE tournament_result (
  id int8 NOT NULL DEFAULT nextval('tournament_result_id_seq'),
  draw int4,
  goals int4,
  goals_missed int4,
  loses int4,
  missed int4,
  place int4,
  points int4,
  wins int4,
  id_team int8,
  id_tournament int8,
  PRIMARY KEY (id));

ALTER TABLE if EXISTS tournament_team
ADD CONSTRAINT team_fk
FOREIGN KEY (id_team) REFERENCES team;

ALTER TABLE if EXISTS tournament_team
ADD CONSTRAINT tournament_fk
FOREIGN KEY (id_tournament) REFERENCES tournament;

ALTER TABLE if EXISTS match
ADD CONSTRAINT guest_fk
FOREIGN KEY (id_guests) REFERENCES team;

ALTER TABLE if EXISTS match
ADD CONSTRAINT owner_fk
FOREIGN KEY(id_owners) REFERENCES team;

ALTER TABLE if EXISTS match
ADD CONSTRAINT  tournament_fk
FOREIGN KEY (id_tournament) REFERENCES tournament;

ALTER TABLE if EXISTS coach
ADD CONSTRAINT  coach_fk
FOREIGN KEY (id_team) REFERENCES team;

ALTER TABLE if EXISTS team_news
ADD CONSTRAINT  team_fk
FOREIGN KEY (id_team) REFERENCES team;

ALTER TABLE if EXISTS team_news
ADD CONSTRAINT  news_fk
FOREIGN KEY (id_news) REFERENCES news;

ALTER TABLE if EXISTS player_team
ADD CONSTRAINT  team_fk
FOREIGN KEY (id_team) REFERENCES team;

ALTER TABLE if EXISTS player_team
ADD CONSTRAINT  player_fk
FOREIGN KEY (id_player) REFERENCES player;

ALTER TABLE if EXISTS tournament_result
ADD CONSTRAINT  team_fk
FOREIGN KEY (id_team) REFERENCES team;

ALTER TABLE if EXISTS tournament_result
ADD CONSTRAINT  tournament_fk
FOREIGN KEY (id_tournament) REFERENCES tournament;

ALTER TABLE if EXISTS tournament_result
ADD CONSTRAINT tournament_team_u
 UNIQUE (id_tournament,id_team);