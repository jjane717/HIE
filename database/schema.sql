CREATE TABLE IF NOT EXISTS Enterprise (
  id_enterprise INT NOT NULL AUTO_INCREMENT,
  enterprise_name VARCHAR(150) NOT NULL,
  enterprise_type VARCHAR(45) NOT NULL,
  enterprise_balance DOUBLE NOT NULL,
  enterprise_code INT NOT NULL,
  street VARCHAR(80) NOT NULL,
  city VARCHAR(45) NOT NULL,
  state VARCHAR(45) NOT NULL,
  zip VARCHAR(15) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  email VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_enterprise));

CREATE TABLE IF NOT EXISTS Privilege (
  id_privilege INT NOT NULL AUTO_INCREMENT,
  privilege_name VARCHAR(100),
  PRIMARY KEY (id_privilege));

CREATE TABLE IF NOT EXISTS Role_Privilege (
  id_role_privilege INT NOT NULL AUTO_INCREMENT,
  id_privilege INT NOT NULL,
  id_role INT NOT NULL,
  PRIMARY KEY (id_role_privilege));

CREATE TABLE IF NOT EXISTS Role (
  id_role INT NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(50) NOT NULL,
  role_type VARCHAR(50) NOT NULL,
  PRIMARY KEY (id_role));

CREATE TABLE IF NOT EXISTS User_Role (
  id_user_role INT NOT NULL AUTO_INCREMENT,
  id_role INT NOT NULL,
  id_user_account INT NOT NULL,
  PRIMARY KEY (id_user_role));

CREATE TABLE IF NOT EXISTS User_Account (
  id_user_account INT NOT NULL AUTO_INCREMENT,
  id_enterprise INT NOT NULL,
  user_name VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  date_of_birth DATETIME NOT NULL,
  age INT NOT NULL,
  id_employee INT,
  is_small_business TINYINT(1) NOT NULL,
  is_family TINYINT(1) NOT NULL,
  income_status VARCHAR(45) NOT NULL,
  street VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  state VARCHAR(45) NOT NULL,
  zip VARCHAR(15) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  email VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_user_account)
);

CREATE TABLE IF NOT EXISTS Enterprise_Product(
  id_enterprise_product INT NOT NULL AUTO_INCREMENT,
  id_enterprise INT NOT NULL,
  id_product INT NOT NULL,
  PRIMARY KEY (id_enterprise_product)
);

CREATE TABLE IF NOT EXISTS Product (
  id_product INT NOT NULL AUTO_INCREMENT,
  id_enterprise INT NOT NULL,
  offer_name VARCHAR(45) NOT NULL,
  co_Pay DOUBLE NOT NULL,
  deductible DOUBLE NOT NULL,
  co_Insurance DOUBLE NOT NULL,
  offer_price DOUBLE NOT NULL,
  description VARCHAR(200) NOT NULL,
  status TINYINT(1) NOT NULL,
  target_market VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_product));

  DROP TABLE Enterprise_Product;

CREATE TABLE IF NOT EXISTS `Order` (
  id_order INT NOT NULL AUTO_INCREMENT,
  id_user_account INT NOT NULL,
  id_product INT NOT NULL,
  create_date DATETIME NOT NULL,
  total_amount DOUBLE NOT NULL,
  PRIMARY KEY (id_order));

CREATE TABLE IF NOT EXISTS Payment (
  id_payment INT NOT NULL AUTO_INCREMENT,
  id_order INT NOT NULL,
  due_date DATETIME NOT NULL,
  pay_date DATETIME NULL,
  is_pay TINYINT(1) NOT NULL,
  amount DOUBLE NOT NULL,
  PRIMARY KEY (id_payment));

drop table Enterprise;
drop TABLE User_Account;
drop table `order`;
drop table Payment;
drop table Privilege;
drop table Product;
drop table User_Role;
drop table Role_Privilege;
drop table Role;

1,HIDE,200,1000,0.5,200,NJASDJDF,0,LowIncomeMarket
2,SDKF,700,500,0.2,250,NJASDJDF,1,LowIncomeMarket