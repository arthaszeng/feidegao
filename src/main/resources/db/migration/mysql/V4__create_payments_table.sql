DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments`
(
    `id`         CHAR(36) NOT NULL PRIMARY KEY,
    `uri`        CHAR(36) NOT NULL,
    `amount`     INT      NOT NULL,
    `created_at` CHAR(36) NOT NULL,
    `expired_at` CHAR(20) NOT NULL
)