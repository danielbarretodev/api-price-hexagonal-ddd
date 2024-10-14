

-- Borra la tabla si existe
DROP TABLE IF EXISTS prices;

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
    PRIMARY KEY (id)
);