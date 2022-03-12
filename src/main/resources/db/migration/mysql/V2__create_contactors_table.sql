DROP TABLE IF EXISTS `contactors`;
CREATE TABLE `contactors`
(
    `id`           CHAR(36) NOT NULL PRIMARY KEY,
    `phone_number` CHAR(20) NOT NULL
)