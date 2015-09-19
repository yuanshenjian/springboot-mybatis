/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tb_contact (
  id INT AUTO_INCREMENT,
  user_id INT ,
  username VARCHAR(20),
  age INT,
  address VARCHAR(100),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;