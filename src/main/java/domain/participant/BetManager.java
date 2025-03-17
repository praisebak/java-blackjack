package domain.participant;

import domain.blackjackgame.BlackjackGame;
import java.util.LinkedHashMap;
import java.util.Map;

public class BetManager {

    public static Map<String, Double> blackjackBettingResult(BlackjackGame blackjackGame) {
        String dealerName = blackjackGame.dealerName();
        Map<String, Double> participantsEarnMoney = new LinkedHashMap<>(Map.of(dealerName, 0.0));
        BlackjackHands dealerHands = blackjackGame.dealerHands();
        for (String name : blackjackGame.playerNames()) {
            BlackjackHands playerHands = blackjackGame.playerHands(name);
            double playerProfit = playerProfit(blackjackGame.playerBet(name), playerHands, dealerHands);
            participantsEarnMoney.put(dealerName, dealerProfit(participantsEarnMoney, playerProfit, dealerName));
            participantsEarnMoney.put(name, playerProfit);
        }
        return participantsEarnMoney;
    }

    private static double dealerProfit(Map<String, Double> participantsEarnMoney, double playerProfit,
                                       String dealerName) {
        return participantsEarnMoney.get(dealerName) - playerProfit;
    }

    private static double playerProfit(BlackjackBet playerBet, BlackjackHands cardSum, BlackjackHands dealerSum) {
        return playerBet.calculateEarnMoney(cardSum, dealerSum) - playerBet.betMoney();
    }
}
