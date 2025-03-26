USE projectCSC3350;

/***********************************************************************/

CREATE TABLE employees (
  empid INT NOT NULL,
  Fname VARCHAR(65) NOT NULL,
  Lname VARCHAR(65) NOT NULL,
  email VARCHAR(65) NOT NULL,
  HireDate DATE,
  Salary DECIMAL(10,2) NOT NULL,
  SSN VARCHAR(12),
  PRIMARY KEY (empid)
);

/***********************************************************************/

CREATE TABLE payroll (
  payID INT PRIMARY KEY,
  pay_date DATE,
  earnings DECIMAL(8,2),
  fed_tax DECIMAL(7,2),
  fed_med DECIMAL(7,2),
  fed_SS DECIMAL(7,2),
  state_tax DECIMAL(7,2),
  retire_401k DECIMAL(7,2),
  health_care DECIMAL(7,2),
  empid NOT NULL INT,
  FOREIGN KEY (empid) REFERENCES employees(empid)
);

/***********************************************************************/ 

CREATE TABLE job_titles (
  job_title_id INT PRIMARY KEY,
  job_title VARCHAR(125) NOT NULL
);

/***********************************************************************/ 

CREATE TABLE employee_job_titles (
  empid INT NOT NULL,
  job_title_id INT NOT NULL,
  FOREIGN KEY (empid) REFERENCES employees(empid),
  FOREIGN KEY (job_title_id) REFERENCES job_titles(job_title_id)
);

/***********************************************************************/ 

CREATE TABLE division (
  div_ID INT PRIMARY KEY,
  Name VARCHAR(100) DEFAULT NULL,
  city VARCHAR(50) NOT NULL,
  addressLine1 VARCHAR(50) NOT NULL,
  addressLine2 VARCHAR(50) DEFAULT NULL,
  state VARCHAR(50) DEFAULT NULL,
  country VARCHAR(50) NOT NULL,
  postalCode VARCHAR(15) NOT NULL
);

/***********************************************************************/ 

CREATE TABLE employee_division (
  empid INT NOT NULL,
  div_ID INT NOT NULL,
  FOREIGN KEY (empid) REFERENCES employees(empid),
  FOREIGN KEY (div_ID) REFERENCES division(div_ID)
);

/***********************************************************************/ 

CREATE TABLE city (
  city_id INT NOT NULL, 
  city VARCHAR(50),
  PRIMARY KEY (city_id)
);

/***********************************************************************/ 

CREATE TABLE state (
  state_id INT NOT NULL, 
  state VARCHAR(50),
  PRIMARY KEY (state_id)
);

/***********************************************************************/ 

CREATE TABLE address (
  empid INT NOT NULL,
  street VARCHAR(50) NOT NULL,
  city_id INT NOT NULL,
  state_id INT NOT NULL,
  zip VARCHAR(15) NOT NULL,
  gender VARCHAR(10) NOT NULL,
  identified_race VARCHAR(15) NOT NULL,
  dob DATE NOT NULL,
  phone VARCHAR(20) NOT NULL,
  FOREIGN KEY (empID) REFERENCES employees(empid),
  FOREIGN KEY (city_id) REFERENCES city(city_id),
  FOREIGN KEY (state_id) REFERENCES state(state_id)
);

