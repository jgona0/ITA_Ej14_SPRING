DROP TABLE IF EXISTS `dicegame`.`roll`;
DROP TABLE IF EXISTS `dicegame`.`user`;

CREATE TABLE `dicegame`.`user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `dicegame`.`roll` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dice_one` int(2) DEFAULT 0,
  `dice_two` int(2) DEFAULT 0,
  `total_roll` int(2) DEFAULT 0,
  `result` varchar (10) NULL,
  `user_id` int(11) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `roll_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
  ON DELETE CASCADE ON UPDATE CASCADE
);

insert into `dicegame`.`user`(name) values('Xavi');
insert into `dicegame`.`user`(name) values('Ainhoa');
insert into `dicegame`.`user`(name) values('Maialen');


insert into `dicegame`.`roll`(dice_one, dice_two, total_roll, result, user_id) values(1, 2, 3, 'LOSE', 1);
insert into `dicegame`.`roll`(dice_one, dice_two, total_roll, result, user_id) values(1, 6, 7, 'WIN', 2);
insert into `dicegame`.`roll`(dice_one, dice_two, total_roll, result, user_id) values(1, 2, 3, 'LOSE', 3);
insert into `dicegame`.`roll`(dice_one, dice_two, total_roll, result, user_id) values(1, 2, 3, 'LOSE', 3);
insert into `dicegame`.`roll`(dice_one, dice_two, total_roll, result, user_id) values(5, 2, 7, 'WIN', 3);
