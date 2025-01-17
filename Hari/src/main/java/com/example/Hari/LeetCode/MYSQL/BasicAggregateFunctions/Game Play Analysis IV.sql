--Table: Activity
--
--+--------------+---------+
--| Column Name  | Type    |
--+--------------+---------+
--| player_id    | int     |
--| device_id    | int     |
--| event_date   | date    |
--| games_played | int     |
--+--------------+---------+
--(player_id, event_date) is the primary key (combination of columns with unique values) of this table.
--This table shows the activity of players of some games.
--Each row is a record of a player who logged in and played a number of games (possibly 0) before logging out on someday using some device.
--
--
--Write a solution to report the fraction of players that logged in again on the day after the day they first logged in, rounded to 2 decimal places. In other words, you need to count the number of players that logged in for at least two consecutive days starting from their first login date, then divide that number by the total number of players.
--
--The result format is in the following example.
--
--
--
--Example 1:
--
--Input:
--Activity table:
--+-----------+-----------+------------+--------------+
--| player_id | device_id | event_date | games_played |
--+-----------+-----------+------------+--------------+
--| 1         | 2         | 2016-03-01 | 5            |
--| 1         | 2         | 2016-03-02 | 6            |
--| 2         | 3         | 2017-06-25 | 1            |
--| 3         | 1         | 2016-03-02 | 0            |
--| 3         | 4         | 2018-07-03 | 5            |
--+-----------+-----------+------------+--------------+
--Output:
--+-----------+
--| fraction  |
--+-----------+
--| 0.33      |
--+-----------+
--Explanation:
--Only the player with id 1 logged back in after the first day he had logged in so the answer is 1/3 = 0.33


# Write your MySQL query statement below
SELECT
 ROUND(
    COUNT(A1.player_id)
    / (SELECT COUNT(DISTINCT A3.player_id) FROM Activity A3)
  , 2) AS fraction
FROM
  Activity A1
WHERE
  (A1.player_id, DATE_SUB(A1.event_date, INTERVAL 1 DAY)) IN (
    SELECT
      A2.player_id,
      MIN(A2.event_date)
    FROM
      Activity A2
    GROUP BY
      A2.player_id
  );
















--
--Complete Explanation:
--Subquery:
--
--It returns the earliest event date for each player, which is the minimum event date for each player_id.
--DATE_SUB(A1.event_date, INTERVAL 1 DAY):
--
--In the outer query, for each row in the Activity table (A1), the query checks if there exists an earlier event date (i.e., one day before the current event_date).
--The condition (A1.player_id, DATE_SUB(A1.event_date, INTERVAL 1 DAY)) IN (...) ensures that the current event is one day after the player’s first event.
--COUNT(A1.player_id):
--
--The outer query counts how many players have an event that is exactly one day after their earliest event.
--Total Unique Players:
--
--The total number of unique players is calculated using the subquery (SELECT COUNT(DISTINCT A3.player_id) FROM Activity A3), which counts distinct player_ids in the Activity table.
--Fraction Calculation:
--
--The fraction is the ratio of players who have a consecutive event (1 day after their earliest event) to the total number of unique players.
--The ROUND(..., 2) rounds the result to two decimal places.
