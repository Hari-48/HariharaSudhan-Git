-- Show unique birth years from patients and order them by ascending.
select distinct year(birth_date) from patients order by birth_date asc
------------------------------------------------------------------------------------------------------------------------
-- Show unique first names from the patients table which only occurs once in the list.
--For example, if two or more people are named 'John' in the first_name column t
--hen don't include their name in the output list. If only 1 person is named 'Leo' then include them in the output.
select  first_name from patients group by first_name having count(*) = 1
------------------------------------------------------------------------------------------------------------------------
--  Show patient_id and first_name from patients where their first_name start and ends with 's'
--and is at least 6 characters long.
select patient_id,first_name from patients
where first_name like 's%s' and len(first_name) >= 6
------------------------------------------------------------------------------------------------------------------------
-- Show patient_id, first_name, last_name from patients whos diagnosis is 'Dementia'.
--Primary diagnosis is stored in the admissions table.
select p.patient_id,p.first_name,p.last_name from patients p
join
admissions a on a.patient_id = p.patient_id
where a.diagnosis = 'Dementia'
-- select patient_id,first_name,last_name from patients where patient_id in (
--select patient_id from admissions where diagnosis = 'Dementia')
-----------------------------------------------------------------------------------------------------------------------
-- Display every patient's first_name.
--Order the list by the length of each name and then by alphabetically.
select first_name  from patients order by  len(first_name) asc , first_name asc

------------------------------------------------------------------------------------------------------------------------
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

------------------------------------------------------------------------------------------------------------------------
--Show first and last name, allergies from patients which have allergies to either 'Penicillin' or 'Morphine'.
-- Show results ordered ascending by allergies then by first_name then by last_name.
select first_name , last_name, allergies from patients where allergies = 'Penicillin' or allergies = 'Morphine'
order by allergies , first_name , last_name
------------------------------------------------------------------------------------------------------------------------
--Show patient_id, diagnosis from admissions. Find patients admitted multiple times for the same diagnosis.
select patient_id ,diagnosis from admissions a group  by diagnosis ,
 patient_id having count(*) > 1
------------------------------------------------------------------------------------------------------------------------
--Show the city and the total number of patients in the city.
--Order from most to least patients and then by city name ascending.
select city ,count(*) as num_patients from patients group by city
order by num_patients desc , city asc
------------------------------------------------------------------------------------------------------------------------
-- show first name, last name and role of every person that is either patient or doctor.
-- he roles are either "Patient" or "Doctor"
select first_name ,last_name , "Patient" as role from patients
union all
select first_name ,last_name , "Doctor" as role from doctors
------------------------------------------------------------------------------------------------------------------------
-- Show all allergies ordered by popularity. Remove NULL values from query.
SELECT p.allergies,
       COUNT(*) AS total_diagnosis
FROM patients p
WHERE p.allergies IS NOT NULL
GROUP BY p.allergies
ORDER BY total_diagnosis DESC;
------------------------------------------------------------------------------------------------------------------------
--Show all patient's first_name, last_name, and birth_date who were born in the 1970s decade.
--Sort the list starting from the earliest birth_date.
SELECT first_name,
       last_name,
       birth_date
FROM patients
where year(birth_date)
between 1970 AND 1979
order by birth_date asc
------------------------------------------------------------------------------------------------------------------------
--We want to display each patient's full name in a single column. Their last_name in all upper letters must appear first,
--then first_name in all lower case letters. Separate the last_name and first_name with a comma.
--Order the list by the first_name in decending order
--EX: SMITH,jane
select concat(upper(last_name), ',', lower(first_name)) as new_name_format
from patients
order by first_name desc
------------------------------------------------------------------------------------------------------------------------
--Show the province_id(s), sum of height; where the total sum of its patient's height is greater than or equal to 7,000.
SELECT
  province_id,
  SUM(height) AS sum_height
FROM patients
GROUP BY province_id
HAVING sum_height >= 7000
-----------------------------------------------------------------------------------------------------------------------
--Show the difference between the largest weight and smallest weight for patients with the last name 'Maroni'
SELECT
  (MAX(weight) - MIN(weight)) AS weight_delta
FROM patients
WHERE last_name = 'Maroni';
-----------------------------------------------------------------------------------------------------------------------
--Show all of the days of the month (1-31) and how many admission_dates occurred on that day.
--Sort by the day with most admissions to least admissions.
SELECT
  DAY(admission_date) AS day_number,
  COUNT(*) AS number_of_admissions
FROM admissions
GROUP BY day_number
ORDER BY number_of_admissions DESC
-----------------------------------------------------------------------------------------------------------------------
--Show all columns for patient_id 542's most recent admission_date.
select a.patient_id,a.admission_date,a.discharge_date,a.diagnosis,a.attending_doctor_id from Patients p
join admissions a on a.patient_id = p.patient_id
where p.patient_id =542 order by a.admission_date desc limit 1
------------------------------------------------------------------------------------------------------------------------
SELECT *
FROM admissions
WHERE patient_id = 542
GROUP BY patient_id
HAVING
  admission_date = MAX(admission_date);
-----------------------------------------------------------------------------------------------------------------------
--Show patient_id, attending_doctor_id, and diagnosis for admissions that match one of the two criteria:
--1. patient_id is an odd number and attending_doctor_id is either 1, 5, or 19.
--2. attending_doctor_id contains a 2 and the length of patient_id is 3 characters.
SELECT
  patient_id,
  attending_doctor_id,
  diagnosis
FROM admissions
WHERE
  (
    attending_doctor_id IN (1, 5, 19)
    AND patient_id % 2 != 0
  )
  OR
  (
    attending_doctor_id LIKE '%2%'
    AND len(patient_id) = 3
  )
------------------------------------------------------------------------------------------------------------------------
--Show first_name, last_name, and the total number of admissions attended for each doctor.
--Every admission has been attended by a doctor.
SELECT
  first_name,
  last_name,
  count(*) as admissions_total
from admissions a
  join doctors ph on ph.doctor_id = a.attending_doctor_id
group by attending_doctor_id
------------------------------------------------------------------------------------------------------------------------
--For each doctor, display their id, full name, and the first and last admission date they attended.
select
  doctor_id,
  first_name || ' ' || last_name as full_name,
  min(admission_date) as first_admission_date,
  max(admission_date) as last_admission_date
from admissions a
  join doctors ph on a.attending_doctor_id = ph.doctor_id
group by doctor_id;
-----------------------------------------------------------------------------------------------------------------------
--Display the total amount of patients for each province. Order by descending.
SELECT
  province_name,
  COUNT(*) as patient_count
FROM patients pa
  join province_names pr on pr.province_id = pa.province_id
group by pr.province_id
order by patient_count desc;
-----------------------------------------------------------------------------------------------------------------------
--For every admission, display the patient's full name, their admission diagnosis, and their doctor's full n
--ame who diagnosed their problem.
SELECT
  CONCAT(patients.first_name, ' ', patients.last_name) as patient_name,
  diagnosis,
  CONCAT(doctors.first_name,' ',doctors.last_name) as doctor_name
FROM patients
  JOIN admissions ON admissions.patient_id = patients.patient_id
  JOIN doctors ON doctors.doctor_id = admissions.attending_doctor_id;
-----------------------------------------------------------------------------------------------------------------------
--display the first name, last name and number of duplicate patients based on their first name and last name.
--Ex: A patient with an identical name can be considered a duplicate.
select
  first_name,
  last_name,
  count(*) as num_of_duplicates
from patients
group by
  first_name,
  last_name
having count(*) > 1
_______________________________________________________________________________________________________________________
--Display patient's full name,
--height in the units feet rounded to 1 decimal,
--weight in the unit pounds rounded to 0 decimals,
--birth_date,
--gender non abbreviated.
--
--Convert CM to feet by dividing by 30.48.
--Convert KG to pounds by multiplying by 2.205.
select
    concat(first_name, ' ', last_name) AS 'patient_name',
    ROUND(height / 30.48, 1) as 'height "Feet"',
    ROUND(weight * 2.205, 0) AS 'weight "Pounds"', birth_date,
CASE
	WHEN gender = 'M' THEN 'MALE'
  ELSE 'FEMALE'
END AS 'gender_type'
from patients

________________________________________________________________________________________________________________________
--Display a single row with max_visits, min_visits, average_visits where the maximum,
-- minimum and average number of admissions per day is calculated. Average is rounded to 2 decimal places.
select
	max(number_of_visits) as max_visits,
	min(number_of_visits) as min_visits,
  round(avg(number_of_visits),2) as average_visits
from (
  select admission_date, count(*) as number_of_visits
  from admissions
  group by admission_date
)
------------------------------------------------------------------------------------------------------------------------
