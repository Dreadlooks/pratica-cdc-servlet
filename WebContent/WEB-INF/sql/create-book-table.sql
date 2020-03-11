CREATE TABLE book (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title varchar(255),
  category_id BIGINT,
  price DECIMAL,
  resume varchar(255),
  author_id BIGINT,
  numberOfPages BIGINT,
  isbn varchar(255),
  primary key(id),
  FOREIGN KEY (category_id) REFERENCES category (ID),
  FOREIGN KEY (author_id) REFERENCES author (ID)
);