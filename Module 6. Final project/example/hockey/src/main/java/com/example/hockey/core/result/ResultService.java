package com.example.hockey.core.result;

import com.example.hockey.core.result.converter.ResultToResultViewConverter;
import com.example.hockey.core.result.web.ResultBaseReq;
import com.example.hockey.core.result.web.ResultView;
import com.example.hockey.core.team.TeamRepo;
import com.example.hockey.core.tournament.TournamentRepo;
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
public class ResultService{

    private final ResultRepo resultRepo;
    private final ResultToResultViewConverter resultToResultViewConverter;
    private final TeamRepo teamRepo;
    private final TournamentRepo tournamentRepo;
    private final MessageUtil messageUtil;

    public ResultService(ResultRepo resultRepo,
                         ResultToResultViewConverter resultToResultViewConverter,
                         TeamRepo teamRepo, TournamentRepo tournamentRepo,
                         MessageUtil messageUtil) {
        this.resultRepo = resultRepo;
        this.resultToResultViewConverter = resultToResultViewConverter;
        this.teamRepo = teamRepo;
        this.tournamentRepo = tournamentRepo;
        this.messageUtil = messageUtil;
    }

    public Result findResultOrThrow(Long id) {
       return resultRepo.findById(id)
               .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("result.NotFound", id)));
    }

    public ResultView getResult(Long id) {
        Result result = findResultOrThrow(id);
        return resultToResultViewConverter.convert(result);
    }

    public Page<ResultView> findAllResult(Pageable pageable) {
        Page<Result> results = resultRepo.findAll(pageable);
        List<ResultView> resultViews = new ArrayList<>();
        results.forEach(result -> {
            ResultView resultView = resultToResultViewConverter.convert(result);
            resultViews.add(resultView);
        });
        return new PageImpl<>(resultViews, pageable, results.getTotalElements());
    }

    public ResultView create(ResultBaseReq req) {
        Result result = new Result();
        this.prepare(result,req);
        Result resultSave = resultRepo.save(result);
        return resultToResultViewConverter.convert(resultSave);
    }

    @Transactional
    public void delete(Long id) {
        try {
            resultRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("result.NotFound", id));
        }
    }

    public ResultView update(Result result, ResultBaseReq req) {
        Result newResult = this.prepare(result,req);
        Result resultSave = resultRepo.save(newResult);
        return resultToResultViewConverter.convert(resultSave);
    }

    public Result prepare(Result result, ResultBaseReq resultBaseReq){
        result.setDraw(resultBaseReq.getDraw());
        result.setGoals(resultBaseReq.getGoals());
        result.setGoalsMissed(resultBaseReq.getGoalsMissed());
        result.setLoses(resultBaseReq.getLoses());
        result.setMissed(resultBaseReq.getMissed());
        result.setPlace(resultBaseReq.getPlace());
        result.setWins(resultBaseReq.getWins());
        result.setPoints(resultBaseReq.getPoints());
        result.setTeam(teamRepo.getOne(resultBaseReq.getTeamId()));
        result.setTournament(tournamentRepo.getOne(resultBaseReq.getTournamentId()));
        return result;
    }
}
