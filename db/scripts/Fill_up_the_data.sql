USE projectCSC3350;

SET FOREIGN_KEY_CHECKS=0;

-- Insert Job Titles
INSERT INTO job_titles (job_title_id, job_title)
VALUES 
(100,'Software Manager'),
(101,'Software Architect'),
(102,'Software Engineer'),
(103,'Software Developer'),
(200,'Marketing Manager'),
(201,'Marketing Associate'),
(202,'Marketing Assistant'),
(900,'Chief Executive Officer'),
(901,'Chief Financial Officer'),
(902,'Chief Information Officer');

-- Insert Employees
INSERT INTO employees (empid, Fname, Lname, email, HireDate, Salary, SSN)
VALUES 
(1,'Snoopy', 'Beagle', 'Snoopy@example.com', '2022-08-01', 45000.00, '111-11-1111'),
(2,'Charlie', 'Brown', 'Charlie@example.com', '2022-07-01', 48000.00, '111-22-1111'),
(3,'Lucy', 'Doctor', 'Lucy@example.com', '2022-07-03', 55000.00, '111-33-1111'),
(4,'Peppermint', 'Patti', 'Peppermint@example.com', '2022-08-02', 98000.00, '111-44-1111'),
(5,'Linus', 'Blanket', 'Linus@example.com', '2022-09-01', 43000.00, '111-55-1111'),
(6,'PigPin', 'Dusty', 'PigPin@example.com', '2022-10-01', 33000.00, '111-66-1111'),
(7,'Scooby', 'Doo', 'Scooby@example.com', '1973-07-03', 78000.00, '111-77-1111'),
(8,'Shaggy', 'Rodgers', 'Shaggy@example.com', '1973-07-11', 77000.00, '111-88-1111'),
(9,'Velma', 'Dinkley', 'Velma@example.com', '1973-07-21', 82000.00, '111-99-1111'),
(10,'Daphne', 'Blake', 'Daphne@example.com', '1973-07-30', 59000.00, '111-00-1111'),
(11,'Bugs', 'Bunny', 'Bugs@example.com', '1934-07-01', 18000.00, '222-11-1111'),
(12,'Daffy', 'Duck', 'Daffy@example.com', '1935-04-01', 16000.00, '333-11-1111'),
(13,'Porky', 'Pig', 'Porky@example.com', '1935-08-12', 16550.00, '444-11-1111'),
(14,'Elmer', 'Fudd', 'Elmer@example.com', '1934-08-01', 15500.00, '555-11-1111'),
(15,'Marvin', 'Martian', 'Marvin@example.com', '1937-05-01', 28000.00, '777-11-1111'),
(16, 'Tom', 'Cat', 'Tom@example.com', '2000-01-01', 42000.00, '888-11-1111'),
(17, 'Jerry', 'Mouse', 'Jerry@example.com', '2000-02-15', 43000.00, '888-22-2222'),
(18, 'Tweety', 'Bird', 'Tweety@example.com', '2001-03-10', 37000.00, '888-33-3333'),
(19, 'Sylvester', 'Cat', 'Sylvester@example.com', '2002-04-05', 46000.00, '888-44-4444'),
(20, 'Road', 'Runner', 'Road@example.com', '2002-05-22', 49000.00, '888-55-5555'),
(21, 'Wile', 'Coyote', 'Wile@example.com', '2002-06-01', 51000.00, '888-66-6666'),
(22, 'Yogi', 'Bear', 'Yogi@example.com', '2003-07-04', 52000.00, '888-77-7777'),
(23, 'BooBoo', 'Bear', 'Booboo@example.com', '2003-07-10', 41500.00, '888-88-8888'),
(24, 'Fred', 'Flintstone', 'Fred@example.com', '1995-01-01', 54000.00, '999-11-1111'),
(25, 'Barney', 'Rubble', 'Barney@example.com', '1995-02-15', 50000.00, '999-22-2222'),
(26, 'Wilma', 'Flintstone', 'Wilma@example.com', '1995-03-10', 49000.00, '999-33-3333'),
(27, 'Betty', 'Rubble', 'Betty@example.com', '1995-04-05', 48000.00, '999-44-4444'),
(28, 'George', 'Jetson', 'George@example.com', '1996-05-01', 51000.00, '999-55-5555'),
(29, 'Jane', 'Jetson', 'Jane@example.com', '1996-05-20', 50500.00, '999-66-6666'),
(30, 'Judy', 'Jetson', 'Judy@example.com', '1996-06-15', 43000.00, '999-77-7777'),
(31, 'Elroy', 'Jetson', 'Elroy@example.com', '1996-07-01', 41000.00, '999-88-8888'),
(32, 'Popeye', 'Sailor', 'Popeye@example.com', '1980-01-01', 46000.00, '123-11-1111'),
(33, 'Olive', 'Oyl', 'Olive@example.com', '1980-02-01', 44000.00, '123-22-2222'),
(34, 'Bluto', 'Strong', 'Bluto@example.com', '1980-03-01', 43000.00, '123-33-3333'),
(35, 'Felix', 'Cat', 'Felix@example.com', '1985-04-01', 41000.00, '123-44-4444'),
(36, 'Garfield', 'Lazy', 'Garfield@example.com', '1985-05-01', 39000.00, '123-55-5555'),
(37, 'Odie', 'Dog', 'Odie@example.com', '1985-06-01', 36000.00, '123-66-6666'),
(38, 'He-Man', 'Strong', 'HeMan@example.com', '1986-01-01', 55000.00, '123-77-7777'),
(39, 'She-Ra', 'Princess', 'SheRa@example.com', '1986-02-01', 53000.00, '123-88-8888'),
(40, 'Inspector', 'Gadget', 'Gadget@example.com', '1987-01-01', 58000.00, '123-99-9999');
-- Insert payroll table
INSERT INTO payroll (payID, pay_date, empid, earnings, fed_tax, fed_med, fed_SS, state_tax, retire_401k, health_care)
SELECT 
    ROW_NUMBER() OVER (ORDER BY empid), '2025-03-31', empid, 
    salary / 12.0,  -- Monthly earnings (assuming salary is annual)
    (salary / 12.0) * 0.32,  -- Federal tax deduction (32% of monthly salary)
    (salary / 12.0) * 0.0145,  -- Medicare tax deduction (1.45% of monthly salary)
    (salary / 12.0) * 0.062,  -- Social Security tax deduction (6.2% of monthly salary)
    (salary / 12.0) * 0.12,  -- State tax deduction (12% of monthly salary)
    (salary / 12.0) * 0.004,  -- Retirement (401k) deduction (0.4% of monthly salary)
    (salary / 12.0) * 0.031  -- Health care deduction (3.1% of monthly salary)
FROM employees;
-- Insert employee_job_titles
INSERT INTO employee_job_titles (empid, job_title_id)
VALUES
(1, 100),
(2, 103),
(3, 102),
(4, 100),
(5, 103),
(6, 202),
(7, 102),
(8, 103),
(9, 102),
(10, 201),
(11, 202),
(12, 202),
(13, 202),
(14, 202),
(15, 103),
(16, 103),
(17, 103),
(18, 202),
(19, 103),
(20, 103),
(21, 102),
(22, 200),
(23, 202),
(24, 200),
(25, 201),
(26, 201),
(27, 202),
(28, 102),
(29, 103),
(30, 103),
(31, 202),
(32, 102),
(33, 201),
(34, 103),
(35, 202),
(36, 202),
(37, 202),
(38, 101),
(39, 101),
(40, 902);
-- Insert division table
INSERT INTO division (div_ID, Name, city, addressLine1, addressLine2, state, country, postalCode) 
VALUES(123,'Technology Engineering', 'Atlanta', '200 17th Street NW', '', 'GA', 'USA', '30363'),
		(345,'Marketing', 'Atlanta', '200 17th Street NW', '', 'GA', 'USA', '30363'),
		(456,'Human Resources','New York', '45 West 57th Street', '', 'NY', 'USA', '00034'),
		(567,'HQ','New York', '45 West 57th Street', '', 'NY', 'USA', '00034');
-- Insert employee_division table
INSERT INTO employee_division (empid, div_ID)
VALUES
(1,2),
(2,3),
(3,4),
(4,1),
(5,2),
(6,3),
(7,4),
(8,1),
(9,2),
(10,3),
(11,4),
(12,1),
(13,2),
(14,3),
(15,4),
(16,1),
(17,2),
(18,3),
(19,4),
(20,1),
(21,2),
(22,3),
(23,4),
(24,1),
(25,2),
(26,3),
(27,4),
(28,1),
(29,2),
(30,3),
(31,4),
(32,1),
(33,2),
(34,3),
(35,4),
(36,1),
(37,2),
(38,3),
(39,4),
(40,1);
-- Insert city and state tables
INSERT INTO city (city_id, city)
VALUES
(1, 'Atlanta'),
(2, 'New York'),
(3, 'Chicago'),
(4, 'San Francisco'),
(5, 'Seattle'),
(6, 'Austin'),
(7, 'Los Angeles'),
(8, 'Boston'),
(9, 'Miami'),
(10, 'Denver');
INSERT INTO state (state_id, state)
VALUES
(1, 'GA'),        -- Georgia
(2, 'NY'),        -- New York
(3, 'IL'),        -- Illinois
(4, 'CA'),        -- California
(5, 'WA'),        -- Washington
(6, 'TX'),        -- Texas
(7, 'MA'),        -- Massachusetts
(8, 'FL'),        -- Florida
(9, 'CO');        -- Colorado
-- Inser adrress table
INSERT INTO address (empid, street, city_id, state_id, zip, gender, identified_race, dob, phone)
VALUES
(1, '123 Peach St', 1, 1, '30363', 'Male', 'White', '1990-01-01', '555-123-0001'),
(2, '456 Maple Ave', 1, 1, '30363', 'Male', 'White', '1991-02-14', '555-123-0002'),
(3, '789 Oak Dr', 2, 2, '10019', 'Female', 'Asian', '1992-03-10', '555-123-0003'),
(4, '101 Pine Ln', 2, 2, '10019', 'Female', 'Hispanic', '1990-04-20', '555-123-0004'),
(5, '202 Elm St', 3, 3, '60601', 'Male', 'Black', '1989-05-05', '555-123-0005'),
(6, '303 Cedar Ave', 3, 3, '60601', 'Male', 'White', '1993-06-18', '555-123-0006'),
(7, '404 Birch Blvd', 4, 4, '94103', 'Male', 'Asian', '1987-07-30', '555-123-0007'),
(8, '505 Palm Rd', 4, 4, '94103', 'Male', 'White', '1988-08-08', '555-123-0008'),
(9, '606 Redwood Ct', 5, 5, '98101', 'Female', 'Black', '1990-09-09', '555-123-0009'),
(10, '707 Cherry St', 5, 5, '98101', 'Female', 'White', '1992-10-10', '555-123-0010'),
(11, '808 Spruce Ln', 6, 6, '73301', 'Male', 'Hispanic', '1985-11-11', '555-123-0011'),
(12, '909 Fir Dr', 6, 6, '73301', 'Male', 'Asian', '1984-12-12', '555-123-0012'),
(13, '1001 Hemlock Ave', 7, 4, '90001', 'Male', 'White', '1993-01-15', '555-123-0013'),
(14, '1112 Cypress Blvd', 7, 4, '90001', 'Male', 'Black', '1990-02-20', '555-123-0014'),
(15, '1213 Willow Rd', 8, 7, '02108', 'Male', 'Asian', '1991-03-25', '555-123-0015'),
(16, '1314 Aspen St', 8, 7, '02108', 'Male', 'White', '1992-04-30', '555-123-0016'),
(17, '1415 Magnolia Ave', 9, 8, '33101', 'Male', 'Hispanic', '1994-05-10', '555-123-0017'),
(18, '1516 Sycamore Ln', 9, 8, '33101', 'Female', 'White', '1995-06-15', '555-123-0018'),
(19, '1617 Beech Dr', 10, 9, '80202', 'Male', 'Black', '1991-07-20', '555-123-0019'),
(20, '1718 Chestnut Ct', 10, 9, '80202', 'Female', 'White', '1993-08-25', '555-123-0020'),
(21, '1819 Hickory Blvd', 1, 1, '30363', 'Male', 'Asian', '1989-09-10', '555-123-0021'),
(22, '1920 Poplar Rd', 2, 2, '10019', 'Male', 'White', '1990-10-15', '555-123-0022'),
(23, '2021 Walnut Ave', 3, 3, '60601', 'Male', 'Black', '1991-11-20', '555-123-0023'),
(24, '2122 Dogwood St', 4, 4, '94103', 'Male', 'White', '1988-12-25', '555-123-0024'),
(25, '2223 Mulberry Ln', 5, 5, '98101', 'Male', 'Hispanic', '1993-01-30', '555-123-0025'),
(26, '2324 Alder Dr', 6, 6, '73301', 'Female', 'Asian', '1994-02-04', '555-123-0026'),
(27, '2425 Juniper Ave', 7, 4, '90001', 'Female', 'White', '1992-03-09', '555-123-0027'),
(28, '2526 Larch St', 8, 7, '02108', 'Male', 'White', '1991-04-14', '555-123-0028'),
(29, '2627 Ash Rd', 9, 8, '33101', 'Female', 'Black', '1990-05-19', '555-123-0029'),
(30, '2728 Cottonwood Blvd', 10, 9, '80202', 'Female', 'Asian', '1989-06-24', '555-123-0030'),
(31, '2829 Maple St', 1, 1, '30363', 'Male', 'Hispanic', '1988-07-29', '555-123-0031'),
(32, '2930 Oak Ln', 2, 2, '10019', 'Male', 'White', '1987-08-03', '555-123-0032'),
(33, '3031 Elm Dr', 3, 3, '60601', 'Female', 'Asian', '1986-09-08', '555-123-0033'),
(34, '3132 Pine Ct', 4, 4, '94103', 'Female', 'White', '1985-10-13', '555-123-0034'),
(35, '3233 Cedar Blvd', 5, 5, '98101', 'Male', 'Black', '1984-11-18', '555-123-0035'),
(36, '3334 Spruce Rd', 6, 6, '73301', 'Male', 'White', '1983-12-23', '555-123-0036'),
(37, '3435 Redwood Ln', 7, 4, '90001', 'Male', 'Asian', '1982-01-28', '555-123-0037'),
(38, '3536 Birch St', 8, 7, '02108', 'Female', 'Hispanic', '1981-02-02', '555-123-0038'),
(39, '3637 Fir Ave', 9, 8, '33101', 'Female', 'White', '1980-03-07', '555-123-0039'),
(40, '3738 Hemlock Ct', 10, 9, '80202', 'Male', 'Black', '1979-04-12', '555-123-0040');

