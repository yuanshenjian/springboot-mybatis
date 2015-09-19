CREATE TABLE sbm_user (
  name     VARCHAR(55)  NOT NULL,
  password VARCHAR(255) NOT NULL,
  sex      ENUM('MALE', 'FEMALE') DEFAULT "MALE",
  PRIMARY KEY (name)
)