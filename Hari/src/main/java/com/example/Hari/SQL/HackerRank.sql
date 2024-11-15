SELECT DISTINCT CITY FROM STATION
WHERE LEFT(CITY, 1) NOT IN ('A','E','I','O','U')
OR RIGHT(CITY, 1) NOT IN ('A','E','I','O','U');


SELECT DISTINCT CITY
FROM STATION
WHERE LEFT(CITY, 1) NOT IN ('A','E','I','O','U')
AND RIGHT(CITY, 1) NOT IN ('A','E','I','O','U');


--Query the Name of any student in STUDENTS who scored higher than  Marks.
--Order your output by the last three characters of each name.
--If two or more students both have names ending in the same last three characters (i.e.: Bobby, Robby, etc.),
--secondary sort them by ascending ID.
SELECT NAME FROM STUDENTS
WHERE MARKS > 75 ORDER BY RIGHT(NAME, 3) , ID ASC;


--Write a query that prints a list of employee names (i.e.: the name attribute) from the Employee
--table in alphabetical order.

SELECT NAME FROM EMPLOYEE ORDER BY NAME ASC;

--Write a query that prints a list of employee names (i.e.: the name attribute) for employees in Employee
-- having a salary greater than  per month who have been employees for less than  months.
-- Sort your result by ascending employee_id.

SELECT NAME FROM EMPLOYEE
WHERE SALARY > 2000 AND MONTHS < 10
ORDER BY EMPLOYEE_ID ASC;



--P(R) represents a pattern drawn by Julia in R rows. The following pattern represents P(5):
--SET @row := 0;
--SELECT REPEAT('* ', @row := @row + 1) AS pattern
--FROM information_schema.tables
--LIMIT 20;

--Equilateral: It's a triangle with  sides of equal length.
--Isosceles: It's a triangle with  sides of equal length.
--Scalene: It's a triangle with  sides of differing lengths.
--Not A Triangle: The given values of A, B, and C don't form a triangle.
SELECT CASE WHEN A + B <= C OR A + C <= B OR B + C <= A THEN 'Not A Triangle'
WHEN A = B AND B = C THEN 'Equilateral' WHEN A = B OR A = C OR B = C
THEN 'Isosceles' ELSE 'Scalene' END AS triangle_Type FROM TRIANGLES;


SELECT CONCAT(NAME, '(', SUBSTRING(occupation, 1, 1), ')') AS formatted_output
FROM OCCUPATIONS
UNION
SELECT CONCAT('There are a total of ', COUNT(*), ' ', LOWER(occupation), 's.') AS formatted_output
FROM OCCUPATIONS
GROUP BY occupation
ORDER BY formatted_output;


SELECT
    doctor_names.NAME AS Doctor,
    professor_names.NAME AS Professor,
    singer_names.NAME AS Singer,
    actor_names.NAME AS Actor
FROM
    (SELECT NAME, ROW_NUMBER() OVER (ORDER BY NAME) AS row_num FROM OCCUPATIONS WHERE OCCUPATION = 'DOCTOR') AS doctor_names
LEFT JOIN
    (SELECT NAME, ROW_NUMBER() OVER (ORDER BY NAME) AS row_num FROM OCCUPATIONS WHERE OCCUPATION = 'PROFESSOR') AS professor_names
    ON doctor_names.row_num = professor_names.row_num
LEFT JOIN
    (SELECT NAME, ROW_NUMBER() OVER (ORDER BY NAME) AS row_num FROM OCCUPATIONS WHERE OCCUPATION = 'SINGER') AS singer_names
    ON doctor_names.row_num = singer_names.row_num
LEFT JOIN
    (SELECT NAME, ROW_NUMBER() OVER (ORDER BY NAME) AS row_num FROM OCCUPATIONS WHERE OCCUPATION = 'ACTOR') AS actor_names
    ON doctor_names.row_num = actor_names.row_num

UNION ALL
-- This section handles the cases where there are more rows for other occupations than there are doctors.
SELECT
    NULL AS Doctor,
    professor_names.NAME AS Professor,
    singer_names.NAME AS Singer,
    actor_names.NAME AS Actor
FROM
    (SELECT NAME, ROW_NUMBER() OVER (ORDER BY NAME) AS row_num FROM OCCUPATIONS WHERE OCCUPATION = 'PROFESSOR') AS professor_names
LEFT JOIN
    (SELECT NAME, ROW_NUMBER() OVER (ORDER BY NAME) AS row_num FROM OCCUPATIONS WHERE OCCUPATION = 'SINGER') AS singer_names
    ON professor_names.row_num = singer_names.row_num
LEFT JOIN
    (SELECT NAME, ROW_NUMBER() OVER (ORDER BY NAME) AS row_num FROM OCCUPATIONS WHERE OCCUPATION = 'ACTOR') AS actor_names
    ON professor_names.row_num = actor_names.row_num
WHERE professor_names.row_num > (SELECT COUNT(*) FROM OCCUPATIONS WHERE OCCUPATION = 'DOCTOR');







