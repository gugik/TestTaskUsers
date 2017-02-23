drop table if exists test.users;

CREATE TABLE test.users
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  isAdmin BIT(1),
  age INT(11),
  createdDate DATETIME,
  name VARCHAR(255)
);