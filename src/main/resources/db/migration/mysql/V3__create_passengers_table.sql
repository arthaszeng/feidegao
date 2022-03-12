DROP TABLE IF EXISTS `passengers`;
CREATE TABLE `passengers`
(
    `id`        CHAR(36) NOT NULL PRIMARY KEY,
    `order_id`  CHAR(36),
    `name`      CHAR(20) NOT NULL,
    `id_number` CHAR(20) NOT NULL
)