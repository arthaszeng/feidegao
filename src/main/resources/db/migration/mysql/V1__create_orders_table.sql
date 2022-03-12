DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`
(
    `id`           CHAR(36)     NOT NULL PRIMARY KEY,
    `contactor_id` CHAR(36)     NOT NULL,
    `proposal_id`  VARCHAR(256) NOT NULL,
    `price`        INT UNSIGNED NOT NULL,
    `amount`       INT UNSIGNED NOT NULL,
    `created_at`   TIMESTAMP
)