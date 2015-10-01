CREATE TABLE sbm_contact (
  username VARCHAR(55),
  name     VARCHAR(55),
  phone    VARCHAR(15),
  address  VARCHAR(100),
  sex      ENUM('MALE', 'FEMALE'),
  age      INT,
  FOREIGN KEY (username) REFERENCES SBM_USER (name),
  UNIQUE INDEX sbm_contact_index (username, name)
)