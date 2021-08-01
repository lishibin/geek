SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_product_info
-- ----------------------------
DROP TABLE IF EXISTS `order_product_info`;
CREATE TABLE `order_product_info`  (
  `id` bigint(20) DEFAULT NULL COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '订单表ID',
  `product_id` bigint(20) NOT NULL COMMENT '商品表ID',
  PRIMARY KEY (`order_id`, `product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;