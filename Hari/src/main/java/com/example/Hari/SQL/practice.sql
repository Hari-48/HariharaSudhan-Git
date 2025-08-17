

-- Show unique birth years from patients and order them by ascending.
select   distinct year(birth_date) from patients order by birth_date asc



-- Show unique first names from the patients table which only occurs once in the list.
--For example, if two or more people are named 'John' in the first_name column t
--hen don't include their name in the output list. If only 1 person is named 'Leo' then include them in the output.
select  first_name from patients group by first_name having count(*) = 1

--  Show patient_id and first_name from patients where their first_name start and ends with 's' and is at least 6 characters long.
select patient_id,first_name from patients where first_name like 's%s' and len(first_name) >= 6


-- Show patient_id, first_name, last_name from patients whos diagnosis is 'Dementia'. Primary diagnosis is stored in the admissions table.
select p.patient_id,p.first_name,p.last_name from patients p
join
admissions a on a.patient_id = p.patient_id
where a.diagnosis = 'Dementia'
-- select patient_id,first_name,last_name from patients where patient_id in (
--select patient_id from admissions where diagnosis = 'Dementia')



-- Display every patient's first_name.
--Order the list by the length of each name and then by alphabetically.
select first_name  from patients order by  len(first_name) asc , first_name asc


--Show the total amount of male patients and the total amount of female patients in the patients table.
--Display the two results in the same row.
select
(
select count(gender)from patients
group by gender having gender = 'M')  as male_count  ,
(
select count(gender) as female_count from patients
group by gender having gender = 'F'
 ) as  female_count;


--Show first and last name, allergies from patients which have allergies to either 'Penicillin' or 'Morphine'.
-- Show results ordered ascending by allergies then by first_name then by last_name.
select first_name , last_name, allergies from patients where allergies = 'Penicillin' or allergies = 'Morphine'
order by allergies , first_name , last_name

--Show patient_id, diagnosis from admissions. Find patients admitted multiple times for the same diagnosis.
select patient_id ,diagnosis from admissions a group  by diagnosis , patient_id having count(*) > 1


--Show the city and the total number of patients in the city.
--Order from most to least patients and then by city name ascending.
select city ,count(*) as num_patients from patients group by city  order by num_patients desc , city asc

-- show first name, last name and role of every person that is either patient or doctor. he roles are either "Patient" or "Doctor"
select first_name ,last_name , "Patient" as role from patients
union all
select first_name ,last_name , "Doctor" as role from doctors




-- Show all allergies ordered by popularity. Remove NULL values from query.

SELECT p.allergies,
       COUNT(*) AS total_diagnosis
FROM patients p
WHERE p.allergies IS NOT NULL
GROUP BY p.allergies
ORDER BY total_diagnosis DESC;



--Show all patient's first_name, last_name, and birth_date who were born in the 1970s decade. Sort the list starting from the earliest birth_date.
SELECT first_name,
       last_name,
       birth_date
FROM patients
where year(birth_date)
between 1970 AND 1979
order by birth_date asc


--We want to display each patient's full name in a single column. Their last_name in all upper letters must appear first,
--then first_name in all lower case letters. Separate the last_name and first_name with a comma.
--Order the list by the first_name in decending order
--EX: SMITH,jane

select concat(upper(last_name), ',', lower(first_name)) as new_name_format
from patients
order by first_name desc




--Show the province_id(s), sum of height; where the total sum of its patient's height is greater than or equal to 7,000.
SELECT
  province_id,
  SUM(height) AS sum_height
FROM patients
GROUP BY province_id
HAVING sum_height >= 7000







