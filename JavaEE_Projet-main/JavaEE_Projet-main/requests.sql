/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Hoël
 * Created: 22 déc. 2021
 */

CREATE TABLE IF NOT EXISTS Users (
     ID_PK INTEGER NOT NULL AUTO_INCREMENT
    ,username VARCHAR(50) NOT NULL
    ,firstname VARCHAR(50) NOT NULL
    ,lastname VARCHAR(50) NOT NULL
    ,PRIMARY KEY (ID_PK)
);

