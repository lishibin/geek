SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(50) DEFAULT NULL COMMENT '用户信息表ID',
  `order_other_info_id` bigint(20) DEFAULT NULL COMMENT '订单其他信息表ID',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
