/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tb_user (
  id   INT AUTO_INCREMENT,
  username VARCHAR(20),
  sex  ENUM('MALE', 'FEMALE'),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;