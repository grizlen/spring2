CREATE TABLE `cart_items` (
  `id` bigserial PRIMARY KEY,
  `product_id` bigint NOT NULL,
  `count` int NOT NULL,
  `price` float NOT NULL
);
