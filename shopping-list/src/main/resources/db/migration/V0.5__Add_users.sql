INSERT INTO users (username, password, enabled)
  values ('user01','$2a$10$jTrPChgppxgy5zrosxCBu.wAWYv8hnCneV8KKcHhN74sL2/5dKqvC',1);

INSERT INTO users (username, password, enabled)
  values ('user02','$2a$10$8BMHJkKlsD1kJnpY7ZDb7uD2NP3s/6RrWOcsrAWd3v7HD0OjYG5IW',0);

INSERT INTO users (username, password, enabled)
  values ('admin','$2a$10$3RXnne1oAWf7/bm2vWWHjuypvbjgDF0ZQWRjJNnoETJ/PW1MJ85dG',1);

INSERT INTO authorities (username, authority)
  values ('user01', 'ROLE_USER');

INSERT INTO authorities (username, authority)
  values ('user02', 'ROLE_USER');

INSERT INTO authorities (username, authority)
  values ('admin', 'ROLE_USER');

INSERT INTO authorities (username, authority)
  values ('admin', 'ROLE_ADMIN');
