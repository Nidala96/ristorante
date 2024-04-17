CREATE TABLE piatto (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   CONSTRAINT pk_piatto PRIMARY KEY (id)
);

CREATE TABLE piatto_lista_ingredienti (
  piatto_id BIGINT NOT NULL,
   lista_ingredienti_id BIGINT NOT NULL,
   CONSTRAINT pk_piatto_listaingredienti PRIMARY KEY (piatto_id, lista_ingredienti_id)
);

ALTER TABLE piatto_lista_ingredienti ADD CONSTRAINT fk_pialising_on_ingrediente FOREIGN KEY (lista_ingredienti_id) REFERENCES ingrediente (id);

ALTER TABLE piatto_lista_ingredienti ADD CONSTRAINT fk_pialising_on_piatto FOREIGN KEY (piatto_id) REFERENCES piatto (id);