package com.tubz.iplbe.data;

import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    @Override
    public Match process(final MatchInput matchInput) {
        final Match transformedMatch = new Match();
        transformedMatch.setId(Long.parseLong(matchInput.getId()));
        transformedMatch.setCity(matchInput.getCity());
        transformedMatch.setDate(LocalDate.parse(matchInput.getDate()));
        transformedMatch.setPlayerOfMatch(matchInput.getPlayer_of_match());
        transformedMatch.setVenue(matchInput.getVenue());

        // Set the team1 and team2 depending on the toss decision
        String firstInningsTeam, secondInningsTeam;

        if ("bat".equals(matchInput.getToss_decision())) {
            firstInningsTeam = matchInput.getToss_winner();
            secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2()
                    : matchInput.getTeam1();
        } else {
            secondInningsTeam = matchInput.getToss_winner();
            firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2()
                    : matchInput.getTeam1();
        }

        transformedMatch.setTeam1(firstInningsTeam);
        transformedMatch.setTeam2(secondInningsTeam);
        transformedMatch.setTossDecision(matchInput.getToss_decision());
        transformedMatch.setTossDecision(matchInput.getToss_decision());
        transformedMatch.setResult(matchInput.getResult());
        transformedMatch.setResultMargin(matchInput.getResult_margin());
        transformedMatch.setUmpire1(matchInput.getUmpire1());
        transformedMatch.setUmpire2(matchInput.getUmpire2());
        return transformedMatch;
    }

}
