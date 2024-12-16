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


-- ************AGGREGATE FUNCTIONS **************
-- Query a count of the number of cities in CITY having a Population larger than 100000.
select count(*) from city where population > 100000;

-- Query the total population of all cities in CITY where District is California.
SELECT SUM(population)
FROM CITY
WHERE DISTRICT = 'California';


--Query the average population of all cities in CITY where District is California.
SELECT AVG(population) FROM CITY WHERE DISTRICT = 'California'


---Query the average population for all cities in CITY, rounded down to the nearest integer.
SELECT ROUND(AVG(population))
FROM CITY;


--Query the sum of the populations for all Japanese cities in CITY. The COUNTRYCODE for Japan is JPN.
SELECT SUM(POPULATION) FROM CITY WHERE COUNTRYCODE = 'JPN'


--Query the difference between the maximum and minimum populations in CITY.
SELECT MAX(POPULATION)- MIN(POPULATION) FROM CITY


--Samantha was tasked with calculating the average monthly salaries for all employees in the EMPLOYEES table, but did not realize her keyboard's  key was broken until after completing the calculation. She wants your help finding the difference between her miscalculation (using salaries with any zeros removed), and the actual average salary.
--Write a query calculating the amount of error (i.e.:  average monthly salaries), and round it up to the next integer.
SELECT CEIL(AVG(Salary) - AVG(REPLACE(Salary,'0',''))) FROM EMPLOYEES



SELECT
  c.company_code,
  c.founder,
  COUNT(DISTINCT lm.lead_manager_code),
  COUNT(DISTINCT sm.senior_manager_code),
  COUNT(DISTINCT m.manager_code),
  COUNT(DISTINCT e.employee_code)
FROM
  Company c
  LEFT JOIN LEAD_MANAGER lm on lm.company_code = c.company_code
  LEFT JOIN SENIOR_MANAGER sm on sm.company_code = c.company_code
  LEFT JOIN Manager m on m.company_code = lm.company_code
  LEFT JOIN Employee e on e.company_code = m.company_code
GROUP BY
  c.company_code,
  c.founder
order by
  c.company_code


--We define an employee's total earnings to be their monthly  worked, and the maximum total earnings to be the maximum total earnings for any employee in the Employee table. Write a query to find the maximum total earnings for all employees as well as the total number of employees who have maximum total earnings. Then print these values as  space-separated integers.
--The Employee table containing employee data for a company is described as follows:
SELECT
  (months * salary) AS max_earnings,
  COUNT(*) AS employee_count
FROM
  Employee
  -- WHERE (months * salary) = (
  --     SELECT MAX(months * salary)
  --     FROM Employee
  -- )
GROUP BY
  (months * salary)
ORDER BY
  (months * salary) DESC
LIMIT
  1;




--Query the following two values from the STATION table:
--The sum of all values in LAT_N rounded to a scale of  decimal places.
--The sum of all values in LONG_W rounded to a scale of  decimal places.
--The STATION table is described as follows:
SELECT ROUND(SUM(LAT_N),2),ROUND(SUM(LONG_W),2) FROM STATION

--Query the sum of Northern Latitudes (LAT_N) from STATION having values greater than  and less than . Truncate your answer to  decimal places.
--Input Format
--The STATION table is described as follows:
SELECT
  ROUND(
    SUM(LAT_N),
    4
  )
FROM
  STATION S
WHERE
  S.LAT_N > 38.7880
  AND S.LAT_N < 137.2345


--Query the greatest value of the Northern Latitudes (LAT_N) from STATION that is less than . Truncate your answer to  decimal places.

SELECT
  ROUND(
    MAX(LAT_N),
    4
  )
FROM
  STATION S
WHERE
  S.LAT_N < 137.2345
GROUP BY
  S.LAT_N
ORDER BY
  S.LAT_N DESC
LIMIT
  1


--Query the Western Longitude (LONG_W) for the largest Northern Latitude (LAT_N) in STATION that is less than . Round your answer to  decimal places.
SELECT ROUND(LONG_W,4) FROM STATION WHERE LAT_N < 137.2345 ORDER BY LAT_N DESC LIMIT 1

--Query the smallest Northern Latitude (LAT_N) from STATION that is greater than . Round your answer to  decimal places.
-- SELECT ROUND(LAT_N,4) FROM STATION WHERE LAT_N > 38.7780 ORDER BY LAT_N LIMIT 1
SELECT MIN(ROUND(LAT_N,4)) FROM STATION WHERE LAT_N > 38.7780



--Query the Western Longitude (LONG_W)where the smallest Northern Latitude (LAT_N) in STATION is greater than . Round your answer to  decimal places.
SELECT ROUND(LONG_W,4)
FROM STATION
WHERE LAT_N = (
    SELECT MIN(LAT_N)
    FROM STATION
    WHERE LAT_N > 38.7780
);

--
--Consider  and  to be two points on a 2D plane.
-- happens to equal the minimum value in Northern Latitude (LAT_N in STATION).
-- happens to equal the minimum value in Western Longitude (LONG_W in STATION).
-- happens to equal the maximum value in Northern Latitude (LAT_N in STATION).
-- happens to equal the maximum value in Western Longitude (LONG_W in STATION).
--Query the Manhattan Distance between points  and  and round it to a scale of  decimal places.
SELECT ROUND(((MAX(LAT_N)-MIN(LAT_N))+(MAX(LONG_W)-MIN(LONG_W))),4)FROM STATION

--Consider  and  to be two points on a 2D plane where  are the respective minimum and maximum values of Northern Latitude (LAT_N) and  are the respective minimum and maximum values of Western Longitude (LONG_W) in STATION.
--Query the Euclidean Distance between points  and  and format your answer to display  decimal digits.
SELECT ROUND(SQRT(POWER(MAX(LAT_N) - MIN(LAT_N), 2) + POWER(MAX(LONG_W) - MIN(LONG_W), 2)), 4)
FROM STATION;

--A median is defined as a number separating the higher half of a data set from the lower half. Query the median of the Northern Latitudes (LAT_N) from STATION and round your answer to  decimal places.
SELECT ROUND( AVG(lat_n), 4 ) AS median
FROM ( SELECT lat_n, ROW_NUMBER() OVER ( ORDER BY lat_n ) AS rn FROM station ) AS subq
 WHERE rn = ( SELECT CEIL( ( COUNT(rn)+ 1 )/ 2 ) FROM station ) OR rn = ( SELECT FLOOR( ( COUNT(rn)+ 1 )/ 2 ) FROM station )

--Given the CITY and COUNTRY tables, query the sum of the populations of all cities where the CONTINENT is 'Asia'.
--Note: CITY.CountryCode and COUNTRY.Code are matching key columns.
SELECT SUM(C1.POPULATION)
FROM CITY C1
LEFT JOIN COUNTRY C2 ON C1.COUNTRYCODE = C2.CODE
WHERE C2.CONTINENT = 'Asia';

--Given the CITY and COUNTRY tables, query the names of all cities where the CONTINENT is 'Africa'.
--Note: CITY.CountryCode and COUNTRY.Code are matching key columns.
SELECT c.name FROM CITY C LEFT JOIN COUNTRY T ON C.COUNTRYCODE = T.CODE WHERE T.CONTINENT ='Africa'



--Given the CITY and COUNTRY tables, query the names of all the continents (COUNTRY.Continent) and their respective average city populations (CITY.Population) rounded down to the nearest integer.
--Note: CITY.CountryCode and COUNTRY.Code are matching key columns.

SELECT
    C2.CONTINENT,
    FLOOR(AVG(C1.POPULATION)) AS AVG_CITY_POPULATION
FROM
    CITY C1
 JOIN
    COUNTRY C2
ON
    C1.COUNTRYCODE = C2.CODE
GROUP BY
    C2.CONTINENT;


Select H.hacker_id , H.name from Submissions S
Left Join Challenges C on S.challenge_id = C.challenge_id
Left Join Difficulty D on C.difficulty_level = D.difficulty_level
Left join Hackers H on S.hacker_id = H.hacker_id
where S.score = D.score
Group by H.hacker_id,H.name Having Count(D.score)>1 Order by Count(D.score) DESC , H.hacker_id;

-- Certificate 1 :-
SELECT s.roll_number,s.name FROM student_information s
LEFT JOIN faculty_information f1 ON f1.employee_id = s.advisor
WHERE
 (f1.gender = 'F' AND f1.salary > 20000)
    OR
    (f1.gender = 'M' AND f1.salary > 15000);

SELECT C.customer_id,C.name,CONCAT('+',c1.country_code,C.phone_number) FROM customers C
LEFT JOIN country_codes c1 ON c1.country = C.country
ORDER BY C.customer_id

-- Certificate 2
SELECT c.city_name,p.product_name ,ROUND(ii.line_total_price,2) FROM city c
JOIN customer c1 on c.id = c1.city_id
JOIN invoice i on c1.id=i.customer_id
JOIN invoice_item ii on i.id=ii.invoice_id
JOIN product p on ii.product_id = p.id
ORDER BY ii.line_total_price DESC, c.city_name DESC , p.product_name ASC


SELECT c.customer_name, i.total_price
FROM customer c
JOIN invoice i ON c.id = i.customer_id
WHERE i.total_price < (
    SELECT AVG(total_price)
    FROM invoice
)
AND i.total_price < (
    SELECT SUM(total_price) * 0.25
    FROM invoice
);





