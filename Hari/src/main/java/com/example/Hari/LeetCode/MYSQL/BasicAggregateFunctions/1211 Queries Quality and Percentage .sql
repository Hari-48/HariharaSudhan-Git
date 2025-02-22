



SELECT
    query_name,
    ROUND(AVG(rating / position), 2) AS quality,
    ROUND((sum(CASE WHEN rating < 3 THEN 1 else 0 END) * 100.0) / COUNT(*),2) AS poor_query_percentage
FROM
    Queries
GROUP BY
    query_name;
