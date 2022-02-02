package com.example.test2_gamification.repository;

import com.example.test2_gamification.domain.LeaderBoardRow;
import com.example.test2_gamification.domain.ScoreCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreCardRepository extends CrudRepository<ScoreCard, Long> {
    /*
    * scorecard의 점수를 합해 사용자의 총 점수를 조회
    * @param userId 총 점수를 조회하고자 하는 사용자의 id
    * @return 사용자의 총 점수
    * */
    @Query("SELECT SUM(s.score) " +
        "FROM com.example.test2_gamification.domain.ScoreCard s " +
            "WHERE s.userId = :userId GROUP BY s.userId")
    int getTotalScoreForUser(@Param("userId") final Long userId);

    /*
    * 사용자와 사용자의 총 점수를 나타내는 {@link LeaderBoardRow} 리스트를 조회
    * @return 높은 점수 순으로 정렬된 리더보드
    * */
    @Query("SELECT NEW com.example.test2_gamification.domain.LeaderBoardRow(s.userId, SUM(s.score)) "
    + "FROM com.example.test2_gamification.domain.ScoreCard s "
    + "GROUP BY s.userId ORDER BY SUM(s.score) DESC")
    List<LeaderBoardRow> findFirst10();

    /*
    * 사용자의 모든 ScoreCard를 조회
    * @param userId 사용자 id
    * @return 특정 사용자의 최근순으로 정렬된 ScoreCard 리스트
    * */
    List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(final Long userId);
}
