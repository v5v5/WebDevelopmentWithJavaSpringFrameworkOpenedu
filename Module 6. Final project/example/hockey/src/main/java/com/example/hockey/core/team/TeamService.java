package com.example.hockey.core.team;

import com.example.hockey.core.team.converter.TeamToTeamViewConverter;
import com.example.hockey.core.team.web.TeamBaseReq;
import com.example.hockey.core.team.web.TeamView;
import com.example.hockey.error.EntityNotFoundException;
import com.example.hockey.util.MessageUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepo teamRepo;
    private final TeamToTeamViewConverter teamToTeamViewConverter;
    private final MessageUtil messageUtil;

    public TeamService(TeamRepo teamRepo,
                       TeamToTeamViewConverter teamToTeamViewConverter,
                       MessageUtil messageUtil) {
        this.teamRepo = teamRepo;
        this.teamToTeamViewConverter = teamToTeamViewConverter;
        this.messageUtil = messageUtil;
    }

    public TeamView getTeam(Long id) {
        Team team = findTeamOrThrow(id);
        return teamToTeamViewConverter.convert(team);
    }

    public Team findTeamOrThrow(Long id) {
        return teamRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("team.NotFound", id)));
    }

    public Page<TeamView> findAllTeam(Pageable pageable){
        Page<Team> teams = teamRepo.findAll(pageable);
        List<TeamView> teamViews = new ArrayList<>();
        teams.forEach(team -> {
            TeamView teamView = teamToTeamViewConverter.convert(team);
            teamViews.add(teamView);
        });
        return new PageImpl<>(teamViews, pageable, teams.getTotalElements());
    }

    public TeamView create(TeamBaseReq req) {
        Team team = new Team();
        this.prepare(team,req);
        Team teamSave = teamRepo.save(team);
        return teamToTeamViewConverter.convert(teamSave);
    }

    @Transactional
    public void delete(Long id) {
        try {
            teamRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("team.NotFound", id));
        }
    }

    public TeamView update(Team team, TeamBaseReq req){
        Team newTeam = this.prepare(team,req);
        Team tournamentSave = teamRepo.save(newTeam);
        return teamToTeamViewConverter.convert(tournamentSave);
    }

    public Team prepare(Team team, TeamBaseReq teamBaseReq){
        team.setName(teamBaseReq.getName());
        team.setNumPlayers(teamBaseReq.getNumPlayers());
        return team;
    }
}
