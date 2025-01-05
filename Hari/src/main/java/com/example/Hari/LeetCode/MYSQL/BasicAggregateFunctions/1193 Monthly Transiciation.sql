SELECT DATE_FORMAT(trans_date, '%Y-%m') AS month,
       country,
       (
           SELECT Count(*)
           FROM Transactions
           WHERE DATE_FORMAT(trans_date, '%Y-%m') = DATE_FORMAT(t.trans_date, '%Y-%m')
                 AND country = t.country
       ) AS trans_count,
       COUNT(*) AS approved_count,
       (
           SELECT SUM(amount)
           FROM Transactions
           WHERE DATE_FORMAT(trans_date, '%Y-%m') = DATE_FORMAT(t.trans_date, '%Y-%m')
                 AND country = t.country
       ) AS trans_total_amount,
       SUM(amount) AS approved_total_amount
FROM Transactions t
WHERE state = 'approved'
GROUP BY DATE_FORMAT(trans_date, '%Y-%m'),
         country;


SELECT
   DATE_FORMAT(trans_date, '%Y-%m') AS month,
    country,
    COUNT(id) AS trans_count,
    SUM(state = 'approved') AS approved_count,
    SUM(amount) AS trans_total_amount,
    SUM((state = 'approved') * amount) AS approved_total_amount
FROM
    Transactions
GROUP BY
    month, country;