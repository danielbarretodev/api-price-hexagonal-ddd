-- Base de datos con dos tablas y foreign key
DROP TABLE IF EXISTS prices;
DROP TABLE IF EXISTS brand;

EN EL CASO DE QUE LA TABLA BRAN SE ENCUENTRE 
EN LA MISMA BASE DE DATOS

DROP TABLE IF EXISTS BRAND;
CREATE TABLE brand (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    description varchar(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


-- Crea la tabla prices
CREATE TABLE prices (
    id bigint NOT NULL AUTO_INCREMENT,
    brand_id bigint NOT NULL,
    start_date timestamp,
    end_date timestamp,
    price_list bigint,
    product_id bigint,
    priority int,
    price decimal(10,2),
    curr varchar(3),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (brand_id)
    REFERENCES brand(id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);