# ConsumeMarvel
Prueba Tecnica de Consumo de api Marvel con Spring boot, MySQL, 





## Context
El conexto de la aplicacion es <h2>apiMarvel</h2>
y el puerto configurado es 8181
por ello para el consumo el BASE URL debe ser

```html
http://server_name:8181/apiMarvel
```

## Descripcion de la api y los servicios expuestos
## API REST
Descripcion de api rest

<h1>login</h1>
Debe proporcionar el usuario y contraseña del usuario para obtener un token para el uso de los demas endpoint

`POST /login`

### Request

  curl -i -H 'Accept: application/json' -d '{"usu":"user01":"pass":"pass01"}' http://server_name:8181/apiMarvel/login
### Response

#### Header
            X-Content-Type-Options: nosniff
            X-XSS-Protection: 1; mode=block
            Cache-Control: no-cache, no-store, max-age=0, must-revalidate
            Pragma: no-cache
            Expires: 0
            X-Frame-Options: DENY
            Content-Type: application/json
            Transfer-Encoding: chunked
            Date: Fri, 10 Dec 2021 04:28:47 GMT
            Keep-Alive: timeout=60
            Connection: keep-alive
#### JsonObject
            {
            "usuario": "carlos",
            "token": "cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTE0ODA4NywiZXhwIjoxNjM5MTQ4Njg3fQ.av3rWWc9wy4NNziAAabR8JXWArjmznGFtBg90mMEHhlQOtnBt_6He29SC0bEvXU3_E3Apb90tPZ8c-0B_o6F9w"
          }  



### Listas

Las listas despliegan las listas generales de Personajes, Creadores, Comics, de las cuales se debe seleccionar el id de los resultados para busquedas mas exactas, ej
al obtener los personajes se obtiene el id de hulk  
<bold>1009351<bold> 
Este id se usara para busqueda de datos de hulk que se enviaran en los Request como se explicara mas adelante
Todas las listas son de Method 
<bold>GET</bold>

##### listPersonajes   
##### listCreadores   
##### listComics   
    
El JSONObject "result" varia segun la lista
    
### Request

`GET /{ENDPOINT}/`

    curl -i -H 'Accept: application/json' http://server_name:8181/apiMarvel/{endpoint}/

### Response



    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 2

    {
    "response": {
      "code": "200",
      "status": "Ok",
      "copyright": "© 2021 MARVEL",
      "attributionText": "Data provided by Marvel. © 2021 MARVEL",
      "attributionHTML": "<a href=\"http://marvel.com\">Data provided by Marvel. © 2021 MARVEL</a>",
      "data": {
        "offset": "0",
        "limit": "20",
        "total": "1",
        "count": "1",
        "results": [
          {
            "id": "1009351",
            "name": "Hulk",
            "series":[]
            "creators":[]
            "comics":[]
            }
        }
    }

tipos de JSONObject de Result
#### MarvelCharacterResponse
        { "id": "int", "name": "string", "description": "string", "modified": "Date", "resourceURI": "string", "thumbnail":{} ,"series":[], "comics":[], "creators":[] }
#### MarvelComicsResponse
        { "id": "int", "digitalId": "int", "title": "string", "issueNumber": "double", "variantDescription": "string", "description": "string", "modified": "Date", "isbn": "string", "upc": "string", "diamondCode": "string", "ean": "string", "issn": "string", "format": "string", "pageCount": "int", "textObjects": [ { "type": "string", "language": "string", "text": "string" } ], "thumbnail":{} ,"series":[], "creators":[]}

#### MarvelCreatorResponse
        { "id": "int", "firstName": "string", "middleName": "string", "lastName": "string", "suffix": "string", "fullName": "string", "modified": "Date", "resourceURI": "string", "urls": [ { "type": "string", "url": "string" }], "thumbnail":{} ,"series":[], "comics":[] , "characters":[]}





## DataBase 

se ha usado como gestor de base de datos MySQL 8.0 , en la aplicacion se definio un usuario de conexion apiMarvelDB y contraseña Superhero2021:

CREATE DATABASE  IF NOT EXISTS `marveldb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `marveldb`;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `busquedas`
--

DROP TABLE IF EXISTS `busquedas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `busquedas` (
  `idbusquedas` int NOT NULL AUTO_INCREMENT,
  `idusuario` int NOT NULL,
  `busqueda` varchar(45) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`idbusquedas`),
  KEY `idusuario_idx` (`idusuario`),
  CONSTRAINT `idusuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `busquedas`
--

LOCK TABLES `busquedas` WRITE;
/*!40000 ALTER TABLE `busquedas` DISABLE KEYS */;
INSERT INTO `busquedas` VALUES (1,1,'Character / Iron Man','2021-12-08 14:20:14'),(2,1,'Character / Comics / Iron Man','2021-12-08 15:01:18'),(3,2,'/apiMarvel/personajes/datos','2021-12-09 21:52:37'),(4,1,'/apiMarvel/personajes/datos','2021-12-09 21:52:42');
/*!40000 ALTER TABLE `busquedas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personajes`
--

DROP TABLE IF EXISTS `personajes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personajes` (
  `idpersonajes` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `idmarvel` int DEFAULT NULL,
  PRIMARY KEY (`idpersonajes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='guardar los personajes para busqueda local primero y luego en la api de marvel';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personajes`
--

LOCK TABLES `personajes` WRITE;
/*!40000 ALTER TABLE `personajes` DISABLE KEYS */;
/*!40000 ALTER TABLE `personajes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `idtoken` int NOT NULL AUTO_INCREMENT,
  `token` varchar(300) DEFAULT NULL,
  `idusuario` int DEFAULT NULL,
  PRIMARY KEY (`idtoken`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTEwMDY2MywiZXhwIjoxNjM5MTAxMjYzfQ.bQjSssuoteAWKg-f92QKv1WJm8_cyvhvhxcStu6cna5NPKuu-3XqtOroHQTnFyU-vIzFiKgGn1HCE_U6QyQqXw',1),(2,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTEwMDY4NCwiZXhwIjoxNjM5MTAxMjg0fQ.Y-MueJjSrRPtB0bz8SEfVQjjDMazACK4OwPleXl6yMQFmmSqO5d_3gp8QIX3SI6m-v-J8urXUxpaPCDtlqbo5w',1),(3,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTEwMTczNSwiZXhwIjoxNjM5MTAyMzM1fQ.pMNGpD2kBnoq3rDELGEXlRbhWXEUcywcwITSJL3N2qnTHjrMHceAub2GDn-QnL8Shqld4hB2kNRt5pfjE4f2oA',1),(4,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTEwMTc0OSwiZXhwIjoxNjM5MTAyMzQ5fQ.B6cval8dg6Peewyun1N5HxeeA0097Lqytz8q2LtNDrJG3rsqzl0L_CXLxjAzUTZry9YEfBu0ptCP47nOqJbedg',1),(5,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTEwMTg2MSwiZXhwIjoxNjM5MTAyNDYxfQ.Y4O894ikfTgA_TVKxkcEDxgqn8M4e02YPtX4V2SIM31xZAs-SDafLkiBhfO5Mbjmq02gNMRSMTPwLgcr8S8V-Q',1),(6,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTEwMjk2MSwiZXhwIjoxNjM5MTAzNTYxfQ.Os75ogk5Pr40TeHALfEjm34_KhsCnrqbiEX69AhxQoik6Zu-nlBRA-C1kSac-te-vgGhFXiyXi4JdWz8zDL5qQ',1),(7,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTEwNTUxNywiZXhwIjoxNjM5MTA2MTE3fQ.Z8zcSuhRrB3wrzl17-4v2cvSONNI0AKUg0Ne8fdzfw_E3r2Ko27vH4o0OJXOHBgqboDw51lIn5Mk0UtjBBI9iw',1),(8,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTEwNjk4MywiZXhwIjoxNjM5MTA3NTgzfQ.sgowvEKNvEYFhZiuYZNf35f9T2juS2RHXyrGg1PZLwOqL5WVIUB_FMdeN05gKPSVextGUYRt4_nVYDS6o0ow6g',1),(9,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTEwNzc4NiwiZXhwIjoxNjM5MTA4Mzg2fQ.H7UE3jlxAYNB2dXUqDzRzmnXArf2CcpR3_MoaOn8wpNfhIe4vEoIRnvnOH65o-KiuRoTpDr5LgoTWhoxnX5OVw',1),(10,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTEwNzg2MSwiZXhwIjoxNjM5MTA4NDYxfQ.nKggMtw3R0cPKoi9K5oCmkuBrJXs_VQ4uGVq3L9SxjVx8jODco5pW7A8MykZbKvAvmnLGEJk-3GK0kn9Va6wtg',1),(11,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTEwOTQxOCwiZXhwIjoxNjM5MTEwMDE4fQ.TynM74J-9e_avYCBf3_AZkyh_wJzXGlsKj2VzbVs0_abKom1F37uyGnq8EKrwe4KxRrJNqmKcSqHdNi_W0iMMA',1),(12,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTEwOTQ2MywiZXhwIjoxNjM5MTEwMDYzfQ.r-hQ8RqptiBdNPaTUmG2swHiLjBJipTRQ4j8YO_exEMsftVeBBt9Ad_xxiDbi-wO7YJtxNptxbLj3O87QhdqDQ',1),(13,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTEwOTYwMSwiZXhwIjoxNjM5MTEwMjAxfQ.8v2RvUWSX8YvQWhaYUB2Aynq7B0cCA0kKbc-LCfltLgxguzllGHd_ZqYYoUrJVnMdHPmXFjV7XTvxD7Xk5_cmA',1),(14,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTExMDA4NCwiZXhwIjoxNjM5MTEwNjg0fQ.CLFsruka8zbztAaFaLgcmoEImS4qmUVqn9YjQQOnisigSgNLou3eBeC5sj_XDIvrcRMMgI76ZOvSdaD9x9FDuQ',1),(15,'cleeyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiY2FybG9zIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYzOTExMDMyNywiZXhwIjoxNjM5MTEwOTI3fQ.ftQyKtWdIqeEHrZl9CGGfOdnsWbI78hF-AdawyBZ57_sh74PpNBK7u2TJDszi8hsX2-4cAMfRu2DcpPkEzcgmQ',1);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `usu` varchar(45) DEFAULT NULL,
  `pass` varchar(45) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'A',
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'carlos','123','A'),(2,'eduardo','456','A');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-10  6:21:20





