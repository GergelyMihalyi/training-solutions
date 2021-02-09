package collectionslist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottery {

    public List<Integer> shuffledBallPool(int ballCount){
        List<Integer> ballPool = new ArrayList<>();
        for(int i = 1 ; i <= ballCount;  i++){
            ballPool.add(i);
        }
        Collections.shuffle(ballPool);
        return ballPool;
    }

    public List<Integer> selectWinningNumber(int lotteryType, int ballCount){
        if(ballCount <= lotteryType){
            throw new IllegalArgumentException("Wrong number");
        }
        List<Integer> ballPool = shuffledBallPool(ballCount);
        List<Integer> winningNumber =  ballPool.subList(0,lotteryType);
        Collections.sort(winningNumber);
        return winningNumber;
    }

    public static void main(String[] args) {
        System.out.println(new Lottery().selectWinningNumber(5,90));
    }
}
