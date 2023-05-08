# hockey

# run postgres in docker on localhost:5432 with no password, db hockey
docker run -d -p 5432:5432 -e POSTGRES_DB=hockey -e POSTGRES_HOST_AUTH_METHOD=trust postgres

# run application
run HockeyApplication

# get information about team 1
http://localhost:8080/hockey/team/1

# get information about players
http://localhost:8080/hockey/player

# get information about player 1
http://localhost:8080/hockey/player/1

# get information about all tournaments
http://localhost:8080/hockey/tournament

