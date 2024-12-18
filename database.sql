-- Création de la base de données ordercustomer
CREATE DATABASE ordercustomer;



USE CommandeClient;

-- Création de la table Customer
CREATE TABLE Customer (
                        	Customerid VARCHAR(255) PRIMARY KEY,      
                        nom VARCHAR(255) NOT NULL,       
                        prenom VARCHAR(255) NOT NULL,    
                        email VARCHAR(255) NOT NULL,      
                        phone VARCHAR(20) NOT NULL       
);



-- Création de la table Commande
CREATE TABLE Commande (
                          idOrder VARCHAR(255) PRIMARY KEY,     
			orderDate TIMESTAMP PRIMARY
                          orderAmount DOUBLE NOT NULL,          
                          idCustomer VARCHAR(255),           
                          statut VARCHAR(50),              
                          
    -- Définition de la clé étrangère liant la commande au customer
                          FOREIGN KEY (idCustomer) REFERENCES Customer(Customerid) ON DELETE CASCADE
);


